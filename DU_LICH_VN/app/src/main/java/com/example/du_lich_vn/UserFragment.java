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

    public UserFragment(String user, String password) {
        this.setUser(user);
        this.setPassword(password);
    }

    public UserFragment(int contentLayoutId, String user, String password) {
        super(contentLayoutId);
        this.user = user;
        this.password = password;
    }

    private String password;

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
        if (CheckAccount()) {
            fragmentManager_Search
                    .beginTransaction()
                    .replace(R.id.UserContainer, UserFragment_True.newInstance())
                    .commit();
        }
        else {
            fragmentManager_Search
                    .beginTransaction()
                    .replace(R.id.UserContainer, UserFragment_False.newInstance())
                    .commit();
        }
    }

    public boolean CheckAccount() {

        if (getUser() == "Admin" && getPassword() == "Admin_depzai")
            return true;
        return false;
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