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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicare.Model.NewUser;
import com.example.medicare.Model.Pill_Item;
import com.example.medicare.R;
import com.example.medicare.Interface.RecyclerViewInterface;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter implements RecyclerViewInterface {

    private Context context;
    private String[] timeInDay;
    private int[] images;
    private RecyclerView recyclerView;
    private ArrayList<Pill_Item>[] pill_items;

    private NewUser newUser;

    private Pill_Item pill_item;


    private LayoutInflater inflater;

    private int day;

    public GridAdapter(Context context, String[] timeInDay, int[] images, ArrayList<Pill_Item>[] pill_items, int day) {
        this.context = context;
        this.timeInDay = timeInDay;
        this.images = images;
        this.pill_items = pill_items;
        this.day = day;
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

        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null)
            view = inflater.inflate(R.layout.grid_item, null);

        ImageView imageView = view.findViewById(R.id.imageGrid);
        imageView.setImageResource(images[position]);

        TextView textView = view.findViewById(R.id.textItem);
        textView.setText(timeInDay[position]);


        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new Adapter_Recycler(context, pill_items[position], this));

        return view;
    }

    @Override
    public void onItemClick(String time, int position) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_pill_img);

        TextView dialogpill_TXT_pillname = dialog.findViewById(R.id.dialogpill_TXT_pillname);
        TextView dialogpill_TXT_time = dialog.findViewById(R.id.dialogpill_TXT_time);
        TextView dialogpill_TXT_count = dialog.findViewById(R.id.dialogpill_TXT_count);
        ImageView dialog_IMG_image = dialog.findViewById(R.id.dialog_IMG_image);
        MaterialButton calander_MBTN_delete = dialog.findViewById(R.id.calander_MBTN_delete);

        String arr[] = time.split(":");
        int timeInt = Integer.parseInt(arr[0]);
        if (timeInt >= 4 && timeInt < 7) {//early morning
            dialogpill_TXT_pillname.setText(pill_items[0].get(position).getNamePill());
            dialogpill_TXT_time.setText(pill_items[0].get(position).getTimeToTake());
            dialogpill_TXT_count.setText(pill_items[0].get(position).getCountToTake() + "");
        }
        if (timeInt >= 7 && timeInt < 12) {//morning
            dialogpill_TXT_pillname.setText(pill_items[1].get(position).getNamePill());
            dialogpill_TXT_time.setText(pill_items[1].get(position).getTimeToTake());
            dialogpill_TXT_count.setText(pill_items[1].get(position).getCountToTake() + "");
        }
        if (timeInt >= 12 && timeInt < 16) {
            dialogpill_TXT_pillname.setText(pill_items[2].get(position).getNamePill());
            dialogpill_TXT_time.setText(pill_items[2].get(position).getTimeToTake());
            dialogpill_TXT_count.setText(pill_items[2].get(position).getCountToTake() + "");
        }
        if (timeInt >= 16 && timeInt < 19) {
            dialogpill_TXT_pillname.setText(pill_items[3].get(position).getNamePill());
            dialogpill_TXT_time.setText(pill_items[3].get(position).getTimeToTake());
            dialogpill_TXT_count.setText(pill_items[3].get(position).getCountToTake() + "");
        }
        if (timeInt >= 19 && timeInt < 24) {
            dialogpill_TXT_pillname.setText(pill_items[4].get(position).getNamePill());
            dialogpill_TXT_time.setText(pill_items[4].get(position).getTimeToTake());
            dialogpill_TXT_count.setText(pill_items[4].get(position).getCountToTake() + "");
        }
        if (timeInt >= 0 && timeInt < 4) {
            dialogpill_TXT_pillname.setText(pill_items[5].get(position).getNamePill());
            dialogpill_TXT_time.setText(pill_items[5].get(position).getTimeToTake());
            dialogpill_TXT_count.setText(pill_items[5].get(position).getCountToTake() + "");
        }

        calander_MBTN_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference();//"Users"
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.child("Users").child(userID).exists()){
                            databaseReference.child("Users").child(userID).get().addOnCompleteListener(task -> {
                                if(task.isSuccessful()){
                                    newUser = task.getResult().getValue(NewUser.class);
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/
            }
        });

        dialog.setCancelable(true);
        dialog.show();
    }

    public Pill_Item createPillToDeleteLater(String pillName, String time, String count, int day) {
        if (day == 1) {
            return pill_item.setNamePill(pillName).setCountToTake(Integer.parseInt(count)).setTimeToTake(time)
                    .setSunday(true).setMonday(false).setTuesday(false)
                    .setWednesday(false).setThursday(false)
                    .setFriday(false).setSaturday(false);

        }
        if (day == 2) {
            return pill_item.setNamePill(pillName).setCountToTake(Integer.parseInt(count)).setTimeToTake(time)
                    .setSunday(false).setMonday(true).setTuesday(false)
                    .setWednesday(false).setThursday(false)
                    .setFriday(false).setSaturday(false);
        }
        if (day == 3) {
            pill_item = new Pill_Item();

            return pill_item.setNamePill(pillName).setCountToTake(Integer.parseInt(count)).setTimeToTake(time)
                    .setSunday(false).setMonday(false).setTuesday(true)
                    .setWednesday(false).setThursday(false)
                    .setFriday(false).setSaturday(false);
        }
        if (day == 4) {
            return pill_item.setNamePill(pillName).setCountToTake(Integer.parseInt(count)).setTimeToTake(time)
                    .setSunday(false).setMonday(false).setTuesday(false)
                    .setWednesday(true).setThursday(false)
                    .setFriday(false).setSaturday(false);
        }
        if (day == 5) {
            return pill_item.setNamePill(pillName).setCountToTake(Integer.parseInt(count)).setTimeToTake(time)
                    .setSunday(false).setMonday(false).setTuesday(false)
                    .setWednesday(false).setThursday(true)
                    .setFriday(false).setSaturday(false);
        }
        if (day == 6) {
            return pill_item.setNamePill(pillName).setCountToTake(Integer.parseInt(count)).setTimeToTake(time)
                    .setSunday(false).setMonday(false).setTuesday(false)
                    .setWednesday(false).setThursday(false)
                    .setFriday(true).setSaturday(false);
        }
        return pill_item.setNamePill(pillName).setCountToTake(Integer.parseInt(count)).setTimeToTake(time)
                    .setSunday(false).setMonday(false).setTuesday(false)
                    .setWednesday(false).setThursday(false)
                    .setFriday(false).setSaturday(true);
        }
    }

