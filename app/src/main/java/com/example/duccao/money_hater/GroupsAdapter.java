package com.example.duccao.money_hater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Duc Cao on 1/18/2018.
 */

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.MyViewHolder> {

    private List<Groups> groupsList;
    private TextView tvGroupChosen;
    private TextView tvBioGroupChosen;

    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView Name, Id, Pass;
        private ItemClickListener itemClickListener;

        public MyViewHolder(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.Name_group);
            Id = (TextView) view.findViewById(R.id.Id_group);
            Pass = (TextView) view.findViewById(R.id.Pass_group);

            view.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition());
        }
    }

    public GroupsAdapter(List<Groups> groupsList, TextView tvGroupChosen, TextView tvBioGroupChosen, Context context) {
        this.groupsList = groupsList;
        this.tvGroupChosen = tvGroupChosen;
        this.tvBioGroupChosen = tvBioGroupChosen;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.groups_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Groups groups = groupsList.get(position);

        holder.Name.setText(groups.getName());
        holder.Id.setText(groups.getId());
        holder.Pass.setText(groups.getPass());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                tvGroupChosen.setText(groups.getName());
                //group selected
                //save data
            }
        });
    }

    @Override
    public int getItemCount() {
        return groupsList.size();
    }


}
