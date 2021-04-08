package com.example.worldapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtData;
    Button btnConfirm;
    EditText edtFullname, edtPhonenumber, edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MappingUI();

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = edtFullname.getText().toString();
                String email = edtEmail.getText().toString();
                String phoneNumber = edtPhonenumber.getText().toString();

                txtData.setText(getResources().getString(R.string.txt_FullName) + ": " + fullName +
                        "\n" + getResources().getString(R.string.txt_Email) + ": " + email +
                        "\n" + getResources().getString(R.string.txt_PhoneNumber) + ": "+ phoneNumber);
            }
        });
    }

    private void MappingUI(){
        edtFullname = (EditText) findViewById(R.id.editTextFullName);
        edtPhonenumber = (EditText) findViewById(R.id.editTextPhoneNumber);
        edtEmail = (EditText) findViewById(R.id.editTextEmail);
        btnConfirm = (Button) findViewById(R.id.buttonConfirm);
        txtData = (TextView) findViewById(R.id.textViewData);
    }
}