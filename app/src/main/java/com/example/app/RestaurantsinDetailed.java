package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class RestaurantsinDetailed extends AppCompatActivity {

    TextView ImageNameDetails1,ImageDiscrptionDetails1,ImageLocationDetails1;
    ImageView ImageDetails1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantsin_detailed);


        ImageNameDetails1 = findViewById(R.id.name_view);
        ImageDiscrptionDetails1= findViewById(R.id.des_view);
        ImageDetails1 = findViewById(R.id.img_view);
        ImageLocationDetails1= findViewById(R.id.url_view);

        Intent i = this.getIntent();
        String name =i.getExtras().getString("Name_Key");
        String imageUrl =i.getExtras().getString("Image_Key");
        String Description =i.getExtras().getString("Description_Key");


        String Location =i.getExtras().getString("Url_Key");




        ImageNameDetails1.setText(name);
        ImageDiscrptionDetails1.setText(Description);
        ImageLocationDetails1.setText(Location);
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(ImageDetails1);
    }
}