package com.example.bedsitmana.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bedsitmana.Adapter.HopDong_Adapter;
import com.example.bedsitmana.Adapter.Phong_Adapter;
import com.example.bedsitmana.Dao.hopDongDao;
import com.example.bedsitmana.R;
import com.example.bedsitmana.model.HopDong;
import com.example.bedsitmana.model.PhongTro;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
    public void xoa(String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(XemhopDong_Activity.this);
        builder.setTitle("Cảnh báo");
        builder.setIcon(R.drawable.baseline_warning_24);
        builder.setMessage("Bạn có chắc chắn muốn xoá");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dao.delete(Id);
                int maphong = getIntent().getIntExtra("maphong", -1);
                dao.updateTrangThaiPhong(maphong, 0);
                dialogInterface.cancel();
                Toast.makeText(XemhopDong_Activity.this, "Kết thúc hợp đồng thành công ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(XemhopDong_Activity.this, phong_Activity.class);
                XemhopDong_Activity.this.startActivity(intent);
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }

}