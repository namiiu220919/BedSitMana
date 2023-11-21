package com.example.bedsitmana.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.bedsitmana.Adapter.HopDong_Adapter;
import com.example.bedsitmana.Adapter.LoaiPhong_Adapter;
import com.example.bedsitmana.Dao.LoaiPhongDao;
import com.example.bedsitmana.Dao.hopDongDao;
import com.example.bedsitmana.R;
import com.example.bedsitmana.model.HopDong;
import com.example.bedsitmana.model.LoaiPhong;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TaoHopDong_Activity extends AppCompatActivity {
    ListView lsthd;
    HopDong item;
    EditText edtmaLoai, edttenLoai, edtPhidv, edtTienDien, edtTienNuoc;
    hopDongDao dao;
    ArrayList<HopDong> list;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    HopDong_Adapter adapter;

    Button btnHuy, btnXacNhan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_hop_dong);

    }
}