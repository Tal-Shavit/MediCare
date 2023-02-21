package com.example.medicare.Recycler;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicare.Model.Pill_Item;
import com.example.medicare.R;
import com.example.medicare.Interface.RecyclerViewInterface;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter implements RecyclerViewInterface{

    private Context context;
    private String[] timeInDay;
    private int[] images;
    private RecyclerView recyclerView;
    private ArrayList<Pill_Item>[] pill_items;

    private LayoutInflater inflater;

    public GridAdapter(Context context, String[] timeInDay, int[] images, ArrayList<Pill_Item>[] pill_items) {
        this.context = context;
        this.timeInDay = timeInDay;
        this.images = images;
        this.pill_items = pill_items;
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
        recyclerView.setAdapter(new Adapter_Recycler(context.getApplicationContext(),pill_items[position], this));

        return view;
    }

    @Override
    public void onItemClick(int position) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_pill_img);

        TextView dialogpill_TXT_pillname = dialog.findViewById(R.id.dialogpill_TXT_pillname);
        TextView dialogpill_TXT_time = dialog.findViewById(R.id.dialogpill_TXT_time);
        TextView dialogpill_TXT_count = dialog.findViewById(R.id.dialogpill_TXT_count);
        ImageView dialog_IMG_image = dialog.findViewById(R.id.dialog_IMG_image);

        //dialogpill_TXT_pillname.setText(pill_items[0].get(position).getNamePill());

        //for (int i = 0; i < 6; i++) {
        //    dialogpill_TXT_pillname.setText(pill_items[i].get(position).getNamePill());

        //}

        dialog.setCancelable(true);
        dialog.show();

    }

}
