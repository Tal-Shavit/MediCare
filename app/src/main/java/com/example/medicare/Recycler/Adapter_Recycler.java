package com.example.medicare.Recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicare.Model.Pill_Item;
import com.example.medicare.R;
import com.example.medicare.Interface.RecyclerViewInterface;

import java.util.ArrayList;

public class Adapter_Recycler extends RecyclerView.Adapter<MyViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    private Context context;
    private ArrayList<Pill_Item> pillItemArrayList;
    private Pill_Item pill_item;

    public Adapter_Recycler(Context context, ArrayList<Pill_Item> pillItemArrayList, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.pillItemArrayList = pillItemArrayList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_row, parent, false);
        return new MyViewHolder(view, recyclerViewInterface);
        //return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        pill_item = pillItemArrayList.get(position);
        holder.textViewName.setText(pill_item.getNamePill());
        holder.textViewTime.setText(pill_item.getTimeToTake()+"");
        holder.textViewCount.setText(pill_item.getCountToTake()+"");
    }

    @Override
    public int getItemCount() {
        return pillItemArrayList.size();
    }
}
