package com.example.du_lich_vn;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FindFragment extends Fragment  {

    public FindFragment() {
        // Required empty public constructor
    }

    public static FindFragment newInstance() {
        return new FindFragment();
    }
    ListView lv;
    View view;
    private MainActivity _MainActivity;
    Context _ThisContext;
    ArrayList<Place> arrLocation=new ArrayList<Place>();
    private SearchView searchView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        super.onCreate(savedInstanceState);
    }



    private void mysearch(String s) {
        ArrayList<Place> find= new ArrayList<>();
        for (Place fl:arrLocation){
            if(fl.getName().toLowerCase().contains(s.toLowerCase())){
                find.add(fl);
            }

        }
        LocationAdapter locationAdapter_Search = new LocationAdapter(getActivity().getApplicationContext(),R.layout.lv_of_find,find);
        if(find.isEmpty()){
            Toast.makeText(getActivity().getApplicationContext(), "hihi", Toast.LENGTH_SHORT).show();
        }
        else {
            lv.setAdapter(locationAdapter_Search);
        }
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchView=view.findViewById(R.id.Search_location);
        searchView.clearFocus();

        lv=view.findViewById(R.id.lvfind);
        arrLocation=_MainActivity.getPlaces();

        LocationAdapter locationAdapter = new LocationAdapter(getActivity().getApplicationContext(),R.layout.lv_of_find,arrLocation);
        lv.setAdapter(locationAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent detail_place = new Intent(_ThisContext, DetailPlaceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("place", arrLocation.get(i));
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
        _MainActivity = (MainActivity) getActivity();
        return view;

    }






}