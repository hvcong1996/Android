package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imgViewAlpha, imgViewRotate, imgViewScale, imgViewTranslate;

    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MappingUI();

        // Get animation
        Animation animationAlpha = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_alpha);
        imgViewAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animationAlpha);
            }
        });

        // Get animation
        Animation animationRotate = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_rotate);
        imgViewRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animationRotate);
            }
        });

        // Get animation
        Animation animationScale = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_scale);
        imgViewScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animationScale);
            }
        });

        // Get animation
        Animation animationTranslate = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_tranlsate);
        imgViewTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animationTranslate);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));

                // Method để xử lý hiệu ứng chuyển activity
                overridePendingTransition(R.anim.anim_enter_activity, R.anim.anim_exit_activity);
            }
        });
    }

    private void MappingUI(){
        imgViewAlpha = (ImageView) findViewById(R.id.imageViewAlpha);
        imgViewRotate = (ImageView) findViewById(R.id.imageViewRotate);
        imgViewScale = (ImageView) findViewById(R.id.imageViewScale);
        imgViewTranslate = (ImageView) findViewById(R.id.imageViewTranslate);

        btnNext = (Button) findViewById(R.id.buttonNext);
    }
}