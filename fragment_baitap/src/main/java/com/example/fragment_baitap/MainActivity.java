package com.example.fragment_baitap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements IStudent {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void GetInfo(Student student) {
        // Mapping fragment Student Detail
        FragmentStudentDetail fragmentStudentDetail = (FragmentStudentDetail) getSupportFragmentManager().findFragmentById(R.id.fragmentStudentDetail);

        // Kiểm tra fragmentStudentDetail vì khi màn hình dọc thì fragmentStudentDetail không có trên UI, màn hình dọc sẽ xử lý redirect sang activity fragmentStudentDetail
        // Vì khi xoay ngang fragmentStudentDetail nằm trên màng hình nên fragmentStudentDetail sẽ != null, vì vậy sẽ vào case If này
        // Nhưng trong khi đó xoay dọc lại thì fragmentStudentDetail lại không có trên UI nên sẽ gây rã lỗi
        // Thêm điều kiện để đảm bảo có fragmentStudentDetail và fragmentStudentDetail có trong layout(fragmentStudentDetail.isInLayout())
        // Hoặc sử dụng điều kiện check màn hình nằm ngang(Configuration.ORIENTATION_LANDSCAPE) hoặc nằm dọc với Configuration
        Configuration configuration = getResources().getConfiguration();
        if(fragmentStudentDetail != null && configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            fragmentStudentDetail.SetInfo(student);
        }
        else {
            // Redirect sang màn hình fragmentStudentDetail
            Intent intent = new Intent(MainActivity.this, StudentInfo.class);
            // put Student object(implements Serializable) to fragmentStudentDetail
            intent.putExtra("StudentDetail", student);
            startActivity(intent);
        }

        // Set thông tin cho fragment detail
        fragmentStudentDetail.SetInfo(student);
    }
}