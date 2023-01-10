package com.example.du_lich_vn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class DetailPlaceActivity extends AppCompatActivity {

    Place place;

    ImageView iImg;
    TextView tName, tDesc, tRate, tProv;

    @SuppressLint({"ResourceType", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_place);

        place = (Place) getIntent().getBundleExtra("bundle").getSerializable("place");


        iImg = findViewById(R.id.iDetail_Img);
        tName = findViewById(R.id.tDetail_Name);
        tDesc = findViewById(R.id.tDetail_Desc);
        tRate = findViewById(R.id.tDetail_Rate);
        tProv = findViewById(R.id.tDetail_Prov);

        iImg.setImageResource(place.getImage());
        tName.setText(place.getName());
        tDesc.setText(place.getDescription());
        tRate.setText(place.getRating());
        tProv.setText(place.getProvince());

        WebView wMap=findViewById(R.id.wMap);

        wMap.loadUrl(place.getLink());
        wMap.getSettings().setJavaScriptEnabled(true);
        wMap.setWebViewClient(new WebViewClient());

        //wBlog.loadUrl("file:///android_asset/vanhuongmai.html");

        findViewById(R.id.bDetail_Link).setOnClickListener(view -> {
            Intent link = new Intent(Intent.ACTION_VIEW);
            link.setData(Uri.parse(place.getLink()));
            startActivity(link);
        });
    }

}