package com.example.duccao.money_hater;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ChooseGroupActivity extends AppCompatActivity {

    private List<Groups> groupsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private GroupsAdapter groupsAdapter;

    private Button btnAddGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_group);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        btnAddGroup = findViewById(R.id.btn_addGroup);

        groupsAdapter = new GroupsAdapter(groupsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(groupsAdapter);

        GroupData();

        btnAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddGroup = new Intent(ChooseGroupActivity.this, AddGroupActivity.class);
                finish();
                startActivity(intentAddGroup);
            }
        });
    }

    private void GroupData(){

        Groups groups = new Groups("Group1", "B15DCCN1", "12345");
        groupsList.add(groups);

        groups = new Groups("Group2","B15DCCN2","54321");
        groupsList.add(groups);

        groups = new Groups("Group3","B15DCCN3","32134");
        groupsList.add(groups);

        groups = new Groups("Group4","B15DCCN4","54646");
        groupsList.add(groups);

        groups = new Groups("Group5","B15DCCN5","54681");
        groupsList.add(groups);
    }
}
