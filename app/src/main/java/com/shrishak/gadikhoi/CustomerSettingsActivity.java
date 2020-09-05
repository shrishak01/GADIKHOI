package com.shrishak.gadikhoi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CustomerSettingsActivity extends AppCompatActivity {
    private EditText mNameField, mPhoneField, mLocation, mOccupation;

    private Button mBack, mConfirm;

    private FirebaseAuth mAuth;

    private DatabaseReference mCustomerDatabase;

    private String userID;

    private String mName;
    private String mPhone;
    private String mLoc;
    private String mOcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_settings);

        mNameField = (EditText) findViewById(R.id.name);
        mPhoneField = (EditText) findViewById(R.id.phone);
        mLocation = (EditText) findViewById(R.id.location);
        mOccupation = (EditText) findViewById(R.id.occupation);

        mBack = (Button) findViewById(R.id.confirm);
        mConfirm = (Button) findViewById(R.id.back);

        mAuth = FirebaseAuth.getInstance();
        userID = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        mCustomerDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child(userID);

        getUserInfo();


        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserInformation();
            }
        });



    }

    private void saveUserInformation() {
            mName = mNameField.getText().toString();
            mPhone = mPhoneField.getText().toString();
            mLoc = mLocation.getText().toString();
            mOcc = mOccupation.getText().toString();

            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("name", mName);
            userInfo.put("phone", mPhone);
            userInfo.put("location", mLoc);
            userInfo.put("occupation", mOcc);
            mCustomerDatabase.updateChildren(userInfo);
            finish();
            return;
    }

    private void getUserInfo() {
        mCustomerDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    assert map != null;
                    if (map.get("name") != null) {
                        mName = Objects.requireNonNull(map.get("name")).toString();
                        mNameField.setText(mName);
                    }
                    if (map.get("phone") != null) {
                        mPhone = Objects.requireNonNull(map.get("phone")).toString();
                        mPhoneField.setText(mPhone);
                    }
                    if (map.get("location") != null) {
                        mLoc = Objects.requireNonNull(map.get("location")).toString();
                        mLocation.setText(mLoc);
                    }
                    if (map.get("occupation") != null) {
                        mOcc = Objects.requireNonNull(map.get("occupation")).toString();
                        mOccupation.setText(mOcc);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}