package com.example.duccao.money_hater;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity {

    TextView txtRegister, txtForgot;
    TextInputLayout til;
    TextInputEditText tiet;
    Button btnSignin;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        txtRegister = findViewById(R.id.txtregister);
        txtForgot = findViewById(R.id.txtforgot);
        btnSignin = findViewById(R.id.btnSignin);

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //kiem tra dinh dang email va hien thi loi
                til = findViewById(R.id.email_til);
                tiet = findViewById(R.id.edtEmail);
                if(tiet.getText().toString().isEmpty() || tiet.getInputType() != 33){

                    //hien thi loi
                    til.setError("Please enter valid address.");
//                    Toast.makeText(SignInActivity.this,tiet.getInputType() + " " + InputType.TYPE_TEXT_VARIATION_EMAIL_SUBJECT , Toast.LENGTH_SHORT).show();
                }else{

                    //xoa loi
                    til.setError("");
                }
            }
        });

        //chuyen sang man hinh Register
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SignInActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        //chuyen sang man hinh Forgot Password
        txtForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SignInActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}
