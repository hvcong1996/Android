package com.example.oop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvMonHoc;
    ArrayList<String> arrayCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMonHoc = (ListView) findViewById(R.id.ListViewMonHoc);
        arrayCourse = new ArrayList<>();
        arrayCourse.add("Android 1");
        arrayCourse.add("Android 2");
        arrayCourse.add("Android 3");
        arrayCourse.add("Android 4");
        arrayCourse.add("Android 5");
        arrayCourse.add("Android 6");
        arrayCourse.add("Android 7");
        arrayCourse.add("Android 8");
        arrayCourse.add("Android 9");
        arrayCourse.add("Android 10");
        arrayCourse.add("Android 11");
        arrayCourse.add("Android 12");
        arrayCourse.add("Android 13");
        arrayCourse.add("Android 14");
        arrayCourse.add("Android 15");
        arrayCourse.add("Android 16");
        arrayCourse.add("Android 17");
        arrayCourse.add("Android 18");
        arrayCourse.add("Android 19");
        arrayCourse.add("Android 20");

        // Sử dụng để lấy data và đổ lên UI
        //(Màn hình hiển thị, layout hiển thị 1 item, object data)
        ArrayAdapter adapter = new ArrayAdapter(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                arrayCourse
        );

        // Set data cho View
        lvMonHoc.setAdapter(adapter);

        // Click event
        lvMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // position: Vị trí của item click
                Toast.makeText(MainActivity.this,arrayCourse.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        // Long click event
        lvMonHoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });
    }
}
