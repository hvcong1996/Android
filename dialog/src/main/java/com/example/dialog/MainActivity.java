package com.example.dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView lvName;
    ArrayList<String> arrayName;
    ArrayAdapter adapter;

    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MappingUI();
        InitializeData();
        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arrayName);
        lvName.setAdapter(adapter);

        lvName.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DeleteConfirm(position);
                return false;
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogLogin();
            }
        });
    }

    private void DialogLogin(){
        Dialog dialog = new Dialog(MainActivity.this);
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_custom);
        // Không cho cancel khi click bên ngoài
        dialog.setCanceledOnTouchOutside(false);

        EditText edtUserName = (EditText) dialog.findViewById(R.id.editTextUserName);
        EditText edtPassword = (EditText) dialog.findViewById(R.id.editTextPassword);
        Button btnOK = (Button) dialog.findViewById(R.id.buttonOK);
        Button btnCancel = (Button) dialog.findViewById(R.id.buttonCancel);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edtUserName.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if(userName.equals("hvcong") && password.equals("123")){
                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Login fail", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cancel dialog
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void MappingUI(){
        lvName = (ListView) findViewById(R.id.listviewName);

        btnLogin = (Button) findViewById(R.id.buttonLogin);
    }

    private void InitializeData(){
        arrayName = new ArrayList<>();
        arrayName.add("Android");
        arrayName.add(".Net");
        arrayName.add("IOS");
        arrayName.add("PHP");
    }

    private void DeleteConfirm(int position){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Notify!");
        alertDialog.setIcon(R.mipmap.ic_launcher);
        alertDialog.setMessage("Bạn có muốn xóa " + arrayName.get(position) + " không?");
        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                arrayName.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }
}