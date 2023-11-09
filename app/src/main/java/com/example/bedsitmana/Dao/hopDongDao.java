package com.example.bedsitmana.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bedsitmana.Database.DbHelper;
import com.example.bedsitmana.model.HoaDon;
import com.example.bedsitmana.model.HopDong;

import java.util.ArrayList;
import java.util.List;

public class hopDongDao {
    private SQLiteDatabase db;

    public hopDongDao(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public  long insert(HopDong hopDong){
        ContentValues values=new ContentValues();
        values.put("giaTien", hopDong.getGiaTien());
        values.put("thoiHan", hopDong.getThoiHan());
        values.put("soNguoi", hopDong.getSoNguoi());
        values.put("soXe", hopDong.getSoXe());
        values.put("maPhong", hopDong.getMaPhong());
        values.put("maNguoiThue", hopDong.getMaNguoiThue());
        return db.insert("HoaDon",null,values);
    }

    public int update(HopDong hopDong){
        ContentValues values=new ContentValues();
        values.put("giaTien", hopDong.getGiaTien());
        values.put("thoiHan", hopDong.getThoiHan());
        values.put("soNguoi", hopDong.getSoNguoi());
        values.put("soXe", hopDong.getSoXe());
        values.put("maPhong", hopDong.getMaPhong());
        values.put("maNguoiThue", hopDong.getMaNguoiThue());
        return db.update("HopDong", values,"maHopDong=?", new String[]{String.valueOf(hopDong.getMaHopDong())});
    }
    public int delete(String id){
        return db.delete("HopDong","maHopDong=?",new String[]{id});
    }

    @SuppressLint("Range")
    private List<HopDong> getDaTa(String sql, String...selectionArgs){
        List<HopDong> list = new ArrayList<>();
        Cursor c=db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            HopDong hopDong = new HopDong();
            hopDong.setMaHopDong(Integer.parseInt(c.getString(c.getColumnIndex("maHopDong"))));
            hopDong.setGiaTien(Integer.parseInt(c.getString(c.getColumnIndex("giaTien"))));
            hopDong.setThoiHan(Integer.parseInt(c.getString(c.getColumnIndex("thoiHan"))));
            hopDong.setSoNguoi(Integer.parseInt(c.getString(c.getColumnIndex("soNguoi"))));
            hopDong.setSoXe(Integer.parseInt(c.getString(c.getColumnIndex("soXe"))));
            hopDong.setMaPhong(Integer.parseInt(c.getString(c.getColumnIndex("maPhong"))));
            hopDong.setMaNguoiThue(c.getString(c.getColumnIndex("maNguoiThue")));

            list.add(hopDong);
        }
        return list;
    }
    //get all
    public List<HopDong> getAll(){
        String sql = "SELECT * FROM HopDong";
        return getDaTa(sql);
    }

    //get id
    public HopDong getID(String id){
        String sql = "SELECT *FROM HopDong WHERE maHopDong=?";
        List<HopDong> list = getDaTa(sql,id);
        return list.get(0);
    }
}
