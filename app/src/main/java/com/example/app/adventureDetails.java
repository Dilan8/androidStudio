package com.example.app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class adventureDetails<mDBListener3> extends AppCompatActivity {


    TextView ImageNameDetails3,ImageDiscrptionDetails3,ImageLocationDetails3;
    ImageView ImageDetails3;
    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure_details);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Details");


        firebaseAuth = FirebaseAuth.getInstance();


        ImageNameDetails3 = findViewById(R.id.ImageNameDetails3);
        ImageDiscrptionDetails3 = findViewById(R.id.ImageDiscrptionDetails3);
        ImageDetails3 = findViewById(R.id.ImageDetails3);
        ImageLocationDetails3=findViewById(R.id.ImageLocationDetails3);


        Intent i = this.getIntent();
        String name =i.getExtras().getString("Name_Key");
        String imageUrl =i.getExtras().getString("Image_Key");
        String Description =i.getExtras().getString("Description_Key");
        String Location =i.getExtras().getString("Url_Key");




        ImageNameDetails3.setText(name);
        ImageDiscrptionDetails3.setText(Description);
        ImageLocationDetails3.setText(Location);
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(ImageDetails3);


    }

    private void checkUserStatus(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            //mProfileTv.setText(user.getEmail());
        }
        else{
            startActivity(new Intent(adventureDetails.this, MainActivity.class));
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
