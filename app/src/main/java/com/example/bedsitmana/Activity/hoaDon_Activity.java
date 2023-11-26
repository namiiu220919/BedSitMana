package com.example.bedsitmana.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bedsitmana.Adapter.HoaDon_Adapter;
import com.example.bedsitmana.Adapter.NguoiThueSpinerAdapter;
import com.example.bedsitmana.Adapter.SPPhong_Adapter;
import com.example.bedsitmana.Dao.hoaDonDao;
import com.example.bedsitmana.Dao.nguoiThueDao;
import com.example.bedsitmana.Dao.phongTroDao;
import com.example.bedsitmana.R;
import com.example.bedsitmana.model.HoaDon;
import com.example.bedsitmana.model.NguoiThue;
import com.example.bedsitmana.model.PhongTro;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class hoaDon_Activity extends AppCompatActivity {
    ListView lstHoaDon;
    ArrayList<HoaDon> list;
    ArrayList<PhongTro> listpt;
    ArrayList<NguoiThue> listnt;
    HoaDon_Adapter hoaDonAdapter;
    HoaDon hoaDon;
    hoaDonDao hdDao;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    TextView txtNgayTao,txtTenTruongPhong,txtSdt,txtTenPhong,txtSoDien,txtGiaDien,txtTongDien,txtSoNguoi,txtGiaNuoc,txtTongNuoc,txtPhiDichVu_hd,txtTienPhong_hd,txtGhiChu_hd,txtTongHd;
    EditText edtMaHoaDon,edtSdt,edtPhiDichVu,edtTienphong,edtSoDien,edtDonGiaDien,edtSoNguoi,edtDonGiaNuoc,edtNgayTao,edtGhiChu_hd;
    ImageView btnAdd, imgAnhhd;
    Spinner spPhong, spNguoiThue;
    NguoiThueSpinerAdapter nguoiThueSpinerAdapter;
    SPPhong_Adapter spPhongAdapter;
    int maPhong,phidichvu,tienPhong, positionNT, positionPT;
    String maNguoiThue,sdt;
    CheckBox chkDaThanhToan;
    Button btnXacNhan, btnHuy;
    Dialog dialog;
    phongTroDao ptDao;
    byte[] hinhAnh;
    nguoiThueDao ntDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Hóa Đơn");

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_back);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lstHoaDon = findViewById(R.id.lstHoaDon);
        hdDao=new hoaDonDao(hoaDon_Activity.this);
        btnAdd = findViewById(R.id.btnadd_toolbar);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(hoaDon_Activity.this,0);
            }
        });
        capNhatLv();
        lstHoaDon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                hoaDon=list.get(i);
                openDialog(hoaDon_Activity.this,1);
                return false;
            }
        });


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void xoa(String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cảnh báo");
        builder.setIcon(R.drawable.baseline_warning_24);
        builder.setMessage("Bạn có chắc chắn muốn xoá");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                hdDao.delete(Id);
                capNhatLv();
                dialogInterface.cancel();
                Toast.makeText(hoaDon_Activity.this, "Xóa thành công ", Toast.LENGTH_SHORT).show();
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

    private void capNhatLv() {
        list = (ArrayList<HoaDon>) hdDao.getAll();
        hoaDonAdapter=new HoaDon_Adapter(hoaDon_Activity.this,list,this);
        lstHoaDon.setAdapter(hoaDonAdapter);
    }
    public void openDialog(final Context context, final int type){
        dialog = new Dialog(hoaDon_Activity.this);
        dialog.setContentView(R.layout.dialog_hoa_don);
        edtMaHoaDon=dialog.findViewById(R.id.edtMaHoaDon);
        edtSdt=dialog.findViewById(R.id.edtSdt);
        edtPhiDichVu=dialog.findViewById(R.id.edtPhiDichVu);
        edtTienphong=dialog.findViewById(R.id.edtTienphong);
        edtSoDien=dialog.findViewById(R.id.edtSoDien);
        edtSoNguoi=dialog.findViewById(R.id.edtSoNguoi);
        edtDonGiaDien=dialog.findViewById(R.id.edtDonGiaDien);
        edtDonGiaNuoc=dialog.findViewById(R.id.edtDonGiaNuoc);
        edtNgayTao=dialog.findViewById(R.id.edtNgayTao);
        edtGhiChu_hd=dialog.findViewById(R.id.edtGhiChu_hd);
        chkDaThanhToan=dialog.findViewById(R.id.chkDaThanhToan);
        btnXacNhan=dialog.findViewById(R.id.btnXacNhan);
        btnHuy=dialog.findViewById(R.id.btnHuy);
        spPhong=dialog.findViewById(R.id.spnPhong);
        spNguoiThue=dialog.findViewById(R.id.spnNguoiThue);
        imgAnhhd=dialog.findViewById(R.id.imgAnhQR);

        edtNgayTao.setText(""+sdf.format(new Date()));
        edtMaHoaDon.setVisibility(View.GONE);


        ntDao = new nguoiThueDao(hoaDon_Activity.this);
        listnt=new ArrayList<NguoiThue>();
        listnt= (ArrayList<NguoiThue>) ntDao.getAll();
        nguoiThueSpinerAdapter = new NguoiThueSpinerAdapter(hoaDon_Activity.this,listnt);
        spNguoiThue.setAdapter(nguoiThueSpinerAdapter);
        spNguoiThue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maNguoiThue= listnt.get(i).getMaNguoithue();
                sdt= listnt.get(i).getSdt();
                edtSdt.setText("Điện thoại: "+sdt);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ptDao = new phongTroDao(hoaDon_Activity.this);
        listpt=new ArrayList<PhongTro>();
        listpt= (ArrayList<PhongTro>) ptDao.getAll();
        spPhongAdapter=new SPPhong_Adapter(hoaDon_Activity.this,listpt);
        spPhong.setAdapter(spPhongAdapter);
        spPhong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maPhong=listpt.get(i).getMaPhong();
                tienPhong=listpt.get(i).getGia();
                edtTienphong.setText("Tiền phòng: "+tienPhong);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        if (type!=0){
            edtMaHoaDon.setText(String.valueOf(hoaDon.getMaHoaDon()));
            for (int i = 0; i<listnt.size();i++){
                if (hoaDon.getMaNguoiThue()==(listnt.get(i).getMaNguoithue())){
                    positionNT=i;
                }
            }
            spNguoiThue.setSelection(positionNT);
            for (int i = 0; i<listpt.size();i++){
                if (hoaDon.getMaPhong()==(listnt.get(i).getMaPhong())){
                    positionPT=i;
                }
            }
            spNguoiThue.setSelection(positionPT);
            edtSdt.setText(hoaDon.getSdt());
            edtPhiDichVu.setText(String.valueOf(hoaDon.getPhiDichVu()));
            edtTienphong.setText(String.valueOf(hoaDon.getTienPhong()));
            edtSoDien.setText(String.valueOf(hoaDon.getSoDien()));
            edtDonGiaDien.setText(String.valueOf(hoaDon.getDonGiaDien()));
            edtSoNguoi.setText(String.valueOf(hoaDon.getSoNguoi()));
            edtDonGiaNuoc.setText(String.valueOf(hoaDon.getDonGiaNuoc()));
            edtNgayTao.setText(""+sdf.format(new Date()));
            edtGhiChu_hd.setText(hoaDon.getGhiChu());

        }
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hoaDon=new HoaDon();
                hoaDon.setMaNguoiThue(maNguoiThue);
                hoaDon.setSdt(sdt);
                hoaDon.setMaPhong(maPhong);
                hoaDon.setPhiDichVu(Integer.parseInt(edtPhiDichVu.getText().toString()));
                hoaDon.setTienPhong(tienPhong);
                hoaDon.setSoDien(Integer.parseInt(edtSoDien.getText().toString ()));
                hoaDon.setDonGiaDien(Integer.parseInt(edtDonGiaDien.getText().toString ()));
                hoaDon.setSoNguoi(Integer.parseInt(edtSoNguoi.getText().toString ()));
                hoaDon.setDonGiaNuoc(Integer.parseInt(edtDonGiaNuoc.getText().toString ()));
                hoaDon.setNgayTao(new Date());
                hoaDon.setGhiChu(edtGhiChu_hd.getText().toString());
                hoaDon.setTrangThai(0);


                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgAnhhd.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                hinhAnh = byteArrayOutputStream.toByteArray();
                hoaDon.setAnhThanhToan(hinhAnh);



                if (type==0){
                    if (hdDao.insert(hoaDon)>0){
                        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        hoaDon.setTrangThai(0);
                    }else {
                        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    if (chkDaThanhToan.isChecked()){
                        hoaDon.setTrangThai(2);
                    }else {
                        hoaDon.setTrangThai(1);
                    }
                    hoaDon.setMaHoaDon(Integer.parseInt(edtMaHoaDon.getText().toString()));
                    if (hdDao.update(hoaDon)>0){
                        Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
                capNhatLv();
                dialog.dismiss();
            }
        });
        dialog.show();

    }

}