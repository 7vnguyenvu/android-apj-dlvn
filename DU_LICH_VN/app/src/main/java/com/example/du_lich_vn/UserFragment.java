package com.example.du_lich_vn;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {

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

    private String user;
    private String password;

    public UserFragment(String user, String password) {
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

    public static UserFragment newInstance(String user, String pass) {
        UserFragment fragment = new UserFragment(user, pass);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentManager fragmentManager_Search = getActivity().getSupportFragmentManager();
        if (CheckAccount() == 1) {
            fragmentManager_Search
                    .beginTransaction()
                    .replace(R.id.UserContainer, UserFragment_True.newInstance(1))
                    .commit();
        }
        else if (CheckAccount() == 2) {
            fragmentManager_Search
                    .beginTransaction()
                    .replace(R.id.UserContainer, UserFragment_True.newInstance(2))
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

        if (getUser().equals("7V_Admin") && getPassword().equals("Admin_depzai"))
            return 1;
        if (getUser().equals("HT_Admin") && getPassword().equals("Admin_depzai"))
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
        return inflater.inflate(R.layout.fragment_user, container, false);
    }
}