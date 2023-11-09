package com.example.bedsitmana.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bedsitmana.Database.DbHelper;
import com.example.bedsitmana.model.Coso;
import com.example.bedsitmana.model.KeToan;

import java.util.ArrayList;
import java.util.List;

public class coSoDao {
    private SQLiteDatabase db;

    public coSoDao(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public  long insert(Coso obj){
        ContentValues values=new ContentValues();
        values.put("tenCS", obj.getTenCoso());
        values.put("diaChi", obj.getDiaChi());
        values.put("phiDichVu", obj.getPhiDichVu());
        values.put("giaDien", obj.getGiaDien());
        values.put("giaNuoc", obj.getGiaNuoc());
        return db.insert("CoSo",null,values);
    }
    public int update(Coso obj){
        ContentValues values=new ContentValues();
        values.put("tenCS", obj.getTenCoso());
        values.put("diaChi", obj.getDiaChi());
        values.put("phiDichVu", obj.getPhiDichVu());
        values.put("giaDien", obj.getGiaDien());
        values.put("giaNuoc", obj.getGiaNuoc());
        return db.update("CoSo",values,"maCS=?",new String[]{String.valueOf(obj.getMaCoso())});
    }
    public int delete(String id){
        return db.delete("CoSo","maCS=?",new String[]{id});
    }

    @SuppressLint("Range")
    private List<Coso> getDaTa(String sql, String...selectionArgs){
        List<Coso> list = new ArrayList<>();
        Cursor c=db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            Coso obj = new Coso();
            obj.setMaCoso(Integer.parseInt(c.getString(c.getColumnIndex("maCS"))));
            obj.setTenCoso(c.getString(c.getColumnIndex("tenCS")));
            obj.setDiaChi(c.getString(c.getColumnIndex("diaChi")));
            obj.setPhiDichVu(Integer.parseInt(c.getString(c.getColumnIndex("phiDichVu"))));
            obj.setGiaDien(Integer.parseInt(c.getString(c.getColumnIndex("giaDien"))));
            obj.setGiaNuoc(Integer.parseInt(c.getString(c.getColumnIndex("giaNuoc"))));

            list.add(obj);
        }
        return list;
    }
    //get all
    public List<Coso> getAll(){
        String sql = "SELECT * FROM CoSo";
        return getDaTa(sql);
    }

    //get id
    public Coso getID(String id){
        String sql = "SELECT *FROM CoSo WHERE maCS=?";
        List<Coso> list = getDaTa(sql,id);
        return list.get(0);
    }
}
