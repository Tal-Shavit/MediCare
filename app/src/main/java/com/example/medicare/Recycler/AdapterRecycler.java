package com.example.medicare.Recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicare.Model.NewUser;
import com.example.medicare.Model.PillItem;
import com.example.medicare.R;
import com.example.medicare.Interface.RecyclerViewInterface;

import java.util.ArrayList;

public class AdapterRecycler extends RecyclerView.Adapter<MyViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    private Context context;
    private ArrayList<PillItem> pillItemArrayList;
    private PillItem pill_item;

    public AdapterRecycler(Context context, ArrayList<PillItem> pillItemArrayList, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.pillItemArrayList = pillItemArrayList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_row, parent, false);
        return new MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        pill_item = pillItemArrayList.get(position);
        holder.textViewName.setText(pill_item.getNamePill());
        holder.textViewTime.setText(pill_item.getTimeToTake() + "");
        holder.textViewCount.setText(pill_item.getCountToTake() + "");
    }

    @Override
    public int getItemCount() {
        return pillItemArrayList.size();
    }
}
