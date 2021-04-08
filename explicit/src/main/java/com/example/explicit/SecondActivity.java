package com.example.explicit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    Button btnPrevious;
    TextView txtData;
    EditText edtInputData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        MappingUI();

        // Get data from Intent
//        Intent intent = getIntent();
        // String value = intent.getStringExtra("SendData");
        // int value = intent.getIntExtra("SendData", 0);
        // String[] value = intent.getStringArrayExtra("SendData");
        // ArrayList value = intent.getStringArrayListExtra("SendData");
//        Student value = (Student) intent.getSerializableExtra("SendData");
//        Bundle bundle = intent.getBundleExtra("Data");
//        String name = bundle.getString("StringNe");
//        txtData.setText(name);

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
//                startActivity(intent);


                // Intent data result
                String data = edtInputData.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("TheName", data);
                setResult(RESULT_OK, intent);
                // Close current activity
                finish();
            }
        });
    }

    private void MappingUI()
    {
        btnPrevious = (Button) findViewById(R.id.buttonPrevious);
        txtData = (TextView) findViewById(R.id.textView);
        edtInputData = (EditText) findViewById(R.id.editTextInputData);
    }
}