package com.example.du_lich_vn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DetailActivity_Find extends AppCompatActivity {
    TextView tv;
    ImageView imv;
    WebView wv;
    ArrayList<Place> arrLocation_Search=new ArrayList<Place>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("data");
        Place diaDiem=(Place) bundle.getSerializable("diadiem");
        tv=findViewById(R.id.txtNameDiaDiem);
        tv.setText(diaDiem.getName());
        wv=findViewById(R.id.web1);
        wv.loadUrl("file:///android_asset/"+diaDiem.getWeb());


    }

}
