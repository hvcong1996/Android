package com.example.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnPopupMenu;
    Button btnContextMenu;
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MappingUI();

        btnPopupMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu();
            }
        });

        // Đăng kí view cho context menu
        registerForContextMenu(btnContextMenu);
    }

    private void MappingUI(){
        btnPopupMenu = (Button) findViewById(R.id.buttonPopupMenu);
        btnContextMenu = (Button) findViewById(R.id.buttonContextMenu);
        layout = (ConstraintLayout) findViewById(R.id.Layout);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        getMenuInflater().inflate(R.menu.context_menu, menu);
        menu.setHeaderTitle("Setting Color");
        menu.setHeaderIcon(R.drawable.setting);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuRed:
                layout.setBackgroundColor(Color.RED);
                break;
            case R.id.menuYellow:
                layout.setBackgroundColor(Color.YELLOW);
                break;
            case R.id.menuBlue:
                layout.setBackgroundColor(Color.BLUE);
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void PopupMenu(){
        PopupMenu popupMenu = new PopupMenu(this, btnPopupMenu);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuAdd:
                        btnPopupMenu.setText("Menu Add");
                        break;
                    case R.id.menuUpdate:
                        btnPopupMenu.setText("Menu Update");
                        break;
                    case R.id.menuDelete:
                        btnPopupMenu.setText("Menu Delete");
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.taskbar_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menuSetting:
                Toast.makeText(MainActivity.this, "Menu setting", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuShare:
                Toast.makeText(MainActivity.this, "Menu share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuSearch:
                Toast.makeText(MainActivity.this, "Menu search", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuEmail:
                Toast.makeText(MainActivity.this, "Menu contact email", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuPhone:
                Toast.makeText(MainActivity.this, "Menu contact phone", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuExit:
                Toast.makeText(MainActivity.this, "Menu exit", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}