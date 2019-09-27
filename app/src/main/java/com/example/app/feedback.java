package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class feedback extends AppCompatActivity {
    private TextView mTextMessage;
    FirebaseAuth firebaseAuth;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    startActivity(new Intent(getApplicationContext(), Main_oldActivity.class));
//                    return false;
//
//                case R.id.navigation_notifications:
//                    startActivity(new Intent(getApplicationContext(), profile.class));
//                    return true;
//            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Feedback");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        firebaseAuth = FirebaseAuth.getInstance();

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

    private void checkUserStatus(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            //mProfileTv.setText(user.getEmail());
        }
        else{
            startActivity(new Intent(feedback.this, MainActivity.class));
            finish();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        checkUserStatus();
        super.onStart();
    }
}


