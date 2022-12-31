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
    ListView lv;
    ArrayList<FindFrag_Location> arrLocation_Search=new ArrayList<FindFrag_Location>();

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
        lv=findViewById(R.id.manyLocation);
        ManyLocation_Search();
        LocationAdapter locationAdapter = new LocationAdapter(this,R.layout.lv_of_find,arrLocation_Search);
        lv.setAdapter(locationAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
    private  void ManyLocation_Search(){

        arrLocation_Search.add(new FindFrag_Location(R.drawable.ic_explore,"Núi cấm","Good"));
        arrLocation_Search.add(new FindFrag_Location(R.drawable.ic_explore,"Núi sập","Not Good"));
        arrLocation_Search.add(new FindFrag_Location(R.drawable.ic_explore,"Gà đốt","Good"));
        arrLocation_Search.add(new FindFrag_Location(R.drawable.ic_explore,"Sông","Good"));
        arrLocation_Search.add(new FindFrag_Location(R.drawable.ic_explore,"Núi sam","Good"));
        arrLocation_Search.add(new FindFrag_Location(R.drawable.ic_explore,"Chùa","Good"));
    }
}
