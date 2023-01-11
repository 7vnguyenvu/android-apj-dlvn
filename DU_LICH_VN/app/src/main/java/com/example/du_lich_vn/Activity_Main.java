package com.example.du_lich_vn;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Activity_Main extends AppCompatActivity{

    Class_Account classAccount;
    SQLiteDatabase database;
    Cursor curPlace;
    ArrayList<Class_Place> classPlaces = new ArrayList<Class_Place>();

    public Class_Account getAccount() {
        return classAccount;
    }

    public ArrayList<Class_Place> getPlaces() {
        return classPlaces;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            database = openOrCreateDatabase("Database.db", MODE_PRIVATE, null);

            curPlace = database.query("PLACES", null, null, null, null, null, null);
            curPlace.moveToFirst();
            do {
                classPlaces.add(new Class_Place(Integer.parseInt(curPlace.getString(6)), curPlace.getString(1), curPlace.getString(2), curPlace.getString(3), curPlace.getString(4), curPlace.getString(5), curPlace.getString(7), curPlace.getString(0)));
            } while (curPlace.moveToNext());

        } catch (Exception ignored) {}

        classAccount = (Class_Account) getIntent().getSerializableExtra("Class_Account");

    }
}