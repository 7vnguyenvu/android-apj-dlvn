package com.example.du_lich_vn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PlaceShowAdapter extends ArrayAdapter<Place> {
    private Context contxt;
    private ArrayList<Place> arr;
    private int res;

    public PlaceShowAdapter(@NonNull Context context, int resour, ArrayList<Place> ArrLocation) {
        super(context,resour,ArrLocation);
        contxt=context;
        arr=ArrLocation;
        res=resour;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(contxt);
        convertView = layoutInflater.inflate(res,parent,false);

        TextView tPlace_Code_Show = convertView.findViewById(R.id.tPlace_Code_Show);
        TextView tPlace_Name_Show = convertView.findViewById(R.id.tPlace_Name_Show);
        TextView tPlace_Prov_Show = convertView.findViewById(R.id.tPlace_Prov_Show);

        tPlace_Code_Show.setText(arr.get(position).getCode());
        tPlace_Name_Show.setText(arr.get(position).getName());
        tPlace_Prov_Show.setText(arr.get(position).getProvince());

        return convertView;
    }
}
