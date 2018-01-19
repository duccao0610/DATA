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

import java.util.ArrayList;

public class AddMemberActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AddMemberLayoutAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<AddMemberItem> listItem;

    private Button btnAdd;
    private Button btnSave;
    private EditText etAddMember;

    private String memberNameTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        recyclerView = findViewById(R.id.rvAddMember1);
        btnAdd = findViewById(R.id.btnAddMember1);
        btnSave = findViewById(R.id.btnSaveMember1);
        etAddMember = findViewById(R.id.etAddMember1);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memberNameTemp = etAddMember.getText().toString();

                if(!memberNameTemp.isEmpty()){
                    AddMemberItem item = new AddMemberItem(R.drawable.icon_bitcoin, memberNameTemp, "temp@mail.com");
                    listItem.add(item);
                    adapter.notifyDataSetChanged();
                    etAddMember.setText("");
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddMemberActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                finish();
                Intent intentChooseGrp = new Intent(AddMemberActivity.this, ChooseGroupActivity.class);
                startActivity(intentChooseGrp);
            }
        });

        listItem = new ArrayList<>();
        AddMemberItem item1 = new AddMemberItem(R.drawable.icon_bitcoin, "Tran Tuan Anh", "t.tuananh112@gmail.com");
        AddMemberItem item2 = new AddMemberItem(R.drawable.icon_bitcoin, "Pham Hoang Anh", "t.tuananh112@gmail.com");
        AddMemberItem item3 = new AddMemberItem(R.drawable.icon_bitcoin, "Vu Van Duc", "t.tuananh112@gmail.com");
        AddMemberItem item4 = new AddMemberItem(R.drawable.icon_bitcoin, "Nguyen Duc Thinh", "t.tuananh112@gmail.com");
        AddMemberItem item5 = new AddMemberItem(R.drawable.icon_bitcoin, "Cao Minh Cop", "t.tuananh112@gmail.com");
        AddMemberItem item6 = new AddMemberItem(R.drawable.icon_bitcoin, "Cao Minh Cop", "t.tuananh112@gmail.com");

        listItem.add(item1);
        listItem.add(item2);
        listItem.add(item3);
        listItem.add(item4);
        listItem.add(item5);
        listItem.add(item6);


        adapter = new AddMemberLayoutAdapter(this, listItem);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }
}
