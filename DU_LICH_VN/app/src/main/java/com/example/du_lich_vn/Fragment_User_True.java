package com.example.du_lich_vn;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_User_True#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_User_True extends Fragment {

    int admin = 0;
    String user_name;

    ImageView tUser_Img;
    TextView tUser_Name, tUser_Disc;


    public Fragment_User_True(int num, String name) {
        admin = num;
        user_name = name;
    }

    public static Fragment_User_True newInstance(int num, String name ) {
        Fragment_User_True fragment = new Fragment_User_True(num, name);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tUser_Img = view.findViewById(R.id.tUser_Img);
        tUser_Name = view.findViewById(R.id.tUser_Name);
        tUser_Disc = view.findViewById(R.id.tUser_Disc);

        if (admin == 1) {
            if (user_name.equals("7V - NGUYEN VU"))
                tUser_Img.setImageResource(R.drawable.nguyenvu);
            else
                tUser_Img.setImageResource(R.drawable.hoangthang);
            tUser_Name.setText(user_name);
            tUser_Disc.setText("Mr. Admin");
        } else if (admin == 2) {
            tUser_Img.setImageResource(R.drawable.ic_user);
            tUser_Name.setText(user_name);
            tUser_Disc.setText("Chào mừng đến với Travel Me");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user__true, container, false);
    }
}