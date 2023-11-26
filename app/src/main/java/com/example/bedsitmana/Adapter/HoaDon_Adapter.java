package com.example.bedsitmana.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bedsitmana.Activity.hoaDon_Activity;
import com.example.bedsitmana.Dao.hoaDonDao;
import com.example.bedsitmana.Dao.nguoiThueDao;
import com.example.bedsitmana.Dao.phongTroDao;
import com.example.bedsitmana.R;
import com.example.bedsitmana.model.HoaDon;
import com.example.bedsitmana.model.NguoiThue;
import com.example.bedsitmana.model.PhongTro;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HoaDon_Adapter extends ArrayAdapter<HoaDon> {
    TextView txtPhong_HoaDon,txtTenTruongPhong_HoaDon,txtNgayTao_HoaDon,txtGhiChu_HoaDon,txtTongHoaDon,txtTrangThai_HoaDon;
    ImageView btnDelete, imgAnh;
    private Context context;
    private ArrayList<HoaDon> list;
    hoaDon_Activity hoaDonActivity;
    phongTroDao ptDao;
    nguoiThueDao ntDao;
    hoaDonDao hoadonDao;
    byte[] anhthanhtoan;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public HoaDon_Adapter(@NonNull Context context, ArrayList<HoaDon> list, hoaDon_Activity hoaDonActivity) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
        this.hoaDonActivity = hoaDonActivity;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            v=inflater.inflate(R.layout.item_hoadon,null);
        }
        final HoaDon hoaDon = list.get(position);
        txtPhong_HoaDon=v.findViewById(R.id.txtPhong_HoaDon);
        txtTenTruongPhong_HoaDon=v.findViewById(R.id.txtTenTruongPhong_HoaDon);
        txtNgayTao_HoaDon=v.findViewById(R.id.txtNgayTao_HoaDon);
        txtGhiChu_HoaDon=v.findViewById(R.id.txtGhiChu_HoaDon);
        txtTongHoaDon=v.findViewById(R.id.txtTongHoaDon);
        txtTrangThai_HoaDon=v.findViewById(R.id.txtTrangThai_HoaDon);
        btnDelete=v.findViewById(R.id.btnDelete);
        imgAnh=v.findViewById(R.id.imgAnh);

        ptDao = new phongTroDao(context);
        PhongTro phongTro = ptDao.getID(String.valueOf(hoaDon.getMaPhong()));
        txtPhong_HoaDon.setText("Phòng: "+phongTro.getTenPhong());
        ntDao = new nguoiThueDao(context);
        NguoiThue nguoiThue = ntDao.getID(hoaDon.getMaNguoiThue());
        txtTenTruongPhong_HoaDon.setText("Tên trưởng phòng: "+nguoiThue.getTenNguoiThue());
        txtNgayTao_HoaDon.setText(sdf.format("Ngày: "+hoaDon.getNgayTao()));
        txtGhiChu_HoaDon.setText("Ghi chú: "+hoaDon.getGhiChu());
        int tong= 0;
        hoadonDao=new hoaDonDao(context);
        tong=hoadonDao.getTongTienDien(hoaDon.getMaHoaDon())+hoadonDao.getTongTienNuoc(hoaDon.getMaHoaDon())+hoaDon.getPhiDichVu()+hoaDon.getTienPhong();
        txtTongHoaDon.setText("Tổng: "+tong);

        if (hoaDon.getTrangThai()==0){
            txtTrangThai_HoaDon.setText("Thanh Toán");
            txtTrangThai_HoaDon.setBackgroundColor(Color.GREEN);
            txtTrangThai_HoaDon.setTextColor(Color.WHITE);
        }else if (hoaDon.getTrangThai()==1){
            txtTrangThai_HoaDon.setText("Chờ xác nhận");
            txtTrangThai_HoaDon.setTextColor(Color.RED);
        }else {
            txtTrangThai_HoaDon.setText("Đã thanh toán");
            txtTrangThai_HoaDon.setTextColor(Color.GREEN);
        }
        anhthanhtoan=hoaDon.getAnhThanhToan();
        Bitmap bitmap = BitmapFactory.decodeByteArray(anhthanhtoan,0,anhthanhtoan.length);
        imgAnh.setImageBitmap(bitmap);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return v;
    }
}
