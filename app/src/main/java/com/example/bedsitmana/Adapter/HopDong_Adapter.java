package com.example.bedsitmana.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bedsitmana.Activity.hopDong_Activity;
import com.example.bedsitmana.R;
import com.example.bedsitmana.model.HopDong;
import com.example.bedsitmana.model.LoaiPhong;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HopDong_Adapter extends ArrayAdapter<HopDong> {
    EditText edtma_hd, edtTenkh_hd, edtSdt_hd, edtCCCD_hd, edtDiaChi_hd, edtNgayki_hd, edtSothang_hd, edtLoaiPhong_hd, edtSoPhong_hd, edtTienCoc_hd, edtTienPhong_hd, edtSonguoi_hd, edtSoxe_hd, edtGhiChu_hd;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private Context context;
    private ArrayList<HopDong> list;
    hopDong_Activity hopDong_activity;

    public HopDong_Adapter(@NonNull Context context, ArrayList<HopDong> list, hopDong_Activity hopDong_activity) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
        this.hopDong_activity = hopDong_activity;
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
            edtLoaiPhong_hd = v.findViewById(R.id.edtLoaiPhong_hd);
            edtSoPhong_hd = v.findViewById(R.id.edtSoPhong_hd);
            edtTienCoc_hd = v.findViewById(R.id.edtTienCoc_hd);
            edtTienPhong_hd = v.findViewById(R.id.edtTienPhong_hd);
            edtSonguoi_hd = v.findViewById(R.id.edtSonguoi_hd);
            edtSoxe_hd = v.findViewById(R.id.edtSoxe_hd);
            edtGhiChu_hd = v.findViewById(R.id.edtGhiChu_hd);
//            edtmahd_hd.setEnabled(false);

            edtma_hd.setText(hd.getMaPhong()+"");
            edtTenkh_hd.setText(hd.getTenNguoiThue());
            edtSdt_hd.setText(hd.getSdt());
            edtCCCD_hd.setText(hd.getCCCD()+"");
            edtDiaChi_hd.setText(hd.getThuongTru());
//            edtNgayki_hd.setText(sdf.format(hd.getNgayKy()));
            edtSothang_hd.setText(hd.getThoiHan()+"");
            edtLoaiPhong_hd.setText(hd.getTenLoai());
            edtSoPhong_hd.setText(hd.getTenPhong());
            edtTienCoc_hd.setText(hd.getTienCoc()+"");
            edtTienPhong_hd.setText(hd.getGiaTien()+"");
            edtSonguoi_hd.setText(hd.getSoNguoi()+"");
            edtSoxe_hd.setText(hd.getSoXe()+"");
            edtGhiChu_hd.setText(hd.getGhiChu());
        }
        return v;
    }
}
