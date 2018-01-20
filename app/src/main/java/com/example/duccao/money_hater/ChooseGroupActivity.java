package com.example.duccao.money_hater;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChooseGroupActivity extends AppCompatActivity {

    private ArrayList<Groups> groupsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private GroupsAdapter groupsAdapter;

    private Button btnAddGroup;
    private Button btnClose;
    private Button btnAddMember;

    private TextView tvGroupChosen;
    private TextView tvBioGroupChosen;


    private DatabaseReference relationsRef, groupsRef;
    private long gid;
    private SharedPreferences shr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_group);
        shr = getSharedPreferences("data", MODE_PRIVATE);
        gid = shr.getLong("gid", -1);
        relationsRef = FirebaseDatabase.getInstance().getReference("relations");
        groupsRef = FirebaseDatabase.getInstance().getReference("groups");

        recyclerView = findViewById(R.id.recycler_view);
        btnAddGroup = findViewById(R.id.btn_addGroup);
        btnClose = findViewById(R.id.btn_close);
        tvGroupChosen = findViewById(R.id.tvGroupChosen);
        tvBioGroupChosen = findViewById(R.id.tvBioGroupChosen);
        btnAddMember = findViewById(R.id.btnAddMember2Grp);

        groupsAdapter = new GroupsAdapter(groupsList, tvGroupChosen, tvBioGroupChosen, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(groupsAdapter);

        if(gid == -1) {
            tvGroupChosen.setText("No group is chosen");
        }
        else {
            groupsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot groupSnapshot : dataSnapshot.getChildren()) {
                        if (groupSnapshot.getKey().toString().equals(gid + "")) {
                            tvGroupChosen.setText(groupSnapshot.child("name").getValue(String.class));
                            break;
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        GroupData();

        btnAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intentAddGroup = new Intent(ChooseGroupActivity.this, AddGroupActivity.class);
                startActivity(intentAddGroup);
            }
        });


        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentClose = new Intent(ChooseGroupActivity.this,MainScreen.class);
                finish();
                startActivity(intentClose);
            }
        });

        btnAddMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gid == -1){
                    Toast.makeText(ChooseGroupActivity.this, "Must be in at least one group to do this", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intentAddMember = new Intent(ChooseGroupActivity.this, AddMemberActivity.class);
                    startActivity(intentAddMember);
                }
            }
        });
    }

    private void GroupData(){

        relationsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (final DataSnapshot relationSnapshot : dataSnapshot.getChildren()){
                    if(relationSnapshot.child("uid").getValue(String.class).equals(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())){
                        groupsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot groupSnapshot : dataSnapshot.getChildren()){
                                    if(relationSnapshot.child("gid").getValue(Long.class).toString().equals(groupSnapshot.getKey().toString())){
                                        Groups tmp = groupSnapshot.getValue(Groups.class);
                                        groupsList.add(tmp);
                                        groupsAdapter.notifyDataSetChanged();
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
