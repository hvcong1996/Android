package com.example.intent_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.Collections;

public class ImageActivity extends AppCompatActivity {

    TableLayout tableLayout;

    ArrayList<String> listImages;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        MappingUI();

        // Get intent
        intent = getIntent();

        listImages = intent.getStringArrayListExtra("ArrayListImage");

        // Trộn list Images
        Collections.shuffle(listImages);

        Initialize();
    }

    private void MappingUI(){
        tableLayout = (TableLayout) findViewById(R.id.tableLayoutBirdImage);
    }

    private void Initialize(){
        int row = 6;
        int column = 3;
        int index = 0;

        // Tạo các row và column trong table
        for (int i = 1; i <= row; i++ ){
            // Tạo row
            TableRow tableRow = new TableRow(this);

            // Tạo column
            for(int j = 1; j <= column; j++){
                ImageView imageView = new ImageView(this);

                // Convert dp to px
                Resources resources = getResources();
                int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 140, resources.getDisplayMetrics());

                // 180x180 chiều rộng và chiều cao của 1 ImageView
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(px,px);
                imageView.setLayoutParams(layoutParams);

                // Get image Id from Resource
                // key word [drawable] là định dạng chuẩn để truy cập vào drawable
                // Get imageId trong resource theo name trong list name duoc define strong strings
                int imageId = getResources().getIdentifier(listImages.get(index), "drawable", getPackageName());
                imageView.setImageResource(imageId);

                // Return imageId for MainActivity khi Click vào ImageView
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Return imageId for MainActivity
                        intent.putExtra("ImageId", imageId);
                        setResult(RESULT_OK, intent);

                        // Close current activity
                        finish();
                    }
                });

                // Tăng index của list image
                index ++;

                // Add imageView vào Row
                tableRow.addView(imageView);
            }

            // Add tableRow vào Table
            tableLayout.addView(tableRow);
        }
    }
}