package com.example.duccao.money_hater;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddMemberActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AddMemberLayoutAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<AddMemberItem> listItem;

    private Button btnAdd;
    private Button btnSave;
    private EditText etAddMember;

    private long gid;

    private String memberNameTemp;
    private DatabaseReference usersRef, groupsRef, relationsRef;
    private SharedPreferences shr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        shr = getSharedPreferences("data", MODE_PRIVATE);
        gid = shr.getLong("gid", 1);

        recyclerView = findViewById(R.id.rvAddMember1);
        btnAdd = findViewById(R.id.btnAddMember1);
        btnSave = findViewById(R.id.btnSaveMember1);
        etAddMember = findViewById(R.id.etAddMember1);

        usersRef = FirebaseDatabase.getInstance().getReference("users");
        groupsRef = FirebaseDatabase.getInstance().getReference("groups");
        relationsRef = FirebaseDatabase.getInstance().getReference("relations");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memberNameTemp = etAddMember.getText().toString();
                if(!memberNameTemp.isEmpty()){
                    usersRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            boolean userExist = false;
                            for (DataSnapshot userSnapshot : dataSnapshot.getChildren()){
                                if(userSnapshot.child("mail").getValue(String.class).equals(memberNameTemp)){
                                    AddMemberItem item = new AddMemberItem(R.drawable.icon_bitcoin, memberNameTemp, "***");
                                    listItem.add(item);
                                    adapter.notifyDataSetChanged();
                                    etAddMember.setText("");
                                    userExist = true;
                                    break;
                                }
                            }
                            if(!userExist){
                                Toast.makeText(AddMemberActivity.this, "User does not exist!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                        }
                    });
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(final AddMemberItem item : listItem){
                    usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                           for(final DataSnapshot userSnapshot : dataSnapshot.getChildren()){
                               if (userSnapshot.child("mail").getValue(String.class).equals(item.getName())){
                                   relationsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                       @Override
                                       public void onDataChange(DataSnapshot dataSnapshot) {
                                           boolean isExist = false;
                                           for(DataSnapshot relationSnapshot : dataSnapshot.getChildren()){
                                               if(relationSnapshot.child("uid").getValue(String.class).equals(userSnapshot.getKey().toString()) && relationSnapshot.child("gid").getValue(Long.class) == gid){
                                                   isExist = true;
                                                   break;
                                               }
                                           }
                                           if(!isExist){
                                               Relation tmp = new Relation(gid, 0, userSnapshot.getKey().toString());
                                               relationsRef.push().setValue(tmp);
                                           }
                                       }
                                       @Override
                                       public void onCancelled(DatabaseError error) {

                                       }
                                   });
                               }
                           }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                        }
                    });
                }

                Toast.makeText(AddMemberActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                finish();
                Intent intentChooseGrp = new Intent(AddMemberActivity.this, ChooseGroupActivity.class);
                startActivity(intentChooseGrp);
            }
        });
        listItem = new ArrayList<>();

        relationsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot relationSnapshot : dataSnapshot.getChildren()){
                    if(relationSnapshot.child("gid").getValue(Long.class) == gid){
                        final String uidTmp = relationSnapshot.child("uid").getValue(String.class);
                        usersRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(DataSnapshot userSnapshot : dataSnapshot.getChildren()){
                                    if(userSnapshot.getKey().toString().equals(uidTmp)){
                                        AddMemberItem item = new AddMemberItem(R.drawable.icon_bitcoin, userSnapshot.child("mail").getValue(String.class), "***");
                                        listItem.add(item);
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError error) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        adapter = new AddMemberLayoutAdapter(this, listItem);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }
}
