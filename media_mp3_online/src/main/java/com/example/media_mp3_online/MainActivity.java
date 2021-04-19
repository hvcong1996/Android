package com.example.media_mp3_online;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Note: Chức năng online chỉ support cho những link mp3/mp4 không bị redirect sang download

    Button btnPlayMp3, btnPlayMp4;
    ProgressBar pbLoading;
    VideoView vViewMp4;
    String urlMp3 = "https://khoapham.vn/download/vietnamoi.mp3";
    String urlMp4 = "https://khoapham.vn/download/vuoncaovietnam.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();
    }

    private void InitView(){
        btnPlayMp3 = (Button) findViewById(R.id.buttonPlayMP3);
        btnPlayMp3.setOnClickListener(MainActivity.this);

        btnPlayMp4 = (Button) findViewById(R.id.buttonPlayMP4);
        btnPlayMp4.setOnClickListener(MainActivity.this);

        pbLoading = (ProgressBar) findViewById(R.id.progressBarLoading);
        pbLoading.setVisibility(View.GONE);

        vViewMp4 = (VideoView) findViewById(R.id.videoView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonPlayMP3:
                // Show processBar
                pbLoading.setVisibility(View.VISIBLE);

                MediaPlayer mediaPlayer = new MediaPlayer();
                // Set type stream music for media Player
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                try {
                    mediaPlayer.setDataSource(urlMp3);

                    // Get mediaPlayer from server bất đồng bộ
                    mediaPlayer.prepareAsync();

                    // Event khi listener được media Player ở server
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            // Start media Player
                            mp.start();

                            // Gone processBar
                            pbLoading.setVisibility(View.GONE);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.buttonPlayMP4:
                Uri uri = Uri.parse(urlMp4);
                vViewMp4.setVideoURI(uri);
                vViewMp4.setMediaController(new MediaController(MainActivity.this));
                vViewMp4.start();
                break;
        }
    }
}