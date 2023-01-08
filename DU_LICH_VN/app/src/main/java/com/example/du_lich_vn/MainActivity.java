package com.example.du_lich_vn;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private String nick_name, user_name, pass;
    private ArrayList<Place> places;

    public void setPlaces(ArrayList<Place> places) {
        this.places = places;
    }

    SQLiteDatabase database;
    Cursor curPlace;

    public String getNick_name() {
        return nick_name;
    }

    public ArrayList<Place> getPlaces() {
        return places;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPass() {
        return pass;
    }

    public ArrayList<Place> ReLoadPlaces() {
        database = openOrCreateDatabase("Database.db", MODE_PRIVATE, null);
        System.out.println("Database đã tồn tại. => Đã mở Databse.");
        curPlace = database.query("PLACES", null, null, null, null, null, null);
        System.out.println("Truy vấn PLACES thành công.");

        curPlace.moveToFirst();
        ArrayList<Place> _places = new ArrayList<>();
        while (!curPlace.isAfterLast()) {
            _places.add(new Place(R.drawable.ic_image, curPlace.getString(1), curPlace.getString(2), curPlace.getString(3), curPlace.getString(4), curPlace.getString(5)));
            curPlace.moveToNext();
            if (curPlace.isAfterLast())
                System.out.println("Lấy danh sách Places thành công.");
        }
        
        return _places;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nick_name = getIntent().getStringExtra("nick_name");
        user_name = getIntent().getStringExtra("user_name");
        pass = getIntent().getStringExtra("pass");
        places = (ArrayList<Place>) getIntent().getSerializableExtra("places");
    }
}