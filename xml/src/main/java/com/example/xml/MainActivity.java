package com.example.xml;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCreate, btnRead;

    private File myExternalFile;

    private String url = "https://flexwpstorage.blob.core.windows.net/autoupdate/passagedrivebox/versioninfo.xml";

    private ListView lvXMLInfo;
    public ArrayList<String> arrayList;
    public ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();

        // Nếu không có bộ nhớ ngoài SDCard hoặc không truy cập được thì disable tính năng
//        if(!isExternalStorageAvailable() || isExternalStorageReadOnly()){
//            btnRead.setEnabled(false);
//            btnCreate.setEnabled(false);
//        }
//        else {
//            String filePath = "MyFileStorage";
//            String fileName = "data.txt";
//
//            // Create file info
//            myExternalFile = new File(getExternalFilesDir(filePath), fileName);
//        }

        // new ReadXML().execute("");
    }

    // Initialize View
    private void InitView(){
        btnCreate = (Button) findViewById(R.id.buttonCreate);
        btnCreate.setOnClickListener(MainActivity.this);

        btnRead = (Button) findViewById(R.id.buttonRead);
        btnRead.setOnClickListener(MainActivity.this);

        lvXMLInfo = (ListView) findViewById(R.id.listViewXMLInfo);

        arrayList = new ArrayList<>();
        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arrayList);
        lvXMLInfo.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonCreate:
                // CreateXMLFileInInternalDevice(myExternalFile);
                Toast.makeText(MainActivity.this, "Create", Toast.LENGTH_SHORT).show();

                break;
            case R.id.buttonRead:
                // String data = ReadXMLFileInInternalDevice(myExternalFile).toString();
                // Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();

                // Xử lý load XML file online
                new LoadXMLOnline(MainActivity.this).execute(url);
                break;
        }
    }

    // Create file internal device
    private void CreateXMLFileInInternalDevice(File fileInfo){
        try {
            FileOutputStream outputStream = new FileOutputStream(fileInfo);

            outputStream.write("Write data 1".getBytes());
            outputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read file external device
    private StringBuilder ReadXMLFileInInternalDevice(File fileInfo){
        StringBuilder content = new StringBuilder();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(fileInfo);
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));

            String line;
            while ((line = bufferedReader.readLine()) != null){
                content.append(line);
            }

            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    // Kiểm tra xem bộ nhớ ngoài SDCard có read only không(nếu là read only thì không thể tiến hành tạo file được)
    private boolean isExternalStorageReadOnly(){
        String extStorageState = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)){
            return true;
        }
        return false;
    }

    // Kiểm tra bộ nhớ ngoài SDCard có tồn tại trên device không
    private boolean isExternalStorageAvailable(){
        String extStorageState = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(extStorageState)){
            return true;
        }
        return false;
    }
}