package com.example.bedsitmana.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bedsitmana.Adapter.Phong_Adapter;
import com.example.bedsitmana.Dao.hopDongDao;
import com.example.bedsitmana.Dao.phongTroDao;
import com.example.bedsitmana.R;
import com.example.bedsitmana.model.HopDong;
import com.example.bedsitmana.model.PhongTro;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class phong_Activity extends AppCompatActivity {
    ListView lstPhong;
    ArrayList<PhongTro> list;
    Phong_Adapter phongAdapter;
    PhongTro phongTro;
    phongTroDao dao;
    Dialog dialog;
    TextView txtXemHopDong;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    EditText edtmahd_hd, edtTenkh_hd, edtSdt_hd,edtCCCD_hd,edtDiaChi_hd, edtNgayki_hd, edtSothang_hd, edtLoaiPhong_hd, edtSoPhong_hd, edtTienCoc_hd, edtTienPhong_hd, edtSonguoi_hd,edtSoxe_hd, edtGhiChu_hd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phong);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Phòng Trọ");

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_back);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        lstPhong = findViewById(R.id.lstPhongTro);
        dao = new phongTroDao(phong_Activity.this);
        list = (ArrayList<PhongTro>) dao.getAll();
        phongAdapter = new Phong_Adapter(phong_Activity.this,this,list);
        lstPhong.setAdapter(phongAdapter);
        lstPhong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PhongTro pp = list.get(i);
                String maPhong = String.valueOf(pp.getMaPhong());

                Intent intent = new Intent(phong_Activity.this, hopDong_Activity.class);
                intent.putExtra("maphong",maPhong);
                startActivity(intent);
            }
        });


    }
//    protected void openDialog_hd(final Context context){
//        dialog=new Dialog(context);
//        dialog.setContentView(R.layout.activity_hop_dong);
//
//        hopDongDao hopDongDao = new hopDongDao(phong_Activity.this);
//
//        edtmahd_hd = dialog.findViewById(R.id.edtmahd_hd);
//        edtTenkh_hd = dialog.findViewById(R.id.edtmahd_hd);
//        edtSdt_hd = dialog.findViewById(R.id.edtmahd_hd);
//        edtCCCD_hd = dialog.findViewById(R.id.edtmahd_hd);
//        edtDiaChi_hd = dialog.findViewById(R.id.edtmahd_hd);
//        edtNgayki_hd = dialog.findViewById(R.id.edtmahd_hd);
//        edtSothang_hd = dialog.findViewById(R.id.edtmahd_hd);
//        edtLoaiPhong_hd = dialog.findViewById(R.id.edtmahd_hd);
//        edtSoPhong_hd = dialog.findViewById(R.id.edtmahd_hd);
//        edtTienCoc_hd = dialog.findViewById(R.id.edtmahd_hd);
//        edtTienPhong_hd = dialog.findViewById(R.id.edtmahd_hd);
//        edtSonguoi_hd = dialog.findViewById(R.id.edtmahd_hd);
//        edtGhiChu_hd = dialog.findViewById(R.id.edtmahd_hd);
//        edtSoxe_hd = dialog.findViewById(R.id.edtmahd_hd);
//
//        HopDong item=new HopDong();
//
//        edtTenkh_hd.setText(item.getTenNguoiThue());
//        edtSdt_hd.setText(item.getSdt());
//        edtCCCD_hd.setText(item.getCCCD());
//        edtDiaChi_hd.setText(item.getThuongTru());
//        edtNgayki_hd.setText(sdf.format(item.getNgayKy()));
//        edtSothang_hd.setText(item.getThoiHan());
//        edtLoaiPhong_hd.setText(item.getTenLoai());
//        edtSoPhong_hd.setText(item.getTenPhong());
//        edtTienCoc_hd.setText(item.getTienCoc());
//        edtTienPhong_hd.setText(item.getGiaTien());
//        edtSonguoi_hd.setText(item.getSoNguoi());
//        edtSoxe_hd.setText(item.getSoXe());
//        edtGhiChu_hd.setText(item.getGhiChu());
//
//
//    }
}