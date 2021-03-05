package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtResult;
    CheckBox cbBird, cbDragon, cbFish, cbElephant;
    SeekBar sbBird, sbDragon, sbFish, sbElephant;
    Button btnStart;
    int Result = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Mapping UI
        MappingUI();

        // Disable SeekBar
        DisableSeekBar();

        // Load result to UI
        txtResult.setText(String.valueOf(Result));

        // Param 1: Tổng thời gian CountDown. Hết thời gian sẽ call onFinish
        // Param 2: Thời gian sẽ callback onTick.
        final CountDownTimer countDownTimer = new CountDownTimer(60000,300) {
            @Override
            public void onTick(long millisUntilFinished) {
                Random random = new Random();
                String toastString = null;
                boolean isCompleted = false;
                boolean isMatch = false;

                // Check Winner
                // API >= 5.0 thì mới có thể sử dụng được this.cancel()
                if(sbBird.getProgress() >= sbBird.getMax()){
                    isCompleted = true;

                    // Cancel process
                    this.cancel();

                    // Update result
                    if(cbBird.isChecked()) {
                        Result += 10;
                        isMatch = true;
                    }
                    else {
                        Result -= 10;
                    }
                    toastString = "Dragon winner";
                }
                if(sbDragon.getProgress() >= sbDragon.getMax()){
                    isCompleted = true;

                    // Cancel process
                    this.cancel();

                    // Update result
                    if(cbDragon.isChecked()) {
                        Result += 10;
                        isMatch = true;
                    }
                    else {
                        Result -= 10;
                    }
                    toastString = "Dragon winner";
                }
                if(sbFish.getProgress() >= sbFish.getMax()){
                    isCompleted = true;

                    // Cancel process
                    this.cancel();

                    // Update result
                    if(cbFish.isChecked()) {
                        Result += 10;
                        isMatch = true;
                    }
                    else {
                        Result -= 10;
                    }

                    toastString = "Fish winner";
                }
                if(sbElephant.getProgress() >= sbElephant.getMax()){
                    isCompleted = true;

                    // Cancel process
                    this.cancel();

                    // Update result
                    if(cbElephant.isChecked()) {
                        Result += 10;
                        isMatch = true;
                    }
                    else {
                        Result -= 10;
                    }

                    toastString = "Elephant winner";
                }

                if(isCompleted){
                    txtResult.setText(String.valueOf(Result));

                    if(isMatch) toastString = "You win, " + toastString;
                    else  toastString = "You lose, " + toastString;

                    Toast.makeText(MainActivity.this, toastString, Toast.LENGTH_LONG).show();

                    // Visible Start button
                    btnStart.setVisibility(View.VISIBLE);

                    // Enable check box
                    EnableCheckBox();
                }

                int sbBirdNumber = random.nextInt(5) + 1;
                int sbDragonNumber = random.nextInt(5) + 1;
                int sbFishNumber = random.nextInt(5) + 1;
                int sbElephantNumber = random.nextInt(5) + 1;

                sbBird.setProgress((sbBird.getProgress() + sbBirdNumber));
                sbDragon.setProgress((sbDragon.getProgress() + sbDragonNumber));
                sbFish.setProgress((sbFish.getProgress() + sbFishNumber));
                sbElephant.setProgress((sbElephant.getProgress() + sbElephantNumber));
            }

            @Override
            public void onFinish() {

            }
        };

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Disable check box
                DisableCheckBox();

                if(cbBird.isChecked() || cbDragon.isChecked() || cbFish.isChecked() || cbElephant.isChecked()){
                    sbBird.setProgress(0);
                    sbDragon.setProgress(0);
                    sbFish.setProgress(0);
                    sbElephant.setProgress(0);

                    btnStart.setVisibility((View.INVISIBLE));

                    countDownTimer.start();
                }
                else {
                    Toast.makeText(MainActivity.this, "Please checked to Start", Toast.LENGTH_SHORT).show();
                    EnableCheckBox();
                }
            }
        });

        cbBird.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    // Uncheck other items
                    cbDragon.setChecked(false);
                    cbFish.setChecked(false);
                    cbElephant.setChecked(false);
                }
            }
        });
        cbDragon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    // Uncheck other items
                    cbBird.setChecked(false);
                    cbFish.setChecked(false);
                    cbElephant.setChecked(false);
                }
            }
        });
        cbFish.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    // Uncheck other items
                    cbBird.setChecked(false);
                    cbDragon.setChecked(false);
                    cbElephant.setChecked(false);
                }
            }
        });
        cbElephant.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    // Uncheck other items
                    cbBird.setChecked(false);
                    cbDragon.setChecked(false);
                    cbFish.setChecked(false);
                }
            }
        });
    }

    private void MappingUI(){
        txtResult = (TextView) findViewById(R.id.textViewResult);

        cbBird = (CheckBox) findViewById(R.id.checkBoxBird);
        cbDragon = (CheckBox) findViewById(R.id.checkBoxDragon);
        cbFish = (CheckBox) findViewById(R.id.checkBoxFish);
        cbElephant = (CheckBox) findViewById(R.id.checkBoxElephant);

        sbBird = (SeekBar) findViewById(R.id.seekBarBird);
        sbDragon = (SeekBar) findViewById(R.id.seekBarDragon);
        sbFish = (SeekBar) findViewById(R.id.seekBarFish);
        sbElephant = (SeekBar) findViewById(R.id.seekBarElephant);

        btnStart = (Button) findViewById(R.id.buttonStart);
    }

    private void EnableCheckBox(){
        cbBird.setEnabled(true);
        cbDragon.setEnabled(true);
        cbElephant.setEnabled(true);
        cbFish.setEnabled(true);
    }
    private void DisableCheckBox(){
        cbBird.setEnabled(false);
        cbDragon.setEnabled(false);
        cbElephant.setEnabled(false);
        cbFish.setEnabled(false);
    }

    private void DisableSeekBar(){
        sbBird.setEnabled(false);
        sbDragon.setEnabled(false);
        sbFish.setEnabled(false);
        sbElephant.setEnabled(false);
    }
}