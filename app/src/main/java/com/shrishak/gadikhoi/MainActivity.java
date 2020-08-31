package com.shrishak.gadikhoi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
 private LinearLayout driver, passenger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        driver = findViewById(R.id.driver);
        driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DriverLoginActivity.class);
                startActivity(i);
            }
        });
        passenger = findViewById(R.id.passenger);
        passenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CustomerLoginActivity.class);
                startActivity(i);
            }
        });
    }
}