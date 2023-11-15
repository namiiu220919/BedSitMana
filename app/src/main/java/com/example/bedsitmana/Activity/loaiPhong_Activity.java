package com.example.bedsitmana.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.bedsitmana.Adapter.LoaiPhong_Adapter;
import com.example.bedsitmana.Dao.LoaiPhongDao;
import com.example.bedsitmana.R;
import com.example.bedsitmana.model.LoaiPhong;

import java.util.ArrayList;

public class loaiPhong_Activity extends AppCompatActivity {
    ListView lstLoaiPhong;
    ArrayList<LoaiPhong> list;
    LoaiPhong_Adapter cosoAdapter;
    LoaiPhong coso;
    LoaiPhongDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_phong);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Loại Phòng");

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_back);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        lstLoaiPhong = findViewById(R.id.lstLoaiPhong);
        dao = new LoaiPhongDao(loaiPhong_Activity.this);
        list = (ArrayList<LoaiPhong>) dao.getAll();
        cosoAdapter = new LoaiPhong_Adapter(loaiPhong_Activity.this,list,this);
        lstLoaiPhong.setAdapter(cosoAdapter);
    }
}