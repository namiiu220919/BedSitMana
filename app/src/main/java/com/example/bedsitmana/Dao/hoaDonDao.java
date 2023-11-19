package com.example.bedsitmana.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bedsitmana.Database.DbHelper;
import com.example.bedsitmana.model.HoaDon;

import java.util.ArrayList;
import java.util.List;

public class hoaDonDao {
    private SQLiteDatabase db   ;

    public hoaDonDao(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public  long insert(HoaDon hd){
        ContentValues values=new ContentValues();
        values.put("soDien", hd.getSoDien());
        values.put("soNuoc", hd.getSoNuoc());
        values.put("phiDichVu", hd.getPhiDichVu());
        values.put("trangThai", hd.getTrangThai());
        values.put("maPhong", hd.getMaPhong());
        values.put("maNguoiThue", hd.getMaNguoiThue());
        values.put("maKeToan", hd.getMaKeToan());
        return db.insert("HoaDon",null,values);
    }

    public int update(HoaDon hd){
        ContentValues values=new ContentValues();
        values.put("soDien", hd.getSoDien());
        values.put("soNuoc", hd.getSoNuoc());
        values.put("phiDichVu", hd.getPhiDichVu());
        values.put("trangThai", hd.getTrangThai());
        values.put("maPhong", hd.getMaPhong());
        values.put("maNguoiThue", hd.getMaNguoiThue());
        values.put("maKeToan", hd.getMaKeToan());
        return db.update("HoaDon", values,"maHoaDon=?", new String[]{String.valueOf(hd.getMaHoaDon())});
    }

    public int delete(String id){
        return db.delete("HoaDon","maHoaDon=?",new String[]{id});
    }

    @SuppressLint("Range")
    private List<HoaDon> getDaTa(String sql, String...selectionArgs){
        List<HoaDon> list = new ArrayList<>();
        Cursor c=db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){

            HoaDon hd = new HoaDon();
            hd.setMaHoaDon(Integer.parseInt(c.getString(c.getColumnIndex("maHoaDon"))));
            hd.setSoDien(Integer.parseInt(c.getString(c.getColumnIndex("soDien"))));
            hd.setSoNuoc(Integer.parseInt(c.getString(c.getColumnIndex("soNuoc"))));
            hd.setPhiDichVu(Integer.parseInt(c.getString(c.getColumnIndex("phiDichVu"))));
            hd.setTrangThai(Integer.parseInt(c.getString(c.getColumnIndex("trangThai"))));
            hd.setMaPhong(Integer.parseInt(c.getString(c.getColumnIndex("maPhong"))));
            hd.setMaNguoiThue(c.getString(c.getColumnIndex("maNguoiThue")));
            hd.setMaKeToan(c.getString(c.getColumnIndex("maKeToan")));

            list.add(hd);
        }
        return list;
    }
    //get all
    public List<HoaDon> getAll(){
        String sql = "SELECT * FROM HoaDon";
        return getDaTa(sql);
    }

    //get id
    public HoaDon getID(String id){
        String sql = "SELECT *FROM HoaDon WHERE maHoaDon=?";
        List<HoaDon> list = getDaTa(sql,id);
        return list.get(0);
    }
}
