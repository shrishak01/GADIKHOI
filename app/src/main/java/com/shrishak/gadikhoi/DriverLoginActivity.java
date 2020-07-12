package com.shrishak.gadikhoi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class DriverLoginActivity<txtsignup> extends AppCompatActivity {
 private EditText username, password_login;
 private Button button_signin;
 private TextView txtSignup;

 private FirebaseAuth mAuth;
 private FirebaseAuth.AuthStateListener firebaseAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);

        button_signin = (Button) findViewById(R.id.button_signin);

    }

}