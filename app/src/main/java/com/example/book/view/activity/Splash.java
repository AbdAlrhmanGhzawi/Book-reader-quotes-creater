package com.example.book.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.book.R;

public class Splash extends AppCompatActivity {
    TextView txtWelcome;
    TextView txtAReader;
    LottieAnimationView lottieAnimationView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        txtWelcome = findViewById(R.id.txtWelcome);
        txtAReader = findViewById(R.id.txtAReader);
        lottieAnimationView = findViewById(R.id.lottie);
        txtWelcome.animate().translationY(-2500).setDuration(1000).setStartDelay(5000);
        txtAReader.animate().translationY(2000).setDuration(1000).setStartDelay(5000);
        lottieAnimationView.animate().translationY(1500).setDuration(1000).setStartDelay(5000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this, MainActivity.class));
                finish();
            }

        },6000);

    }
}