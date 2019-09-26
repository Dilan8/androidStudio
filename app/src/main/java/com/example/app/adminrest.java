package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class adminrest extends AppCompatActivity {

    DatabaseReference updateRefer;

    EditText ImageNameDetails1,ImageDiscrptionDetails1,ImageLocationDetails1;
    ImageView ImageDetails1;
    Button update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminrest);




        ImageNameDetails1 = findViewById(R.id.name_view4);
        ImageDiscrptionDetails1= findViewById(R.id.des_view4);
        ImageDetails1 = findViewById(R.id.img_view4);
        ImageLocationDetails1= findViewById(R.id.url_view4);
        update = findViewById(R.id.button401);

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



    public void update(View view) {

        Intent i = this.getIntent();
        String id = i.getExtras().getString("ID_Key");

        updateRefer = FirebaseDatabase.getInstance().getReference().child("Restaurants").child(id);

        ImageNameDetails1 = findViewById(R.id.name_view4);
        ImageDiscrptionDetails1= findViewById(R.id.des_view4);
        ImageLocationDetails1= findViewById(R.id.url_view4);



        String name = ImageNameDetails1.getText().toString();
        String name1 = ImageDiscrptionDetails1.getText().toString();
        String name2 = ImageLocationDetails1.getText().toString();

        Toast.makeText(this,id,Toast.LENGTH_SHORT).show();
        updateRefer.child("name").setValue(name);
        updateRefer.child("des").setValue(name1);
        updateRefer.child("url").setValue(name2);
        Toast.makeText(this,"Successfully Updated",Toast.LENGTH_SHORT).show();

    }
}
