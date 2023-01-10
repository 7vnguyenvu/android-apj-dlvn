package com.example.du_lich_vn;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.AccountAuthenticatorActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class AdminToolsActivity extends AppCompatActivity implements View.OnClickListener {

    private Account account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tools);

        account = (Account) getIntent().getSerializableExtra("Account");

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
                startActivity(new Intent(AdminToolsActivity.this, MainActivity.class)
                        .putExtra("Account", account));
                break;

            case R.id.bAdd_Place:
                startActivity(new Intent(AdminToolsActivity.this, ActionsPlaceActivity.class));
                break;

            case R.id.bShow_Places:
                startActivity(new Intent(AdminToolsActivity.this, ShowPlacesActivity.class));
                break;

            case R.id.bAdd_Account:
                break;

            case R.id.bShow_Accounts:
                break;
        }
    }
}