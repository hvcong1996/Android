package com.example.json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String jsonObjectUrl = "https://khoapham.vn/KhoaPhamTraining/json/tien/demo1.json";
    String jsonArrayUrl = "https://khoapham.vn/KhoaPhamTraining/json/tien/demo2.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new ReadJSON().execute(jsonObjectUrl);
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

//            Xử lý với json object
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

//             Xử lý với json array
            try {
                // Read json from (https://khoapham.vn/KhoaPhamTraining/json/tien/demo2.json)
                // Vì data trả về là json object bọc ngoài json array nên cần khai báo json object
                JSONObject object = new JSONObject(s);

                // Vì child item là kiểu Array nên sẽ getJSONArray
                JSONArray array = object.getJSONArray("danhsach");
                for(int i = 0; i < array.length(); i++){
                    // Vì child item trong array là kiểu object nên sẽ getJSONObject
                    JSONObject childObject = array.getJSONObject(i);

                    // Vì child item trong object là kiểu string nên sẽ getString
                    String khoahoc = childObject.getString("khoahoc");

                    Toast.makeText(MainActivity.this, "khoahoc", Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            // Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }
}