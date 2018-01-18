package com.example.duccao.money_hater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Tran Tuan Anh on 1/17/2018.
 */

public class MainScreenLayoutAdapter extends RecyclerView.Adapter<MainScreenViewHolder> {

    private Context context;
    private ArrayList<MainScreenItem> list;

    public MainScreenLayoutAdapter(Context context, ArrayList<MainScreenItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MainScreenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainScreenViewHolder(
                LayoutInflater.from(context).inflate(R.layout.items_main_screen, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(MainScreenViewHolder holder, int position) {
        final int pos = position;
        if(list.size() > 0){
            holder.bindItem(list.get(position));

            holder.getTvContent().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, pos + " selected", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
