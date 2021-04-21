package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // Xử lý tạo fragment bằng code
//    Button btnAddFragmentA, btnAddFragmentB;
//    FragmentTransaction fragmentTransaction;

    TextView txtMainTitle;
    Button btnChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();
    }

    private void InitView(){
        // Xử lý tạo fragment bằng code
//        btnAddFragmentA = (Button) findViewById(R.id.buttonAddFragmentA);
//        btnAddFragmentA.setOnClickListener(MainActivity.this);
//
//        btnAddFragmentB = (Button) findViewById(R.id.buttonAddFragmentB);
//        btnAddFragmentB.setOnClickListener(MainActivity.this);

        txtMainTitle = (TextView) findViewById(R.id.textViewMainActivityTitle);

        btnChange = (Button) findViewById(R.id.buttonChange);
        btnChange.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        // Xử lý tạo fragment bằng code
        // Init fragment manager
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentTransaction = fragmentManager.beginTransaction();
//
//        switch (v.getId()){
//            case R.id.buttonAddFragmentA:
//                // Add fragment(Vì có kéo thả 1 framelayout ở UI nên có thể add hoặc replace theo cách này)
//                // Trường hợp không sử dụng framelayout để load lên thì sẽ load thẳng lên activity
//                fragmentTransaction.replace(R.id.frameContent, new FragmentA());
//                // fragmentTransaction.add(R.id.frameContent, new FragmentA());
//                break;
//            case R.id.buttonAddFragmentB:
//                // Add fragment(Vì có kéo thả 1 framelayout ở UI nên có thể add hoặc replace theo cách này)
//                // Trường hợp không sử dụng framelayout để load lên thì sẽ load thẳng lên activity
//                fragmentTransaction.replace(R.id.frameContent, new FragmentB());
//                // fragmentTransaction.add(R.id.frameContent, new FragmentB());
//                break;
//        }
//
//        fragmentTransaction.commit();

        switch (v.getId()){
            case R.id.buttonChange:
                // Xử lý get fragment
                // Nếu fragment được kéo thả từ UI sẽ sử dụng: getSupportFragmentManager().findFragmentById()
                // Nếu fragment được tạo từ code thì sẽ sử dụng: getSupportFragmentManager().findFragmentByTag()
                FragmentA fragmentA = (FragmentA)getSupportFragmentManager().findFragmentById(R.id.fragmentA);
                fragmentA.SetFragATitle("FragmentA changed");
                break;
        }
    }
}