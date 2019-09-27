package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class staysfulldetail<mDBListener3> extends AppCompatActivity {

    TextView ImageNameDetails3,ImageDiscrptionDetails3,ImageLocationDetails3;
    ImageView ImageDetails3;
    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staysfulldetail);

        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setTitle("Details");


        firebaseAuth = FirebaseAuth.getInstance();


        ImageNameDetails3 = findViewById(R.id.ImageNameDetails8);
        ImageDiscrptionDetails3 = findViewById(R.id.ImageDiscrptionDetails8);
        ImageDetails3 = findViewById(R.id.ImageDetails8);
        ImageLocationDetails3=findViewById(R.id.ImageLocationDetails8);


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
}
