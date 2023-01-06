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
 * Use the {@link UserFragment_True#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment_True extends Fragment {

    int admin = 0;

    ImageView tUser_Img;
    TextView tUser_Name, tUser_Disc;

    public UserFragment_True(int num_ad) {
        admin = num_ad;
    }

    public static UserFragment_True newInstance(int num_ad) {
        UserFragment_True fragment = new UserFragment_True(num_ad);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tUser_Name = view.findViewById(R.id.tUser_Name);
        tUser_Disc = view.findViewById(R.id.tUser_Disc);

        if (admin == 1) {
            tUser_Name.setText("7V - NGUYEN VU");
            tUser_Disc.setText("Mr. Admin DTH206069");
        } else if (admin == 2) {
            tUser_Name.setText("HT - HOANG THANG");
            tUser_Disc.setText("Mr. Admin");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user__true, container, false);
    }
}