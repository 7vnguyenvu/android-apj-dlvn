package com.example.du_lich_vn;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class FindFragment extends Fragment {

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        editText= view.findViewById(R.id.editTextTextPersonName);
//        lv=view.findViewById(R.id.lvfind);
//        arrLocation.add(new FindFrag_Location(R.drawable.ic_user,"An giang","Good"));
//        LocationAdapter locationAdapter = new LocationAdapter(getActivity() ,R.layout.lv_of_find,FindFragment.this,arrLocation);
//        lv.setAdapter(locationAdapter);
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_find, container, false);
        editText= view.findViewById(R.id.editTextTextPersonName);
        lv=view.findViewById(R.id.lvfind);
        arrLocation.add(new FindFrag_Location(R.drawable.ic_user,"An giang","Good"));
        LocationAdapter locationAdapter = new LocationAdapter(getActivity().getApplicationContext() ,R.layout.lv_of_find,arrLocation);
        lv.setAdapter(locationAdapter);
        return view;

    }
}