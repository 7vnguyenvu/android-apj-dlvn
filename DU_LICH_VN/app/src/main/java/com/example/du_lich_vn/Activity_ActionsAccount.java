package com.example.du_lich_vn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class Activity_ActionsAccount extends AppCompatActivity implements View.OnClickListener {

    final int CODE_UPDATE = 444;
    int random = new Random().nextInt(900) + 100;

    SQLiteDatabase database;

    EditText eID, eName, eUser, ePass;
    Button bImg, bSave, bUpdate, bClear;

    Class_Account classAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actions_account);

        eID = findViewById(R.id.eAccount_Code);
        eName = findViewById(R.id.eAccount_Name);
        eUser = findViewById(R.id.eAccount_User);
        ePass = findViewById(R.id.eAccount_Pass);

        bImg = findViewById(R.id.bAccount_Img);
        bSave = findViewById(R.id.bSave_Account);
        bUpdate = findViewById(R.id.bUpdate_Account);
        bClear = findViewById(R.id.bClear_Account_ActionForm);

        bImg.setOnClickListener(this);
        bSave.setOnClickListener(this);
        bUpdate.setOnClickListener(this);
        bClear.setOnClickListener(this);
        findViewById(R.id.bBackToAdminTools_ActionAccounts).setOnClickListener(this);

        // Update
        if (getIntent().getBundleExtra("bundle") != null && getIntent().getBundleExtra("bundle").getInt("CODE_UPDATE") == CODE_UPDATE) {
            bUpdate.setEnabled(true);
            bSave.setEnabled(false);
            bClear.setVisibility(View.GONE);

            classAccount = (Class_Account) getIntent().getBundleExtra("bundle").getSerializable("classAccount");

            eID.setText(classAccount.get_id());
            eName.setText(classAccount.get_name());
            eUser.setText(classAccount.get_user());
            ePass.setText(classAccount.get_pass());
        }
        else
            eID.setText(random+"");
    }

    public boolean haveEmpty() {
        if (
                eID.getText().toString().trim().equals("") ||
                eName.getText().toString().trim().equals("") ||
                eUser.getText().toString().trim().equals("") ||
                ePass.getText().toString().trim().equals("")
        )
            return true;
        return false;

    }

    @SuppressLint("SetTextI18n")
    public void Insert_Account(){
        if (!haveEmpty()) {
            database = openOrCreateDatabase("Database.db", MODE_PRIVATE, null);

            ContentValues values = new ContentValues();
            values.put("_ID", random);
            values.put("NICK_NAME", eName.getText().toString());
            values.put("USER_NAME", eUser.getText().toString());
            values.put("PASSWORD", ePass.getText().toString());
            //values.put("IMAGE", "1111111111");

            if (database.insert("ACCOUNTS", null, values) == -1){
                Toast.makeText(this, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void Update_Account(){
        if (!haveEmpty()) {
            database = openOrCreateDatabase("Database.db", MODE_PRIVATE, null);

            ContentValues values = new ContentValues();
            values.put("_ID", eID.getText().toString());
            values.put("NICK_NAME", eName.getText().toString());
            values.put("USER_NAME", eUser.getText().toString());
            values.put("PASSWORD", ePass.getText().toString());

            if (database.update("ACCOUNTS", values, "_ID=?", new String[] { classAccount.get_id() }) == 0){
                Toast.makeText(this, "Cập nhật thất bại! Trùng khóa chính.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bAccount_Img:
                Intent pick = new Intent(Intent.ACTION_PICK);
                pick.setType("image/*");
                startActivity(pick);
                break;

            case R.id.bBackToAdminTools_ActionAccounts:
                finish();
                break;

            case R.id.bClear_Account_ActionForm:
                ClearForm();
                break;

            case R.id.bSave_Account:
                Insert_Account();
                ClearForm();
                break;

            case R.id.bUpdate_Account:
                Update_Account();
                finish();
                break;
        }
    }

    public void ClearForm() {
        eID.setText(random+"");
        eName.setText("");
        eUser.setText("");
        ePass.setText("");
        eName.requestFocus();
    }
}