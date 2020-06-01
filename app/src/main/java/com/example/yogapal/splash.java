package com.example.yogapal;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;


import com.airbnb.lottie.LottieAnimationView;

public class splash extends AppCompatActivity {
    private com.airbnb.lottie.LottieAnimationView LottieAnimationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        LottieAnimationView =findViewById(R.id.lottie_layer_name);
        LottieAnimationView.addAnimatorListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

               //Intent intent = new Intent(splash.this,MainActivity.class);
               startActivity(new Intent(splash.this, MainActivity.class));
               finish();
              //  startActivity(intent);
            }
        });
        }


    }




