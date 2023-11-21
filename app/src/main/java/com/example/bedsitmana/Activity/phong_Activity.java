package com.example.bedsitmana.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bedsitmana.Adapter.LoaiPhongSpinnerAdapter;
import com.example.bedsitmana.Adapter.Phong_Adapter;
import com.example.bedsitmana.Dao.LoaiPhongDao;
import com.example.bedsitmana.Dao.phongTroDao;
import com.example.bedsitmana.R;
import com.example.bedsitmana.model.LoaiPhong;
import com.example.bedsitmana.model.PhongTro;

import java.util.ArrayList;

public class phong_Activity extends AppCompatActivity {
    ListView lstPhong;
    ArrayList<PhongTro> list;
    ArrayList<LoaiPhong> list_lp;
    Phong_Adapter adapter;
    PhongTro item;
    phongTroDao dao;
    ImageView btnAdd;
    EditText edtmaPhong, edttenPhong, edtGia, edtTienNghi;
    Button btnHuy, btnXacNhan;
    Spinner spinner;
    int position, maLoaiPhong;
    CheckBox chk;
    LoaiPhongDao dao_lp;
    LoaiPhong item_lp;
    LoaiPhongSpinnerAdapter spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phong);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Phòng Trọ");

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_back);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        lstPhong = findViewById(R.id.lstPhongTro);
        dao = new phongTroDao(phong_Activity.this);
        btnAdd = findViewById(R.id.btnadd_toolbar);
        capNhapLv();
//        lstPhong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                PhongTro pp = list.get(i);
//                int maPhong = pp.getMaPhong();
//
//                Intent intent = new Intent(phong_Activity.this, hopDong_Activity.class);
//                intent.putExtra("maphong", maPhong);
//                startActivity(intent);
//            }
//        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          opendialog(phong_Activity.this, 0);
                                      }
                                  }
        );
        lstPhong.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = list.get(i);
                opendialog(phong_Activity.this, 1);
                return false;
            }
        });
    }

    public void opendialog(Context context, int type) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_phong);
        edtmaPhong = dialog.findViewById(R.id.edtMaPhong);
        edtTienNghi = dialog.findViewById(R.id.edtTienNghi);
        edttenPhong = dialog.findViewById(R.id.edtTenPhong);
        edtGia = dialog.findViewById(R.id.edtGia);
        chk = dialog.findViewById(R.id.chkDaChoThue);
        spinner = dialog.findViewById(R.id.spnLoaiPhong);
        btnHuy = dialog.findViewById(R.id.btnHuy);
        btnXacNhan = dialog.findViewById(R.id.btnXacNhan);
        chk.setVisibility(View.GONE);
        list_lp = new ArrayList<LoaiPhong>();
        dao_lp = new LoaiPhongDao(context);
        list_lp = (ArrayList<LoaiPhong>) dao_lp.getAll();
        spinnerAdapter = new LoaiPhongSpinnerAdapter(context, list_lp);
        //
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maLoaiPhong = list_lp.get(position).getMaLoaiPhong();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        edtmaPhong.setEnabled(false);
        if (type != 0) {
            edtmaPhong.setText(item.getMaPhong() + "");
            edttenPhong.setText(item.getTenPhong() + "");
            edtTienNghi.setText(item.getTenPhong() + "");
            edtGia.setText(item.getGia() + "");
            if (item.getTrangThai() == 1) {
                chk.setChecked(true);
            } else {
                chk.setChecked(false);
            }
            for (int i = 0; i < list_lp.size(); i++) {
                if (item.getMaLoai() == (list_lp.get(i).getMaLoaiPhong())) {
                    position = i;
                }
                Log.i("zzzzzzzzzzzz", "posPhong: " + position);
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
                public void onClick(View view) {
                    if (TextUtils.isEmpty(edttenPhong.getText().toString()) || TextUtils.isEmpty(edtTienNghi.getText().toString()) || TextUtils.isEmpty(edtGia.getText().toString())) {
                        Toast.makeText(context, "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    try {
                        int g = Integer.parseInt(edtGia.getText().toString());
                        if (g <= 0) {
                            Toast.makeText(context, "Giá phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } catch (Exception e) {
                        Toast.makeText(context, "Giá phải là số", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    item = new PhongTro();
                    item.setTenPhong(edttenPhong.getText().toString());
                    item.setTienNghi(edtTienNghi.getText().toString());
                    item.setGia(Integer.parseInt(edtGia.getText().toString()));
                    item.setMaLoai(maLoaiPhong);
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
                        item.setMaPhong(Integer.parseInt(edtmaPhong.getText().toString()));
                        if (dao.update(item) > 0) {
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    capNhapLv();
                    dialog.dismiss();
                }
            });
        dialog.show();

    }

    public void xoa(String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cảnh báo");
        builder.setIcon(R.drawable.baseline_warning_24);
        builder.setMessage("Bạn có chắc chắn muốn xoá");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dao.delete(Id);
                capNhapLv();
                dialogInterface.cancel();
                Toast.makeText(phong_Activity.this, "Xóa thành công ", Toast.LENGTH_SHORT).show();
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

    public void capNhapLv() {

        list = (ArrayList<PhongTro>) dao.getAll();
        adapter = new Phong_Adapter(phong_Activity.this, this, list);
        lstPhong.setAdapter(adapter);
    }
    public void xemHD(int i){
        PhongTro pp = list.get(i);
        int maPhong = pp.getMaPhong();

        Intent intent = new Intent(phong_Activity.this, XemhopDong_Activity.class);
        intent.putExtra("maphong", maPhong);
        startActivity(intent);
    }

}