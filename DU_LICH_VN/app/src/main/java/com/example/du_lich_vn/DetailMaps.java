package com.example.du_lich_vn;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailMaps extends AppCompatActivity {
    TextView tv;
    WebView wv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps_activity);
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("data1");
        Place diaDiem=(Place) bundle.getSerializable("diadiemmaps");
        tv=findViewById(R.id.textViewTenmaps);
        tv.setText(diaDiem.getName());
        wv=findViewById(R.id.web1maps);
        wv.getSettings().setJavaScriptEnabled(true);

        wv.loadData("<iframe width='100%' height='100%' frameborder='0' style='border:0' src='https://www.google.com/maps/embed/v1/place?key=AIzaSyBHVP-N2alGWmbZOJ_S6rFR4BdS8iI9ylg&zoom=10&maptype=satellite&q="+diaDiem.getName()+"'></iframe>","text/html", "UTF-8");



    }
}
