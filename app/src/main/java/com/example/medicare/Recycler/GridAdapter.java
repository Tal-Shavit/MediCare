package com.example.medicare.Recycler;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicare.Model.PillItem;
import com.example.medicare.R;
import com.example.medicare.Interface.RecyclerViewInterface;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GridAdapter extends BaseAdapter implements RecyclerViewInterface {
    private Context context;
    private String[] timeInDay;
    private int[] images;
    private RecyclerView recyclerView;
    private ArrayList<PillItem>[] pill_items;
    private StorageReference storageReference;
    private LayoutInflater inflater;
    private ImageView imageView, dialog_IMG_image;
    private TextView textView, dialogpill_TXT_pillname, dialogpill_TXT_time, dialogpill_TXT_count;
    private int day;
    private MaterialButton calander_MBTN_delete;

    public GridAdapter(Context context, String[] timeInDay, int[] images, ArrayList<PillItem>[] pill_items, int day) {
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

        findViews(view);
        initViews(position);

        return view;
    }

    private void initViews(int position) {
        imageView.setImageResource(images[position]);
        textView.setText(timeInDay[position]);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new AdapterRecycler(context, pill_items[position], this));
    }

    private void findViews(View view) {
        imageView = view.findViewById(R.id.imageGrid);
        textView = view.findViewById(R.id.textItem);
        recyclerView = view.findViewById(R.id.recycler);
    }

    public void retriveFromStorage(String name, ImageView imageView) {
        storageReference = FirebaseStorage.getInstance().getReference().child("images/" + name + ".jpg");
        try {
            final File localFile = File.createTempFile(name, ".jpg");
            storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    imageView.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context, "DIDN'T CHOOSE A PHOTO", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onItemClick(String time, int position) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_pill_img_in_grid);

        findViewsDialog(dialog);
        initViewsDialog(time, position);

        dialog.setCancelable(true);
        dialog.show();
    }

    private void initViewsDialog(String time, int position) {
        String arr[] = time.split(":");
        int timeInt = Integer.parseInt(arr[0]);
        initEarlyMorning(timeInt, position);
        initMorning(timeInt, position);
        initNoon(timeInt, position);
        initAfterNoon(timeInt, position);
        initEvening(timeInt, position);
        initNight(timeInt, position);
        dialogCountTxt();
        onDelete();
    }


    private void dialogCountTxt() {
        if (Integer.parseInt(dialogpill_TXT_count.getText().toString()) == 1) {
            dialogpill_TXT_count.setText(dialogpill_TXT_count.getText() + " pill");
        } else {
            dialogpill_TXT_count.setText(dialogpill_TXT_count.getText() + " pills");
        }
    }

    private void initNight(int timeInt, int position) {
        if (timeInt >= 0 && timeInt < 4) {
            dialogpill_TXT_pillname.setText(pill_items[5].get(position).getNamePill());
            dialogpill_TXT_time.setText(pill_items[5].get(position).getTimeToTake());
            dialogpill_TXT_count.setText(pill_items[5].get(position).getCountToTake() + "");
            retriveFromStorage(dialogpill_TXT_pillname.getText().toString(), dialog_IMG_image);
        }
    }

    private void initEvening(int timeInt, int position) {
        if (timeInt >= 19 && timeInt < 24) {
            dialogpill_TXT_pillname.setText(pill_items[4].get(position).getNamePill());
            dialogpill_TXT_time.setText(pill_items[4].get(position).getTimeToTake());
            dialogpill_TXT_count.setText(pill_items[4].get(position).getCountToTake() + "");
            retriveFromStorage(dialogpill_TXT_pillname.getText().toString(), dialog_IMG_image);
        }
    }

    private void initAfterNoon(int timeInt, int position) {
        if (timeInt >= 16 && timeInt < 19) {
            dialogpill_TXT_pillname.setText(pill_items[3].get(position).getNamePill());
            dialogpill_TXT_time.setText(pill_items[3].get(position).getTimeToTake());
            dialogpill_TXT_count.setText(pill_items[3].get(position).getCountToTake() + "");
            retriveFromStorage(dialogpill_TXT_pillname.getText().toString(), dialog_IMG_image);
        }
    }

    private void initNoon(int timeInt, int position) {
        if (timeInt >= 12 && timeInt < 16) {
            dialogpill_TXT_pillname.setText(pill_items[2].get(position).getNamePill());
            dialogpill_TXT_time.setText(pill_items[2].get(position).getTimeToTake());
            dialogpill_TXT_count.setText(pill_items[2].get(position).getCountToTake() + "");
            retriveFromStorage(dialogpill_TXT_pillname.getText().toString(), dialog_IMG_image);
        }
    }

    private void initMorning(int timeInt, int position) {
        if (timeInt >= 7 && timeInt < 12) {//morning
            dialogpill_TXT_pillname.setText(pill_items[1].get(position).getNamePill());
            dialogpill_TXT_time.setText(pill_items[1].get(position).getTimeToTake());
            dialogpill_TXT_count.setText(pill_items[1].get(position).getCountToTake() + "");
            retriveFromStorage(dialogpill_TXT_pillname.getText().toString(), dialog_IMG_image);
        }
    }

    private void initEarlyMorning(int timeInt, int position) {
        if (timeInt >= 4 && timeInt < 7) {//early morning
            dialogpill_TXT_pillname.setText(pill_items[0].get(position).getNamePill());
            dialogpill_TXT_time.setText(pill_items[0].get(position).getTimeToTake());
            dialogpill_TXT_count.setText(pill_items[0].get(position).getCountToTake() + "");
            retriveFromStorage(dialogpill_TXT_pillname.getText().toString(), dialog_IMG_image);
        }
    }

    private void findViewsDialog(Dialog dialog) {
        dialogpill_TXT_pillname = dialog.findViewById(R.id.dialogpill_TXT_pillname);
        dialogpill_TXT_time = dialog.findViewById(R.id.dialogpill_TXT_time);
        dialogpill_TXT_count = dialog.findViewById(R.id.dialogpill_TXT_count);
        dialog_IMG_image = dialog.findViewById(R.id.dialog_IMG_image);
        calander_MBTN_delete = dialog.findViewById(R.id.calander_MBTN_delete);
    }

    private void onDelete() {
        calander_MBTN_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
