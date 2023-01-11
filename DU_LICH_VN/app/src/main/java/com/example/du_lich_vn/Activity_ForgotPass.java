package com.example.du_lich_vn;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Activity_ForgotPass extends Activity implements View.OnClickListener{
    EditText eUser;
    Button bHuy,bHienThiPass;
    SQLiteDatabase database;

    Cursor curAccount;
    ArrayList<Class_Account> classAccounts;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass_login);
        eUser=findViewById(R.id.eTextUser_Forgotpass);
        bHuy=findViewById(R.id.btnHuy_Forgotpass);
        bHienThiPass=findViewById(R.id.btnlayPass_Forgotpass);
        bHuy.setOnClickListener(this);
        bHienThiPass.setOnClickListener(this);
        classAccounts = (ArrayList<Class_Account>) getIntent().getSerializableExtra("Class_Account");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnHuy_Forgotpass:
                xoaTrang();
                finish();
                break;
            case R.id.btnlayPass_Forgotpass:
                if (eUser.getText().toString().trim().equals(""))
                    showMessage(this,"Bạn phải nhập tài khoản","Lỗi");
                else {

                    String mss = "";

                    //Toast.makeText(this, classAccounts.size(), Toast.LENGTH_SHORT).show();
                    System.out.println(classAccounts.size());

                    for(int i = 0; i< classAccounts.size(); i++){

                        System.out.println(classAccounts.get(i).get_name());
                        if (eUser.getText().toString().trim().equals(classAccounts.get(i).get_user())){
                            mss = classAccounts.get(i).get_pass();
                            System.out.println(classAccounts.get(i).get_pass());
                            break;
                        }
                    }

                    showMessage(this,""+mss,"Mật khẩu");
                }







                break;
        }

    }
    private  void xoaTrang(){
        eUser.setText(null);
        eUser.setFocusable(true);
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
