package com.example.bedsitmana.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.SQLDataException;
import java.text.SimpleDateFormat;

public class DbHelper extends SQLiteOpenHelper {
    static final String dbName="BEDSIT";
    static final int dbVersion=1;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public DbHelper(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
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
                "(1,'P102',3500000,'Điều hoà, Nóng lạnh, Tủ lạnh, Tủ quần áo',1)," +
                "(1,'P202',3200000,'Điều hoà, Nóng lạnh, Tủ lạnh, Tủ quần áo',0)");

        //Tạo bảng HoaDon
        String createTableHoaDon="create table HoaDon("+
            "maHoaDon INTEGER PRIMARY KEY AUTOINCREMENT," +
                "soDien INTEGER NOT NULL," +
                "soNuoc INTEGER NOT NULL," +
                "phiDichVu INTEGER NOT NULL," +
                "trangThai INTEGER NOT NULL," +
                "maPhong INTEGER REFERENCES PhongTro(maPhong)," +
                "maNguoiThue TEXT REFERENCES NguoiThue(maNguoiThue)," +
                "maKeToan TEXT REFERENCES KeToan(maKeToan))";
        sqLiteDatabase.execSQL(createTableHoaDon);

        //Tạo bảng NguoiThue
        String createTableNguoiThue ="create table NguoiThue(" +
                "maNguoiThue TEXT PRIMARY KEY," +
                "matKhauNT TEXT NOT NULL," +
                "tenNguoiThue TEXT NOT NULL," +
                "thuongTru TEXT NOT NULL," +
                "sdt TEXT NOT NULL," +
                "CCCD INTEGER NOT NULL," +
                "namSinh INTEGER NOT NULL," +
                "gioiTinh TEXT NOT NULL)";
        sqLiteDatabase.execSQL(createTableNguoiThue);

        sqLiteDatabase.execSQL("insert into NguoiThue values('quynh01','quynh','Quỳnh','Bắc Giang','3456789987',847837487,2004,'Nam')," +
                "('huy','huy','Huy','Hải Dương','123456789',123456789,2004,'Nam'),"+
                "('quynh02','quynh','Quỳnh','Bắc Giang','3456789987',847837487,2004,'Nam')");

        //Tạo bảng HopDong
        String createTableHopDong = "create table HopDong(" +
                "maHopDong INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenNguoiThue TEXT NOT NULL," +
                "sdt TEXT NOT NULL," +
                "CCCD INTEGER NOT NULL," +
                "thuongTru TEXT NOT NULL," +
                "ngayKy DATE NOT NULL," +
                "thoiHan INTEGER NOT NULL," +
                "tenLoai TEXT NOT NULL," +
                "tenPhong TEXT NOT NULL," +
                "tienCoc INTEGER NOT NULL," +
                "giaTien INTEGER NOT NULL," +
                "soNguoi INTEGER NOT NULL," +
                "soXe INTEGER NOT NULL," +
                "ghiChu TEXT NOT NULL," +
                "maNguoiThue TEXT REFERENCES NguoiThue(maNguoiThue)," +
                "maPhong INTEGER REFERENCES PhongTro(maPhong))";
        sqLiteDatabase.execSQL(createTableHopDong);
        //Thêm dữ liệu bảng HopDong
        sqLiteDatabase.execSQL("insert into HopDong(tenNguoiThue,sdt,CCCD,thuongTru,ngayKy,thoiHan,tenLoai,tenPhong,tienCoc,giaTien,soNguoi,soXe,ghiChu,maNguoiThue,maPhong) values" +
                "('Quỳnh','847837487',847837487,'Bắc Giang','2023/11/17',6,'Full option','P102',2000000,3000000,3,3,'non','quynh01',1),"+
        "('Huy','123456789',123456789,'Hải Dương','2023/11/17',6,'Full option','P202',2000000,3000000,3,3,'non','huy',2)");

        //Tạo bảng SuCo
        String createTableSuCo = "create table SuCo(" +
                "maSuCo INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenSuCo TEXT NOT NULL," +
                "noiDung TEXT NOT NULL," +
                "trangThai INTEGER NOT NULL," +
                "maPhong INTEGER REFERENCES PhongTro(maPhong)," +
                "maNguoiThue INTEGER REFERENCES NguoiThue(maNguoiThue))";
        sqLiteDatabase.execSQL(createTableSuCo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
