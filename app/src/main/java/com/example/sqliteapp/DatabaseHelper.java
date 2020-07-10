package com.example.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "NIM";

    public static final String TABLE_NAME_2 = "na_table";
    public static final String COL_1_2 = "ID";
    public static final String COL_2_2 = "PAPBL";
    public static final String COL_3_2 = "PUX";
    public static final String COL_4_2 = "GRAFKOM";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,NIM TEXT);");
        db.execSQL("create table " + TABLE_NAME_2 + " (ID INTEGER, PAPBL TEXT, PUX TEXT, GRAFKOM TEXT, FOREIGN KEY (ID) REFERENCES "
                + TABLE_NAME +"(ID));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_2);
        onCreate(db);
    }

    public boolean insertData(String name,String nim) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,nim);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean insertData_2(String id,String papbl,String pux, String grafkom) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1_2,id);
        contentValues.put(COL_2_2,papbl);
        contentValues.put(COL_3_2,pux);
        contentValues.put(COL_4_2,grafkom);
        long result = db.insert(TABLE_NAME_2,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public Cursor getAllData_2() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_2,null);
        return res;
    }

    public Cursor searchData(String searchText) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME + " where ID LIKE '%" + searchText +"%' " +
                "OR NAME LIKE '%" + searchText + "%' " +
                "OR NIM LIKE '%" + searchText + "%' ", null);
        return res;
    }

    public Cursor searchData_2(String searchText) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_2+" where ID= '" + Integer.parseInt(searchText) + "'", null);
//        Cursor res = db.rawQuery("select * from "+TABLE_NAME_2 + " where ID LIKE '%" + searchText +"%' " +
//                "OR PAPBL LIKE '%" + searchText + "%' " +
//                "OR PUX LIKE '%" + searchText + "%' " +
//                "OR GRAFKOM LIKE '%" + searchText + "%' ", null);
        return res;
    }

    public boolean updateData(String id,String name,String nim) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,nim);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public boolean updateData_2(String id,String papbl,String pux,String grafkom) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1_2,id);
        contentValues.put(COL_2_2,papbl);
        contentValues.put(COL_3_2,pux);
        contentValues.put(COL_4_2,grafkom);
        db.update(TABLE_NAME_2, contentValues, "ID = ?",new String[] {String.valueOf(id)});
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

    public Integer deleteData_2 (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_2, "ID = ?",new String[] {id});
    }
}