package com.example.duccao.money_hater;

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

public class AddGroupActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AddMemberLayoutAdapter adapter;
    private LinearLayoutManager layoutManager;

    private ArrayList<AddMemberItem> listItem;

    private Button btnAdd;
    private Button btnSave;
    private EditText etAddMember;

    private String memberNameTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        recyclerView = findViewById(R.id.rvAddMember);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        btnAdd = findViewById(R.id.btnAddMember);
        btnSave = findViewById(R.id.btnSaveAddGroup);
        etAddMember = findViewById(R.id.etSearchAddMember);

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

        //save nho get name, value .... cua group
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddGroupActivity.this, "Saved", Toast.LENGTH_SHORT).show();

                finish();
            }
        });


                ///list temp
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

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        adapter = new AddMemberLayoutAdapter(this, listItem);
        recyclerView.setAdapter(adapter);

    }
}
