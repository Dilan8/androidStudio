package com.example.app;


import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseRegistrar;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


public class add_adventure extends AppCompatActivity {


    EditText editName;
    EditText editDis;
    EditText loca;
    Button add,choose;
    ImageView img;
    adventureAdd adventureAdd;
    StorageReference mStorageRef;
    private StorageTask uploadTask;
    public Uri imguri;
    DatabaseReference  databaseAdventure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_adventure);


        mStorageRef=FirebaseStorage.getInstance().getReference("Adventure");
        databaseAdventure = FirebaseDatabase.getInstance().getReference().child("adventureAdd");

        loca = (EditText)findViewById(R.id.loca);
        editName = ( EditText) findViewById(R.id.editName);
        editDis = ( EditText) findViewById(R.id.editDis);
        add = (Button) findViewById(R.id.add);
        choose=(Button) findViewById(R.id.chooseImage);
        img=(ImageView) findViewById(R.id.image);
        adventureAdd =new adventureAdd();

        choose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Filechooser();


            }


        });

        add.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                if(uploadTask != null && uploadTask.isInProgress() ){
                    Toast.makeText(add_adventure.this, "upload in progress",Toast.LENGTH_LONG).show();
                }else{
                    Fileuploder();
                }


            }


        });
    }

    private String getExtension(Uri uri)
    {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    private void Filechooser()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
            imguri=data.getData();
            img.setImageURI(imguri);

        }
    }

    private void Fileuploder(){

        String  ImagID;
        ImagID =System.currentTimeMillis()+"."+getExtension(imguri);
        adventureAdd.setAdventureName(editName.getText().toString().trim());
        adventureAdd.setAdventureDis(editDis.getText().toString().trim());
        adventureAdd.setAdventureLoc(loca.getText().toString().trim());
        adventureAdd.setImageID(ImagID);
        databaseAdventure.child(adventureAdd.getAdventureName()).push().setValue(adventureAdd);

        StorageReference Ref = mStorageRef.child(ImagID);
        uploadTask = Ref.putFile(imguri)
         .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Toast.makeText(add_adventure.this, "Image add suceesfully",Toast.LENGTH_LONG).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...

                    }
                });

    }



//    private void addAdventure(){
//
//        String name = editName.getText().toString().trim();
//        String Dis = editDis.getText().toString().trim();
//
//        if(!TextUtils.isEmpty(name)){
//
//           String id = databaseAdventure.push().getKey();
//
//            adventureAdd addAdventure = new adventureAdd(id, name,Dis);
//            databaseAdventure.child(addAdventure.getAdventureDis()).setValue(addAdventure);
//            databaseAdventure.child(addAdventure.getAdventureName()).setValue(addAdventure);
//            databaseAdventure.child(addAdventure.getAdventureId()).setValue(addAdventure);
//            Toast.makeText(this,"places added", Toast.LENGTH_LONG).show();
//        }else{
//            Toast.makeText(this,"You should enter a name",Toast.LENGTH_LONG).show();
//
//        }

 //   }


}
