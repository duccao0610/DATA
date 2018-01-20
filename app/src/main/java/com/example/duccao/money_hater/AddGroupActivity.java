package com.example.duccao.money_hater;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import java.util.Objects;

public class AddGroupActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AddMemberLayoutAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<AddMemberItem> listItem;

    private Button btnAdd;
    private Button btnSave;
    private EditText etAddMember;
    private EditText etGroupName;

    private DatabaseReference usersRef, groupsRef, relationsRef;

    private String memberNameTemp;
    private String nameGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
        listItem = new ArrayList();
        recyclerView = findViewById(R.id.rvAddMember);
        btnAdd = findViewById(R.id.btnAddMember);
        btnSave = findViewById(R.id.btnSaveAddGroup);
        etAddMember = findViewById(R.id.etSearchAddMember);
        etGroupName = findViewById(R.id.etGroupName);
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
                                Toast.makeText(AddGroupActivity.this, "User does not exist!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                        }
                    });
                }
            }
        });

        //save nho get name, value .... cua group
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameGroup = etGroupName.getText().toString();
                if (!nameGroup.equals("")) {
                    groupsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            boolean isExist = false;
                            for(DataSnapshot groupSnapshot : dataSnapshot.getChildren()){
                               if(groupSnapshot.child("name").getValue(String.class).equals(nameGroup)){
                                   Toast.makeText(AddGroupActivity.this, "The name had existed", Toast.LENGTH_SHORT).show();
                                   isExist = true;
                                   break;
                               }
                            }
                            if(!isExist){
                                final long count = dataSnapshot.getChildrenCount() + 1;
                                Group tmp = new Group(0, nameGroup);
                                groupsRef.child(count+"").setValue(tmp);
                                for(final AddMemberItem item : listItem){
                                    usersRef.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            for(DataSnapshot userSnapshot : dataSnapshot.getChildren()){
                                                if(userSnapshot.child("mail").getValue(String.class).equals(item.getName())){
                                                    Relation tmp = new Relation(count, 0, userSnapshot.getKey().toString());
                                                    relationsRef.push().setValue(tmp);
                                                    break;
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


                    Toast.makeText(AddGroupActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                    finish();
//                    Intent intentChooseGrp = new Intent(AddGroupActivity.this, ChooseGroupActivity.class);
//                    startActivity(intentChooseGrp);
                }
                else {
                    Toast.makeText(AddGroupActivity.this, "Name can not be blank", Toast.LENGTH_SHORT).show();
                }
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
