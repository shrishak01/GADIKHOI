package com.shrishak.gadikhoi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException;
import com.google.firebase.auth.FirebaseUser;

public class DriverLoginActivity extends AppCompatActivity {
 private EditText username, password_login;
 private Button button_signin;
 private TextView txtSignup;
 LinearLayout linearLayout2;
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
                    Intent i = new Intent(DriverLoginActivity.this, MapActivity.class);
                    startActivity(i);
                }
            }
        };


        username = (EditText) findViewById(R.id.username);
        password_login = (EditText) findViewById(R.id.password_login);

        button_signin = (Button) findViewById(R.id.button_signin);
//signin vako cha teak needed
        button_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String DEmail = username.getText().toString();
                String DPaswd = password_login.getText().toString();
                if (DEmail.isEmpty()) {
                    username.setError("Enter email address");
                    username.requestFocus();
                } else if (DPaswd.isEmpty()) {
                    password_login.setError("Enter Password!");
                    password_login.requestFocus();
                }else if (DEmail.isEmpty() && DPaswd.isEmpty()) {
                    Toast.makeText(DriverLoginActivity.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
                } else if (!(DEmail.isEmpty() && DPaswd.isEmpty())) {
                    mAuth.signInWithEmailAndPassword(DEmail, DPaswd).addOnCompleteListener(DriverLoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(DriverLoginActivity.this, "Not successful", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(DriverLoginActivity.this, MapActivity.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(DriverLoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });


        linearLayout2 = findViewById(R.id.linearLayout2);
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DriverLoginActivity.this, "Feature coming", Toast.LENGTH_SHORT).show();
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
    @Override
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthListener);
    }
    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthListener);
    }

}