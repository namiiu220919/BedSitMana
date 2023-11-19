package com.example.bedsitmana.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bedsitmana.Activity.phong_Activity;
import com.example.bedsitmana.R;
import com.example.bedsitmana.model.PhongTro;

import java.util.ArrayList;

public class Phong_Adapter extends ArrayAdapter<PhongTro> {
    private Context context;
    phong_Activity phong_activity;
    private ArrayList<PhongTro> list;
    TextView txtPhong, txtXemHopDong, txtGia, txtTienNghi,txtCoSo_Phong,txtTinhTrang,txtma;
    ImageView btnDelete;

    public Phong_Adapter(@NonNull Context context, phong_Activity phong_activity, ArrayList<PhongTro> list) {
        super(context, 0,list);
        this.context = context;
        this.phong_activity = phong_activity;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_phong, null);
        }

        final PhongTro phongTro = list.get(position);
        if (phongTro != null) {

            txtPhong = v.findViewById(R.id.txtPhong);
            btnDelete=v.findViewById(R.id.btnDelete);

            txtGia = v.findViewById(R.id.txtGia);
            txtTienNghi = v.findViewById(R.id.txtTienNghi);
            txtCoSo_Phong = v.findViewById(R.id.txtLoaiPhong_Phong);
            txtTinhTrang = v.findViewById(R.id.txtTinhTrang);


            txtPhong.setText("Phòng: " + phongTro.getTenPhong());
            txtGia.setText("Giá: " + phongTro.getGia());
            txtTienNghi.setText("Tiện nghi: " + phongTro.getTienNghi());
            txtCoSo_Phong.setText("Cơ sở: " + phongTro.getTenCS());
            if (phongTro.getTrangThai() == 1) {
                txtTinhTrang.setText("Đã cho thuê");
                txtTinhTrang.setTextColor(Color.GREEN);
            } else {
                txtTinhTrang.setText("Đang trống");
                txtTinhTrang.setTextColor(Color.RED);
            }
        }

        return v;
    }
}
