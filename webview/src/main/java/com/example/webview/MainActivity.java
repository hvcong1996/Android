package com.example.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnBack, btnNext, btnReload, btnSearch;
    EditText edtInput;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MappingUI();

        // Setting để chỉ open trong webView chứ không redirect sang open ở trình duyệt.
        webView.setWebViewClient(new WebViewClient());

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = edtInput.getText().toString().trim();

                if(!url.startsWith("https://")) url = "https://" + url;
                webView.loadUrl(url);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kiểm tra webView có thể back về trang trước đó không, nếu có thì thực hiện back về
                if(webView.canGoBack()){
                    // Back về trang trước đó
                    webView.goBack();

                    // Set lại text cho text input
                    edtInput.setText(webView.getUrl());

                    Toast.makeText(MainActivity.this, "Back completed", Toast.LENGTH_SHORT);
                }
                else {
                    Toast.makeText(MainActivity.this, "Can't go back", Toast.LENGTH_SHORT);
                }
            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kiểm tra webView có thể next tới trang sau không, nếu có thì thực hiện next
                if(webView.canGoForward()){
                    webView.goForward();

                    // Set lại text cho text input
                    edtInput.setText(webView.getUrl());

                    Toast.makeText(MainActivity.this, "Go forward completed", Toast.LENGTH_SHORT);
                }
                else {
                    Toast.makeText(MainActivity.this, "Can't go forward", Toast.LENGTH_SHORT);
                }
            }
        });

        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reload WebView
                webView.reload();

                // Set lại text cho text input
                edtInput.setText(webView.getUrl());
            }
        });

        // Setting to can zoom in WebView
        WebSettings webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        // Hidden the control zoom if any in UI
        webSettings.setDisplayZoomControls(false);
        // Setting to can load script in website
        webSettings.setJavaScriptEnabled(true);
    }

    private void MappingUI(){
        btnBack = (Button) findViewById(R.id.buttonBack);
        btnNext = (Button) findViewById(R.id.buttonNext);
        btnReload = (Button) findViewById(R.id.buttonReload);
        btnSearch = (Button) findViewById(R.id.buttonSeach);

        edtInput = (EditText) findViewById(R.id.editTextInputUrl);
        webView = (WebView) findViewById(R.id.webView);
    }
}