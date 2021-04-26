package com.example.fragment_baitap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class StudentInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        // Get Intent
        Intent intent = getIntent();
        // Get Student data from Intent
        Student student = (Student) intent.getSerializableExtra("StudentDetail");

        // Set data for fragmentStudentDetail
        FragmentStudentDetail fragmentStudentDetail = (FragmentStudentDetail) getSupportFragmentManager().findFragmentById(R.id.fragmentStudentDetail);
        fragmentStudentDetail.SetInfo(student);
    }

    @Override
    public void onBackPressed() {
        finish();
//        super.onBackPressed();
    }
}