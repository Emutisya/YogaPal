package com.example.yogapal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder>{
    private LayoutInflater layoutInflater;
    private List<String> data;

    Adapter(Context context, List<String>data){
        this.layoutInflater=LayoutInflater.from(context);
        this.data=data;

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view =layoutInflater.inflate(R.layout.custom_view,viewGroup,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewholder, int i) {

        //BIND TEXT VIEW WITH DATA RECEIVED

        String title= data.get(i);
       viewholder.textTitle.setText(title);


       //SIMILARLY YOU CAN SET NEW IMAGES FOR EACH CARD AND DESCRIPTION





    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

       TextView textTitle,textDescription;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle=itemView.findViewById(R.id.textTitle);
            textDescription=itemView.findViewById(R.id.textDesc);
        }
    }
}
