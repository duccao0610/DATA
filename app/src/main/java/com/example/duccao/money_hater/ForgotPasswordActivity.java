package com.example.duccao.money_hater;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class ForgotPasswordActivity extends AppCompatActivity {

    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

//        btnSubmit = findViewById(R.id.btnSubmit);
//        btnSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ForgotPasswordActivity.this, ThemGiaoDichActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}
