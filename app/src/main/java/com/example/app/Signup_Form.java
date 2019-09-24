package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Signup_Form extends AppCompatActivity {

    EditText txt_fullName, txt_username, txt_email, txt_password;
    Button btn_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);

        Button button = findViewById(R.id.btn_login);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login_Form.class));
            }
        });


        //Casting Views
        txt_fullName = (EditText)findViewById(R.id.txt_full_name);
        txt_username = (EditText)findViewById(R.id.txt_username);
        txt_email = (EditText)findViewById(R.id.txt_email);
        txt_password = (EditText)findViewById(R.id.txt_password);
        btn_register = (Button) findViewById(R.id.btn_register);



        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 final String fullName=txt_fullName.getText().toString();
                 final String userName=txt_username.getText().toString();
                 final String email=txt_email.getText().toString();
                 String password=txt_password.getText().toString();


                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Signup_Form.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Signup_Form.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(fullName)){
                    Toast.makeText(Signup_Form.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(Signup_Form.this, "Please Enter Username", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }









}
