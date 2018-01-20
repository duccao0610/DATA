package com.example.duccao.money_hater;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditProfileActivity extends AppCompatActivity {

    Button btnSua;
    String email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        btnSua = findViewById(R.id.btnSua);

        Toolbar myChildToolbar = (Toolbar) findViewById(R.id.edit_bar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();


        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check()){
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("email", email);
                    returnIntent.putExtra("password", pass);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
            }
        });
    }

    private boolean check(){
        TextInputLayout til = (TextInputLayout) findViewById(R.id.email_til);
        TextInputEditText tiet = (TextInputEditText) findViewById(R.id.edtEmail);
        TextInputLayout til_pass = findViewById(R.id.password_til);
        EditText edtPass = findViewById(R.id.edtPassword);
        if(tiet.getText().toString().trim().isEmpty()){
            til.setError("Please enter valid email.");
            return false;
        }else{
            til.setError(null);
            if(edtPass.getText().toString().trim().isEmpty()){
                til_pass.setError("Please enter your password");
                return false;
            }
            else {
                til_pass.setError(null);
                email = tiet.getText().toString().trim();
                pass = edtPass.getText().toString().trim();
                return true;
            }
        }

    }
}
