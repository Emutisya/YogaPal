package com.example.yogapal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class timer extends AppCompatActivity {
    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;


    private long mStartTimeInMillis;
    private EditText mEditTextInput;
    private Button mButtonSet;

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        mEditTextInput=findViewById(R.id.edit_text_input);
        mButtonSet=findViewById(R.id.button_set);
        mButtonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String input= mEditTextInput.getText().toString();
                if(input.length()==0){
                    Toast.makeText(timer.this,"Field can't be empty",Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });




        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }


    public void onClickStart(View view) {
        running = true;
    }


    public void onClickStop(View view) {
        running = false;
    }

    public void onClickReset(View view) {
        running = false;
        seconds = 0;

    }
    private void runTimer() {
        final TextView timeview = findViewById(R.id.watchScreen);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timeview.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
        back =(ImageView)findViewById(R.id.back);
       back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(timer.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("state", running);
        outState.putBoolean("wasRunning", wasRunning);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;

    }

}
