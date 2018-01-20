package com.example.duccao.money_hater;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Tran Tuan Anh on 1/17/2018.
 */

public class MainScreenViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ImageView avatar;
    private TextView tvTime;
    private TextView tvContent;
    private TextView tvPersonalConsume;
    private TextView tvGroupConsume;
    private TextView tvNumberOfPeople;

    private FrameLayout container;

    private ItemClickListener itemClickListener;

    public MainScreenViewHolder(View itemView) {
        super(itemView);
        avatar = itemView.findViewById(R.id.ivAvatarPost);
        tvTime = itemView.findViewById(R.id.tvTimePost);
        tvContent = itemView.findViewById(R.id.tvContentPost);
        tvPersonalConsume = itemView.findViewById(R.id.tvPersonalConsume);
        tvGroupConsume = itemView.findViewById(R.id.tvGroupConsume);
        tvNumberOfPeople = itemView.findViewById(R.id.tvNumberOfPeople);
        container = itemView.findViewById(R.id.container);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition());
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public void bindItem(MainScreenItem newItem){
        avatar.setImageResource(newItem.getAvatar());
        tvTime.setText(newItem.getTime());
        tvContent.setText(newItem.getContent());
        tvPersonalConsume.setText(newItem.getPersonalConsume());
        tvGroupConsume.setText(newItem.getGroupConsume());
        long num = newItem.getNumberOfPeople();
        if(num > 1)
            tvNumberOfPeople.setText("+" + (newItem.getNumberOfPeople()));
        else
            container.removeAllViews();

        if(newItem.isOut())
            tvGroupConsume.setTextColor(Color.parseColor("#b90000"));
        else
            tvGroupConsume.setTextColor(Color.parseColor("#04c441"));
    }


    public ImageView getAvatar() {
        return avatar;
    }

    public TextView getTvTime() {
        return tvTime;
    }

    public TextView getTvContent() {
        return tvContent;
    }

    public TextView getTvPersonalConsume() {
        return tvPersonalConsume;
    }

    public TextView getTvGroupConsume() {
        return tvGroupConsume;
    }


}
