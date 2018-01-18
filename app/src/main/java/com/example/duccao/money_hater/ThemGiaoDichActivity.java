package com.example.duccao.money_hater;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class ThemGiaoDichActivity extends AppCompatActivity {

    RecyclerView recyclerViewThanhVien;
    ArrayList<ThanhVien> listThanhVien;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_giao_dich);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // my_child_toolbar is defined in the layout file
        Toolbar myChildToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        listThanhVien = new ArrayList<>();
        ThanhVien tv1 = new ThanhVien(R.drawable.ic_launcher_foreground, "Vu Van Duc");
        ThanhVien tv2 = new ThanhVien(R.drawable.ic_launcher_foreground, "Cao Minh Duc");
        listThanhVien.add(tv1);
        listThanhVien.add(tv2);
        recyclerViewThanhVien = findViewById(R.id.recyclerViewThanhVien);
        recyclerViewThanhVien.setHasFixedSize(true);
        adapter = new CustomAdapter(listThanhVien, getApplicationContext());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewThanhVien.setLayoutManager(layoutManager);
        recyclerViewThanhVien.setAdapter(adapter);
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
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
