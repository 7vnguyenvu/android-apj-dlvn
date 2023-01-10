package com.example.du_lich_vn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends Activity {

    SQLiteDatabase database;
    Cursor curAccount, curPlace;
    EditText eAccount_Login, ePassword_Login;

    ArrayList<Place> places = new ArrayList<Place>();

    public ArrayList<Place> getPlaces() {
        return places;
    }

    public void CreateDB() {
        database = openOrCreateDatabase("Database.db", MODE_PRIVATE, null);
        String sql = "CREATE TABLE PLACES (PLACE_CODE TEXT PRIMARY KEY, PLACE_NAME TEXT, DESCRIPTION TEXT, RATING TEXT, PROVINCE TEXT, POSITION TEXT, IMAGE INT, LINK TEXT)";
        database.execSQL(sql);
        sql = "CREATE TABLE ACCOUNTS (_ID TEXT PRIMARY KEY, NICK_NAME TEXT, USER_NAME TEXT, PASSWORD TEXT)";
        database.execSQL(sql);

        // INSERT VALUES - ACCOUNTS - PLACES
        InsertAccount();
        InsertPlaces();

        System.out.println("Đã tạo Database thành công.");
    }


    public void OpenDB() {
        database = openOrCreateDatabase("Database.db", MODE_PRIVATE, null);
        System.out.println("Database đã tồn tại. => Đã mở Databse.");
        curAccount = database.query("ACCOUNTS", null, null, null, null, null, null);
        System.out.println("Truy vấn ACCOUNTS thành công.");
        curPlace = database.query("PLACES", null, null, null, null, null, null);
        System.out.println("Truy vấn PLACES thành công.");

        curPlace.moveToFirst();
        while (!curPlace.isAfterLast()) {
            places.add(new Place(Integer.parseInt(curPlace.getString(6)), curPlace.getString(1), curPlace.getString(2), curPlace.getString(3), curPlace.getString(4), curPlace.getString(5), curPlace.getString(7), curPlace.getString(0)));

            curPlace.moveToNext();
            if (curPlace.isAfterLast())
                System.out.println("Lấy danh sách Places thành công.");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try {
            if (!this.getDatabasePath("Database.db").exists()) {
                CreateDB();
                OpenDB();
            }
            else {
                OpenDB();
            }
        } catch (Exception ignored) {}

        eAccount_Login = findViewById(R.id.eAccount_Login);
        ePassword_Login = findViewById(R.id.ePassword_Login);

        // Skip
        findViewById(R.id.bSkip_Login).setOnClickListener(view -> {
            Intent main = new Intent(LoginActivity.this, MainActivity.class);
            main.putExtra("nick_name", "");
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

                curAccount.moveToFirst();
                System.out.println("Đã đặt con trỏ tại đối tượng đầu tiên");

                while (!curAccount.isAfterLast()){
                    if (
                            eAccount_Login.getText().toString().trim().equals(curAccount.getString(2).trim()) &&
                            ePassword_Login.getText().toString().trim().equals(curAccount.getString(3).trim())
                    ) {
//                        String user = "";
//                        user = cursor.getString(1) + " - " + cursor.getString(2);
//                        Toast.makeText(LoginActivity.this, user, Toast.LENGTH_SHORT).show();
                        System.out.println("Đã tìm thấy. Con trỏ đang ở đối tượng thứ " + curAccount.getPosition());

                        Intent main = new Intent(LoginActivity.this, MainActivity.class);
                        main.putExtra("nick_name", curAccount.getString(1));
                        main.putExtra("user_name", curAccount.getString(2));
                        main.putExtra("pass", curAccount.getString(3));

                        startActivity(main);

                        break;
                    }
                    else {
                        System.out.println("Chưa tìm thấy. Con trỏ đang ở đối tượng thứ " + curAccount.getPosition());
                        curAccount.moveToNext();
                    }
                }
            }
        });
    }

    public void InsertAccount() {
        String sql = "INSERT INTO ACCOUNTS (_ID, NICK_NAME, USER_NAME, PASSWORD) VALUES ('777', '7V - NGUYEN VU', '7V_Admin', 'Admin_depzai')";
        database.execSQL(sql);
        sql = "INSERT INTO ACCOUNTS (_ID, NICK_NAME, USER_NAME, PASSWORD) VALUES ('888', 'HT - HOANG THANG', 'HT_Admin', 'Admin_depzai')";
        database.execSQL(sql);
        System.out.println("Mặc định Admin đã tạo.");
    }
    public void InsertPlaces() {
        String sql = "INSERT INTO PLACES (PLACE_CODE, PLACE_NAME, DESCRIPTION, RATING, PROVINCE, POSITION, IMAGE, LINK ) VALUES ('M5C7X6', 'Vạn Hương Mai', 'Công ty Du lịch Vạn Hương Mai', '4.0 (1237 reviews)', 'Châu Đốc, An Giang', 'M5C7+X6', '"+ R.drawable.vanhuongmai +"', 'https://www.google.com/maps/place/V%E1%BA%A1n+H%C6%B0%C6%A1ng+Mai/@10.6719982,105.1627947,214m/data=!3m1!1e3!4m18!1m9!3m8!1s0x310a23a295a9bbbb:0x7c5bda564210a0ce!2zVuG6oW4gSMawxqFuZyBNYWk!5m2!4m1!1i2!8m2!3d10.6724095!4d105.1630551!3m7!1s0x310a23a295a9bbbb:0x7c5bda564210a0ce!5m2!4m1!1i2!8m2!3d10.6724095!4d105.1630551')";
        database.execSQL(sql);
        sql = "INSERT INTO PLACES (PLACE_CODE, PLACE_NAME, DESCRIPTION, RATING, PROVINCE, POSITION, IMAGE, LINK ) VALUES ('M3JJR4', 'Miếu Bà Chúa Xứ Núi Sam', 'Chùa Bà Châu Đốc', '4.6 (299 reviews)', 'Châu Đốc, An Giang', 'M3JJ+R4', '"+ R.drawable.mieubanuisam +"', 'https://www.google.com/maps/place/B%C3%A0+Ch%C3%BAa+X%E1%BB%A9+Temple/@10.6738187,105.0672981,3236m/data=!3m1!1e3!4m12!1m6!3m5!1s0x310a20e0036383a3:0x7d569d34abf3e62c!2zQsOgIENow7phIFjhu6kgVGVtcGxl!8m2!3d10.6773825!4d105.076493!3m4!1s0x310a20e0036383a3:0x7d569d34abf3e62c!8m2!3d10.6773825!4d105.076493')";
        database.execSQL(sql);
        sql = "INSERT INTO PLACES (PLACE_CODE, PLACE_NAME, DESCRIPTION, RATING, PROVINCE, POSITION, IMAGE, LINK ) VALUES ('GX3JC7', 'Chùa Vạn Linh', 'Chùa Vạn Linh, Thiên Cấm Sơn', '4.6 (729 reviews)', 'Tịnh Biên, An Giang', 'GX3J+C7', '"+ R.drawable.chuavanlinh +"', 'https://www.google.com/maps/place/Van+Linh+Pagoda/@10.5035192,104.97854,809m/data=!3m2!1e3!4b1!4m5!3m4!1s0x3109f6f46f915fa3:0xae5efce4d6c496aa!8m2!3d10.5035177!4d104.9807654')";
        database.execSQL(sql);
        System.out.println("Mặc định Places đã tạo.");
    }
}