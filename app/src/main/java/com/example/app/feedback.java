package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class feedback extends AppCompatActivity {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    return false;

                case R.id.navigation_notifications:
                    startActivity(new Intent(getApplicationContext(), profile.class));
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        BottomNavigationView navView = findViewById(R.id.nav_view);
       // mTextMessage = findViewById(R.id.message111);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Button button2 = findViewById(R.id.but);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activityIntent = new Intent(getApplicationContext(), Request.class);
                startActivity(activityIntent);
            }
        });
    }








}
