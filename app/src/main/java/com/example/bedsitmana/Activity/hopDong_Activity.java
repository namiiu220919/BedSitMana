    package com.example.bedsitmana.Activity;

    import androidx.appcompat.app.AppCompatActivity;

    import android.os.Bundle;
    import android.util.Log;
    import android.widget.EditText;
    import android.widget.Toast;

    import com.example.bedsitmana.Dao.hopDongDao;
    import com.example.bedsitmana.R;
    import com.example.bedsitmana.model.HopDong;

    import java.text.SimpleDateFormat;
    import java.util.ArrayList;

    public class hopDong_Activity extends AppCompatActivity {
        hopDongDao dao;
        ArrayList<HopDong> list;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        EditText edtmahd_hd, edtTenkh_hd, edtSdt_hd, edtCCCD_hd, edtDiaChi_hd, edtNgayki_hd, edtSothang_hd, edtLoaiPhong_hd, edtSoPhong_hd, edtTienCoc_hd, edtTienPhong_hd, edtSonguoi_hd, edtSoxe_hd, edtGhiChu_hd;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_hop_dong);

            edtTenkh_hd = findViewById(R.id.edtTenkh_hd);
            edtSdt_hd = findViewById(R.id.edtSdt_hd);
            edtCCCD_hd = findViewById(R.id.edtCCCD_hd);
            edtDiaChi_hd = findViewById(R.id.edtDiaChi_hd);
            edtNgayki_hd = findViewById(R.id.edtNgayki_hd);
            edtSothang_hd = findViewById(R.id.edtSothang_hd);
            edtLoaiPhong_hd = findViewById(R.id.edtLoaiPhong_hd);
            edtSoPhong_hd = findViewById(R.id.edtSoPhong_hd);
            edtTienCoc_hd = findViewById(R.id.edtTienCoc_hd);
            edtTienPhong_hd = findViewById(R.id.edtTienPhong_hd);
            edtSonguoi_hd = findViewById(R.id.edtSonguoi_hd);
            edtSoxe_hd = findViewById(R.id.edtSoxe_hd);
            edtGhiChu_hd = findViewById(R.id.edtGhiChu_hd);

            int maphong = getIntent().getIntExtra("maphong", -1);
            dao = new hopDongDao(hopDong_Activity.this);
            list = (ArrayList<HopDong>) dao.getAll();
            HopDong hopDong = null;

                for (HopDong hd : list) {
                    if (hd.getMaPhong() == maphong) {
                        hopDong = hd;
                        break;
                    }
                }


            if (hopDong != null) {
                // Hiển thị thông tin hợp đồng tương ứng
                hienThiHopDong(hopDong);
            } else {
                // Xử lý khi không tìm thấy hợp đồng
                Toast.makeText(this, "Chưa có hợp đồng", Toast.LENGTH_SHORT).show();
                return;
            }

    //        edtTenkh_hd.setText(item.getTenNguoiThue());
    //        edtSdt_hd.setText(item.getSdt());
    //        edtCCCD_hd.setText(item.getCCCD());
    //        edtDiaChi_hd.setText(item.getThuongTru());
    //        edtNgayki_hd.setText(sdf.format(item.getNgayKy()));
    //        edtSothang_hd.setText(item.getThoiHan());
    //        edtLoaiPhong_hd.setText(item.getTenLoai());
    //        edtSoPhong_hd.setText(item.getTenPhong());
    //        edtTienCoc_hd.setText(item.getTienCoc());
    //        edtTienPhong_hd.setText(item.getGiaTien());
    //        edtSonguoi_hd.setText(item.getSoNguoi());
    //        edtSoxe_hd.setText(item.getSoXe());
    //        edtGhiChu_hd.setText(item.getGhiChu());

        }

        private void hienThiHopDong(HopDong hopDong) {
            edtTenkh_hd.setText(hopDong.getTenNguoiThue());
            edtSdt_hd.setText(hopDong.getSdt());
            edtCCCD_hd.setText(hopDong.getCCCD());
            edtDiaChi_hd.setText(hopDong.getThuongTru());
            edtNgayki_hd.setText(sdf.format(hopDong.getNgayKy()));
            edtSothang_hd.setText(hopDong.getThoiHan());
            edtLoaiPhong_hd.setText(hopDong.getTenLoai());
            edtSoPhong_hd.setText(hopDong.getTenPhong());
            edtTienCoc_hd.setText(hopDong.getTienCoc());
            edtTienPhong_hd.setText(hopDong.getGiaTien());
            edtSonguoi_hd.setText(hopDong.getSoNguoi());
            edtSoxe_hd.setText(hopDong.getSoXe());
            edtGhiChu_hd.setText(hopDong.getGhiChu());
        }
    }