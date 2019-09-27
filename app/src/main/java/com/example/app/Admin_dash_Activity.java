package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Admin_dash_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash_);

        Button button = findViewById(R.id.btn_adventure);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), addImage.class));
            }
        });
        Button button2 = findViewById(R.id.btn_add_stays);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(), add_stays.class));
            }
        });

        Button button3 = findViewById(R.id.btn_add_restaurants);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), add_restaurants.class));
            }
        });
    }
}
