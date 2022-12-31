package com.example.du_lich_vn;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    SQLiteDatabase database;
    Cursor cursor;
    EditText eAccount_Login, ePassword_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try {
            if (!this.getDatabasePath("Database.db").exists()) {

                database = openOrCreateDatabase("Database.db", MODE_PRIVATE, null);
                String sql = "CREATE TABLE PLACES (PLACE_CODE TEXT PRIMARY KEY, PLACE_NAME TEXT, DISCRIPTION TEXT, POSITION TEXT)";
                database.execSQL(sql);
                sql = "CREATE TABLE ACCOUNT (_ID TEXT PRIMARY KEY, USER_NAME TEXT, PASSWORD TEXT)";
                database.execSQL(sql);
                sql = "INSERT INTO ACCOUNT (_ID, USER_NAME, PASSWORD) VALUES ('777', 'Admin', 'Admin_depzai')";
                database.execSQL(sql);

                System.out.println("Đã tạo Database.");
            }
            else {

                database = openOrCreateDatabase("Database.db", MODE_PRIVATE, null);
                System.out.println("Đã mở Databse.");
                cursor = database.query("ACCOUNT", null, null, null, null, null, null);
                System.out.println("Truy vẫn ACCOUNT thành công.");

            }
        } catch (Exception ignored) {}

        eAccount_Login = findViewById(R.id.eAccount_Login);
        ePassword_Login = findViewById(R.id.ePassword_Login);


        // Skip
        findViewById(R.id.bSkip_Login).setOnClickListener(view -> {
            Intent main = new Intent(LoginActivity.this, MainActivity.class);
            main.putExtra("user_name", "");
            main.putExtra("pass", "");
            startActivity(main);
        });

        // Sign in
        findViewById(R.id.bSignin_Login).setOnClickListener(view -> {
            if (eAccount_Login.getText().toString().trim().equals("") || ePassword_Login.getText().toString().trim().equals("")) {
                Toast.makeText(LoginActivity.this, "Please enter login information!", Toast.LENGTH_SHORT).show();
            }
            else {

                cursor.moveToFirst();
                System.out.println("Đã đặt con trỏ tại đối tượng đầu tiên");

                while (!cursor.isAfterLast()){
                    if (
                            eAccount_Login.getText().toString().trim().equals(cursor.getString(1).trim()) &&
                            ePassword_Login.getText().toString().trim().equals(cursor.getString(2).trim())
                    ) {
//                        String user = "";
//                        user = cursor.getString(1) + " - " + cursor.getString(2);
//                        Toast.makeText(LoginActivity.this, user, Toast.LENGTH_SHORT).show();
                        System.out.println("Đã tìm thấy. Con trỏ đang ở đối tượng thứ " + cursor.getPosition());

                        Intent main = new Intent(LoginActivity.this, MainActivity.class);
                        main.putExtra("user_name", cursor.getString(1));
                        main.putExtra("pass", cursor.getString(2));
                        startActivity(main);

                        break;
                    }
                    else {
                        System.out.println("Chưa tìm thấy. Con trỏ đang ở đối tượng thứ " + cursor.getPosition());
                        cursor.moveToNext();
                    }
                }
            }
        });
    }
}