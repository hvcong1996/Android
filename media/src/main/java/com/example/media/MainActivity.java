package com.example.media;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnPlayMP3, btnPlayMP4;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();
    }

    private void InitView(){
        btnPlayMP3 = (Button) findViewById(R.id.buttonPlayMP3);
        btnPlayMP3.setOnClickListener(MainActivity.this);

        btnPlayMP4 = (Button) findViewById(R.id.buttonPlayMP4);
        btnPlayMP4.setOnClickListener(MainActivity.this);

        videoView = (VideoView) findViewById(R.id.videoViewMP4);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonPlayMP3:
                MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.nguoi_ay);
                mediaPlayer.start();
                break;
            case R.id.buttonPlayMP4:
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.intro1));
                videoView.start();

                // Tạo thanh control để tùy chỉnh video
                MediaController mediaController = new MediaController(MainActivity.this);
                mediaController.setMediaPlayer(videoView);
                videoView.setMediaController(mediaController);
                break;
        }
    }
}