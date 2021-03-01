package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

//    TextView txtContent;
//    Button btnChangeText;
//    EditText edtUserName;
//    Button btnGetUserName;
    EditText edtMinNumber, edtMaxNumber;
    TextView txtRandomNumber;
    Button btnExecuteRandom;

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

//        edtUserName = (EditText) findViewById(R.id.editTextPersonName);
//        btnGetUserName = (Button) findViewById(R.id.buttonGetPersonName);
//
//        btnGetUserName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String content = edtUserName.getText().toString();
//                Toast.makeText(MainActivity.this, content, Toast.LENGTH_LONG).show();
//            }
//        });

        MappingUI();

        btnExecuteRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringMinValue = edtMinNumber.getText().toString();
                String stringMaxValue = edtMaxNumber.getText().toString();

                // If value input is string then trim() value input and check.
                if(stringMinValue.length() == 0 || stringMaxValue.length() == 0){
                    Toast.makeText(MainActivity.this, "Please input info", Toast.LENGTH_LONG).show();
                    return;
                }

                int intMinValue = Integer.parseInt(stringMinValue);
                int intMaxValue = Integer.parseInt(stringMaxValue);
                Random random = new Random();
                int number = random.nextInt(intMaxValue - intMinValue + 1) + intMinValue;
                txtRandomNumber.setText(String.valueOf(number));
            }
        });
    }

    public void MappingUI(){
        edtMinNumber = (EditText) findViewById(R.id.editTextMinNumber);
        edtMaxNumber = (EditText) findViewById(R.id.editTextMaxNumber);
        txtRandomNumber = (TextView) findViewById(R.id.textViewNumber);
        btnExecuteRandom = (Button) findViewById(R.id.buttonRandomNumber);
    }
}