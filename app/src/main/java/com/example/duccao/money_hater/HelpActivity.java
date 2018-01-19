package com.example.duccao.money_hater;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    TextView txtQuestion, txtPrivacy;
    int c = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        // my_child_toolbar is defined in the layout file
        Toolbar myChildToolbar = (Toolbar) findViewById(R.id.help_bar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();


        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        txtQuestion = findViewById(R.id.txtquestion);
        txtPrivacy = findViewById(R.id.txtprivacy);

        txtQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentQuestion = new Intent(HelpActivity.this, QuestionActivity.class);
                startActivity(intentQuestion);
            }
        });

        txtPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPrivacy = new Intent(HelpActivity.this, PrivacyActivity.class);
                startActivity(intentPrivacy);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.help_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
