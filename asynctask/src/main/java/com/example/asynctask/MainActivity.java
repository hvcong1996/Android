package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    Button btnDownload;
    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MappingUI();

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Process process = new Process();
//                process.execute();
//                Toast.makeText(MainActivity.this, "Next step", Toast.LENGTH_SHORT).show();

                String imgURL = "https://vcdn-sohoa.vnecdn.net/2021/02/06/closeup-google-android-figure-4042-8267-1612580917.jpg";
//                new LoadIMGInternet().execute(imgURL);
                new ReadContentInternet().execute(imgURL);

            }
        });
    }

    private void MappingUI(){
        btnDownload = (Button) findViewById(R.id.buttonDownload);
        imgView = (ImageView) findViewById(R.id.imageView);
    }

    // String: url
    // Void: Giá trị published Progress để update UI (Giá trị sẽ được gửi đến onProgressUpdate)
    // String: Result của Background (text)
    private class ReadContentInternet extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {

            // Nếu text thay đổi nhiều thì nên sử dụng StringBuilder
            StringBuilder stringBuilder = new StringBuilder();

            try {
                // Get url
                URL url = new URL(strings[0]);

                // Connect url
                URLConnection urlConnection = url.openConnection();

                // Get stream (byte stream)
                InputStream inputStream = urlConnection.getInputStream();

                // Get stream(Convert byte stream to character stream)
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = null;

                // đọc thêm line mới nếu có (line = bufferedReader.readLine()) != null
                while (bufferedReader != null && (line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line + "\n");
                }

                bufferedReader.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
        }
    }

    // String: url
    // Void: Giá trị published Progress để update UI (Giá trị sẽ được gửi đến onProgressUpdate)
    // Bitmap: Result của Background (bitmap)
    private class LoadIMGInternet extends AsyncTask<String, Void, Bitmap>{
        @Override
        protected Bitmap doInBackground(String... strings) {

            Bitmap bitmapResult = null;
            try {
                URL url = new URL(strings[0]);

                // Get data form url
                InputStream inputStream = url.openConnection().getInputStream();

                // Decode to bitmap
                bitmapResult = BitmapFactory.decodeStream(inputStream);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmapResult;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            imgView.setImageBitmap(bitmap);
        }
    }


//    // AsyncTask<Params, Progress, Result>:
//    // Params: là giá trị khi execute sẽ truyền vào doInBackground
//    // Progress: Giá trị published Progress để update UI (Giá trị sẽ được gửi đến onProgressUpdate)
//    // Result: Result của Background (Giá trị sẽ được gửi đến onPostExecute)
//    private class Process extends AsyncTask<Void, Integer, String>{
//
//        // callback pre execute background
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            txtInfo.setText("Ready to Start. \n");
//        }
//
//        // callback background working
//        @Override
//        protected String doInBackground(Void... voids) {
//            String result = "Processing ...";
//            for (int i = 0; i <= 10; i++){
//                publishProgress(i);
//            }
//            return result;
//        }
//
//        // callback updateUI of background
//        @Override
//        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
//
//            txtInfo.append(values+"");
//        }
//
//        // callback background completed
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            String completed = "Completed. \n";
//            txtInfo.append(s);
//            txtInfo.append(completed);
//        }
//    }
}