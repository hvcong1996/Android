package com.example.sql_lite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    // Database(context: màn hình, name: Database name, factory: Con trỏ để duyệt dữ liệu, version: version Database)
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // Truy vấn không return về kết quả: CREATE, INSERT, UPDATE, DELETE
    public void QueryData(String sql){
        // Create instance để ghi vào Database
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        // Execute sql
        sqLiteDatabase.execSQL(sql);
    }

    // Truy vấn có return về kết quả(factory): SELECT
    // Cursor: Kiểu trả về là con trỏ
    public Cursor GetData(String sql){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        // Return về rawQuery dựa theo sql
        return sqLiteDatabase.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
