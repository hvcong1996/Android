package com.example.oop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvMonHoc;
    ArrayList<String> arrayCourse;
    EditText edtInputItem;
    Button btnAddItem;
    Button btnUpdateData;
    // Data adapter
    ArrayAdapter adapter;

    int index = -1;

    public void MappingUI(){
        lvMonHoc = (ListView) findViewById(R.id.ListViewMonHoc);
        edtInputItem = (EditText) findViewById(R.id.editTextInputItem);
        btnAddItem = (Button) findViewById(R.id.buttonAddItem);
        btnUpdateData = (Button) findViewById(R.id.buttonUpdateData);
    }

    public void  InitializeArray(){
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
    }

    public void LoadDataToUI(){
        // Sử dụng để lấy data và đổ lên UI
        //(Màn hình hiển thị, layout hiển thị 1 item, object data)
        adapter = new ArrayAdapter(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                arrayCourse
        );

        // Set data cho View
        lvMonHoc.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MappingUI();

        InitializeArray();

        LoadDataToUI();

        // Click event
        lvMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // position: Vị trí của item click
                index = position;
                edtInputItem.setText(arrayCourse.get(position));
            }
        });

        lvMonHoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Get from UI and add to add
                arrayCourse.remove(position);

                // Update data when changed to UI
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get from UI and add to add
                String item = edtInputItem.getText().toString();
                arrayCourse.add(item);

                // Update data when changed to UI
                adapter.notifyDataSetChanged();
            }
        });

        btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get from UI and add to add
                arrayCourse.set(index, edtInputItem.getText().toString());

                // Update data when changed to UI
                adapter.notifyDataSetChanged();
            }
        });
    }
}
