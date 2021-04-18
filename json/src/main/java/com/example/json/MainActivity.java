package com.example.json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnVietNam, btnEnglish;
    TextView txtInfo;
    ListView lvInfo;
    String Info = "";

    ArrayList<String> arrayCourse;
    ArrayAdapter arrayAdapter;

    String jsonObjectUrl = "https://khoapham.vn/KhoaPhamTraining/json/tien/demo1.json";
    String jsonArrayUrl = "https://khoapham.vn/KhoaPhamTraining/json/tien/demo2.json";
    String jsonUrl = "https://khoapham.vn/KhoaPhamTraining/json/tien/demo3.json";
    String jsonArrayObjectUrl = "https://khoapham.vn/KhoaPhamTraining/json/tien/demo4.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();


//        new ReadJSON().execute(jsonObjectUrl);
//        new ReadJSON().execute(jsonArrayUrl);

//        new ReadJSON().execute(jsonUrl);
        new ReadJSON().execute(jsonArrayObjectUrl);
    }

    private void InitView(){
        btnVietNam = (Button) findViewById(R.id.buttonVietNam);
        btnVietNam.setOnClickListener(MainActivity.this);
        btnEnglish = (Button) findViewById(R.id.buttonEnglish);
        btnEnglish.setOnClickListener(MainActivity.this);

        txtInfo = (TextView) findViewById(R.id.textViewInfo);

        lvInfo = (ListView) findViewById(R.id.listViewInfo);
        arrayCourse = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arrayCourse);
        lvInfo.setAdapter(arrayAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonVietNam:
                String getVNInfo = ReadObjectLanguage(Info, "vn");
                txtInfo.setText(getVNInfo);
//                Toast.makeText(MainActivity.this, vnInfo, Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonEnglish:
                String getENInfo = ReadObjectLanguage(Info, "en");
                txtInfo.setText(getENInfo);
//                Toast.makeText(MainActivity.this, enInfo, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private class ReadJSON extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {

            // Sử dụng StringBuilder để có thể append
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);

                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = null;
                while ((line = bufferedReader.readLine()) != null){
                    content.append(line);
                }

                inputStreamReader.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

//            Xử lý với json object demo1
//            try {
//                JSONObject object = new JSONObject(s);
//
//                // Read json from (https://khoapham.vn/KhoaPhamTraining/json/tien/demo1.json)
//                // Vì từng item là kiểu string nên sẽ getString
//                String monhoc = object.getString("monhoc");
//                String website = object.getString("website");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

//            Xử lý với json array demo2
//            try {
//                // Read json from (https://khoapham.vn/KhoaPhamTraining/json/tien/demo2.json)
//                // Vì data trả về là json object bọc ngoài json array nên cần khai báo json object
//                JSONObject object = new JSONObject(s);
//
//                // Vì child item là kiểu Array nên sẽ getJSONArray
//                JSONArray array = object.getJSONArray("danhsach");
//                for(int i = 0; i < array.length(); i++){
//                    // Vì child item trong array là kiểu object nên sẽ getJSONObject
//                    JSONObject childObject = array.getJSONObject(i);
//
//                    // Vì child item trong object là kiểu string nên sẽ getString
//                    String khoahoc = childObject.getString("khoahoc");
//
//                    Toast.makeText(MainActivity.this, "khoahoc", Toast.LENGTH_LONG).show();
//                }
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

//            Xử lý với json object demo3
//            try {
//                JSONObject object = new JSONObject(s);
//
//                JSONObject languageObject = object.getJSONObject("language");
//                JSONObject vnObject = languageObject.getJSONObject("vn");
//                JSONObject enObject = languageObject.getJSONObject("en");
//
//                vnInfo = vnObject.getString("name") + "\n";
//                vnInfo = vnInfo + vnObject.getString("address");
//
//                enInfo = enObject.getString("name") + "\n";
//                enInfo = enInfo + enObject.getString("address");
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

//            Info = s;

//            Xử lý với json object demo4
            try {
                JSONArray jsonArray = new JSONArray(s);

                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String khoahoc = jsonObject.getString("khoahoc");
                    String hocphi = jsonObject.getString("hocphi");

                    arrayCourse.add(khoahoc + "\n" + hocphi);
                }

                // Notify changed
                arrayAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private String ReadObjectLanguage(String json, String lang){
        String info = "";
        try {
            JSONObject object = new JSONObject(json);

            JSONObject languageObject = object.getJSONObject("language");
            JSONObject typeLanguageObject = languageObject.getJSONObject(lang);

            info = typeLanguageObject.getString("name") + "\n";
            info = info + typeLanguageObject.getString("address");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return info;
    }
}