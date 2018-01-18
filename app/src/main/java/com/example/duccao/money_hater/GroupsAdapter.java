package com.example.duccao.money_hater;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Duc Cao on 1/18/2018.
 */

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.MyViewHolder> {

    private List<Groups> groupsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Name, Id, Pass;

        public MyViewHolder(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.Name_group);
            Id = (TextView) view.findViewById(R.id.Id_group);
            Pass = (TextView) view.findViewById(R.id.Pass_group);
        }
    }

    public GroupsAdapter(List<Groups> groupsList) {
        this.groupsList = groupsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.groups_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Groups groups = groupsList.get(position);
        holder.Name.setText(groups.getName());
        holder.Id.setText(groups.getId());
        holder.Pass.setText(groups.getPass());
    }

    @Override
    public int getItemCount() {
        return groupsList.size();
    }


}
