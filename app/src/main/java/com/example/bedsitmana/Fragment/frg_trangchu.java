package com.example.bedsitmana.Fragment;

import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toolbar;

import com.example.bedsitmana.R;
import com.google.android.material.navigation.NavigationView;

public class frg_trangchu extends Fragment {
    Button btnokok;


    public frg_trangchu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View v=  inflater.inflate(R.layout.fragment_frg_trangchu, container, false);
        btnokok=v.findViewById(R.id.btnokok);
        btnokok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frg_coso frgcoso= new frg_coso();
                replaceFrg(frgcoso);
            }
        });


        return v;
    }
    public void replaceFrg(Fragment frg){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frmnav,frg).commit();
    }
}