package com.example.medicare.Recycler;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicare.R;
import com.example.medicare.Interface.RecyclerViewInterface;


public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView textViewName;
    TextView textViewTime;
    TextView textViewCount;

    public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
        super(itemView);
        textViewName = itemView.findViewById(R.id.name_pill);
        textViewTime = itemView.findViewById(R.id.timeToTake);
        textViewCount = itemView.findViewById(R.id.countToTake);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recyclerViewInterface != null) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION)
                        recyclerViewInterface.onItemClick(position);
                }
            }
        });
    }


}
