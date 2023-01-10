package com.example.du_lich_vn;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Random;

public class RegisActivity extends Activity implements View.OnClickListener {
    EditText eName, eUser,ePass;
    Button bHuy,bLuu;
    SQLiteDatabase database;
    final int random = new Random().nextInt(900) + 100;
    Cursor curAccount;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis_login);
        eName=findViewById(R.id.eTextNickName);
        eUser=findViewById(R.id.eTextUser);
        ePass=findViewById(R.id.eTextPass);
        bHuy=findViewById(R.id.btnHuy);
        bLuu=findViewById(R.id.btnDangKy);
        bHuy.setOnClickListener(this);
        bLuu.setOnClickListener(this);
        database = openOrCreateDatabase("Database.db", MODE_PRIVATE, null);
        System.out.println("Database đã tồn tại. => Đã mở Databse.");
        curAccount = database.query("ACCOUNTS", null, null, null, null, null, null);
        System.out.println("Truy vấn ACCOUNTS thành công.");

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnHuy:
                xoaTrang();
                break;
            case R.id.btnDangKy:
                if (eName.getText().toString().trim().equals(""))
                    showMessage(this,"Bạn phải nhập tên","Lỗi");
                else if (eUser.getText().toString().trim().equals(""))
                    showMessage(this,"Bạn phải nhập tài khoản","Lỗi");
                else if (ePass.getText().toString().trim().equals(""))
                    showMessage(this,"Bạn phải nhập mật khẩu","Lỗi");
                else {
                    ContentValues values = new ContentValues();
                    while(true){
                        try {
                            values.put("_ID", random);
                            values.put("NICK_NAME", eName.getText().toString());
                            values.put("USER_NAME", eUser.getText().toString());
                            values.put("PASSWORD", ePass.getText().toString());
                            String msg;
                            if (database.insert("ACCOUNTS", null, values) == -1)
                                msg = "Thêm không thành công. Trùng khóa chính";
                            else
                                msg = "Thêm mới tài khoản thành công";
                            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                            xoaTrang();
                            startActivity(new Intent(RegisActivity.this,LoginActivity.class));
                            break;
                        }catch (Exception exception){

                        }
                    }

                }
                break;
        }

    }
    private  void xoaTrang(){
        eName.setText(null);
        eUser.setText(null);
        ePass.setText(null);
        eName.setFocusable(true);
    }
    public static  void showMessage(Context c, String message, String title){
        AlertDialog.Builder builder=new
                AlertDialog.Builder(c,AlertDialog.THEME_HOLO_LIGHT);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }
}
