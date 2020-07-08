package com.example.yogapal;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.util.AttributeSet;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;

public class CustomCalendarView extends LinearLayout {

    ImageButton NextBtn, PreviousButton;
    TextView CurrentDate;
    GridView gridview;
    private static final int MAX_CALENDAR_DAYS=42;
    Calendar calendar =Calendar.getInstance(Locale.ENGLISH);
    Context context;
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy",Locale.ENGLISH);
    SimpleDateFormat monthFormat= new SimpleDateFormat("MMMM",Locale.ENGLISH);
    SimpleDateFormat yearFormat= new SimpleDateFormat("yyyy",Locale.ENGLISH);

    MyGridAdapter myGridAdapter;

    AlertDialog alertDialog;
    List<Date> dates= new ArrayList<>();
    List<Events> eventsList =new ArrayList<>();
    DBOpenHelper dbOpenHelper;

    public CustomCalendarView(Context context) {
        super(context);
    }

    public CustomCalendarView(final Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
        this.context =context;
        IntializeLayout();
        SetUpCalendar();

        //Calendar SetUP
        PreviousButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH,-1);
                SetUpCalendar();
            }
        });
        NextBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH,1);
                SetUpCalendar();
            }
        });

gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        final View addView = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_newevent_layout, null);
        final EditText EventName = addView.findViewById(R.id.events_id);
        final TextView EventTime = addView.findViewById(R.id.eventtime);
        ImageButton SetTime = addView.findViewById(R.id.seteventtime);
        Button AddEvent = addView.findViewById(R.id.addevent);
        SetTime.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                final int hours = calendar.get(Calendar.HOUR);
                int minuts = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(addView.getContext(), R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        Calendar c = Calendar.getInstance();
                        c.set(Calendar.HOUR, hours);
                        c.set(Calendar.MINUTE, minute);
                        c.setTimeZone(TimeZone.getDefault());
                        SimpleDateFormat hformate = new SimpleDateFormat("K:mm a", Locale.ENGLISH);
                        String event_Time = hformate.format(c.getTime());
                        EventTime.setText(event_Time);

                    }
                }, hours, minuts, false);
                timePickerDialog.show();

            }
        });

        final String date = dateFormat.format(dates.get(position));
        final String month = monthFormat.format(dates.get(position));
        final String year = yearFormat.format(dates.get(position));

        AddEvent.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                SaveEvent(EventName.getText().toString(), EventTime.getText().toString(), date, month, year);
                SetUpCalendar();
                alertDialog.dismiss();
            }
        });
        builder.setView(addView);
        alertDialog = builder.create();
        alertDialog.show();

    }
});




    }

    public CustomCalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    private void SaveEvent(String event,String time,String date,String month,String year){

        dbOpenHelper = new DBOpenHelper(context);
        SQLiteDatabase database= dbOpenHelper.getWritableDatabase();
        dbOpenHelper.saveEvent(event,time,date,month,year,database);
        dbOpenHelper.close();
        Toast.makeText(context,"Yoga Session Scheduled", Toast.LENGTH_SHORT).show();

    }

    private void IntializeLayout(){
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.calendar_layout,this);
        NextBtn =view.findViewById(R.id.nextBtn);
        PreviousButton =view.findViewById(R.id.previousBtn);
        CurrentDate =view.findViewById(R.id.current_date);
        gridview=view.findViewById(R.id.gridview);
    }

private void SetUpCalendar(){
        String currentDate = dateFormat.format(calendar.getTime());
        CurrentDate.setText(currentDate);
        dates.clear();
        Calendar monthCalendar=(Calendar)calendar.clone();
        monthCalendar.set(Calendar.DAY_OF_MONTH,1);
        int FirstDayofMonth= monthCalendar.get(Calendar.DAY_OF_WEEK)-1;
        monthCalendar.add(Calendar.DAY_OF_MONTH, -FirstDayofMonth);

        while(dates.size()<MAX_CALENDAR_DAYS){
            dates.add(monthCalendar.getTime());
            monthCalendar.add(Calendar.DAY_OF_MONTH,1);
        }

        myGridAdapter=new MyGridAdapter(context,dates,calendar,eventsList);
        gridview.setAdapter(myGridAdapter);

}

private void CollectEventsPerMonth(String Month, String year){
        dbOpenHelper =new DBOpenHelper(context);
        SQLiteDatabase database= dbOpenHelper.getReadableDatabase();
    Cursor cursor = dbOpenHelper.ReadEventsperMonth(Month,year,database);
    while(cursor.moveToNext()){
        String event= cursor.getString(cursor.getColumnIndex(DBStructure.EVENT));
        String time= cursor.getString(cursor.getColumnIndex(DBStructure.TIME));
        String date= cursor.getString(cursor.getColumnIndex(DBStructure.DATE));
        String month= cursor.getString(cursor.getColumnIndex(DBStructure.MONTH));
        String Year= cursor.getString(cursor.getColumnIndex(DBStructure.YEAR));

        Events events =new Events(event,time,date,month,Year);
    }

}

}
