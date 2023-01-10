package com.example.du_lich_vn;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    Account account;
    SQLiteDatabase database;
    Cursor curPlace;
    ArrayList<Place> places = new ArrayList<Place>();

    public Account getAccount() {
        return account;
    }

    public ArrayList<Place> getPlaces() {
        return places;
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
                places.add(new Place(Integer.parseInt(curPlace.getString(6)), curPlace.getString(1), curPlace.getString(2), curPlace.getString(3), curPlace.getString(4), curPlace.getString(5), curPlace.getString(7), curPlace.getString(0)));
            } while (curPlace.moveToNext());

        } catch (Exception ignored) {}

        account = (Account) getIntent().getSerializableExtra("Account");

    }
}