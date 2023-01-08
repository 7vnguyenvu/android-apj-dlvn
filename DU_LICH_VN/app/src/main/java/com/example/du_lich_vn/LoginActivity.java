package com.example.du_lich_vn;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends Activity {

    SQLiteDatabase database;
    Cursor curAccount, curPlace;
    EditText eAccount_Login, ePassword_Login;

    public ArrayList<Place> getPlaces() {
        return places;
    }

    ArrayList<Place> places = new ArrayList<Place>();

    String[] images = new String[] {""+R.drawable.test,""+R.drawable.vanhuongmai

    };

    public void CreateDB() {
        database = openOrCreateDatabase("Database.db", MODE_PRIVATE, null);
        String sql = "CREATE TABLE PLACES (PLACE_CODE TEXT PRIMARY KEY, PLACE_NAME TEXT, DESCRIPTION TEXT, RATING TEXT, PROVINCE TEXT, POSITION TEXT,IMG INT,WEB TEXT)";
        database.execSQL(sql);
        sql = "CREATE TABLE ACCOUNTS (_ID TEXT PRIMARY KEY, NICK_NAME TEXT, USER_NAME TEXT, PASSWORD TEXT)";
        database.execSQL(sql);

        // INSERT VALUES - PLACES
        sql = "INSERT INTO PLACES (PLACE_CODE, PLACE_NAME, DESCRIPTION, RATING, PROVINCE, POSITION,IMG ,WEB ) VALUES ('M5C7X6', 'Vạn Hương Mai', 'Công ty Du lịch Vạn Hương Mai', '4.0 (1236 reviews)', 'Châu Đốc, An Giang', 'M5C7+X6','2131230940','vanhuongmai.html')";
        database.execSQL(sql);
        sql = "INSERT INTO PLACES (PLACE_CODE, PLACE_NAME, DESCRIPTION, RATING, PROVINCE, POSITION,IMG ,WEB ) VALUES ('M3JJR4', 'Miếu Bà Chúa Xứ Núi Sam', 'Chùa Bà Châu Đốc', '4.6 (299 reviews)', 'Châu Đốc, An Giang', 'M3JJ+R4','2131230941','mieubanuisam.html')";
        database.execSQL(sql);

        // INSERT VALUES - ACCOUNTS
        sql = "INSERT INTO ACCOUNTS (_ID, NICK_NAME, USER_NAME, PASSWORD) VALUES ('777', '7V - NGUYEN VU', '7V_Admin', 'Admin_depzai')";
        database.execSQL(sql);
        sql = "INSERT INTO ACCOUNTS (_ID, NICK_NAME, USER_NAME, PASSWORD) VALUES ('888', 'HT - HOANG THANG', 'HT_Admin', 'Admin_depzai')";
        database.execSQL(sql);

        System.out.println("Đã tạo Database.");
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
            // nếu tên

            for (int i = 0; i < images.length; i++) {
                System.out.println(images[i]);
                if(images[i].equals(curPlace.getString(6)))
                    places.add(new Place(Integer.parseInt(images[i]), curPlace.getString(1), curPlace.getString(2), curPlace.getString(3), curPlace.getString(4), curPlace.getString(5),curPlace.getString(7)));

            }
            //places.add(new Place(2131230940, curPlace.getString(1), curPlace.getString(2), curPlace.getString(3), curPlace.getString(4), curPlace.getString(5)));
            curPlace.moveToNext();
        }


        if (curPlace.isAfterLast())
            System.out.println("Lấy danh sách Places thành công.");


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
            main.putExtra("nick_name", "7V - NGUYEN VU");
            main.putExtra("user_name", "7V_Admin");
            main.putExtra("pass", "Admin_depzai");
            main.putExtra("places", places);

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
                        main.putExtra("places", places);

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
}