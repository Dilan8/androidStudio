package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class IndexActivity extends AppCompatActivity {

    ImageView adventure, stay, restaurant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);


        adventure = findViewById(R.id.adventureIv);
        stay = findViewById(R.id.staysIv);
        restaurant = findViewById(R.id.restaurantIv);



        adventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IndexActivity.this, RegisterActivity.class));
            }
        });

        stay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IndexActivity.this, RegisterActivity.class));
            }
        });

        restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IndexActivity.this, RegisterActivity.class));
            }
        });
        
    }


}
