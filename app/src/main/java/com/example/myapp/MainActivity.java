package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    TextView txtContent;
//    Button btnChangeText;
    EditText edtUserName;
    Button btnGetUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        txtContent = (TextView) findViewById(R.id.textViewData);
//        btnChangeText = (Button) findViewById(R.id.buttonChangeText);
//
//        btnChangeText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                txtContent.setText("Text changed");
//            }
//        });

        edtUserName = (EditText) findViewById(R.id.editTextTextPersonName);
        btnGetUserName = (Button) findViewById(R.id.buttonGetPersonName);

        btnGetUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = edtUserName.getText().toString();
                Toast.makeText(MainActivity.this, content, Toast.LENGTH_LONG).show();
            }
        });
    }
}