package com.example.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class stayadmindetail<mDBListener> extends AppCompatActivity {
    DatabaseReference updateRef;

    EditText ImageNameDetails,ImageDiscrptionDetails,ImageLocationDetails,namex;
    ImageView ImageDetails;
    Button updateDet;
    StaysUpload mUploads;

    private static final  int PICK_IMAGE_REQUEST = 1;
    private Button mButtonChooseImage;

    private Uri mImageUri;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stayadmindetail);


        updateRef= FirebaseDatabase.getInstance().getReference().child("StaysData");

        ImageNameDetails = findViewById(R.id.ImageNameDetails9);
        ImageDiscrptionDetails = findViewById(R.id.ImageDiscrptionDetails9);
        ImageDetails = findViewById(R.id.ImageDetails9);
        ImageLocationDetails=findViewById(R.id.ImageLocationDetails9);
        updateDet = findViewById(R.id.button99);




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

        updateRef= FirebaseDatabase.getInstance().getReference().child("StaysData").child(id);
        ImageNameDetails = findViewById(R.id.ImageNameDetails9);
        ImageDiscrptionDetails = findViewById(R.id.ImageDiscrptionDetails9);
        ImageLocationDetails = findViewById(R.id.ImageLocationDetails9);


        String content = ImageNameDetails.getText().toString();


        String contentA = ImageDiscrptionDetails.getText().toString();
        String contentB = ImageLocationDetails.getText().toString();


        Toast.makeText(this, id, Toast.LENGTH_LONG).show();
        updateRef.child("name3").setValue(contentA);
        updateRef.child("name4").setValue(contentB);
        updateRef.child("name").setValue(content);



        Toast.makeText(this,"Successfully Updated", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, StaysImagesActivity.class);
        startActivity(intent);

    }
}