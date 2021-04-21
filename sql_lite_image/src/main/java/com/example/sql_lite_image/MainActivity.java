package com.example.sql_lite_image;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAddImage;

    public static Database database;

    ListView lvItem;
    ArrayList<Item> arrayListItem;
    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();

        InitDatabase();

        InitListItem();
    }

    private void InitView(){
        btnAddImage = (Button) findViewById(R.id.buttonOpenAddActivity);
        btnAddImage.setOnClickListener(MainActivity.this);

        lvItem = (ListView) findViewById(R.id.listViewItem);
    }

    // Init database image_info.sqlite
    private void InitDatabase(){
        database = new Database(MainActivity.this, "image_info.sqlite", null, 1);

        // Tạo bảng Item(Id, Name, Description, Image)
        // BLOB: kiểu để lưu hình ảnh
        database.QueryData("CREATE TABLE IF NOT EXISTS Item(Id INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR(250), Description VARCHAR(250), Image BLOB)");

    }

    private void InitListItem(){
        arrayListItem = new ArrayList<>();

        itemAdapter = new ItemAdapter(MainActivity.this, R.layout.item_row, arrayListItem);

        lvItem.setAdapter(itemAdapter);

        // Get data from database
        Cursor cursor = database.GetData("SELECT * FROM Item");

        while (cursor.moveToNext()){
            arrayListItem.add(new Item(
                    // Get tất cả thông tin của table item
                    // Các thông tin có trong column lần lượt theo thứ tự từ 0 là:
                    // Id(column index: 0), Name(column index: 1), Description(column index: 2), Image(column index: 3)
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getBlob(3)
            ));
        }

        itemAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonOpenAddActivity:
                startActivity(new Intent(MainActivity.this, AddItemActivity.class));
                break;
        }
    }
}