package com.example.du_lich_vn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Adapter_AccountShow extends ArrayAdapter<Class_Account> {
    private Context contxt;
    private ArrayList<Class_Account> arr;
    private int res;

    public Adapter_AccountShow(@NonNull Context context, int resour, ArrayList<Class_Account> acc) {
        super(context,resour,acc);
        contxt=context;
        arr=acc;
        res=resour;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(contxt);
        convertView = layoutInflater.inflate(res,parent,false);

        TextView tAcc_Code_Show = convertView.findViewById(R.id.tCode_Show);
        TextView tAcc_Name_Show = convertView.findViewById(R.id.tName_Show);
        TextView tAcc_User_Show = convertView.findViewById(R.id.tProvOrUser_Show);

        tAcc_Code_Show.setText(arr.get(position).get_id());
        tAcc_Name_Show.setText(arr.get(position).get_name());
        tAcc_User_Show.setText(arr.get(position).get_user());

        return convertView;
    }
}
