package com.example.explicit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnNext;
    Button btnShowDialog;

    TextView txtResult;

    int REQUEST_CODE_EDIT = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MappingUI();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent(MainActivity.this, SecondActivity.class);

//                String[] arrayCourse = {"1", "2", "3", "4", "5"};
//                ArrayList<String> lists = new ArrayList<String>() {{
//                    add("SO 1");
//                    add("SO 2");
//                    add("SO 3");
//                    add("SO 4");
//                }};
//                Student student = new Student("Tên Hoc Sinh", 21);
//
//                Bundle bundle = new Bundle();
//                bundle.putString("StringNe", "The Text");
//                bundle.putInt("IntNe", 2021);
//                bundle.putStringArrayList("ArrayListNe", lists);
//                bundle.putStringArray("ArrayNe", arrayCourse);
//                bundle.putSerializable("SerializableNe", student);
//
//                intent.putExtra("Data", bundle);

                // intent.putExtra("SendData", student);

//                startActivity(intent);


                // Intent data result
                    startActivityForResult(intent, REQUEST_CODE_EDIT);
            }
        });

        btnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog();
            }
        });
    }

    // method callback when Intent return result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intentData) {

        if(requestCode == REQUEST_CODE_EDIT && resultCode == RESULT_OK && intentData != null){
            String data = intentData.getStringExtra("TheName");
            txtResult.setText(data);
        }

        super.onActivityResult(requestCode, resultCode, intentData);
    }

    private void MappingUI(){
        btnNext = (Button) findViewById(R.id.buttonNext);

        btnShowDialog = (Button) findViewById(R.id.buttonShowDialog);

        txtResult = (TextView) findViewById(R.id.textViewResult);
    }

    private void CustomDialog(){
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_custom);
        dialog.setCanceledOnTouchOutside(false);

        Button btnClose = (Button) dialog.findViewById(R.id.buttonClose);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}