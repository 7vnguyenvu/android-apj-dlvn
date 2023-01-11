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
import android.widget.SearchView;

import java.util.ArrayList;

public class Fragment_Find extends Fragment  {

    public Fragment_Find() {
        // Required empty public constructor
    }

    public static Fragment_Find newInstance() {
        return new Fragment_Find();
    }
    ListView lv;
    View view;
    private Activity_Main _ActivityMain;
    Context _ThisContext;
    ArrayList<Class_Place> arrLocation=new ArrayList<Class_Place>();
    ArrayList<Class_Place> find= new ArrayList<>();
    private SearchView searchView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        super.onCreate(savedInstanceState);
    }

    private void mysearch(String s) {
        find.clear();
        for (Class_Place fl:arrLocation)
            if(fl.getName().toLowerCase().contains(s.toLowerCase()))
                find.add(fl);

        Adapter_PlaceFind locationAdapter_Search = new Adapter_PlaceFind(getActivity().getApplicationContext(),R.layout.place_item_of_find_custom,find);

        if(!find.isEmpty())
            lv.setAdapter(locationAdapter_Search);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchView=view.findViewById(R.id.Search_location);
        searchView.clearFocus();

        lv=view.findViewById(R.id.lvfind);
        arrLocation= _ActivityMain.getPlaces();

        Adapter_PlaceFind locationAdapter = new Adapter_PlaceFind(getActivity().getApplicationContext(),R.layout.place_item_of_find_custom,arrLocation);

        lv.setAdapter(locationAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent detail_place = new Intent(_ThisContext, Activity_DetailPlace.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("classPlace", find.get(i));
                detail_place.putExtra("bundle", bundle);
                startActivity(detail_place);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                mysearch(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mysearch(s);
                return true;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_find, container, false);
        _ThisContext = container.getContext();
        _ActivityMain = (Activity_Main) getActivity();
        return view;

    }






}