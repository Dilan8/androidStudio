package com.example.app;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;


public class DetailsActivity<mDBListener> extends AppCompatActivity {
    DatabaseReference updateRef;

    EditText ImageNameDetails,ImageDiscrptionDetails,ImageLocationDetails,namex;
    ImageView ImageDetails;
    Button updateDet;
    upload mUploads;

    private static final  int PICK_IMAGE_REQUEST = 1;
    private Button mButtonChooseImage;

    private Uri mImageUri;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


         updateRef= FirebaseDatabase.getInstance().getReference().child("uploads");

        ImageNameDetails = findViewById(R.id.ImageNameDetails);
        ImageDiscrptionDetails = findViewById(R.id.ImageDiscrptionDetails);
        ImageDetails = findViewById(R.id.ImageDetails);
        ImageLocationDetails=findViewById(R.id.ImageLocationDetails);
        updateDet = findViewById(R.id.button4);




        Intent i = this.getIntent();
        String name =i.getExtras().getString("Name_Key");
        String imageUrl =i.getExtras().getString("Image_Key");
        String Description =i.getExtras().getString("Description_Key");


        String Location =i.getExtras().getString("Url_Key");




        ImageNameDetails.setText(name);
        ImageDiscrptionDetails.setText(Description);
        ImageLocationDetails.setText(Location);
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(ImageDetails);


    }

    public void update(View view) {


        Intent i = this.getIntent();
        String id =i.getExtras().getString("ID_Key");

        updateRef= FirebaseDatabase.getInstance().getReference().child("uploads").child(id);
        ImageNameDetails = findViewById(R.id.ImageNameDetails);
        ImageDiscrptionDetails = findViewById(R.id.ImageDiscrptionDetails);
        ImageLocationDetails = findViewById(R.id.ImageLocationDetails);


        String content = ImageNameDetails.getText().toString();


        String contentA = ImageDiscrptionDetails.getText().toString();
        String contentB = ImageLocationDetails.getText().toString();


        Toast.makeText(this, id, Toast.LENGTH_LONG).show();
        updateRef.child("name3").setValue(contentA);
        updateRef.child("name4").setValue(contentB);
        updateRef.child("name").setValue(content);
        Toast.makeText(this,"Sucessfully updated",Toast.LENGTH_SHORT).show();

    }
}
