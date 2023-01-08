package com.example.du_lich_vn;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.AccountAuthenticatorActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class AdminToolsActivity extends AppCompatActivity implements View.OnClickListener {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tools);

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

                try {
                    // Phải load lại ExploreFragment trước khi finish()
                } catch (Exception ignored) {
                    System.out.println("Có cái nịt!");
                }

                finish();
                break;

            case R.id.bAdd_Place:
                intent = new Intent(AdminToolsActivity.this, ActionsPlaceActivity.class);
                startActivity(intent);
                break;

            case R.id.bShow_Places:
                break;

            case R.id.bAdd_Account:
                break;

            case R.id.bShow_Accounts:
                break;
        }
    }
}