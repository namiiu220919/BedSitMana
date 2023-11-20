package com.example.bedsitmana.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bedsitmana.Database.DbHelper;
import com.example.bedsitmana.model.NguoiThue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class nguoiThueDao {
    private SQLiteDatabase db;
    private Context context;

    public nguoiThueDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(NguoiThue obj) {
        ContentValues values = new ContentValues();
        values.put("maNguoiThue", obj.getMaNguoithue());
        values.put("matKhauNT", obj.getMatKhauNT());
        values.put("tenNguoiThue", obj.getTenNguoiThue());
        values.put("thuongTru", obj.getThuongTru());
        values.put("sdt", obj.getSdt());
        values.put("CCCD", obj.getcCCD());
        values.put("namSinh", obj.getNamSinh());
        values.put("gioiTinh", obj.getGioiTinh());
        values.put("maPhong", obj.getMaPhong());
        return db.insert("NguoiThue", null, values);
    }

    public int update(NguoiThue obj) {
        ContentValues values = new ContentValues();
        values.put("maNguoiThue", obj.getMaNguoithue());
        values.put("matKhauNT", obj.getMatKhauNT());
        values.put("tenNguoiThue", obj.getTenNguoiThue());
        values.put("thuongTru", obj.getThuongTru());
        values.put("sdt", obj.getSdt());
        values.put("CCCD", obj.getcCCD());
        values.put("namSinh", obj.getNamSinh());
        values.put("gioiTinh", obj.getGioiTinh());
        values.put("maPhong", obj.getMaPhong());
        return db.update("NguoiThue", values, "maNguoiThue=?", new String[]{obj.getMaNguoithue()});
    }

    public int delete(String id) {
        return db.delete("NguoiThue", "maNguoiThue=?", new String[]{id});
    }

    public List<NguoiThue> getAll() {
        String sql="SELECT * FROM NguoiThue";
        return getData(sql);
    }

    public NguoiThue getID(String id) {
        String sql = "SELECT * FROM NguoiThue WHERE maNguoiThue=?";
        List<NguoiThue> list = getData(sql, id);
        return list.get(0);
    }
    @SuppressLint("Range")
    private List<NguoiThue> getData(String sql, String...selectionArgs){
        List<NguoiThue> list = new ArrayList<>();
        Cursor c=db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            NguoiThue obj = new NguoiThue();
            obj.setMaNguoithue(c.getString(c.getColumnIndex("maNguoiThue")));
            obj.setMatKhauNT(c.getString(c.getColumnIndex("matKhauNT")));
            obj.setTenNguoiThue(c.getString(c.getColumnIndex("tenNguoiThue")));
            obj.setThuongTru(c.getString(c.getColumnIndex("thuongTru")));
            obj.setSdt( c.getString(c.getColumnIndex("sdt")));
            obj.setcCCD(Integer.parseInt(c.getString(c.getColumnIndex("CCCD"))));
            obj.setNamSinh( Integer.parseInt(c.getString(c.getColumnIndex("namSinh"))));
            obj.setGioiTinh(Integer.parseInt(c.getString(c.getColumnIndex("gioiTinh"))));
            obj.setMaPhong(Integer.parseInt(c.getString(c.getColumnIndex("maPhong"))));
            list.add(obj);
        }
        return list;
    }
    public int CheckLoginNT(String id,String password){
        String sql = "select * from NguoiThue where maNguoiThue=? and matKhauNT=?";
        List<NguoiThue> list=getData(sql,id,password);
        if (list.size()==0){
            return -1;
        }return 1;
    }

}
