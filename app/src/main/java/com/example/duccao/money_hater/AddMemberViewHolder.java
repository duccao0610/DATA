package com.example.duccao.money_hater;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Tran Tuan Anh on 1/18/2018.
 */

public class AddMemberViewHolder extends RecyclerView.ViewHolder {

    private ImageView ivAvatarAddMember;
    private TextView tvNameAddMember;
    private TextView tvEmailAddMember;
    private ImageView ivDeleteAddMember;

    public AddMemberViewHolder(View itemView) {
        super(itemView);
        ivAvatarAddMember = itemView.findViewById(R.id.ivAvatarAddMember);
        tvNameAddMember = itemView.findViewById(R.id.tvNameAddMember);
        tvEmailAddMember = itemView.findViewById(R.id.tvEmailAddMember);
        ivDeleteAddMember = itemView.findViewById(R.id.ivDeleteAddMember);

    }

    public void bindItem(AddMemberItem newItem){
        ivAvatarAddMember.setImageResource(newItem.getAvatar());
        tvNameAddMember.setText(newItem.getName());
        tvEmailAddMember.setText(newItem.getEmail());
        ivDeleteAddMember.setImageResource(R.drawable.icon_delete);

    }

    public TextView getTvNameAddMember() {
        return tvNameAddMember;
    }

    public ImageView getIvDeleteAddMember() {
        return ivDeleteAddMember;
    }
}
