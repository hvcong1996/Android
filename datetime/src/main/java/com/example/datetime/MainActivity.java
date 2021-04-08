package com.example.datetime;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText edtDate1, edtDate2;
    Button btnCount;
    TextView txtTime, txtSelectTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MappingUI();

        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();

        edtDate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectDate(calendar1, edtDate1);
            }
        });

        edtDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectDate(calendar2, edtDate2);
            }
        });


        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int countDate = (int)(calendar2.getTimeInMillis() - calendar1.getTimeInMillis()) / (1000*60*60*24);
                if(countDate < 0 ){
                    Toast.makeText(MainActivity.this, "Select date again", Toast.LENGTH_SHORT).show();
                }
                else {
                    txtTime.setText("Số ngày xa nhau: " + countDate);
                }
            }
        });

        Calendar calendar3 = Calendar.getInstance();
        txtSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectTime(calendar3);
            }
        });
    }

    private void MappingUI(){
        edtDate1 = (EditText) findViewById(R.id.editTextDate1);
        edtDate2 = (EditText) findViewById(R.id.editTextDate2);
        btnCount = (Button) findViewById(R.id.buttonCount);
        txtTime = (TextView) findViewById(R.id.textViewTime);

        txtSelectTime = (TextView) findViewById(R.id.textViewSelectTime);
    }

    private void SelectTime(Calendar calendar){
        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

                        calendar.set(0,0,0, hourOfDay, minute);
                        txtSelectTime.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, calendar.get(calendar.HOUR_OF_DAY), calendar.get(calendar.MINUTE), true
        );

        timePickerDialog.show();
    }

    private Calendar SelectDate(Calendar calendar, EditText edtSelectDate){
        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edtSelectDate.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, calendar.get(calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(calendar.DATE));

        datePickerDialog.show();

        return  calendar;
    }
}