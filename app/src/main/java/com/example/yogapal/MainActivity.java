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

    Button btnExercises,btnTimer,btnCalendar;
    ImageView btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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



        btnExercises =(Button)findViewById(R.id.btnExercise);
        btnExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ListExercises.class);
                startActivity(intent);
            }
        });

        btnTimer =(Button)findViewById(R.id.btnTimer);
        btnTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,timer.class);
                startActivity(intent);
            }
        });
            }
    }

