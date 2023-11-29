package com.example.bedsitmana.Adapter;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bedsitmana.Activity.ThanhToan_Activity;
import com.example.bedsitmana.Activity.hoaDon_Activity;
import com.example.bedsitmana.Activity.hopDong_Activity;
import com.example.bedsitmana.Dao.NganHangDao;
import com.example.bedsitmana.Dao.hoaDonDao;
import com.example.bedsitmana.Dao.nguoiThueDao;
import com.example.bedsitmana.Dao.phongTroDao;
import com.example.bedsitmana.R;
import com.example.bedsitmana.model.HoaDon;
import com.example.bedsitmana.model.NganHang;
import com.example.bedsitmana.model.NguoiThue;
import com.example.bedsitmana.model.PhongTro;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HoaDon_Adapter extends ArrayAdapter<HoaDon> {
    TextView txtPhong_HoaDon,txtTenTruongPhong_HoaDon,txtNgayTao_HoaDon,txtGhiChu_HoaDon,txtTongHoaDon,txtTrangThai_HoaDon;
    ImageView btnDelete, imgAnh,imgAnhQR_tt, imgAnhThanhToan,imgXN;
    private Context context;
    private ArrayList<HoaDon> list;
    hoaDon_Activity hoaDonActivity;
    phongTroDao ptDao;
    Spinner spNganHang;
    NganHangSpinner_Adapter nganHangSpinnerAdapter;
    nguoiThueDao ntDao;
    EditText edtmaHoaDon;
    hoaDonDao hoadonDao;
    byte[] anhthanhtoan;
    byte[] anhqr;
    int maNganHang;
    Button btnChonAnhtt,btnXacNhantt,btnHuytt;
    Dialog dialog;
    ArrayList<NganHang> listnh;
    NganHangDao nhDao;
    HoaDon hoaDon;
    final int REQUEST_CODE_FOLDER = 456;
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
        hoaDon = list.get(position);
        if (hoaDon!=null){
            txtPhong_HoaDon=v.findViewById(R.id.txtPhong_HoaDon);
            txtTenTruongPhong_HoaDon=v.findViewById(R.id.txtTenTruongPhong_HoaDon);
            txtNgayTao_HoaDon=v.findViewById(R.id.txtNgayTao_HoaDon);
            txtGhiChu_HoaDon=v.findViewById(R.id.txtGhiChu_HoaDon);
            txtTongHoaDon=v.findViewById(R.id.txtTongHoaDon);
            txtTrangThai_HoaDon=v.findViewById(R.id.txtTrangThai_HoaDon);
            btnDelete=v.findViewById(R.id.btnDelete);
            imgAnh=v.findViewById(R.id.imgAnh);
            imgXN=v.findViewById(R.id.imgXN);
            txtTrangThai_HoaDon=v.findViewById(R.id.txtTrangThai_HoaDon);

            ptDao = new phongTroDao(context);
            PhongTro phongTro = ptDao.getID(String.valueOf(hoaDon.getMaPhong()));
            txtPhong_HoaDon.setText("Phòng: "+phongTro.getTenPhong());
            ntDao = new nguoiThueDao(context);
            NguoiThue nguoiThue = ntDao.getID(hoaDon.getMaNguoiThue());
            txtTenTruongPhong_HoaDon.setText("Tên trưởng phòng: "+nguoiThue.getTenNguoiThue());
            txtNgayTao_HoaDon.setText("Ngày: "+sdf.format(hoaDon.getNgayTao()));
            txtGhiChu_HoaDon.setText("Ghi chú: "+hoaDon.getGhiChu());
            int tong= 0;
            hoadonDao=new hoaDonDao(context);
            tong=hoadonDao.getTongTienDien(hoaDon.getMaHoaDon())+hoadonDao.getTongTienNuoc(hoaDon.getMaHoaDon())+hoaDon.getPhiDichVu()+hoaDon.getTienPhong();
            txtTongHoaDon.setText("Tổng: "+tong+"đ");

            if (hoaDon.getTrangThai()==0){
                txtTrangThai_HoaDon.setText("Thanh toán ngay");
                txtTrangThai_HoaDon.setTextColor(Color.GREEN);
                imgXN.setVisibility(View.GONE);
            }else if (hoaDon.getTrangThai()==1){
                txtTrangThai_HoaDon.setText("Chờ xác nhận");
                txtTrangThai_HoaDon.setTextColor(Color.RED);
            }else {
                txtTrangThai_HoaDon.setText("Đã thanh toán");
                txtTrangThai_HoaDon.setTextColor(Color.GREEN);
                imgXN.setVisibility(View.GONE);
            }
            anhthanhtoan=hoaDon.getAnhThanhToan();
            Bitmap bitmap = BitmapFactory.decodeByteArray(anhthanhtoan,0,anhthanhtoan.length);
            imgAnh.setImageBitmap(bitmap);


            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hoaDonActivity.xoa(String.valueOf(hoaDon.getMaHoaDon()));
                }
            });

            txtTrangThai_HoaDon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (hoaDon.getTrangThai()==0){
                        Intent intent = new Intent(context, ThanhToan_Activity.class);
                        intent.putExtra("mahoadon",hoaDon.getMaHoaDon());
                        context.startActivity(intent);
                    }
                }
            });
        }


        return v;
    }
    public void openDialogThanhToan(){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_thanhtoan);

        spNganHang=dialog.findViewById(R.id.spnNganHang);
        imgAnhQR_tt=dialog.findViewById(R.id.imgAnhQR_tt);
        imgAnhThanhToan=dialog.findViewById(R.id.imgAnhThanhToan);
        btnChonAnhtt=dialog.findViewById(R.id.btnChonAnhtt);
        btnXacNhantt=dialog.findViewById(R.id.btnXacNhantt);
        btnHuytt=dialog.findViewById(R.id.btnHuytt);
        edtmaHoaDon=dialog.findViewById(R.id.edtMaHoaDon);
        edtmaHoaDon.setVisibility(View.GONE);

        btnChonAnhtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                hoaDonActivity.startActivityForResult(intent,REQUEST_CODE_FOLDER);
//                ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_FOLDER);
            }
        });

        nhDao = new NganHangDao(context);
        listnh=new ArrayList<NganHang>();
        listnh= (ArrayList<NganHang>) nhDao.getAll();
        nganHangSpinnerAdapter = new NganHangSpinner_Adapter(context,listnh);
        spNganHang.setAdapter(nganHangSpinnerAdapter);
        spNganHang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maNganHang= listnh.get(i).getId();
                anhqr=listnh.get(i).getHinhAnh();
                Bitmap bitmap = BitmapFactory.decodeByteArray(anhqr,0,anhqr.length);
                imgAnhQR_tt.setImageBitmap(bitmap);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        edtmaHoaDon.setText(String.valueOf(hoaDon.getMaHoaDon()));

        btnHuytt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnXacNhantt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hoaDon=new HoaDon();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgAnhThanhToan.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                anhthanhtoan = byteArrayOutputStream.toByteArray();
                hoaDon.setAnhThanhToan(anhthanhtoan);
                hoaDon.setTrangThai(1);
                hoaDon.setMaHoaDon(Integer.parseInt(edtmaHoaDon.getText().toString()));
                if (hoadonDao.update(hoaDon)>0){
                    Toast.makeText(context, "Đã gửi", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
                }
                hoaDonActivity.capNhatLv();
                dialog.dismiss();
            }
        });





        dialog.show();
    }

}
