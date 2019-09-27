package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Login_Form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);


        Button button = findViewById(R.id.btn_profile);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Main_oldActivity.class));
            }
        });

        Button button2 = findViewById(R.id.btn_register);
        button2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Signup_Form.class));
            }
        });

        Button button3 = findViewById(R.id.btn_admin);
        button3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), admin.class));
            }
        });
    }


    }






