package com.example.bedsitmana.Adapter;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.bedsitmana.model.PhongTro;

public class Phong_Adapter extends ArrayAdapter<PhongTro> {
    private Context context;
    Activity activity_phong;
    private ArrayAdapter<PhongTro> lists;

    public Phong_Adapter(@NonNull Context context, int resource) {
        super(context, resource);
    }
}
