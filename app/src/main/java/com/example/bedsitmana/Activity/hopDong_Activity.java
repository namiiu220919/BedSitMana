package com.example.bedsitmana.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bedsitmana.Adapter.HopDong_Adapter;
import com.example.bedsitmana.Adapter.LoaiPhong_Adapter;
import com.example.bedsitmana.Dao.LoaiPhongDao;
import com.example.bedsitmana.Dao.hopDongDao;
import com.example.bedsitmana.R;
import com.example.bedsitmana.model.HopDong;
import com.example.bedsitmana.model.LoaiPhong;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class hopDong_Activity extends AppCompatActivity {
    hopDongDao dao;
    ArrayList<HopDong> list;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    ListView lsthopDong;
    HopDong_Adapter hopDongAdapter;
    ArrayList<HopDong> list_hdm = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hop_dong);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Hợp đồng");

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_back);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lsthopDong = findViewById(R.id.lsthopDong);
        dao = new hopDongDao(hopDong_Activity.this);
        list = (ArrayList<HopDong>) dao.getAll();
        hopDongAdapter = new HopDong_Adapter(hopDong_Activity.this, list, this);
        lsthopDong.setAdapter(hopDongAdapter);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        int maphong = getIntent().getIntExtra("maphong", -1);
        list_hdm = dao.getHopDongByMaPhong(maphong);
        hopDongAdapter = new HopDong_Adapter(hopDong_Activity.this, list_hdm, this); // Sử dụng list_hdm thay vì list
        lsthopDong.setAdapter(hopDongAdapter);


    }
}