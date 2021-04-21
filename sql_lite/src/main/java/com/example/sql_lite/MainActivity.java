package com.example.sql_lite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
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

        // Init database note.sqlite
        database = new Database(MainActivity.this, "note.sqlite", null, 1);

        // Create table job
        // autoincrement: tự động tăng dần
        // Tạo bảng Job(Id,Name)
        database.QueryData("CREATE TABLE IF NOT EXISTS Job(Id INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR(250))");

        // Insert data
//        database.QueryData("INSERT INTO Job VALUES(null, 'The name 1')");
//        database.QueryData("INSERT INTO Job VALUES(null, 'The name 2')");
//        database.QueryData("INSERT INTO Job VALUES(null, 'The name 3')");

        GetJobs();
    }

    private void GetJobs(){
        // Get data
        Cursor dataJob = database.GetData("SELECT * FROM Job");

        // Clear mảng trước khi get từ database để add vào lại
        arrayJob.clear();

        // dataJob.moveToNext(): Trỏ đến đối tượng kế bên, nếu có data thì trỏ được
        while (dataJob.moveToNext()){
            // Con trỏ dataJob sẽ return về object(id, name) (0,1)
            // Get name nên sẽ lấy từ vị trí 1
            Integer id = dataJob.getInt(0);
            String name = dataJob.getString(1);

            arrayJob.add(new Job(id, name));
            //Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
        }

        // Vì job adapter có view holder giữ chỗ, nên khi delete thì cần phải call set adapter lần nữa để reload lại view holder
        lvJob.setAdapter(jobAdapter);
    }

    private void InitView(){
        lvJob = (ListView) findViewById(R.id.listViewJob);
    }

    private void InitData(){
        arrayJob = new ArrayList<>();

        jobAdapter = new JobAdapter(MainActivity.this, R.layout.job_row, arrayJob);

        lvJob.setAdapter(jobAdapter);
    }

    // Event để init menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // inflate the menu item R.menu.add_job to menu
        getMenuInflater().inflate(R.menu.add_job, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Event khi click vào item trên menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // If user click vào menuAddItem thì tiến hành thực hiện add
        if(item.getItemId() == R.id.menuAddItem){
            AddJobDiglog();
        }

        return super.onOptionsItemSelected(item);
    }

    private void AddJobDiglog(){
        Dialog dialog = new Dialog(MainActivity.this);
        // Bỏ title trong dialog
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Set nội dung dialog là add job dialog
        dialog.setContentView(R.layout.dialog_add_job);

        // Không close dialog nếu user touch out side
        dialog.setCanceledOnTouchOutside(false);

        EditText edtJobName = dialog.findViewById(R.id.editTextJobName);
        Button btnAddJob = dialog.findViewById(R.id.buttonAddJob);
        Button btnCancelAddJob = dialog.findViewById(R.id.buttonCancelAddJob);

        btnCancelAddJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                dialog.dismiss();
            }
        });

        btnAddJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jobName = edtJobName.getText().toString();
                if(jobName.equals("")){
                    Toast.makeText(MainActivity.this, "Add job fail", Toast.LENGTH_SHORT).show();
                }
                else {
                    // Add job to Database
                    database.QueryData("INSERT INTO Job VALUES(null, '" + jobName + "')");
                    Toast.makeText(MainActivity.this, "Add job successful", Toast.LENGTH_SHORT).show();

                    // Close dialog
                    dialog.dismiss();

                    // Get lại thông tin Job lần nữa
                    GetJobs();
                }
            }
        });

        dialog.show();
    }

    public void EditJobDialog(Job job){
        Dialog dialog = new Dialog(MainActivity.this);
        // Bỏ title trong dialog
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Set nội dung dialog là add job dialog
        dialog.setContentView(R.layout.dialog_edit_job);

        // Không close dialog nếu user touch out side
        dialog.setCanceledOnTouchOutside(false);

        EditText edtJobName = dialog.findViewById(R.id.editTextJobName);
        Button btnEditJob = dialog.findViewById(R.id.buttonEditJob);
        Button btnCancelEditJob = dialog.findViewById(R.id.buttonCancelEditJob);

        // Set job name cho edit text
        edtJobName.setText(job.getName());

        btnCancelEditJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                dialog.dismiss();
            }
        });

        btnEditJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jobName = edtJobName.getText().toString().trim();
                if(jobName.equals("")){
                    Toast.makeText(MainActivity.this, "Edit job fail", Toast.LENGTH_SHORT).show();
                }
                else {
                    // Add job to Database
                    database.QueryData("UPDATE Job SET Name = '"+ jobName +"' WHERE Id = '"+ job.getId() +"'");
                    Toast.makeText(MainActivity.this, "Edit job successful", Toast.LENGTH_SHORT).show();

                    // Close dialog
                    dialog.dismiss();

                    // Get lại thông tin Job lần nữa
                    GetJobs();
                }
            }
        });

        dialog.show();
    }

    public void DeleteJobDialog(Job job){
        // Sử dụng tạm thời AlertDialog để thực hiện chức năng delete
        AlertDialog.Builder builderDialogDelete = new AlertDialog.Builder(MainActivity.this);

        // Messsage alert
        builderDialogDelete.setMessage("Bạn có muốn delete job: " + job.getName() + " ?");

        // Tạo yes button trên alert
        // Set event khi nhấn yes button trên alert
        builderDialogDelete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Delete data from database
                database.QueryData("DELETE FROM Job WHERE Id = '" + job.getId() + "'");
                Toast.makeText(MainActivity.this, "Delete job " + job.getName(), Toast.LENGTH_SHORT).show();

                // Get lại thông tin Job lần nữa
                GetJobs();
            }
        });

        // Tạo no button trên alert
        // Set event khi nhấn no button trên alert
        builderDialogDelete.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builderDialogDelete.show();
    }
}