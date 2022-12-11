package com.example.du_lich_vn;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class LocationAdapter extends ArrayAdapter<FindFrag_Location> {
    private Context contxt;
    private ArrayList<FindFrag_Location> arr;
    private int res;
//    public LocationAdapter(@NonNull Context context, int resource, FindFragment contxt, ArrayList<FindFrag_Location> arr) {
//        super(context, resource);
//        this.contxt = contxt;
//        this.arr = arr;
//    }


    public LocationAdapter(@NonNull Context context,int resour,ArrayList<FindFrag_Location> ArrLocation) {
        super(context,R.layout.lv_of_find,ArrLocation);
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
        imageView.setImageResource(arr.get(position).getImg());
        textViewTen.setText(arr.get(position).getName());
        textViewNoiDung.setText(arr.get(position).getMota());
        return convertView;
    }
}
