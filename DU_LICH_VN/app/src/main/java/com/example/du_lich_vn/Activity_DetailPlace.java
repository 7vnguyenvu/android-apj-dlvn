package com.example.du_lich_vn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity_DetailPlace extends AppCompatActivity {

    Class_Place classPlace;

    ImageView iImg;
    TextView tName, tDesc, tRate, tProv;

    @SuppressLint({"ResourceType", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_place);

        classPlace = (Class_Place) getIntent().getBundleExtra("bundle").getSerializable("classPlace");


        iImg = findViewById(R.id.iDetail_Img);
        tName = findViewById(R.id.tDetail_Name);
        tDesc = findViewById(R.id.tDetail_Desc);
        tRate = findViewById(R.id.tDetail_Rate);
        tProv = findViewById(R.id.tDetail_Prov);

        iImg.setImageResource(classPlace.getImage());
        tName.setText(classPlace.getName());
        tDesc.setText(classPlace.getDescription());
        tRate.setText(classPlace.getRating());
        tProv.setText(classPlace.getProvince());

        WebView wMap=findViewById(R.id.wMap);

        wMap.loadUrl(classPlace.getLink());
        wMap.getSettings().setJavaScriptEnabled(true);
        wMap.setWebViewClient(new WebViewClient());

        findViewById(R.id.bDetail_Link).setOnClickListener(view -> {
            Intent link = new Intent(Intent.ACTION_VIEW);
            link.setData(Uri.parse(classPlace.getLink()));
            startActivity(link);
        });
    }

}