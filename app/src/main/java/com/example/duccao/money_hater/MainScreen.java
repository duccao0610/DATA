package com.example.duccao.money_hater;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Tran Tuan Anh on 1/17/2018.
 */

public class MainScreen extends AppCompatActivity {
    private DatabaseReference usersref;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private MainScreenLayoutAdapter adapter;

    private ArrayList<MainScreenItem> listItem;

    private ImageView ivGroupAB;
    private TextView tvCashAB;
    private ImageView ivNotificationAB;
    private ImageView ivMenuAB;

    private LinearLayout btnTotalGroupSpent;
    private LinearLayout btnSpentWithGroup;
    private TextView tvTotalGroupSpent;
    private TextView tvSpentWithGroup;

    private FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        recyclerView = findViewById(R.id.rvMainScreen);
        btnTotalGroupSpent = findViewById(R.id.btnTotalGroupSpent);
        btnSpentWithGroup = findViewById(R.id.btnSpentWithGroup);
        tvTotalGroupSpent = findViewById(R.id.tvTotalGroupSpent);
        tvSpentWithGroup = findViewById(R.id.tvSpentWithGroup);
        btnAdd = findViewById(R.id.btnAddMainScreen);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        usersref = FirebaseDatabase.getInstance().getReference("users").child(uid);

        recyclerView.setHasFixedSize(true);


        //setup action bar
        //////////////////
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        //set layout action bar
        LayoutInflater li = LayoutInflater.from(this);
        View customView = li.inflate(R.layout.actionbar_main_screen_layout, null);
        actionBar.setCustomView(customView);
        actionBar.setDisplayShowCustomEnabled(true);

        ivGroupAB = customView.findViewById(R.id.ivGroupAB);
        tvCashAB = customView.findViewById(R.id.tvCashAB);
        ivNotificationAB = customView.findViewById(R.id.ivNotificationAB);

        ivGroupAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainScreen.this, "Group selected", Toast.LENGTH_SHORT).show();
                Intent intentGroupChange = new Intent(MainScreen.this, ChooseGroupActivity.class);
                startActivity(intentGroupChange);
            }
        });
        tvCashAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainScreen.this, "Cash selected", Toast.LENGTH_SHORT).show();
            }
        });
        ivNotificationAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainScreen.this, "Notification selected", Toast.LENGTH_SHORT).show();
            }
        });


        ////////////

        ///////////
        btnTotalGroupSpent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainScreen.this, "TotalGroupSpent selected", Toast.LENGTH_SHORT).show();
            }
        });
        btnSpentWithGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainScreen.this, "SpentWithGroup selected", Toast.LENGTH_SHORT).show();
            }
        });
        ///////////

        //////////
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainScreen.this, "Add selected", Toast.LENGTH_SHORT).show();
                Intent intentAdd = new Intent(MainScreen.this, ThemGiaoDichActivity.class);
                startActivity(intentAdd);
            }
        });

        usersref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User current = dataSnapshot.getValue(User.class);
                tvCashAB.setText(current.getCash() + "");
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });



        listItem = new ArrayList<>();
        //list Temp
        MainScreenItem item1 = new MainScreenItem(R.drawable.profile_icon,
                "1/1/2018","Đi uống Starbucks liên hoan NewYear", "-134k đ", "-560.000 đ", true, 1);
        MainScreenItem item2 = new MainScreenItem(R.drawable.profile_icon,
                "2/1/2018","Thu tiền quỹ tháng 1/2018", "-60k đ", "+1.280.000 đ", false, 2);
        MainScreenItem item3 = new MainScreenItem(R.drawable.profile_icon,
                "1/1/2018","Đi uống Starbucks liên hoan NewYear", "-134k đ", "-560.000 đ", true, 3);
        MainScreenItem item4 = new MainScreenItem(R.drawable.profile_icon,
                "2/1/2018","Thu tiền quỹ tháng 1/2018", "-60k đ", "+1.280.000 đ", false, 4);
        MainScreenItem item5 = new MainScreenItem(R.drawable.profile_icon,
                "1/1/2018","Đi uống Starbucks liên hoan NewYear", "-134k đ", "-560.000 đ", true, 5);
        MainScreenItem item6 = new MainScreenItem(R.drawable.profile_icon,
                "2/1/2018","Thu tiền quỹ tháng 1/2018", "-60k đ", "+1.280.000 đ", false, 6);
        MainScreenItem item7 = new MainScreenItem(R.drawable.profile_icon,
                "1/1/2018","Đi uống Starbucks liên hoan NewYear", "-134k đ", "-560.000 đ", true, 7);
        MainScreenItem item8 = new MainScreenItem(R.drawable.profile_icon,
                "2/1/2018","Thu tiền quỹ tháng 1/2018", "-60k đ", "+1.280.000 đ", false, 8);

        listItem.add(item1);
        listItem.add(item2);
        listItem.add(item3);
        listItem.add(item4);
        listItem.add(item5);
        listItem.add(item6);
        listItem.add(item7);
        listItem.add(item8);

//
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MainScreenLayoutAdapter(this, listItem);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mainHelp:
                Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mainAbout:
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mainLogOut:
                Toast.makeText(this, "Log out", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mainExit:
                Toast.makeText(this, "Exit", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
    
}
