package com.example.volley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnStringRequest, btnJsonObjectRequest, btnJsonArrayRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();
    }

    private void InitView(){
        btnStringRequest = (Button) findViewById(R.id.buttonStringRequest);
        btnStringRequest.setOnClickListener(MainActivity.this);

        btnJsonObjectRequest = (Button) findViewById(R.id.buttonJsonObjectRequest);
        btnJsonObjectRequest.setOnClickListener(MainActivity.this);

        btnJsonArrayRequest = (Button) findViewById(R.id.buttonJsonArrayRequest);
        btnJsonArrayRequest.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonStringRequest:
                VolleyStringRequest();
                break;
            case R.id.buttonJsonObjectRequest:
                VolleyJsonObjectRequest();
                break;
            case  R.id.buttonJsonArrayRequest:
                VolleyJsonArrayRequest();
                break;
        }
    }

    // String request using Volley
    private void VolleyStringRequest(){
        // Create RequestQueue using Volley
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        String url = "https://online.khoapham.vn/";

        // StringRequest(method, url, response listener, response error)
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Processing fail", Toast.LENGTH_SHORT).show();
                        Log.d("Error", "Response error: " + error.toString());
                    }
                }
        );

        // Add request to RequestQueue
        // RequestQueue auto execute
        requestQueue.add(stringRequest);
    }

    // Json object request using Volley
    private void VolleyJsonObjectRequest(){
        // Create RequestQueue using Volley
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        String url = "https://khoapham.vn/KhoaPhamTraining/json/tien/demo1.json";

        // JsonObjectRequest(method, url, the json object to push with request, response listener, response error)
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String monhoc = response.getString("monhoc");
                            String website = response.getString("website");
                            Toast.makeText(MainActivity.this, monhoc + "\n" + website, Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Processing fail", Toast.LENGTH_SHORT).show();
                        Log.d("Error", "Response error: " + error.toString());
                    }
                }
        );

        // Add request to RequestQueue
        // RequestQueue auto execute
        requestQueue.add(jsonObjectRequest);
    }

    // Json array request using Volley
    private void VolleyJsonArrayRequest(){
        // Create RequestQueue using Volley
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        String url = "https://khoapham.vn/KhoaPhamTraining/json/tien/demo4.json";

        // JsonArrayRequest(method, url, the json array to push with request, response listener, response error)
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String khoahoc = jsonObject.getString("khoahoc");
                                String hocphi = jsonObject.getString("hocphi");

                                Toast.makeText(MainActivity.this,  khoahoc + "\n" + hocphi, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Processing fail", Toast.LENGTH_SHORT).show();
                        Log.d("Error", "Response error: " + error.toString());
                    }
                }
        );

        requestQueue.add(jsonArrayRequest);
    }
}