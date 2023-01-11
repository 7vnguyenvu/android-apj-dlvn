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
    ArrayList<Class_Place> arrLocation =new ArrayList<Class_Place>();
    private ArrayList<Class_Place> finds = new ArrayList<>();

    public void setFinds(ArrayList<Class_Place> finds) {
        this.finds = finds;
    }

    private SearchView searchView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        super.onCreate(savedInstanceState);
    }

    private void mysearch(String s) {
        if (!s.equals("")){
            System.out.println("Đang tìm: " + s);

            ArrayList<Class_Place> tmps = new ArrayList<>();
            for (Class_Place place:finds) {
                if(place.getName().toLowerCase().contains(s.toLowerCase())) {
                    tmps.add(place);
                }
            }

            if(!tmps.isEmpty()) {
                setFinds(tmps);
            }
        } else {
            setFinds(arrLocation);
        }
        Adapter_PlaceFind locationAdapter_Search = new Adapter_PlaceFind(_ThisContext,R.layout.place_item_of_find_custom, finds);
        lv.setAdapter(locationAdapter_Search);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        arrLocation = _ActivityMain.getPlaces();
        setFinds(arrLocation);

        searchView=view.findViewById(R.id.Search_location);
        searchView.clearFocus();

        Adapter_PlaceFind placeAdapter = new Adapter_PlaceFind(getActivity().getApplicationContext(),R.layout.place_item_of_find_custom, finds);

        lv=view.findViewById(R.id.lvfind);
        lv.setAdapter(placeAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent detail_place = new Intent(_ThisContext, Activity_DetailPlace.class);
                Bundle bundle = new Bundle();
                for (Class_Place place :arrLocation)
                    if(place.getCode().equals(finds.get(i).getCode()))
                        bundle.putSerializable("classPlace", place);
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
                System.out.println(s);
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