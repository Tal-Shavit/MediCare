package com.example.medicare.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medicare.Model.Pill_Item;
import com.example.medicare.R;
import com.example.medicare.Recycler.Adapter_calander;
import com.example.medicare.RecyclerViewInterface;

import java.util.ArrayList;

public class Fragment_Data1 extends Fragment implements RecyclerViewInterface {

    private RecyclerView fragment_RV;
    private Adapter_calander adapterCalander;
    private View view;

    private ArrayList<Pill_Item> pill_items;

    //private Callback....

    public static Fragment_Data1 newInstance(){
        Fragment_Data1 fragment_data1 = new Fragment_Data1();
        return fragment_data1;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment__data1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);


        pill_items = new ArrayList<Pill_Item>();
        pill_items.add(new Pill_Item("Acamol","10:30","1 pill"));
        pill_items.add(new Pill_Item("Advil","20:00","2 pills"));
        pill_items.add(new Pill_Item("Adil","20:00","2 pills"));

        initRecycler();
    }

    private void initRecycler() {
        fragment_RV.setLayoutManager(new LinearLayoutManager(getContext()));
        fragment_RV.setHasFixedSize(true);
        adapterCalander = new Adapter_calander(getContext(),pill_items,this);
        fragment_RV.setAdapter(adapterCalander);
        adapterCalander.notifyDataSetChanged();
    }



    private void findViews(View view) {
        fragment_RV = view.findViewById(R.id.fragment_RV);
    }

    @Override
    public void onItemClick(int position) {

    }


}