package com.example.du_lich_vn;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

    private String user_name, pass;

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

        user_name = getIntent().getStringExtra("user_name");
        pass = getIntent().getStringExtra("pass");
    }
}