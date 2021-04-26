package com.example.fragment_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnAddFragment;

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();
    }

    private void InitView(){
        btnAddFragment = (Button) findViewById(R.id.buttonAddFrag);
        btnAddFragment.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (v.getId()){
            case R.id.buttonAddFrag:
                fragmentTransaction.add(R.id.frameLayout, new FragmentDanhSach());
                break;
        }
        fragmentTransaction.commit();
    }
}