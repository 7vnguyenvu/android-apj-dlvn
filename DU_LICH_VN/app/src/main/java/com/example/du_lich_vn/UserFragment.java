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
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {

    View _View;
    private MainActivity _MainActivity;
    Context _ThisConText;
    Button bAd_Tools;
    ArrayList<Place> places = new ArrayList<Place>();

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    private String nick_name;
    private String user;
    private String password;

    public UserFragment( String nick_name, String user, String password) {
        this.setNick_name(nick_name);
        this.setUser(user);
        this.setPassword(password);
    }

    public UserFragment(int contentLayoutId, String user, String password) {
        super(contentLayoutId);
        this.user = user;
        this.password = password;
    }


    public UserFragment() {
        // Required empty public constructor
    }

    public static UserFragment newInstance(String nick_name, String user, String pass) {
        UserFragment fragment = new UserFragment(nick_name, user, pass);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        places = _MainActivity.getPlaces();

        FragmentManager fragmentManager_Search = getActivity().getSupportFragmentManager();
        if (CheckAccount() == 1) {
            fragmentManager_Search
                    .beginTransaction()
                    .replace(R.id.UserContainer, UserFragment_True.newInstance(1, getNick_name()))
                    .commit();

            bAd_Tools.setVisibility(View.VISIBLE);
            bAd_Tools.setOnClickListener(_view -> {
                startActivity(new Intent(_ThisConText, AdminToolsActivity.class).putExtra("places", places));
            });
        }
        else if (CheckAccount() == 2) {
            fragmentManager_Search
                    .beginTransaction()
                    .replace(R.id.UserContainer, UserFragment_True.newInstance(2, getNick_name()))
                    .commit();
        }
        else {
            fragmentManager_Search
                    .beginTransaction()
                    .replace(R.id.UserContainer, UserFragment_False.newInstance())
                    .commit();
        }
    }

    public int CheckAccount() {

        if ((getUser().equals("7V_Admin") && getPassword().equals("Admin_depzai")) || (getUser().equals("HT_Admin") && getPassword().equals("Admin_depzai")))
            return 1;
        if (!(getUser().equals("") && getPassword().equals("")))
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
        _MainActivity = (MainActivity) getActivity();

        bAd_Tools = _View.findViewById(R.id.bAdmin_Tools);

        return _View;
    }
}