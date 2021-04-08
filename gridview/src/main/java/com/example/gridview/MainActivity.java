package com.example.gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    GridView gvName;
//    String[] arrayName = {"A", "B", "C", "D", "E", "L", "M", "N", "O", "P", "Q"};

    GridView gvFruits;
    ArrayList<Fruit> alFruits;
    FruitAdapter fruitAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MappingUI();
        InitializeData();

        fruitAdapter = new FruitAdapter(MainActivity.this, R.layout.fruit_record, alFruits);

        gvFruits.setAdapter(fruitAdapter);
//        gvName = (GridView) findViewById(R.id.imageViewFruits);
//
//        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arrayName);
//
//        gvName.setAdapter(arrayAdapter);
//
//        gvName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, arrayName[position], Toast.LENGTH_LONG).show();
//            }
//        });
    }

    private void MappingUI(){
        gvFruits = (GridView) findViewById(R.id.gridViewFruits);
    }

    private void InitializeData(){
        alFruits = new ArrayList<>();
        alFruits.add(new Fruit("Banana",R.drawable.banana_fruit));
        alFruits.add(new Fruit("Beet",R.drawable.beet_fruit));
        alFruits.add(new Fruit("Citrus",R.drawable.citrus_fruit));
        alFruits.add(new Fruit("Kiwi",R.drawable.kiwi_fruit));
        alFruits.add(new Fruit("Peach",R.drawable.peach_fruit));
        alFruits.add(new Fruit("Raspberry",R.drawable.raspberry_fruit));
        alFruits.add(new Fruit("Watermelon",R.drawable.watermelon_fruit));
    }
}
