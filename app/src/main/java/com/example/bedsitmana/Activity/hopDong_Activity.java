package com.example.bedsitmana.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bedsitmana.Adapter.HopDong_Adapter;
import com.example.bedsitmana.Adapter.NguoiThueSpinerAdapter;
import com.example.bedsitmana.Dao.hopDongDao;
import com.example.bedsitmana.Dao.nguoiThueDao;
import com.example.bedsitmana.Dao.phongTroDao;
import com.example.bedsitmana.R;
import com.example.bedsitmana.model.HopDong;
import com.example.bedsitmana.model.NguoiThue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class hopDong_Activity extends AppCompatActivity {
    hopDongDao dao;
    ArrayList<HopDong> list;
    ArrayList<NguoiThue> list_nt;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    ListView lsthopDong;
    HopDong_Adapter hopDongAdapter;
    HopDong item;
    ArrayList<HopDong> list_hdm = new ArrayList<>();
    ImageView btnAdd;
    EditText edtma_hd, edtTenkh_hd, edtSdt_hd, edtCCCD_hd, edtDiaChi_hd, edtNgayki_hd, edtSothang_hd, edtSoPhong_hd, edtTienCoc_hd, edtTienPhong_hd, edtSonguoi_hd, edtSoxe_hd, edtGhiChu_hd;
    Spinner spinner;
    int position,cccd,maphong;
    String mant,dc,sdt;
    nguoiThueDao dao_nt;
    NguoiThue nt;
    NguoiThueSpinerAdapter spinerAdapter;
    phongTroDao dao_pt;
    Button btnTaoHD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hop_dong);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Xem hợp đồng");

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_back);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnAdd = findViewById(R.id.btnadd_toolbar);
        btnAdd.setVisibility(View.GONE);
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
        maphong = getIntent().getIntExtra("maphong", -1);
        list_hdm = dao.getHopDongByMaPhong(maphong);
        if (list_hdm.isEmpty()) {
            list.clear();
           openDialog();
        } else {
            hopDongAdapter = new HopDong_Adapter(hopDong_Activity.this, list_hdm, this);
            lsthopDong.setAdapter(hopDongAdapter);
        }



    }
    public void xoa(String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(hopDong_Activity.this);
        builder.setTitle("Cảnh báo");
        builder.setIcon(R.drawable.baseline_warning_24);
        builder.setMessage("Bạn có chắc chắn muốn kết thúc hợp đồng");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dao.delete(Id);
                 maphong = getIntent().getIntExtra("maphong", -1);
                dao.updateTrangThaiPhong(maphong, 0);
                dialogInterface.cancel();
                Toast.makeText(hopDong_Activity.this, "Kết thúc hợp đồng thành công ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(hopDong_Activity.this, phong_Activity.class);
                hopDong_Activity.this.startActivity(intent);
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
    public void openDialog() {
        Dialog dialog = new Dialog(hopDong_Activity.this);
        dialog.setContentView(R.layout.item_taohopdong);
        edtTenkh_hd = dialog.findViewById(R.id.edtTenkh_hd);
        edtSdt_hd = dialog.findViewById(R.id.edtSdt_hd);
        edtCCCD_hd = dialog.findViewById(R.id.edtCCCD_hd);
        edtDiaChi_hd =dialog.findViewById(R.id.edtDiaChi_hd);
        edtNgayki_hd = dialog.findViewById(R.id.edtNgayki_hd);
        edtSothang_hd = dialog.findViewById(R.id.edtSothang_hd);
        edtSoPhong_hd = dialog.findViewById(R.id.edtSoPhong_hd);
        edtTienCoc_hd = dialog.findViewById(R.id.edtTienCoc_hd);
        edtTienPhong_hd = dialog.findViewById(R.id.edtTienPhong_hd);
        edtSonguoi_hd = dialog.findViewById(R.id.edtSonguoi_hd);
        edtSoxe_hd = dialog.findViewById(R.id.edtSoxe_hd);
        edtGhiChu_hd = dialog.findViewById(R.id.edtGhiChu_hd);
        btnTaoHD = dialog.findViewById(R.id.btnTao);
        spinner = dialog.findViewById(R.id.spnNguoiThue);
        dao_pt = new phongTroDao(hopDong_Activity.this);
        list_nt = new ArrayList<NguoiThue>();
        dao_nt = new nguoiThueDao(hopDong_Activity.this);
        list_nt = (ArrayList<NguoiThue>) dao_nt.getAll();
        spinerAdapter = new NguoiThueSpinerAdapter(hopDong_Activity.this, list_nt);
        spinner.setAdapter(spinerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mant = list_nt.get(i).getMaNguoithue();

                cccd = list_nt.get(i).getcCCD();
                sdt = list_nt.get(i).getSdt();
                dc = list_nt.get(i).getThuongTru();
                edtCCCD_hd.setText(cccd+"");
                edtSdt_hd.setText(sdt);
                edtDiaChi_hd.setText(dc);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        maphong = getIntent().getIntExtra("maphong", -1);
        int gia = dao_pt.getGiaPhongTheoMaPhong(maphong);
        String tenphong=dao_pt.getTenPhongTheoMaPhong(maphong);
        edtTienPhong_hd.setText(gia + "");
        edtSoPhong_hd.setText(tenphong);
        edtNgayki_hd.setText(sdf.format(new Date()));
        edtSoPhong_hd.setEnabled(false);
        edtTienPhong_hd.setEnabled(false);
        edtNgayki_hd.setEnabled(false);
        edtCCCD_hd.setEnabled(false);
        edtSdt_hd.setEnabled(false);
        edtDiaChi_hd.setEnabled(false);
        btnTaoHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soThang = Integer.parseInt(edtSothang_hd.getText().toString());
                int tienCoc = Integer.parseInt(edtTienCoc_hd.getText().toString());
                int soNguoi = Integer.parseInt(edtSonguoi_hd.getText().toString());
                int soXe = Integer.parseInt(edtSoxe_hd.getText().toString());
                item = new HopDong();
                item.setMaNguoiThue(mant);
                item.setMaPhong(maphong);
                item.setTenPhong(tenphong);
                item.setGhiChu(edtGhiChu_hd.getText().toString());
                item.setNgayKy(new Date());
                item.setSdt(sdt);
                item.setCCCD(cccd);
                item.setThuongTru(dc);
                item.setThoiHan(soThang);
                item.setTienCoc(tienCoc);
                item.setSoNguoi(soNguoi);
                item.setSoXe(soXe);
                item.setGhiChu(edtGhiChu_hd.getText().toString());
                if (dao.insert(item) > 0) {

                    Toast.makeText(hopDong_Activity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(hopDong_Activity.this, phong_Activity.class);
                    hopDong_Activity.this.startActivity(intent);
                    dao.updateTrangThaiPhong(maphong, 1);
                } else {
                    Toast.makeText(hopDong_Activity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
//                capNhapLv();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    void capNhapLv(){
        list= (ArrayList<HopDong>) dao.getAll();
        hopDongAdapter=new HopDong_Adapter(hopDong_Activity.this,list, hopDong_Activity.this);
        lsthopDong.setAdapter(hopDongAdapter);
    }
}