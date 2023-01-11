package com.example.du_lich_vn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Activity_AdminTools extends AppCompatActivity implements View.OnClickListener {

    private Class_Account classAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tools);

        classAccount = (Class_Account) getIntent().getSerializableExtra("Class_Account");

        findViewById(R.id.bBack).setOnClickListener(this);
        findViewById(R.id.bAdd_Place).setOnClickListener(this);
        findViewById(R.id.bShow_Places).setOnClickListener(this);
        findViewById(R.id.bAdd_Account).setOnClickListener(this);
        findViewById(R.id.bShow_Accounts).setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bBack:
                startActivity(new Intent(Activity_AdminTools.this, Activity_Main.class)
                        .putExtra("Class_Account", classAccount));
                break;

            case R.id.bAdd_Place:
                startActivity(new Intent(Activity_AdminTools.this, Activity_ActionsPlace.class));
                break;

            case R.id.bShow_Places:
                startActivity(new Intent(Activity_AdminTools.this, Activity_ShowPlaces.class));
                break;

            case R.id.bAdd_Account:
                startActivity(new Intent(Activity_AdminTools.this, Activity_ActionsAccount.class));
                break;

            case R.id.bShow_Accounts:
                startActivity(new Intent(Activity_AdminTools.this, Activity_ShowAccounts.class));
                break;
        }
    }
}