package com.example.yogapal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Date;

public class Calendar extends AppCompatActivity {

    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        MaterialCalendarView materialCalendarView = (MaterialCalendarView) findViewById(R.id.calendar);
        materialCalendarView.state().edit()

                .setFirstDayOfWeek(1)
                .setMinimumDate(CalendarDay.from(2020, 1, 1))
                .setMaximumDate(CalendarDay.from(2025, 1, 12))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Toast.makeText(Calendar.this,"Schedule Yoga Session For: " +date, Toast.LENGTH_SHORT).show();
            }
        });
        back =(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Calendar.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }


}
