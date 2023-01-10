package com.example.du_lich_vn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.viewmodel.CreationExtras;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActionsPlaceActivity extends AppCompatActivity  implements View.OnClickListener {

    final int RC_IMG = 111;

    SQLiteDatabase database;
    EditText eID, eName, eDesc, eRate, eProv, ePos, eLink;
    Button bImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actions_place);

        eID = findViewById(R.id.ePlace_Code);
        eName = findViewById(R.id.ePlace_Name);
        //bImg = findViewById(R.id.bPlace_Img);
        eDesc = findViewById(R.id.ePlace_Desc);
        eRate = findViewById(R.id.ePlace_Rate);
        eProv = findViewById(R.id.ePlace_Pro);
        ePos = findViewById(R.id.ePlace_Pos);
        eLink = findViewById(R.id.ePlace_Link);

        ePos.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                System.out.println("beforeTextChanged: " + eID.getText());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                System.out.println("onTextChanged: " + eID.getText());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Create_IdText();
                System.out.println("afterTextChanged: " + eID.getText());
            }
        });

        findViewById(R.id.bBackToAdminTools).setOnClickListener(this);
        findViewById(R.id.bClear_ActionForm).setOnClickListener(this);
        findViewById(R.id.bSave_Place).setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    public void Create_IdText() {
        if (!ePos.getText().toString().trim().equals("")) {
            if (ePos.getText().toString().trim().contains("+"))
                eID.setText(RemoveCharAt(ePos.getText().toString().trim() ,ePos.getText().toString().trim().indexOf("+")));
            else
                eID.setText(ePos.getText().toString().trim());
        }
    }
    public static String RemoveCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
    }

    public boolean haveEmpty() {
        if (
                eID.getText().toString().trim().equals("") ||
                eName.getText().toString().trim().equals("") ||
                eDesc.getText().toString().trim().equals("") ||
                eRate.getText().toString().trim().equals("") ||
                eProv.getText().toString().trim().equals("") ||
                eLink.getText().toString().trim().equals("") ||
                ePos.getText().toString().trim().equals("")
        )
            return true;
        return false;

    }

    @SuppressLint("SetTextI18n")
    public void Insert_Place(){
        if (!haveEmpty()) {
            database = openOrCreateDatabase("Database.db", MODE_PRIVATE, null);

            ContentValues values = new ContentValues();
            values.put("PLACE_CODE", eID.getText().toString());
            values.put("PLACE_NAME", eName.getText().toString());
            values.put("DESCRIPTION", eDesc.getText().toString());
            values.put("RATING", eRate.getText().toString());
            values.put("PROVINCE", eProv.getText().toString());
            values.put("POSITION", ePos.getText().toString());
            values.put("IMAGE", "1111111111");
            values.put("LINK", eLink.getText().toString());

            if (database.insert("PLACES", null, values) == -1){
                Toast.makeText(this, "Lưu thất bại!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Lưu thành công!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.bBackToAdminTools:
                finish();
                break;

            case R.id.bClear_ActionForm:
                eID.setText("");
                eName.setText("");
                eDesc.setText("");
                eRate.setText("");
                eProv.setText("");
                ePos.setText("");
                eName.requestFocus();
                break;

            case R.id.bSave_Place:
                Insert_Place();
                startActivity(new Intent(ActionsPlaceActivity.this, LoginActivity.class));
                break;
        }

    }
}