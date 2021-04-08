package com.example.share_preferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtUserName, edtPassword;
    Button btnLogin;
    CheckBox cbRemember;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MappingUI();

        // Setting để lưu thông tin history lại
        // loginData: name tự define
        sharedPreferences = getSharedPreferences("loginData", MODE_PRIVATE);

        // Get data đã được lưu ở SharedPreferences
        edtUserName.setText(sharedPreferences.getString("username", null));
        edtPassword.setText(sharedPreferences.getString("password", null));
        cbRemember.setChecked(sharedPreferences.getBoolean("isChecked", false));

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edtUserName.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if(userName.equals("hvcong") && password.equals("12345")){
                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                    // User chọn remember account sẽ lưu thông tin lại
                    if(cbRemember.isChecked()){
                        // Tạo thông tin và lưu vào SharedPreferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", userName);
                        editor.putString("password", password);
                        editor.putBoolean("isChecked", true);
                        editor.commit();
                    }
                    else {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        // Remove thông tin ở SharedPreferences
                        editor.remove("username");
                        editor.remove("password");
                        editor.remove("isChecked");
                        editor.commit();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "Login fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void MappingUI(){
        edtUserName = (EditText) findViewById(R.id.editTextLoginName);
        edtPassword = (EditText) findViewById(R.id.editTextPassword);
        cbRemember = (CheckBox) findViewById(R.id.checkBoxRemember);
        btnLogin = (Button) findViewById(R.id.buttonLogin);
    }
}