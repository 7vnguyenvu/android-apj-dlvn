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

public class Adapter_PlaceFind extends ArrayAdapter<Class_Place> {
    private Context contxt;
    private ArrayList<Class_Place> arr;
    private int res;

    public Adapter_PlaceFind(@NonNull Context context, int resour, ArrayList<Class_Place> ArrLocation) {
        super(context,resour,ArrLocation);
        contxt=context;
        arr=ArrLocation;
        res=resour;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       LayoutInflater layoutInflater = LayoutInflater.from(contxt);
       convertView=layoutInflater.inflate(res,parent,false);
        //LayoutInflater layoutInflater =this.contxt.getLay();
        ImageView  imageView=convertView.findViewById(R.id.imageViewLocation);
        TextView textViewTen=convertView.findViewById(R.id.txtTen);
        TextView textViewNoiDung= convertView.findViewById(R.id.txtNoiDung);
        imageView.setImageResource(arr.get(position).getImage());
        textViewTen.setText(arr.get(position).getName());
        textViewNoiDung.setText(arr.get(position).getDescription());
        return convertView;
    }
}
