package com.example.duccao.money_hater;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Tran Tuan Anh on 1/17/2018.
 */

public class MainScreenViewHolder extends RecyclerView.ViewHolder {

    private ImageView avatar;
    private TextView tvTime;
    private TextView tvContent;
    private TextView tvPersonalConsume;
    private TextView tvGroupConsume;


    public MainScreenViewHolder(View itemView) {
        super(itemView);
        avatar = itemView.findViewById(R.id.ivAvatarPost);
        tvTime = itemView.findViewById(R.id.tvTimePost);
        tvContent = itemView.findViewById(R.id.tvContentPost);
        tvPersonalConsume = itemView.findViewById(R.id.tvPersonalConsume);
        tvGroupConsume = itemView.findViewById(R.id.tvGroupConsume);
    }

    public void bindItem(MainScreenItem newItem){
        avatar.setImageResource(newItem.getAvatar());
        tvTime.setText(newItem.getTime());
        tvContent.setText(newItem.getContent());
        tvPersonalConsume.setText(newItem.getPersonalConsume());
        tvGroupConsume.setText(newItem.getGroupConsume());

        if(newItem.isOut()){
            tvGroupConsume.setTextColor(Color.parseColor("#b90000"));
        }
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
