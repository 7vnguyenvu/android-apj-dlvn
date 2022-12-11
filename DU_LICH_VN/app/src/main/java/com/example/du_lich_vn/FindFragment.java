package com.example.du_lich_vn;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class FindFragment extends Fragment  {

    public FindFragment() {
        // Required empty public constructor
    }

    public static FindFragment newInstance() {
        FindFragment fragment = new FindFragment();
        return fragment;
    }
    EditText editText;
    ListView lv;
    View view;
    ArrayList<FindFrag_Location> arrLocation=new ArrayList<FindFrag_Location>();
    ArrayAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_find, container, false);
        editText= view.findViewById(R.id.editTextTextPersonName);
        lv=view.findViewById(R.id.lvfind);
        AddArrayLocation();

        LocationAdapter locationAdapter = new LocationAdapter(getActivity().getApplicationContext(),R.layout.lv_of_find,arrLocation);
        lv.setAdapter(locationAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =new Intent(getActivity().getApplicationContext(),DetailActivity_Find.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("diadiem",arrLocation.get(i));
                intent.putExtra("data",bundle);
                startActivity(intent);
            }
        });

        return view;

    }


    private void AddArrayLocation(){
        arrLocation.add(new FindFrag_Location(R.drawable.ic_user,"An giang","Good"));
        arrLocation.add(new FindFrag_Location(R.drawable.ic_user,"Cần Thơ","Not Good"));
        arrLocation.add(new FindFrag_Location(R.drawable.ic_user,"Đồng Tháp","Good"));
        arrLocation.add(new FindFrag_Location(R.drawable.ic_user,"Kiên Giang","Good"));
        arrLocation.add(new FindFrag_Location(R.drawable.ic_user,"Hà Nội","Good"));
        arrLocation.add(new FindFrag_Location(R.drawable.ic_user,"Bạc Liêu","Good"));
    }


}