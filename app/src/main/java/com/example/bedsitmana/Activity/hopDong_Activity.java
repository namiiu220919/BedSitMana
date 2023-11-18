package com.example.bedsitmana.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.bedsitmana.Dao.hopDongDao;
import com.example.bedsitmana.R;
import com.example.bedsitmana.model.HopDong;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class hopDong_Activity extends AppCompatActivity {
    hopDongDao dao;
    ArrayList<HopDong> list;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    EditText edtmahd_hd, edtTenkh_hd, edtSdt_hd,edtCCCD_hd,edtDiaChi_hd, edtNgayki_hd, edtSothang_hd, edtLoaiPhong_hd, edtSoPhong_hd, edtTienCoc_hd, edtTienPhong_hd, edtSonguoi_hd,edtSoxe_hd, edtGhiChu_hd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hop_dong);

        edtmahd_hd = findViewById(R.id.edtmahd_hd);
        edtTenkh_hd = findViewById(R.id.edtmahd_hd);
        edtSdt_hd =findViewById(R.id.edtmahd_hd);
        edtCCCD_hd = findViewById(R.id.edtmahd_hd);
        edtDiaChi_hd = findViewById(R.id.edtmahd_hd);
        edtNgayki_hd = findViewById(R.id.edtmahd_hd);
        edtSothang_hd = findViewById(R.id.edtmahd_hd);
        edtLoaiPhong_hd = findViewById(R.id.edtmahd_hd);
        edtSoPhong_hd = findViewById(R.id.edtmahd_hd);
        edtTienCoc_hd = findViewById(R.id.edtmahd_hd);
        edtTienPhong_hd = findViewById(R.id.edtmahd_hd);
        edtSonguoi_hd = findViewById(R.id.edtmahd_hd);
        edtGhiChu_hd = findViewById(R.id.edtmahd_hd);
        edtSoxe_hd = findViewById(R.id.edtmahd_hd);

        String maphong=getIntent().getStringExtra("maphong");
        dao=new hopDongDao(hopDong_Activity.this);

        list= (ArrayList<HopDong>) dao.getAll();
        HopDong item=new HopDong();

        edtTenkh_hd.setText(item.getTenNguoiThue());
        edtSdt_hd.setText(item.getSdt());
        edtCCCD_hd.setText(item.getCCCD());
        edtDiaChi_hd.setText(item.getThuongTru());
        edtNgayki_hd.setText(sdf.format(item.getNgayKy()));
        edtSothang_hd.setText(item.getThoiHan());
        edtLoaiPhong_hd.setText(item.getTenLoai());
        edtSoPhong_hd.setText(item.getTenPhong());
        edtTienCoc_hd.setText(item.getTienCoc());
        edtTienPhong_hd.setText(item.getGiaTien());
        edtSonguoi_hd.setText(item.getSoNguoi());
        edtSoxe_hd.setText(item.getSoXe());
        edtGhiChu_hd.setText(item.getGhiChu());

    }
}