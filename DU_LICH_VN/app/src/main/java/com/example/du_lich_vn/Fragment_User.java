package com.example.du_lich_vn;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_User#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_User extends Fragment {

    View _View;
    private Activity_Main _ActivityMain;
    Context _ThisConText;
    Button bDetail, bAd_Tools, bSignout;
    ArrayList<Class_Place> classPlaces = new ArrayList<Class_Place>();
    Class_Account classAccount;

    public Fragment_User(Class_Account acc) {
        this.classAccount = acc;
    }

    public Fragment_User(int contentLayoutId, Class_Account acc) {
        super(contentLayoutId);
        this.classAccount = acc;
    }


    public Fragment_User() {
        // Required empty public constructor
    }

    public static Fragment_User newInstance(Class_Account acc) {
        Fragment_User fragment = new Fragment_User(acc);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        classPlaces = _ActivityMain.getPlaces();

        FragmentManager fragmentManager_Search = getActivity().getSupportFragmentManager();
        if (CheckAccount() != 0) {
            bDetail.setVisibility(View.VISIBLE);
            bSignout.setVisibility(View.VISIBLE);

            if (CheckAccount() == 1) {
                bAd_Tools.setVisibility(View.VISIBLE);
                fragmentManager_Search
                        .beginTransaction()
                        .replace(R.id.UserContainer, Fragment_User_True.newInstance(1, classAccount.get_name()))
                        .commit();
            }
            else if (CheckAccount() == 2) {
                fragmentManager_Search
                        .beginTransaction()
                        .replace(R.id.UserContainer, Fragment_User_True.newInstance(2, classAccount.get_name()))
                        .commit();
            }
        }
        else {
            fragmentManager_Search
                    .beginTransaction()
                    .replace(R.id.UserContainer, Fragment_User_False.newInstance())
                    .commit();
        }

        bAd_Tools.setOnClickListener(_view -> {
            startActivity(new Intent(_ThisConText, Activity_AdminTools.class)
                    .putExtra("Class_Account", classAccount));
        });

        bSignout.setOnClickListener(_view -> {
            startActivity(new Intent(_ThisConText, Activity_Login.class));
        });

    }

    public int CheckAccount() {

        if ((classAccount.get_user().equals("7V_Admin") && classAccount.get_pass().equals("Admin_depzai")) || (classAccount.get_user().equals("HT_Admin") && classAccount.get_pass().equals("Admin_depzai")))
            return 1;
        if (!(classAccount.get_user().equals("") && classAccount.get_pass().equals("")))
            return 2;
        return 0;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        _View = inflater.inflate(R.layout.fragment_user, container, false);
        _ThisConText = container.getContext();
        _ActivityMain = (Activity_Main) getActivity();

        bDetail = _View.findViewById(R.id.bDetail_User);
        bAd_Tools = _View.findViewById(R.id.bAdmin_Tools);
        bSignout = _View.findViewById(R.id.bSignout_Login);

        return _View;
    }
}