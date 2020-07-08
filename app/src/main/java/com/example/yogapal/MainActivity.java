package com.example.yogapal;

import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
//btn btnExercises
    Button btnYoga,btnMeditation,btnCalendar;
  //  ImageView btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        final MediaPlayer meditation = MediaPlayer.create(this, R.raw.meditation);
        btnPlay =(ImageView)findViewById(R.id.btnPlay);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // If the music is playing
                if(meditation.isPlaying())
                    // Pause the music player
                    meditation.pause();
                    // If it's not playing
                else
                    // Resume the music player
                    meditation.start();



            }
        });

*/

        btnYoga =(Button)findViewById(R.id.btnYoga);
        btnYoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,YogaActivity.class);
                startActivity(intent);
            }
        });

/*
        btnCalendar =(Button)findViewById(R.id.btnCalendar);
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CustomCalendarViewWithEvents.class);
                startActivity(intent);
            }
        });
        */


        btnMeditation =(Button)findViewById(R.id.btnMeditation);
        btnMeditation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MeditationActivity.class);
                startActivity(intent);
            }
        });
            }
    }

