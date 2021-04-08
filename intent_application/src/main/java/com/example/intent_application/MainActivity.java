package com.example.intent_application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ImageView imgRequest, imageResult;
    ArrayList<String> arrayImage;

    int REQUEST_CODE = 999;

    int imageId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MappingUI();
        Initialize();

        // Trộn mảng để tạo sự ngẫu nhiên về image
        Collections.shuffle(arrayImage);

        // Get image Id from Resource
        // key word [drawable] là định dạng chuẩn để truy cập vào drawable
        // Get imageId trong resource theo name trong list name duoc define strong strings
        this.imageId = getResources().getIdentifier(arrayImage.get(0), "drawable", getPackageName());
        imgRequest.setImageResource(imageId);

        imageResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImageActivity.class);

                intent.putStringArrayListExtra("ArrayListImage", arrayImage);

                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intentData) {
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK && intentData != null){
            int imageId = intentData.getIntExtra("ImageId", 0);
            imageResult.setImageResource(imageId);

            if(imageId == this.imageId){
                Toast.makeText(MainActivity.this, "Bạn đã chọn đúng", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(MainActivity.this, "Bạn đã chọn sai", Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, intentData);
    }

    private void MappingUI(){
        imgRequest = (ImageView) findViewById(R.id.imageViewRequest);
        imageResult = (ImageView) findViewById(R.id.imageViewResult);
    }

    private void Initialize(){
        // List bird name get from strings resource
        String[] arrayBirdName = getResources().getStringArray(R.array.list_bird_name);

        // Khởi tạo mảng mới với giá trị copy từ mảng cũ
        arrayImage = new ArrayList<>(Arrays.asList(arrayBirdName));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_reload, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuReload){
            // Trộn mảng để tạo sự ngẫu nhiên về image
            Collections.shuffle(arrayImage);

            // Get image Id from Resource
            // key word [drawable] là định dạng chuẩn để truy cập vào drawable
            // Get imageId trong resource theo name trong list name duoc define strong strings
            this.imageId = getResources().getIdentifier(arrayImage.get(0), "drawable", getPackageName());
            imgRequest.setImageResource(imageId);
        }
        return super.onOptionsItemSelected(item);
    }
}