package com.example.du_lich_vn;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity_Find extends AppCompatActivity {
    TextView tv;
    ImageView imv;
    WebView wv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("data");
        FindFrag_Location diaDiem=(FindFrag_Location) bundle.getSerializable("diadiem");
        tv=findViewById(R.id.txtNameDiaDiem);
        tv.setText(diaDiem.getName());
        imv=findViewById(R.id.imageView);
        imv.setImageResource(diaDiem.getImg());
        wv=findViewById(R.id.webnoidung);
       // wv.loadUrl("file:///android_asset/"+diaDiem.getNoidung());
    }
}
