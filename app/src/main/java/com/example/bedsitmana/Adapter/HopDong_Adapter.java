package com.example.bedsitmana.Adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.bedsitmana.Activity.XemhopDong_Activity;
import com.example.bedsitmana.Activity.loaiPhong_Activity;
import com.example.bedsitmana.Activity.phong_Activity;
import com.example.bedsitmana.Dao.hopDongDao;
import com.example.bedsitmana.MainActivity;
import com.example.bedsitmana.R;
import com.example.bedsitmana.model.HopDong;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HopDong_Adapter extends ArrayAdapter<HopDong> {
    EditText edtma_hd, edtTenkh_hd, edtSdt_hd, edtCCCD_hd, edtDiaChi_hd, edtNgayki_hd, edtSothang_hd, edtSoPhong_hd, edtTienCoc_hd, edtTienPhong_hd, edtSonguoi_hd, edtSoxe_hd, edtGhiChu_hd;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private Context context;
    private ArrayList<HopDong> list;
    XemhopDong_Activity hopDong_activity;
    Button btnKetThuc,btnCapNhap;
    hopDongDao dao;
    private Phong_Adapter phongAdapter;

    public HopDong_Adapter(@NonNull Context context, ArrayList<HopDong> list, XemhopDong_Activity hopDong_activity) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
        this.hopDong_activity = hopDong_activity;
        dao = new hopDongDao(context);
    }
    public void setPhongAdapter(Phong_Adapter adapter) {
        this.phongAdapter = adapter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            v=inflater.inflate(R.layout.item_hopdong,null);
        }
        final HopDong hd = list.get(position);
        if(hd!=null){
            edtma_hd = v.findViewById(R.id.edtmaPhong_hd);
            edtTenkh_hd = v.findViewById(R.id.edtTenkh_hd);
            edtSdt_hd = v.findViewById(R.id.edtSdt_hd);
            edtCCCD_hd = v.findViewById(R.id.edtCCCD_hd);
            edtDiaChi_hd =v.findViewById(R.id.edtDiaChi_hd);
            edtNgayki_hd = v.findViewById(R.id.edtNgayki_hd);
            edtSothang_hd = v.findViewById(R.id.edtSothang_hd);
            edtSoPhong_hd = v.findViewById(R.id.edtSoPhong_hd);
            edtTienCoc_hd = v.findViewById(R.id.edtTienCoc_hd);
            edtTienPhong_hd = v.findViewById(R.id.edtTienPhong_hd);
            edtSonguoi_hd = v.findViewById(R.id.edtSonguoi_hd);
            edtSoxe_hd = v.findViewById(R.id.edtSoxe_hd);
            edtGhiChu_hd = v.findViewById(R.id.edtGhiChu_hd);
            btnCapNhap=v.findViewById(R.id.btnCapNhat_hd);
            btnKetThuc=v.findViewById(R.id.btnKetThuc_hd);
            edtma_hd.setEnabled(false);
            edtTenkh_hd.setEnabled(false);
            edtSdt_hd.setEnabled(false);
            edtCCCD_hd.setEnabled(false);
            edtDiaChi_hd.setEnabled(false);
            edtSoPhong_hd.setEnabled(false);
            edtTienPhong_hd.setEnabled(false);
            edtNgayki_hd.setEnabled(false);

            edtma_hd.setText(hd.getMaHopDong()+"");
            edtTenkh_hd.setText(hd.getTenNguoiThue());
            edtSdt_hd.setText(hd.getSdt());
            edtCCCD_hd.setText(hd.getCCCD()+"");
            edtDiaChi_hd.setText(hd.getThuongTru());
            edtNgayki_hd.setText(sdf.format(hd.getNgayKy()));
            edtSothang_hd.setText(hd.getThoiHan()+"");
            edtSoPhong_hd.setText(hd.getTenPhong());
            edtTienCoc_hd.setText(hd.getTienCoc()+"");
            edtTienPhong_hd.setText(hd.getGiaTien()+"");
            edtSonguoi_hd.setText(hd.getSoNguoi()+"");
            edtSoxe_hd.setText(hd.getSoXe()+"");
            edtGhiChu_hd.setText(hd.getGhiChu());
            btnCapNhap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
        btnKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hopDong_activity.xoa(String.valueOf(hd.getMaHopDong()));
            }
        });
        return v;
    }
}
