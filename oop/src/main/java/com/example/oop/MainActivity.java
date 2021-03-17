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

    ListView lvFruit;
    ArrayList<Fruit> arrayFruit;
    FruitApdapter adapterFruit;

    EditText edtInputItem;
    Button btnAddItem;
    Button btnUpdateData;

    int index = -1;

    public void MappingUI(){
        lvFruit = (ListView) findViewById(R.id.ListViewFruit);

        edtInputItem = (EditText) findViewById(R.id.editTextInputItem);
        btnAddItem = (Button) findViewById(R.id.buttonAddItem);
        btnUpdateData = (Button) findViewById(R.id.buttonUpdateData);
    }

    public void  InitializeArray(){
        arrayFruit = new ArrayList<>();

        arrayFruit.add(new Fruit("Banana", "Banana (Description)", R.drawable.banana_fruit));
        arrayFruit.add(new Fruit("Beet", "Beet (Description)", R.drawable.beet_fruit));
        arrayFruit.add(new Fruit("Citrus", "Citrus (Description)", R.drawable.citrus_fruit));
        arrayFruit.add(new Fruit("Kiwi", "Kiwi (Description)", R.drawable.kiwi_fruit));
        arrayFruit.add(new Fruit("Peach", "Peach (Description)", R.drawable.peach_fruit));
        arrayFruit.add(new Fruit("Raspberry", "Raspberry (Description)", R.drawable.raspberry_fruit));
        arrayFruit.add(new Fruit("Watermelon", "Watermelon (Description)", R.drawable.watermelon_fruit));
    }

    public void LoadDataToUI(){
        // Sử dụng để lấy data và đổ lên UI
        //(Màn hình hiển thị, layout hiển thị 1 item, object data)
        adapterFruit = new FruitApdapter(MainActivity.this, R.layout.fruit_record, arrayFruit);

        // Set data cho View
        lvFruit.setAdapter(adapterFruit);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MappingUI();

        InitializeArray();

        LoadDataToUI();

        // Click event
//        lvMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // position: Vị trí của item click
//                index = position;
//                edtInputItem.setText(arrayCourse.get(position));
//            }
//        });

//        lvMonHoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                // Get from UI and add to add
//                arrayCourse.remove(position);
//
//                // Update data when changed to UI
//                adapter.notifyDataSetChanged();
//                return false;
//            }
//        });

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get from UI and add to add
//                String item = edtInputItem.getText().toString();
//                arrayCourse.add(item);
//
//                // Update data when changed to UI
//                adapter.notifyDataSetChanged();
            }
        });

        btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                // Get from UI and add to add
//                arrayCourse.set(index, edtInputItem.getText().toString());
//
//                // Update data when changed to UI
//                adapter.notifyDataSetChanged();
            }
        });
    }
}
