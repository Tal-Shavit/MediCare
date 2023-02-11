package com.example.medicare.Recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicare.Model.Pill_Item;
import com.example.medicare.R;
import com.example.medicare.RecyclerViewInterface;

import java.util.ArrayList;

public class Adapter_calander extends RecyclerView.Adapter<Adapter_calander.MyViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    private ArrayList<Pill_Item> pillItemArrayList;
    private Pill_Item pill_item;
    private Context context;

    public Adapter_calander(Context context,ArrayList<Pill_Item> pillItemArrayList,RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.pillItemArrayList = pillItemArrayList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        pill_item = pillItemArrayList.get(position);
        holder.time.setText(""+pill_item.getTimeToTake());
        holder.pill_name.setText(""+pill_item.getNamePill());
        holder.count_pill.setText(""+pill_item.getCountToTake());
        //holder.cal_IMG_image.setImageResource();
    }

    @Override
    public int getItemCount() {
        return pillItemArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView time;
        TextView pill_name;
        TextView count_pill;
        ImageView cal_IMG_image;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface){
            super((itemView));
            time = itemView.findViewById(R.id.time);
            pill_name = itemView.findViewById(R.id.pill_name);
            count_pill = itemView.findViewById(R.id.count_pill);

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
}
