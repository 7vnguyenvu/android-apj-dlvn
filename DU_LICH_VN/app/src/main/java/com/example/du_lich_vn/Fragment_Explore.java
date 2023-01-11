package com.example.du_lich_vn;

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
import android.widget.ListView;

import java.util.ArrayList;

public class Fragment_Explore extends Fragment {

    Context _ThisContext;
    View _View;

    ListView lPlaces;
    ArrayList<Class_Place> classPlaces;
    private Activity_Main _ActivityMain;

    public Fragment_Explore() {
    }

    public static Fragment_Explore newInstance() {
        Fragment_Explore fragment = new Fragment_Explore();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lPlaces = view.findViewById(R.id.lPlaces);
        classPlaces = _ActivityMain.getPlaces();

        Adapter_PlaceExplore placeAdapter = new Adapter_PlaceExplore(_ThisContext, R.layout.place_item_of_explore_custom, classPlaces);
        lPlaces.setAdapter(placeAdapter);

        lPlaces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent detail_place = new Intent(_ThisContext, Activity_DetailPlace.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("classPlace", classPlaces.get(i));
                detail_place.putExtra("bundle", bundle);
                startActivity(detail_place);
            }
        });




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        _View = inflater.inflate(R.layout.fragment_explore, container, false);
        _ThisContext = container.getContext();
        _ActivityMain = (Activity_Main) getActivity();



        return _View;
    }
}