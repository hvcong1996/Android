package com.example.fragment_dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IDeleteData{

    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();
    }

    private void InitView(){
        btnDelete = (Button) findViewById(R.id.buttonDelete);
        btnDelete.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonDelete:
                FragmenHopThoai fragmenHopThoai = new FragmenHopThoai();
                fragmenHopThoai.show(getSupportFragmentManager(), "Dialog");
                break;
        }
    }

    @Override
    public void Delete(boolean isDelete) {
        if(isDelete){
            Toast.makeText(MainActivity.this, "Xóa", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(MainActivity.this, "Không Xóa", Toast.LENGTH_SHORT).show();
        }
    }
}