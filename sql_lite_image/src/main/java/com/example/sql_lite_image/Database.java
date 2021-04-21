package com.example.sql_lite_image;


import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void QueryData(String sql){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql);
    }

    public Cursor GetData(String sql){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery(sql, null);
    }

    // Khi xử lý với database với những kiểu dữ liệu phức tạp thì nên viết method riêng và sử dụng sqLiteStatement
    // Vì kiểu hình ảnh là kiểu BLOB
    // Nên cần tạo method để xử lý insert riêng sử dụng SQLiteStatement để có thể bindBlob vào câu sql
    public void InsertItem(String itemName, String itemDescription, byte[] itemImage){
        // Khởi tạo sqLiteDatabase chức năng write
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        // Tạo câu lệnh sql : ()
        // Sử dụng dấu ? để xử lý bind các variable vào câu sql
        String sql = "INSERT INTO Item VALUES(null, ?, ?, ?)";
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(sql);

        // clearBindings của sqLiteStatement trước khi thực hiện bind mới
        sqLiteStatement.clearBindings();

        // Thứ tự các index trong câu sql của VALUES là (0,1,2,3)
        sqLiteStatement.bindString(1, itemName);
        sqLiteStatement.bindString(2, itemDescription);
        sqLiteStatement.bindBlob(3, itemImage);

        // Execute insert
        sqLiteStatement.executeInsert();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
