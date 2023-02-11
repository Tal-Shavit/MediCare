package com.example.medicare.Recycler;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicare.R;


public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView textViewName;
    TextView textViewTime;
    TextView textViewCount;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewName = itemView.findViewById(R.id.name_pill);
        textViewTime = itemView.findViewById(R.id.timeToTake);
        textViewCount = itemView.findViewById(R.id.countToTake);
    }
}
