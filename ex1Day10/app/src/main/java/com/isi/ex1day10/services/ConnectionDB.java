package com.isi.ex1day10.services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ConnectionDB {
    private static final String name = "students";
    private static final int version = 1;
    private static SQLiteDatabase db;


    public static SQLiteDatabase getDB(Context context) {
        DBManager dbManager = new DBManager(context,name,null,version);
        return db = dbManager.getWritableDatabase();
    }

    public static void closeDB(){
        db.close();
    }

}
