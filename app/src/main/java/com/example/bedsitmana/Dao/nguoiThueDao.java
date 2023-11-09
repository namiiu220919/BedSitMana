package com.example.bedsitmana.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.bedsitmana.Database.DbHelper;
import com.example.bedsitmana.model.NguoiThue;

public class nguoiThueDao {
    private SQLiteDatabase db;
    private Context context;
    public nguoiThueDao(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
public long insert(NguoiThue obj){
    ContentValues values = new ContentValues();
values.put("maNguoiThue");

}
}
