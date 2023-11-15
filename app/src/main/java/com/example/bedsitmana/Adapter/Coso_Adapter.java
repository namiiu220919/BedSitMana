package com.example.bedsitmana.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bedsitmana.Activity.loaiPhong_Activity;
import com.example.bedsitmana.R;
import com.example.bedsitmana.model.Coso;

import java.util.ArrayList;

public class Coso_Adapter extends ArrayAdapter<Coso> {
    TextView txtCoso,txtDiaChi,txtPhiDV,txtGiaDien,txtGiaNuoc;
    Button btnXoa;
private  Context context;
private  ArrayList<Coso> list;
loaiPhong_Activity coSo_activity;


    public Coso_Adapter(@NonNull Context context,ArrayList<Coso> list, loaiPhong_Activity coSo_activity) {
        super(context,0 ,list);
        this.context = context;
        this.list = list;
        this.coSo_activity = coSo_activity;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            v=inflater.inflate(R.layout.item_coso,null);
        }
        final Coso coso = list.get(position);
        if(coso!=null){
            txtCoso = v.findViewById(R.id.txtCoSo);
            txtDiaChi = v.findViewById(R.id.txtDiaChi);
            txtPhiDV = v.findViewById(R.id.txtPhiDichVu);
            txtGiaDien = v.findViewById(R.id.txtGiaDien);
            txtGiaNuoc = v.findViewById(R.id.txtGiaNuoc);

            txtCoso.setText("Cơ sở: "+coso.getTenCoso());
            txtDiaChi.setText("Địa chỉ: "+coso.getDiaChi());
            txtPhiDV.setText("Phí dịch vụ: "+coso.getPhiDichVu()+"/người");
            txtGiaDien.setText("Giá điện: "+coso.getGiaDien()+"/số");
            txtGiaNuoc.setText("Giá nước: "+coso.getGiaNuoc()+"/người");
        }
        return v;
    }
}
