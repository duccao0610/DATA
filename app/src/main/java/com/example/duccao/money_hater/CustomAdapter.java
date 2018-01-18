package com.example.duccao.money_hater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by VuDuc on 18-Jan-18.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    ArrayList<ThanhVien> list;
    Context context;

    public CustomAdapter(ArrayList<ThanhVien> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate the view for this view holder
        View thisItemsView = inflater.inflate(R.layout.thanh_vien, parent, false);
        // Call the view holder's constructor, and pass the view to it;
        // return that new view holder
        return new CustomViewHolder(thisItemsView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.text.setImageResource(list.get(position).getAnh());
        holder.txtten.setText(list.get(position).getTen());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{
        ImageView text;
        TextView txtten;
        CheckBox checkBox;

        public CustomViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.imageView);
            txtten = itemView.findViewById(R.id.txtTen);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}
