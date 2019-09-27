package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class admin_stays extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_stays);

        Button button = findViewById(R.id.add_stays_btn);
        Button button1 = findViewById(R.id.btn_manage_stays);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), add_stays.class));
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStaysImagesActivity();
            }
        });
    }
    private void openStaysImagesActivity(){
        Intent intent = new Intent(this,StaysImagesActivity.class);
        startActivity(intent);
    }
}
