package com.example.du_lich_vn;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment_False#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment_False extends Fragment {

    public UserFragment_False() {
        // Required empty public constructor
    }

    public static UserFragment_False newInstance() {
        UserFragment_False fragment = new UserFragment_False();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.bSignin_UserFalse).setOnClickListener(v -> {
            Intent login = new Intent(UserFragment_False.this.getContext(), LoginActivity.class);
            startActivity(login);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user__false, container, false);
    }
}