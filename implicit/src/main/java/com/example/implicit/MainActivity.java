package com.example.implicit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Notification;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnOpenBrowser;
    Button btnSendMessage;
    Button btnCall;
    Button btnOpenCamera;
    ImageView imageCameraImage;

    int REQUEST_CODE_CAMERA = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MappingUI();

        btnOpenBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://viblo.asia/p/android-lifecycle-L4x5x0v15BM"));
                startActivity(intent);
            }
        });

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SENDTO);
                // key word [sms_body] là định dạng chuẩn để xác định name body name message
                intent.putExtra("sms_body","Xin chao ...");
                // key word [sms:] là định dạng chuẩn để xác định số điện thoại send message
                intent.setData(Uri.parse("sms:0983655051"));
                startActivity(intent);
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                 intent.setAction(Intent.ACTION_DIAL);
                // key word [tel:] là định dạng chuẩn để xác định số điện thoại call
                intent.setData(Uri.parse("tel:0983655051"));
                startActivity(intent);
            }
        });

        btnOpenCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Hiển thị request open camera
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CAMERA},
                        REQUEST_CODE_CAMERA);

//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }
        });
    }

    // method callback khi user accept/cancel việc truy câp vào camera
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        // user accept truy câp vào camera
        if(requestCode == REQUEST_CODE_CAMERA && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
        }
        else {
            Toast.makeText(MainActivity.this, "Cancel open camera...", Toast.LENGTH_SHORT).show();
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    // method callback khi user chụp hình và chọn lưu hình ảnh, sẽ return về hình ảnh lưu đó
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intentData) {
        if(requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && intentData != null){
            // key word [data] là định dạng chuẩn của camera
            Bitmap bitmap = (Bitmap) intentData.getExtras().get("data");
            imageCameraImage.setImageBitmap(bitmap);
        }

        super.onActivityResult(requestCode, resultCode, intentData);
    }

    private void MappingUI(){
        btnOpenBrowser = (Button) findViewById(R.id.buttonOpenBrowser);
        btnSendMessage = (Button) findViewById(R.id.buttonSendMessage);
        btnCall = (Button) findViewById(R.id.buttonCall);
        btnOpenCamera = (Button) findViewById(R.id.buttonOpenCamera);
        imageCameraImage = (ImageView) findViewById(R.id.imageViewCameraImage);
    }
}