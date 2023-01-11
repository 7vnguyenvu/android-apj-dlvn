package com.example.du_lich_vn;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Control#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Control extends Fragment implements View.OnClickListener {

    Context _ThisContext;
    View _View;
    private Activity_Main _ActivityMain;

    public Fragment_Control() {
        // Required empty public constructor
    }

    public static Fragment_Control newInstance() {
        return new Fragment_Control();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    ImageView iS, iE, iU;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        _ThisContext = view.getContext();

        iS = view.findViewById(R.id.iSearch_Ctrl);
        iE = view.findViewById(R.id.iExplore_Ctrl);
        iU = view.findViewById(R.id.iUser_Ctrl);

        iS.setOnClickListener(this);
        iE.setOnClickListener(this);
        iU.setOnClickListener(this);

        FragmentManager fragmentManager_Explore = getActivity().getSupportFragmentManager();
        fragmentManager_Explore
                .beginTransaction()
                .replace(R.id.PageContainer, Fragment_Explore.newInstance())
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
                        .replace(R.id.PageContainer, Fragment_Find.newInstance())
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
                        .replace(R.id.PageContainer, Fragment_Explore.newInstance())
                        .commit();

                iS.getLayoutParams().height = 55;
                iE.getLayoutParams().height = 100;
                iU.getLayoutParams().height = 55;
                view.requestLayout();
                break;

            case R.id.iUser_Ctrl:


                Class_Account acc = _ActivityMain.getAccount();

                FragmentManager fragmentManager_User = getActivity().getSupportFragmentManager();
                fragmentManager_User
                        .beginTransaction()
                        .replace(R.id.PageContainer, Fragment_User.newInstance(acc))
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
        _View = inflater.inflate(R.layout.fragment_control, container, false);
        _ActivityMain = (Activity_Main) getActivity();


        return _View;
    }
}