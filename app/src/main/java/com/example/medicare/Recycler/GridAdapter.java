package com.example.medicare.Recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicare.Model.Pill_Item;
import com.example.medicare.R;
import com.example.medicare.Recycler.Adapter_Recycler;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {

    private Context context;
    private String[] timeInDay;
    private int[] images;
    private RecyclerView recyclerView;
    //private Adapter_Recycler adapterRecycler;


    //private View view;
    private ArrayList<Pill_Item> pill_items;


    //private ArrayList<Pill_Item> pillItemsArrayList;
    //private Pill_Item pill_item;

    private LayoutInflater inflater;

    public GridAdapter(Context context, String[] timeInDay, int[] images) {//ArrayList<Pill_Item> pillItemsArrayList
        this.context = context;
        this.timeInDay = timeInDay;
        this.images = images;

        pill_items = new ArrayList<Pill_Item>();
        pill_items.add(new Pill_Item("Acamol","10:30","1 pill"));
        pill_items.add(new Pill_Item("Advil","20:00","2 pills"));
        pill_items.add(new Pill_Item("Adil","20:00","2 pills"));
        pill_items.add(new Pill_Item("Adil","20:00","2 pills"));
        pill_items.add(new Pill_Item("Adil","20:00","2 pills"));





        //this.pillItemsArrayList = pillItemsArrayList;
    }


    @Override
    public int getCount() {
        return timeInDay.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if(inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null)
            view = inflater.inflate(R.layout.grid_item, null);

        ImageView imageView = view.findViewById(R.id.imageGrid);
        imageView.setImageResource(images[position]);

        TextView textView = view.findViewById(R.id.textItem);
        textView.setText(timeInDay[position]);


        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new Adapter_Recycler(context.getApplicationContext(),pill_items));


        //pill_item = pillItemsArrayList.get(position);
        //imageView.setImageResource(pill_item.getImgPill());

        return view;
    }
}
