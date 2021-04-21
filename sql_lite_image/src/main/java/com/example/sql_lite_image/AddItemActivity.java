package com.example.sql_lite_image;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddItemActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnAdd, btnCancelAdd;
    EditText edtName, edtDescription;
    ImageButton imgButtonCamera, imgButtonFolder;
    ImageView imgViewImage;

    int REQUEST_CODE_CAMERA = 900;
    int REQUEST_CODE_FOLDER = 800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        InitView();
    }

    private void InitView(){
        btnAdd = (Button) findViewById(R.id.buttonAdd);
        btnAdd.setOnClickListener(AddItemActivity.this);
        btnCancelAdd = (Button) findViewById(R.id.buttonCancelAdd);
        btnCancelAdd.setOnClickListener(AddItemActivity.this);

        edtName = (EditText) findViewById(R.id.editTextItemName);
        edtDescription = (EditText) findViewById(R.id.editTextItemDescription);

        imgButtonCamera = (ImageButton) findViewById(R.id.imageButtonCamera);
        imgButtonCamera.setOnClickListener(AddItemActivity.this);
        imgButtonFolder = (ImageButton) findViewById(R.id.imageButtonFolder);
        imgButtonFolder.setOnClickListener(AddItemActivity.this);
        imgViewImage = (ImageView) findViewById(R.id.imageViewImage);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.buttonAdd:
                // Convert imageView to byte[] to insert database
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgViewImage.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                // Nén lại kiểu dữ liệu cho bitmap
                // quality: chất lượng hình ảnh, default là 100
                // byteArrayOutputStream: Output stream của xử lý compressed data
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                // Get OutputStream với kiểu byte[]
                byte[] byteImage = byteArrayOutputStream.toByteArray();

                // Xử lý insert item vào database
                MainActivity.database.InsertItem(
                        edtName.getText().toString().trim(),
                        edtDescription.getText().toString().trim(),
                        byteImage
                );

                // Toast message successful
                Toast.makeText(AddItemActivity.this, "Add item successful", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonCancelAdd:
                // Close current activity
                finish();
                break;
            case R.id.imageButtonCamera:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
                break;
            case R.id.imageButtonFolder:
                intent = new Intent(Intent.ACTION_PICK);

                // Set explicit data type chỉ là loại hình ảnh
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_FOLDER);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == REQUEST_CODE_CAMERA && data != null && resultCode == RESULT_OK){
            // get with key default is [data]
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgViewImage.setImageBitmap(bitmap);
        }

        if(requestCode == REQUEST_CODE_FOLDER && data != null && resultCode == RESULT_OK){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgViewImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}