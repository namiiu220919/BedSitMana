package com.example.bedsitmana.Fragment;

import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.bedsitmana.R;


public class frg_trangchu extends Fragment {



    public frg_trangchu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View v=  inflater.inflate(R.layout.fragment_frg_trangchu, container, false);




        return v;
    }
    public void replaceFrg(Fragment frg){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frmnav,frg).commit();
    }
}