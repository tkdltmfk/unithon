package com.unithon.gamsung;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kyun on 2016-07-30.
 */
public class SqliteHelper extends SQLiteOpenHelper {

    public SqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table data (num integer primary key,contents text, setting integer);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(int i, String contents,int setting) {
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("insert into data values ('" + i + "','" + contents + "','" + setting + "');");
        db.close();
    }

    public Cursor getResult() {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from data", null);

        return cursor;
    }
}
