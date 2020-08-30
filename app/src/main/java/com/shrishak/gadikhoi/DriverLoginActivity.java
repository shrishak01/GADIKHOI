package com.shrishak.gadikhoi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class DriverLoginActivity extends AppCompatActivity {
 private EditText username, password_login;
 private Button button_signin;
 private TextView txtSignup;

 private FirebaseAuth mAuth;
 private FirebaseAuth.AuthStateListener firebaseAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);

        mAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null){
                    Intent intent = new Intent(DriverLoginActivity.this, MapActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };


        username = (EditText) findViewById(R.id.username);
        password_login = (EditText) findViewById(R.id.password_login);

        button_signin = (Button) findViewById(R.id.button_signin);
        button_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailID = username.getText().toString();
                String paswd = password_login.getText().toString();
                mAuth.signInWithEmailAndPassword(emailID, paswd).addOnCompleteListener(DriverLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(DriverLoginActivity.this.getApplicationContext(), "Invalid Email or Password: Please check again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });











        txtSignup = (TextView) findViewById(R.id.txtSignUp);
        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DriverLoginActivity.this, Activity_register_driver.class);
                startActivity(i);
            }
        });


    }

}