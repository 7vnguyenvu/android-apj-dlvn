package com.example.du_lich_vn;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class ExploreFragment extends Fragment {

    Context _ThisContext;
    View _View;

    ListView lPlaces;
    ArrayList<Place> places;
    private MainActivity _MainActivity;

    public ExploreFragment() {
        // Required empty public constructor
    }

    public static ExploreFragment newInstance() {
        ExploreFragment fragment = new ExploreFragment();
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
        //Addplaces();
        places = _MainActivity.getPlaces();

        PlaceAdapter placeAdapter = new PlaceAdapter(_ThisContext, R.layout.place_item_custom, places);
        lPlaces.setAdapter(placeAdapter);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        _View = inflater.inflate(R.layout.fragment_explore, container, false);
        _ThisContext = container.getContext();
        _MainActivity = (MainActivity) getActivity();



        return _View;
    }

    private void Addplaces() {
        places.add(new Place(R.drawable.ic_image,"Vạn Hương Mai","Công ty Du lịch Vạn Hương Mai", "4.0 (1236 review)", "Châu Đốc, An Giang", "M5C7+X6"));
        places.add(new Place(R.drawable.ic_image,"Miếu Bà Chúa Xứ Núi Sam", "Chùa Bà Châu Đốc", "4.6 (299 review)", "Châu Đốc, An Giang", "M3JJ+R4"));
    }
}