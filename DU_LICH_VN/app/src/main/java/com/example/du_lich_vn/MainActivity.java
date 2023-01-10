package com.example.du_lich_vn;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private String nick_name, user_name, pass;
    private ArrayList<Place> places;

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