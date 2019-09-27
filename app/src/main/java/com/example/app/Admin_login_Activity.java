package com.example.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Admin_login_Activity extends AppCompatActivity {

    TextView User;
    Button mLoginBtn;
    EditText mEmailEt, mPasswordEt;

    private FirebaseAuth mAuth;

    ProgressDialog pd;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login_);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Admin Login");

        mAuth = FirebaseAuth.getInstance();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        User = findViewById(R.id.user);
        mEmailEt = findViewById(R.id.edtemail);
        mPasswordEt = findViewById(R.id.edtpassword);
        mLoginBtn = findViewById(R.id.login_btn);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmailEt.getText().toString();
                String passw = mPasswordEt.getText().toString();

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    mEmailEt.setError("Invalid Email..");
                    mEmailEt.setFocusable(true);
                }
                else{
                    loginAdmin(email, passw);
                }

            }
        });









        User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_login_Activity.this, LoginActivity.class));
                finish();
            }
        });
    }

    private void loginAdmin(String email, String passw){
        if(email.equals("admin@123.com") && passw.equals("admin123")){
            startActivity(new Intent(Admin_login_Activity.this, Admin_dash_Activity.class));
            finish();
        }
        else{
            Toast.makeText(this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}
