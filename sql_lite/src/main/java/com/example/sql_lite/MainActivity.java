package com.example.sql_lite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Database database;
    ListView lvJob;

    ArrayList<Job> arrayJob;
    JobAdapter jobAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();
        InitData();

        // Init database note.sql
        database = new Database(MainActivity.this, "note.sqlite", null, 1);

        // Create table job
        // autoincrement: tự động tăng dần
        database.QueryData("CREATE TABLE IF NOT EXISTS Job(Id INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR(250))");

        // Insert data
//        database.QueryData("INSERT INTO Job VALUES(null, 'The name 1')");
//        database.QueryData("INSERT INTO Job VALUES(null, 'The name 2')");
//        database.QueryData("INSERT INTO Job VALUES(null, 'The name 3')");

        // Get data
        Cursor dataJob = database.GetData("SELECT * FROM Job");

        // dataJob.moveToNext(): Trỏ đến đối tượng kế bên, nếu có data thì trỏ được
        while (dataJob.moveToNext()){
            // Con trỏ dataJob sẽ return về object(id, name) (0,1)
            // Get name nên sẽ lấy từ vị trí 1
            Integer id = dataJob.getInt(0);
            String name = dataJob.getString(1);

            arrayJob.add(new Job(id, name));
            //Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
        }
    }

    private void InitView(){
        lvJob = (ListView) findViewById(R.id.listViewJob);
    }

    private void InitData(){
        arrayJob = new ArrayList<>();

        jobAdapter = new JobAdapter(MainActivity.this, R.layout.job_row, arrayJob);

        lvJob.setAdapter(jobAdapter);
    }
}