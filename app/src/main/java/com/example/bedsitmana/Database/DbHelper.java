package com.example.bedsitmana.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.SQLDataException;

public class DbHelper extends SQLiteOpenHelper {
    static final String dbName="BEDSIT";
    static final int dbVersion=1;
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
        String createTableCoSo="create table CoSo(" +
                "maCS INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenCS TEXT NOT NULL," +
                "diaChi TEXT NOT NULL," +
                "phiDichVu INTEGER NOT NULL," +
                "giaDien INTEGER NOT NULL," +
                "giaNuoc INTEGER NOT NULL)";
        sqLiteDatabase.execSQL(createTableCoSo);
        //Thêm dữ liệu bảng CoSo
        sqLiteDatabase.execSQL("insert into CoSo(tenCS,diaChi,phiDichVu,giaDien,giaNuoc) values" +
                "('Trịnh Văn Bô','Số 1 Đường Trịnh Văn Bô',100000,3500,100000)");

        //Tạo bảng PhongTro
        String createTablePhongTro = "create table PhongTro(" +
                "maPhong INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenCS INTEGER REFERENCES CoSo(tenCS)," +
                "tenPhong TEXT NOT NULL," +
                "tienNghi TEXT NOT NULL," +
                "trangThai INTEGER NOT NULL)";
        sqLiteDatabase.execSQL(createTablePhongTro);

        //Thêm dữ liệu bảng PhongTro
        sqLiteDatabase.execSQL("insert into PhongTro(tenCS,tenPhong,tienNghi,trangThai) values" +
                "('Trịnh Văn Bô','P102','Điều hoà, Nóng lạnh, Tủ lạnh, Tủ quần áo',1)," +
                "('Trịnh Văn Bô','P202','Điều hoà, Nóng lạnh, Tủ lạnh, Tủ quần áo',0)");

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
                "('quynh02','quynh','Quỳnh','Bắc Giang','3456789987',847837487,2004,'Nam')");

        //Tạo bảng HopDong
        String createTableHopDong = "create table HopDong(" +
                "maHopDong INTEGER PRIMARY KEY AUTOINCREMENT," +
                "giaTien INTEGER NOT NULL," +
                "thoiHan INTEGER NOT NULL," +
                "soNguoi INTEGER NOT NULL," +
                "soXe INTEGER NOT NULL," +
                "maPhong INTEGER REFERENCES PhongTro(maPhong)," +
                "maNguoiThue TEXT REFERENCES NguoiThue(maNguoiThue))";
        sqLiteDatabase.execSQL(createTableHopDong);

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
