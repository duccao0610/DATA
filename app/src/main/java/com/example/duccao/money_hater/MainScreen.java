package com.example.duccao.money_hater;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Tran Tuan Anh on 1/17/2018.
 */

public class MainScreen extends AppCompatActivity {
    private DatabaseReference usersref, groupsref, relationsref, paymentsRef;

    private LinearLayoutManager layoutManager;
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

//    private FloatingActionButton btnAdd;
    private FloatingActionMenu menuExtend;
    private com.github.clans.fab.FloatingActionButton btnAddItem;
    private com.github.clans.fab.FloatingActionButton btnPersonalProfile;
    private com.github.clans.fab.FloatingActionButton btnGroupProfile;
    private Handler mUiHandler = new Handler();

    private boolean showOnlyPerson = false;
    final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        recyclerView = findViewById(R.id.rvMainScreen);
        btnTotalGroupSpent = findViewById(R.id.btnTotalGroupSpent);
        btnSpentWithGroup = findViewById(R.id.btnSpentWithGroup);
        tvTotalGroupSpent = findViewById(R.id.tvTotalGroupSpent);
        tvSpentWithGroup = findViewById(R.id.tvSpentWithGroup);

        menuExtend = findViewById(R.id.menu_red);
        btnAddItem = findViewById(R.id.fab1);
        btnPersonalProfile = findViewById(R.id.fab2);
        btnGroupProfile = findViewById(R.id.fab3);



        usersref = FirebaseDatabase.getInstance().getReference("users").child(uid);
        groupsref = FirebaseDatabase.getInstance().getReference("groups");
        relationsref = FirebaseDatabase.getInstance().getReference("relations");
        paymentsRef = FirebaseDatabase.getInstance().getReference("payments");

        recyclerView.setHasFixedSize(true);

        //Floating button
        btnAddItem.setButtonSize(FloatingActionButton.SIZE_MINI);
        btnAddItem.setLabelText("Add items");
        btnAddItem.setImageResource(R.drawable.ic_edit);
        btnPersonalProfile.setButtonSize(FloatingActionButton.SIZE_MINI);
        btnPersonalProfile.setLabelText("Change personal profile");
        btnPersonalProfile.setImageResource(R.drawable.ic_edit);
        btnGroupProfile.setButtonSize(FloatingActionButton.SIZE_MINI);
        btnGroupProfile.setLabelText("Change group profile");
        btnGroupProfile.setImageResource(R.drawable.ic_edit);

        menuExtend.setClosedOnTouchOutside(true);
        menuExtend.hideMenuButton(false);

        int delay = 400;
        mUiHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                menuExtend.showMenuButton(true);
            }
        }, delay);

        menuExtend.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuExtend.isOpened()) {
//                    Toast.makeText(MainScreen.this, menuExtend.getMenuButtonLabelText(), Toast.LENGTH_SHORT).show();
                }
                menuExtend.toggle(true);
            }
        });

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainScreen.this, "Add selected", Toast.LENGTH_SHORT).show();
                Intent intentAdd = new Intent(MainScreen.this, ThemGiaoDichActivity.class);
                startActivity(intentAdd);
            }
        });
        btnPersonalProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainScreen.this, "Personal profile selected", Toast.LENGTH_SHORT).show();
            }
        });
        btnGroupProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainScreen.this, "Group profile selected", Toast.LENGTH_SHORT).show();
            }
        });

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
                showOnlyPerson = false;
                loadData();

            }
        });
        btnSpentWithGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainScreen.this, "SpentWithGroup selected", Toast.LENGTH_SHORT).show();
                showOnlyPerson = true;
                loadData();
            }
        });
        ///////////


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

        loadData();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            Intent intentChange = new Intent(this, SignInActivity.class);
            startActivity(intentChange);
        }
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
                Intent intentHelp = new Intent(MainScreen.this, HelpActivity.class);
                startActivity(intentHelp);
                break;
            case R.id.mainAbout:
                Intent intentAbout = new Intent(MainScreen.this, AboutActivity.class);
                startActivity(intentAbout);
                break;
            case R.id.mainLogOut:
                Toast.makeText(this, "Log out", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                Intent intentChange = new Intent(MainScreen.this, SignInActivity.class);
                startActivity(intentChange);
                break;
            case R.id.mainExit:
                Intent intent = new Intent(getApplicationContext(), MainScreen.class);
                startActivity(intent);
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startActivity(startMain);
                finish();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadData() {
        relationsref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean haveGroup = false;
                long gid = 0;
                for (DataSnapshot relationSnapshot : dataSnapshot.getChildren()) {
                    if (relationSnapshot.child("uid").getValue(String.class).equals(uid)) {
                        gid = relationSnapshot.child("gid").getValue(Long.class);
                        final long gid1 = gid;
                        groupsref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot2) {
                                Group tmp = dataSnapshot2.child(String.valueOf(gid1)).getValue(Group.class);
                                tvTotalGroupSpent.setText((double) tmp.getTotal() / 1000 + "k");
                            }

                            @Override
                            public void onCancelled(DatabaseError error) {

                            }
                        });


                        tvSpentWithGroup.setText((double) (relationSnapshot.child("spentwith").getValue(Long.class)) / 1000 + "k");
                        haveGroup = true;
                        break;
                    }
                }
                if (!haveGroup) {
                    tvTotalGroupSpent.setText("NO GROUP");
                    tvSpentWithGroup.setText("NO GROUP");
                } else {
                    listItem = new ArrayList<>();
                    final long gid2 = gid;
                    paymentsRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot3) {
                            for (DataSnapshot paymentSnapshot : dataSnapshot3.getChildren()) {
                                if (paymentSnapshot.child("gid").getValue(Long.class) == gid2 && paymentSnapshot.child("uid").getValue(String.class).equals(uid)) {
                                    Payment tmp = paymentSnapshot.getValue(Payment.class);
                                    MainScreenItem item = new MainScreenItem(R.drawable.profile_icon,
                                            tmp.getDate(),
                                            tmp.getTitle(),
                                            tmp.getMoney() + "",
                                            tmp.getGmoney() + "",
                                            true,
                                            tmp.getGmoney() / tmp.getMoney());
                                    listItem.add(item);
                                }
                            }
                            if (!showOnlyPerson) {
                                for (DataSnapshot paymentSnapshot : dataSnapshot3.getChildren()) {
                                    if (paymentSnapshot.child("gid").getValue(Long.class) == gid2) {
                                        boolean isExisted = false;
                                        for (MainScreenItem tmp : listItem) {
                                            if (paymentSnapshot.child("date").getValue(String.class).equals(tmp.getTime())) {
                                                isExisted = true;
                                                break;
                                            }
                                        }
                                        if (isExisted) continue;
                                        Payment tmp = paymentSnapshot.getValue(Payment.class);
                                        MainScreenItem item = new MainScreenItem(R.drawable.profile_icon,
                                                tmp.getDate(),
                                                tmp.getTitle(),
                                                0 + "",
                                                tmp.getGmoney() + "",
                                                true,
                                                tmp.getGmoney() / tmp.getMoney());
                                        //cai numberOfPeople nay phai -1 nhe vi k tinh nguoi post
                                        listItem.add(item);
                                    }
                                }
                            }
                            Collections.sort(listItem);
                            layoutManager = new LinearLayoutManager(MainScreen.this);
                            recyclerView.setLayoutManager(layoutManager);
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                            adapter = new MainScreenLayoutAdapter(MainScreen.this, listItem);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                        }
                    });


                }

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }


    //double click back to exit
    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }

}
