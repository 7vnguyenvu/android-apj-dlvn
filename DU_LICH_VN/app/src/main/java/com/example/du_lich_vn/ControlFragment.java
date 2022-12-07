package com.example.du_lich_vn;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ControlFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ControlFragment extends Fragment implements View.OnClickListener {

    public ControlFragment() {
        // Required empty public constructor
    }

    public static ControlFragment newInstance() {
        ControlFragment fragment = new ControlFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    ImageView iS, iE, iU;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iS = view.findViewById(R.id.iSearch_Ctrl);
        iE = view.findViewById(R.id.iExplore_Ctrl);
        iU = view.findViewById(R.id.iUser_Ctrl);

        iS.setOnClickListener(this);
        iE.setOnClickListener(this);
        iU.setOnClickListener(this);

        FragmentManager fragmentManager_Explore = getActivity().getSupportFragmentManager();
        fragmentManager_Explore
                .beginTransaction()
                .replace(R.id.PageContainer, ExploreFragment.newInstance())
                .commit();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iSearch_Ctrl:

                FragmentManager fragmentManager_Search = getActivity().getSupportFragmentManager();
                fragmentManager_Search
                        .beginTransaction()
                        .replace(R.id.PageContainer, FindFragment.newInstance())
                        .commit();

                iS.getLayoutParams().height = 100;
                iE.getLayoutParams().height = 55;
                iU.getLayoutParams().height = 55;
                view.requestLayout();
                break;

            case R.id.iExplore_Ctrl:

                FragmentManager fragmentManager_Explore = getActivity().getSupportFragmentManager();
                fragmentManager_Explore
                        .beginTransaction()
                        .replace(R.id.PageContainer, ExploreFragment.newInstance())
                        .commit();

                iS.getLayoutParams().height = 55;
                iE.getLayoutParams().height = 100;
                iU.getLayoutParams().height = 55;
                view.requestLayout();
                break;

            case R.id.iUser_Ctrl:

                FragmentManager fragmentManager_User = getActivity().getSupportFragmentManager();
                fragmentManager_User
                        .beginTransaction()
                        .replace(R.id.PageContainer, UserFragment.newInstance())
                        .commit();

                iS.getLayoutParams().height = 55;
                iE.getLayoutParams().height = 55;
                iU.getLayoutParams().height = 100;
                view.requestLayout();
                break;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_control, container, false);
    }
}