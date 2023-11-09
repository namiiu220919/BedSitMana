package com.example.bedsitmana.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.bedsitmana.Database.DbHelper;

import java.sql.SQLData;

public class keToanDao {
public SQLiteDatabase db;
private Context context;
    public keToanDao(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
}