package com.example.du_lich_vn;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Adapter_PlaceExplore extends ArrayAdapter<Class_Place> {

    private Context _context;
    private ArrayList<Class_Place> array;
    private int res;

    public Adapter_PlaceExplore(@NonNull Context context, int resour, ArrayList<Class_Place> classPlaces) {
        super(context,resour, classPlaces);
        _context=context;
        array= classPlaces;
        res=resour;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(_context);
        convertView = layoutInflater.inflate(res,parent,false);

        ImageView  iPlace = convertView.findViewById(R.id.iPlace_Img);
        TextView nPlace = convertView.findViewById(R.id.tPlace_Name);
        TextView dPlace = convertView.findViewById(R.id.tPlace_Desc);
        TextView rPlace = convertView.findViewById(R.id.tPlace_Rate);
        TextView pPlace = convertView.findViewById(R.id.tPlace_Pro);
        TextView sPlace = convertView.findViewById(R.id.tPlace_Detail);

        iPlace.setImageResource(array.get(position).getImage());
        nPlace.setText(array.get(position).getName());
        dPlace.setText(array.get(position).getDescription());
        rPlace.setText("Đánh giá: " + array.get(position).getRating());
        pPlace.setText("Vị trí: " + array.get(position).getProvince());
        sPlace.setPaintFlags(sPlace.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        return convertView;
    }
}
