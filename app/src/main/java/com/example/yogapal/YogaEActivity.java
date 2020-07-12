package com.example.yogapal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class YogaEActivity extends AppCompatActivity {
    Adapter adapter;
    ArrayList<String> items;
RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga_e);


        items=new ArrayList<>();
        items.add("Warrior pose");
        items.add("High stretch");
        items.add("Low stretch");
        items.add("Fourth Workout");
        items.add("Fifth Workout");



        recyclerView=findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new Adapter (this,items);
        recyclerView.setAdapter(adapter);

    }
}
