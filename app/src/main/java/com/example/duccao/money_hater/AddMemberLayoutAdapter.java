package com.example.duccao.money_hater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Tran Tuan Anh on 1/18/2018.
 */

public class AddMemberLayoutAdapter extends RecyclerView.Adapter<AddMemberViewHolder>{

    private Context context;
    private ArrayList<AddMemberItem> list;

    public AddMemberLayoutAdapter(Context context, ArrayList<AddMemberItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public AddMemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddMemberViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_add_member, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(AddMemberViewHolder holder, int position) {
        final int pos = position;
        if(list.size() > 0){
            holder.bindItem(list.get(position));

            holder.getTvNameAddMember().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, pos + " selected", Toast.LENGTH_SHORT).show();
                }
            });

            holder.getIvDeleteAddMember().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(pos);
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public ArrayList<AddMemberItem> getList() {
        return list;
    }
}
