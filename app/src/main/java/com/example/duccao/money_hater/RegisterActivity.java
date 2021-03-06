package com.example.duccao.money_hater;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    DatabaseReference userRef;
    private static final String TAG = "";
    private FirebaseAuth mAuth;
    EditText email, pass;
    Button register;
    TextView txtSignin;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userRef = FirebaseDatabase.getInstance().getReference("users");
        register = findViewById(R.id.btnregister);
        mAuth = FirebaseAuth.getInstance();

        txtSignin = findViewById(R.id.txtsignin);
        txtSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent( RegisterActivity.this,SignInActivity.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
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
                return true;
            }
        }

    }

    private void Register(){
        if(check()){
            email = findViewById(R.id.edtEmail);
            pass = findViewById(R.id.edtPassword);
            mAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void updateUI(FirebaseUser user) {
        User tmp = new User(0, user.getEmail());
        userRef.child(user.getUid()).setValue(tmp);
        intent = new Intent( RegisterActivity.this,SignInActivity.class);
        startActivity(intent);
    }
}
