package com.example.yogapal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import Adapter.RecyclerViewAdapter;
import Model.Exercise;

public class ListExercises extends AppCompatActivity {

List<Exercise> exerciseList = new ArrayList<>();
RecyclerView.LayoutManager layoutManager;
RecyclerView recyclerView;
RecyclerViewAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exercises);

        initData();
        recyclerView = (RecyclerView)findViewById(R.id.list_ex);
        adapter =new RecyclerViewAdapter(exerciseList,getBaseContext());
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
    }





    private void initData() {

        exerciseList.add(new Exercise(R.drawable.easy_pose,"Easy Pose"));
        exerciseList.add(new Exercise(R.drawable.bout_pose,"Easy Pose"));
        exerciseList.add(new Exercise(R.drawable.warrior_pose,"Warrior Pose"));

    }
}
