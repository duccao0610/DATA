package com.example.duccao.money_hater;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ThemGiaoDichActivity extends AppCompatActivity {

    RecyclerView recyclerViewThanhVien;
    ArrayList<ThanhVien> listThanhVien;
    RecyclerView.Adapter adapter;
    DatabaseReference relationsRef, usersRef, groupsRef, paymentsRef;
    EditText tongTien, title;
    long gid;
    private SharedPreferences shr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_giao_dich);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        shr = getSharedPreferences("data", MODE_PRIVATE);
        gid = shr.getLong("gid", 1);
        relationsRef = FirebaseDatabase.getInstance().getReference("relations");
        usersRef = FirebaseDatabase.getInstance().getReference("users");
        groupsRef = FirebaseDatabase.getInstance().getReference("groups");
        paymentsRef = FirebaseDatabase.getInstance().getReference("payments");
        // my_child_toolbar is defined in the layout file
        Toolbar myChildToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();


        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        final long gid1 = gid;
        relationsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listThanhVien = new ArrayList();
                for(final DataSnapshot relationSnapshot : dataSnapshot.getChildren()){
                    if(relationSnapshot.child("gid").getValue(Long.class) == gid1){
                        usersRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot2) {
                                User tmp = dataSnapshot2.child(relationSnapshot.child("uid").getValue(String.class)).getValue(User.class);
                                ThanhVien tv = new ThanhVien(R.drawable.intro1, tmp.getMail());
                                listThanhVien.add(tv);
                            }

                            @Override
                            public void onCancelled(DatabaseError error) {
                            }
                        });
                    }
                }
                recyclerViewThanhVien = findViewById(R.id.recyclerViewThanhVien);
                recyclerViewThanhVien.setHasFixedSize(true);
                adapter = new CustomAdapter(listThanhVien, getApplicationContext());

                LinearLayoutManager layoutManager = new LinearLayoutManager(ThemGiaoDichActivity.this, LinearLayoutManager.VERTICAL, false);
                recyclerViewThanhVien.setLayoutManager(layoutManager);
                DividerItemDecoration decoration = new DividerItemDecoration(ThemGiaoDichActivity.this, layoutManager.getOrientation());
                recyclerViewThanhVien.addItemDecoration(decoration);
                recyclerViewThanhVien.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ThemGiaoDichActivity.this, MainScreen.class);
                process();
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void process() {
        tongTien = findViewById(R.id.edtTongtien);
        title = findViewById(R.id.edtDetails);
        ArrayList<ThanhVien> Selected = new ArrayList();
        for (ThanhVien tv : listThanhVien){
            if(tv.isChecked()) Selected.add(tv);
        }
        try {
            final long Sum = Long.parseLong(tongTien.getText().toString());
            final String content = title.getText().toString();
            final Date d = new Date();
            /////
            SimpleDateFormat dateFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");
            final String date = dateFormat.format(d);

            final long each = Sum/Selected.size();

            groupsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    long oldSum = dataSnapshot.child(gid+"").child("total").getValue(Long.class);
                    groupsRef.child(gid+"").child("total").setValue(oldSum+Sum);
                }

                @Override
                public void onCancelled(DatabaseError error) {

                }
            });

            for(final ThanhVien tv : Selected){
                Log.d("", "process: " + Selected.size());
                usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(final DataSnapshot userSnapshot : dataSnapshot.getChildren()){
                            if (userSnapshot.child("mail").getValue(String.class).equals(tv.getTen())){
                                long oldcash = userSnapshot.child("cash").getValue(Long.class);
                                usersRef.child(userSnapshot.getKey().toString()).child("cash").setValue(oldcash-each);
                                paymentsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        Payment tmp = new Payment(date, content, userSnapshot.getKey().toString(), each, gid, Sum);
                                        paymentsRef.push().setValue(tmp);
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError error) {

                                    }
                                });
                                relationsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for(DataSnapshot relationSnapshot : dataSnapshot.getChildren()){
                                            if(relationSnapshot.child("uid").getValue(String.class).equals(userSnapshot.getKey().toString()) && relationSnapshot.child("gid").getValue(Long.class) == gid){
                                                long oldspent = relationSnapshot.child("spentwith").getValue(Long.class);
                                                relationsRef.child(relationSnapshot.getKey().toString()).child("spentwith").setValue(oldspent + each);
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
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }

        }
        catch (Exception ex){

        }

    }
}
