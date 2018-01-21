package com.example.duccao.money_hater;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Duc Cao on 1/18/2018.
 */

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.MyViewHolder> {
    private ArrayList<Groups> groupsList;
    private TextView tvGroupChosen;
    private TextView tvBioGroupChosen;
    private DatabaseReference groupsRef = FirebaseDatabase.getInstance().getReference("groups");
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView Name, Id;
        public ImageView ivLeaveGroup;
        private ItemClickListener itemClickListener;

        public MyViewHolder(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.Name_group);
            Id = (TextView) view.findViewById(R.id.Id_group);
            ivLeaveGroup = view.findViewById(R.id.ivLeaveGroup);

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

    public GroupsAdapter(ArrayList<Groups> groupsList, TextView tvGroupChosen, TextView tvBioGroupChosen, Context context) {
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
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final int pos = position;
        final Groups groups = groupsList.get(pos);

        holder.Name.setText(groups.getName());
        holder.Id.setText(groups.getTotal()+"");

        holder.ivLeaveGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupsList.remove(pos);
                notifyDataSetChanged();
            }
        });

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                tvGroupChosen.setText(groups.getName());
                final SharedPreferences shr = context.getSharedPreferences("data", MODE_PRIVATE);
                final SharedPreferences.Editor update = shr.edit();
                groupsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot groupSnapshot : dataSnapshot.getChildren()){
                            if(groupSnapshot.child("name").getValue(String.class).equals(groups.getName())){
                                update.putLong("gid", Long.parseLong(groupSnapshot.getKey().toString()));
                                update.apply();
//                                Intent intentMainScreen = new Intent(context, MainScreen.class);
//                                context.startActivity(intentMainScreen);

                                ((Activity)context).finish();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

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
