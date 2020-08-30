package com.shrishak.gadikhoi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Activity_register extends AppCompatActivity {
    private EditText res_username, res_password, res_check_password,email;
    private Button button_signup,check_box;
    private TextView txtsignin;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_registeration);

        mAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null){
                    Intent intent = new Intent(Activity_register.this, DriverLoginActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };

        res_username = (EditText) findViewById(R.id.res_username);
        res_password = (EditText) findViewById(R.id.res_password);
        res_check_password = (EditText) findViewById(R.id.res_check_password);
        email = (EditText) findViewById(R.id.email);
        button_signup = (Button) findViewById(R.id.button_signUp);
        check_box = findViewById(R.id.chkBox1);






        txtsignin = findViewById(R.id.txtSignIn);
        txtsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_register.this, DriverLoginActivity.class);
                startActivity(i);
            }
        });
    }

}