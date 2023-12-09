package com.example.bedsitmana.Database;





import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import com.example.bedsitmana.R;

import java.io.ByteArrayOutputStream;
import java.sql.SQLDataException;
import java.text.SimpleDateFormat;

public class DbHelper extends SQLiteOpenHelper {
    static final String dbName="BEDSIT";
    static final int dbVersion=1;
    Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public DbHelper(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Tạo bảng KeToan
        String createTableKeToan="create table KeToan(" +
                "maKeToan TEXT PRIMARY KEY," +
                "tenKeToan TEXT NOT NULL," +
                "matKhauKT TEXT NOT NULL)";
        sqLiteDatabase.execSQL(createTableKeToan);

        //Tạo bảng CoSo
        String createTableLoaiPhong="create table LoaiPhong(" +
                "maLoai INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenLoai TEXT NOT NULL," +
                "phiDichVu INTEGER NOT NULL," +
                "giaDien INTEGER NOT NULL," +
                "giaNuoc INTEGER NOT NULL)";
        sqLiteDatabase.execSQL(createTableLoaiPhong);
        //Thêm dữ liệu bảng CoSo
        sqLiteDatabase.execSQL("insert into LoaiPhong(tenLoai,phiDichVu,giaDien,giaNuoc) values" +
                "('Full option',100000,3500,100000)," +
                "('Đồ cơ bản',100000,3500,100000)");

        //Tạo bảng PhongTro
        String createTablePhongTro = "create table PhongTro(" +
                "maPhong INTEGER PRIMARY KEY AUTOINCREMENT," +
                "maLoai INTEGER REFERENCES LoaiPhong(maLoai)," +
                "tenPhong TEXT NOT NULL," +
                "giaTien INTEGER NOT NULL," +
                "tienNghi TEXT NOT NULL," +
                "trangThai INTEGER NOT NULL)";
        sqLiteDatabase.execSQL(createTablePhongTro);

        //Thêm dữ liệu bảng PhongTro
        sqLiteDatabase.execSQL("insert into PhongTro(maLoai,tenPhong,giaTien,tienNghi,trangThai) values" +
                "(1,'P102',3500000,'Điều hoà, Nóng lạnh, Tủ lạnh, Tủ quần áo',0)," +
                "(2,'P202',3200000,'Điều hoà, Nóng lạnh, Tủ lạnh, Tủ quần áo',0)");

        //Tạo bảng HoaDon
        String createTableHoaDon="create table HoaDon("+
                "maHoaDon INTEGER PRIMARY KEY AUTOINCREMENT," +
                "sdt TEXT ," +
                "ngayTao DATE ," +
                "soDien INTEGER," +
                "donGiaDien INTEGER," +
                "soNguoi INTEGER," +
                "donGiaNuoc INTEGER," +
                "phiDichVu INTEGER," +
                "ghiChu TEXT ," +
                "tienPhong INTEGER," +
                "anhThanhToan BLOB," +
                "trangThai INTEGER ," +
                "maPhong INTEGER REFERENCES PhongTro(maPhong)," +
                "maNguoiThue TEXT REFERENCES NguoiThue(maNguoiThue))";
        sqLiteDatabase.execSQL(createTableHoaDon);

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.img_camera);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] imageData = stream.toByteArray();






        //Thêm dữ liệu bảng HopDong

        //Tạo bảng SuCo
        String createTableSuCo = "create table SuCo(" +
                "maSuCo INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenSuCo TEXT NOT NULL," +
                "noiDung TEXT NOT NULL," +
                "trangThai INTEGER NOT NULL," +
                "maPhong INTEGER REFERENCES PhongTro(maPhong)," +
                "maNguoiThue INTEGER REFERENCES NguoiThue(maNguoiThue))";
        sqLiteDatabase.execSQL(createTableSuCo);

        sqLiteDatabase.execSQL("insert into SuCo(tenSuCo, noiDung, trangThai, maPhong, maNguoiThue) values" +
                "('Điện','Hỏng bóng đèn',0,1,1)," +
                "('Nước','Vỡ ống nước',0,1,1)");

        //Tạo bảng ngân hàng
        String createTableNganHang = "create table NganHang(" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenTKNganHang TEXT ," +
                "tenNganHang TEXT ," +
                "STK TEXT," +
                "HinhAnh BLOB)";
        sqLiteDatabase.execSQL(createTableNganHang);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
