package com.example.fragment_send_data;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAddFragA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();
    }

    private void InitView(){
        btnAddFragA = (Button) findViewById(R.id.buttonAddFragA);
        btnAddFragA.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View v) {

        // Create fragment Manager
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Create fragment Transaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (v.getId()){
            case R.id.buttonAddFragA:
                // Send data(Bundle object) to Fragment using setArguments
                FragmentA fragmentA = new FragmentA();
                Bundle bundle = new Bundle();
                // bundle.putString([key],[value])
                bundle.putString("FullName", "Huỳnh Văn Công");
                bundle.putInt("FullName", 1);
                fragmentA.setArguments(bundle);

                // Trường hợp không sử dụng framelayout để load fragment lên thì sử dụng mainlayout cũng được
                fragmentTransaction.add(R.id.mainLinearLayout, fragmentA);
                break;
        }

        fragmentTransaction.commit();
    }
}