package com.example.duccao.money_hater;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ContactActivity extends AppCompatActivity {

    Button btnFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar myChildToolbar = (Toolbar) findViewById(R.id.contact_bar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();


        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        btnFeedback = findViewById(R.id.btnFeedback);
        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContactActivity.this, "Thank for your feedback. We will answer you soon!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ContactActivity.this, MainScreen.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contact_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
