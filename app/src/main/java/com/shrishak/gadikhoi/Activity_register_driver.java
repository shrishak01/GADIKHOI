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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Activity_register_driver extends AppCompatActivity {
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
                if (user != null) {
                    Intent intent = new Intent(Activity_register_driver.this, DriverLoginActivity.class);
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
        check_box = findViewById(R.id.chkBox1);
        button_signup = (Button) findViewById(R.id.button_signUp);

        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailID = email.getText().toString();
                String paswd = res_password.getText().toString();
                String conpaswd = res_check_password.getText().toString();
                String user_name = res_username.getText().toString();
                if (emailID.isEmpty()) {
                    email.setError("Check your email");
                    email.requestFocus();
                } else if (paswd.isEmpty()) {
                    res_password.setError("Set your password");
                    res_password.requestFocus();
                } else if (conpaswd.isEmpty()) {
                    res_check_password.setError("Set the password");
                    res_check_password.requestFocus();
                } else if (user_name.isEmpty()) {
                    res_username.setError("Set a username");
                    res_username.requestFocus();
                } else if (emailID.isEmpty() && paswd.isEmpty() && user_name.isEmpty() && conpaswd.isEmpty()) {
                    Toast.makeText(Activity_register_driver.this, "Please fill everything!", Toast.LENGTH_SHORT).show();
                } else if (!(emailID.isEmpty() && paswd.isEmpty() && user_name.isEmpty() && conpaswd.isEmpty())) {
                    mAuth.createUserWithEmailAndPassword(emailID, paswd).addOnCompleteListener(Activity_register_driver.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Activity_register_driver.this.getApplicationContext(), "Signup unsuccessful:", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Activity_register_driver.this.getApplicationContext(), "Signup successful: Please loginin to access our services", Toast.LENGTH_SHORT).show();
                                String user_id = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                                DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(user_id);
                                current_user_db.setValue(true);
                                startActivity(new Intent(Activity_register_driver.this, DriverLoginActivity.class));
                            }
                        }
                    });
                }
            }
        });





        txtsignin = findViewById(R.id.txtSignIn);
        txtsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_register_driver.this, DriverLoginActivity.class);
                startActivity(i);
            }
        });
    }
}
