package com.example.bedsitmana.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bedsitmana.Adapter.Phong_Adapter;
import com.example.bedsitmana.Adapter.SPPhong_Adapter;
import com.example.bedsitmana.Adapter.SuCo_Adapter;
import com.example.bedsitmana.Dao.phongTroDao;
import com.example.bedsitmana.Dao.suCoDao;
import com.example.bedsitmana.R;
import com.example.bedsitmana.model.PhongTro;
import com.example.bedsitmana.model.suCo;

import java.util.ArrayList;

public class suCo_Activity extends AppCompatActivity {

    ListView lstSuCo;
    ArrayList<suCo> list;
    ArrayList<PhongTro> list_phong;
    SuCo_Adapter adapter;
    suCo item;
    suCoDao dao;
    ImageView btnAdd;
    EditText edtMaSuCo, edtLoaiSuCo, edtMoTa;
    Button btnHuy, btnXacNhan;
    Spinner spinner;
    int position, maPhong;
    CheckBox chk;
    phongTroDao dao_phong;
    PhongTro item_phong;
    SPPhong_Adapter spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_su_co);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sự cố");

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_back);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        lstSuCo = findViewById(R.id.lstSuCo);
        dao = new suCoDao(suCo_Activity.this);
        btnAdd = findViewById(R.id.btnadd_toolbar);
        capnhatLv();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendialog(suCo_Activity.this, 0);
            }
        });
        lstSuCo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = list.get(i);
                opendialog(suCo_Activity.this, 1);
                return false;
            }
        });
    }

    public void opendialog(Context context, int type){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_su_co);
        edtMaSuCo = dialog.findViewById(R.id.edtMaSuCo);
        edtLoaiSuCo = dialog.findViewById(R.id.edtLoaiSuCo);
        edtMoTa = dialog.findViewById(R.id.edtMota);
        spinner = dialog.findViewById(R.id.spnPhong_SuCo);
        chk = dialog.findViewById(R.id.chkDaSua);
        btnHuy = dialog.findViewById(R.id.btnHuy);
        btnXacNhan = dialog.findViewById(R.id.btnXacNhan);
        list_phong = new ArrayList<PhongTro>();
        dao_phong = new phongTroDao(context);
        list_phong = (ArrayList<PhongTro>) dao_phong.getAll();
        spinnerAdapter = new SPPhong_Adapter(context,list_phong);
        //
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maPhong = list_phong.get(position).getMaPhong();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        edtMaSuCo.setEnabled(false);
        edtLoaiSuCo.setInputType(InputType.TYPE_NULL);
        edtLoaiSuCo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] loaiSuCo ={"Điện","Nước","Khác"};
                AlertDialog.Builder builder = new AlertDialog.Builder(suCo_Activity.this);
                builder.setTitle("Chọn Loại sự cố");
                builder.setItems(loaiSuCo, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        edtLoaiSuCo.setText(loaiSuCo[i]);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        if(type != 0){
            edtMaSuCo.setText(item.getMaSuCo() +"");
            edtLoaiSuCo.setText(item.getTenSuCo() + "");
            edtMoTa.setText(item.getNoiDung() + "");
            if(item.getTrangThai() == 1){
                chk.setChecked(true);
            } else {
                chk.setChecked(false);
            }
            for(int i =0; i< list_phong.size();i++){
                if(item.getMaPhong() == (list_phong.get(i).getMaPhong())){
                    position = i;
                }
                spinner.setSelection(position);
            }

        }
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edtLoaiSuCo.getText().toString()) || TextUtils.isEmpty(edtMoTa.getText().toString())){
                    Toast.makeText(context, "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                item = new suCo();
                item.setTenSuCo(edtLoaiSuCo.getText().toString());
                item.setNoiDung(edtMoTa.getText().toString());
                item.setMaPhong(maPhong);
                if (chk.isChecked()) {
                    item.setTrangThai(1);
                } else {
                    item.setTrangThai(0);
                }
                if (type == 0) {
                    if (dao.insert(item) > 0) {
                        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    item.setMaSuCo(Integer.parseInt(edtMaSuCo.getText().toString()));
                    if (dao.update(item) > 0) {
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }

                capnhatLv();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void capnhatLv() {

        list = (ArrayList<suCo>) dao.getAll();
        adapter = new SuCo_Adapter(suCo_Activity.this, this, list);
        lstSuCo.setAdapter(adapter);
    }
}