package com.example.bedsitmana.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bedsitmana.Activity.suCo_Activity;
import com.example.bedsitmana.Dao.suCoDao;
import com.example.bedsitmana.R;
import com.example.bedsitmana.model.suCo;

import java.util.ArrayList;

public class SuCo_Adapter extends ArrayAdapter<suCo> {
    private Context context;
    suCo_Activity suCo_activity;
    private ArrayList<suCo> list;
    suCoDao sCDao;

    TextView txtSuCo, txtMoTa,txtPhong_SuCo, txtTinhTrang_SuCo;


    public SuCo_Adapter(@NonNull Context context, suCo_Activity suCo_activity, ArrayList<suCo> list) {
        super(context,0,list);
        this.context = context;
        this.suCo_activity = suCo_activity;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if( v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_suco, null);
        }
        final suCo suCo = list.get(position);
        if(suCo != null){
            txtSuCo = v.findViewById(R.id.txtSuCo);
            txtMoTa = v.findViewById(R.id.txtMoTa);
            txtPhong_SuCo = v.findViewById(R.id.txtPhong_SuCo);
            txtTinhTrang_SuCo = v.findViewById(R.id.txtTinhTrang_SuCo);

            txtSuCo.setText("Loại sự cố: " + suCo.getTenSuCo());
            txtMoTa.setText("Mô tả: " + suCo.getNoiDung());
            txtPhong_SuCo.setText("Phòng: " +suCo.getMaPhong());
            if(suCo.getTrangThai() == 0){
                txtTinhTrang_SuCo.setText("Chưa sửa chữa");
                txtTinhTrang_SuCo.setTextColor(Color.RED);
            }else{
                txtTinhTrang_SuCo.setText("Đã sửa chữa");
                txtTinhTrang_SuCo.setTextColor(Color.GREEN);
            }
        }
        return v;
    }
}
