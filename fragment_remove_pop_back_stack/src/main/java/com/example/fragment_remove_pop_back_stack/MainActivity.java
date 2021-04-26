package com.example.fragment_remove_pop_back_stack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnAddFragA, btnAddFragB, btnRemoveFragA, btnRemoveFragB, btnBack, btnPop;

    FragmentManager fragmentManager = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();
    }

    private void InitView(){
        btnAddFragA = (Button) findViewById(R.id.buttonAddFragmentA);
        btnAddFragA.setOnClickListener(MainActivity.this);
        btnAddFragB = (Button) findViewById(R.id.buttonAddFragmentB);
        btnAddFragB.setOnClickListener(MainActivity.this);
        btnRemoveFragA = (Button) findViewById(R.id.buttonRemoveFragA);
        btnRemoveFragA.setOnClickListener(MainActivity.this);
        btnRemoveFragB = (Button) findViewById(R.id.buttonRemoveFragB);
        btnRemoveFragB.setOnClickListener(MainActivity.this);
        btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(MainActivity.this);
        btnPop = (Button) findViewById(R.id.buttonPop);
        btnPop.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        // Sử dụng fragmentTransaction để add hoặc replace các fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Sử dụng findFragmentByTag để get thông tin Fragment để thực hiện remove
        FragmentA fragmentA = (FragmentA) getSupportFragmentManager().findFragmentByTag("FragmentA");
        FragmentB fragmentB = (FragmentB) getSupportFragmentManager().findFragmentByTag("FragmentB");

        switch (v.getId()){
            // Khi add hoặc replace Fragment cần thêm string Tag để có thể remove theo Tag
            case R.id.buttonAddFragmentA:
                // fragmentTransaction.add(frameLayout, Fragment, Tag)
                fragmentTransaction.add(R.id.frameLayout, new FragmentA(), "FragmentA");
                // Add fragment với Tag name(FragmentA) vào stack để phục vụ cho việc back về
                fragmentTransaction.addToBackStack("FragmentA");
            break;
            case R.id.buttonAddFragmentB:
                // fragmentTransaction.add(frameLayout, Fragment, Tag)
                fragmentTransaction.add(R.id.frameLayout, new FragmentB(), "FragmentB");
                // Add fragment với Tag name(FragmentB) vào stack để phục vụ cho việc back về
                fragmentTransaction.addToBackStack("FragmentB");
                break;
            // Cần kiểm tra fragment có tồn tại hay không, nếu có mới xóa được
            case R.id.buttonRemoveFragA:
                if(fragmentA != null){
                    fragmentTransaction.remove(fragmentA);
                }
                break;
            case R.id.buttonRemoveFragB:
                if(fragmentB != null){
                    fragmentTransaction.remove(fragmentB);
                }
                break;
            case R.id.buttonBack:
                // Xử lý back về fragment trước theo cơ chế stack
                getSupportFragmentManager().popBackStack();
                break;
            case R.id.buttonPop:
                // Xử lý back đến fragment có name là FragmentA(tất cả fragment nằm trong stack ở khoảng giữa cũng sẽ bị remove luôn)
                getSupportFragmentManager().popBackStack("FragmentA", 0);
                break;
        }

        fragmentTransaction.commit();
    }

    // Xử lý event back của device
    @Override
    public void onBackPressed() {
        // Check fragment trong back stack -> nếu có thì sẽ có thể back
        if(getSupportFragmentManager().getBackStackEntryCount() > 0){
            getSupportFragmentManager().popBackStack();
        }
        else {
            super.onBackPressed();
        }
    }
}