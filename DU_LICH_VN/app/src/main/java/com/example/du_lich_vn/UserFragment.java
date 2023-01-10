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
    Button bAd_Tools, bSignout;
    ArrayList<Place> places = new ArrayList<Place>();
    Account account;

    public UserFragment(Account acc) {
        this.account = acc;
    }

    public UserFragment(int contentLayoutId, Account acc) {
        super(contentLayoutId);
        this.account = acc;
    }


    public UserFragment() {
        // Required empty public constructor
    }

    public static UserFragment newInstance(Account acc) {
        UserFragment fragment = new UserFragment(acc);
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
                    .replace(R.id.UserContainer, UserFragment_True.newInstance(1, account.get_name()))
                    .commit();

            bAd_Tools.setVisibility(View.VISIBLE);
            bSignout.setVisibility(View.VISIBLE);
        }
        else if (CheckAccount() == 2) {
            fragmentManager_Search
                    .beginTransaction()
                    .replace(R.id.UserContainer, UserFragment_True.newInstance(2, account.get_name()))
                    .commit();

            bSignout.setVisibility(View.VISIBLE);
        }
        else {
            fragmentManager_Search
                    .beginTransaction()
                    .replace(R.id.UserContainer, UserFragment_False.newInstance())
                    .commit();
        }

        bAd_Tools.setOnClickListener(_view -> {
            startActivity(new Intent(_ThisConText, AdminToolsActivity.class)
                    .putExtra("Account", account));
        });

        bSignout.setOnClickListener(_view -> {
            startActivity(new Intent(_ThisConText, LoginActivity.class));
        });

    }

    public int CheckAccount() {

        if ((account.get_user().equals("7V_Admin") && account.get_pass().equals("Admin_depzai")) || (account.get_user().equals("HT_Admin") && account.get_pass().equals("Admin_depzai")))
            return 1;
        if (!(account.get_user().equals("") && account.get_pass().equals("")))
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
        bSignout = _View.findViewById(R.id.bSignout_Login);

        return _View;
    }
}