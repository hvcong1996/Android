package com.example.media_mp3;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.MissingFormatArgumentException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnNextSong, btnPlaySong, btnPreviousSong, btnStopSong;
    TextView txtSongTitle, txtTimeSongLoading, txtTimeSongTotal;
    SeekBar sbSong;

    // Danh sách bài hát
    ArrayList<Song> songArrayList;
    // song index
    int songPosition = 0;

    MediaPlayer mediaPlayer;

    ImageView imgViewDisc;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();

        InitSong();

        InitMediaPlayer();

        // Event khi seek bar change
        sbSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            // Event khi user thực hiện thao tác thay đổi giá trị của seekbar
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Format time data mm:ss
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");

                // Set time for time loading
                txtTimeSongLoading.setText(simpleDateFormat.format(progress));
            }

            // Event khi user start touch seekbar
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            // Event khi user stop touch seekbar
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Set media player tới time được chỉ định
                mediaPlayer.seekTo(sbSong.getProgress());
            }
        });
    }

    private void InitView(){
        btnNextSong = (Button) findViewById(R.id.buttonNextSong);
        btnNextSong.setOnClickListener(MainActivity.this);
        btnPlaySong = (Button) findViewById(R.id.buttonPlaySong);
        btnPlaySong.setOnClickListener(MainActivity.this);
        btnPreviousSong = (Button) findViewById(R.id.buttonPreviousSong);
        btnPreviousSong.setOnClickListener(MainActivity.this);
        btnStopSong = (Button) findViewById(R.id.buttonStopSong);
        btnStopSong.setOnClickListener(MainActivity.this);

        txtSongTitle = (TextView) findViewById(R.id.textViewSongTitle);
        txtTimeSongLoading = (TextView) findViewById(R.id.textViewTimeSongLoading);
        txtTimeSongTotal = (TextView) findViewById(R.id.textViewTimeSongTotal);

        sbSong = (SeekBar) findViewById(R.id.seekBarSong);

        imgViewDisc = (ImageView) findViewById(R.id.imageViewDisc);
    }

    private void InitSong(){
        songArrayList = new ArrayList<>();

        songArrayList.add(new Song("Anh mới chính là người em yêu", R.raw.anh_moi_chinh_la_nguoi_em_yeu));
        songArrayList.add(new Song("Đâu ai đợi mình", R.raw.dau_ai_doi_minh));
        songArrayList.add(new Song("Lạc đường", R.raw.lac_duong));
        songArrayList.add(new Song("Người ấy", R.raw.nguoi_ay));
        songArrayList.add(new Song("Từ khi gặp em", R.raw.tu_khi_gap_em));

        animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.disc_rotate);
    }

    private void InitMediaPlayer(){
        // Init bài hát với vị trí = songPosition
        mediaPlayer = MediaPlayer.create(MainActivity.this, songArrayList.get(songPosition).getFile());

        // Set title cho bài hát với vị trí = songPosition
        txtSongTitle.setText(songArrayList.get(songPosition).getTitle());

        SetTimeTotal();
    }

    private void SetTimeTotal(){
        // Format time data mm:ss
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");

        // getDuration: get thời lượng của media player được tính theo miliseconds
        txtTimeSongTotal.setText(simpleDateFormat.format(mediaPlayer.getDuration()));

        // Setting để giá trị maximum của seekBar match với giá trị thời gian của media player(mediaPlayer.getDuration())
        sbSong.setMax(mediaPlayer.getDuration());
    }

    private void UpdateTimeSong(){
        Handler handler = new Handler();
        // postDelayed(Runnable: Xử lý execute, thời gian(milis) delay đến khi execute)
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Format time data mm:ss
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");

                // Set time for time loading
                // mediaPlayer.getCurrentPosition(): Lấy vị trí phát hiện tại của media
                txtTimeSongLoading.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));

                // Set progess for seekbar
                // mediaPlayer.getCurrentPosition(): Lấy vị trí phát hiện tại của media
                sbSong.setProgress(mediaPlayer.getCurrentPosition());

                // If song completed -> next song
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        // Tăng position lên
                        songPosition ++;
                        // Check to reset positon
                        if(songPosition > songArrayList.size() - 1){
                            songPosition = 0;
                        }

                        // If media Playing then stop it
                        if(mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                        }

                        // Init Media Player
                        InitMediaPlayer();

                        // Start media Player with the positon reseted
                        mediaPlayer.start();

                        // Set text play song is PAUSE
                        btnPlaySong.setText("PAUSE");
                    }
                });

                // Đệ quy lại xử lý của handler
                handler.postDelayed(this, 500);
            }
        }, 100);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonNextSong:
                // Tăng position lên
                songPosition ++;
                // Check to reset positon
                if(songPosition > songArrayList.size() - 1){
                    songPosition = 0;
                }

                // If media Playing then stop it
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }

                // Init Media Player
                 InitMediaPlayer();

                // Start media Player with the positon reseted
                mediaPlayer.start();

                // Set text play song is PAUSE
                btnPlaySong.setText("PAUSE");

                imgViewDisc.startAnimation(animation);
                break;
            case R.id.buttonPlaySong:
                // Check media is playing
                // If playing then pause
                // If pausing then play
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();

                    btnPlaySong.setText("PLAY");
                }
                else {
                    mediaPlayer.start();

                    btnPlaySong.setText("PAUSE");
                }

                // Update time song
                UpdateTimeSong();

                imgViewDisc.startAnimation(animation);
                break;
            case R.id.buttonPreviousSong:
                songPosition --;
                // Check to reset positon
                if(songPosition < 0){
                    songPosition = songArrayList.size() - 1;
                }

                // If media Playing then stop it
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }

                // Init Media Player
                 InitMediaPlayer();

                // Start media Player with the positon reseted
                mediaPlayer.start();

                // Set text play song is PAUSE
                btnPlaySong.setText("PAUSE");

                imgViewDisc.startAnimation(animation);
                break;
            case R.id.buttonStopSong:

                mediaPlayer.stop();
                // Release the mediaPlayer
                // mediaPlayer.release();

                imgViewDisc.clearAnimation();

                // Update text play
                btnPlaySong.setText("PLAY");

                // (Trường hợp user click stop sau đó click start)
                // Re-Init media player
                 InitMediaPlayer();
                break;
        }
    }
}