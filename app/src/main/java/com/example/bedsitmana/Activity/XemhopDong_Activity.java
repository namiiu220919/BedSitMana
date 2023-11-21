package com.example.bedsitmana.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.bedsitmana.Adapter.HopDong_Adapter;
import com.example.bedsitmana.Dao.hopDongDao;
import com.example.bedsitmana.R;
import com.example.bedsitmana.model.HopDong;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class XemhopDong_Activity extends AppCompatActivity {
    hopDongDao dao;
    ArrayList<HopDong> list;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    ListView lsthopDong;
    HopDong_Adapter hopDongAdapter;
    ArrayList<HopDong> list_hdm = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_hop_dong);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Xem hợp đồng");

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_back);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lsthopDong = findViewById(R.id.lsthopDong);
        dao = new hopDongDao(XemhopDong_Activity.this);
        list = (ArrayList<HopDong>) dao.getAll();
        hopDongAdapter = new HopDong_Adapter(XemhopDong_Activity.this, list, this);
        lsthopDong.setAdapter(hopDongAdapter);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        int maphong = getIntent().getIntExtra("maphong", -1);
        list_hdm = dao.getHopDongByMaPhong(maphong);
        hopDongAdapter = new HopDong_Adapter(XemhopDong_Activity.this, list_hdm, this); // Sử dụng list_hdm thay vì list
        lsthopDong.setAdapter(hopDongAdapter);


    }
}