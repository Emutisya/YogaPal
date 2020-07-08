package com.example.yogapal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CustomCalendarViewWithEvents extends AppCompatActivity {
CustomCalendarView customCalendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_calendar_view_with_events);

        customCalendarView=(CustomCalendarView)findViewById(R.id.custom_calendar_view);
    }
}
