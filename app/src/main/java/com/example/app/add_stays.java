package com.example.app;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class add_stays extends AppCompatActivity {

    addStaysData data;
    Button choose,done,map;
    EditText name, cost;
    ImageView img;
    StorageReference mStorageRef;
    DatabaseReference dbreff;
    public Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stays);
        mStorageRef = FirebaseStorage.getInstance().getReference("StaysImages");
        dbreff = FirebaseDatabase.getInstance().getReference().child("StaysData");
        choose = (Button)findViewById(R.id.btnChoose);
        done = (Button)findViewById(R.id.addStaysDone);
        map = (Button)findViewById(R.id.addStaysMap);
        img = (ImageView)findViewById((R.id.addStayImage));
        name = (EditText)findViewById(R.id.addStaysName);
        cost = (EditText)findViewById(R.id.addStaysCost);
        data = new addStaysData();

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileChooser();
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataUploader();
            }
        });
    }
    private String getExtension(Uri uri)
    {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));

    }
    private void FileChooser()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    private void DataUploader()
    {
        data.setName(name.getText().toString().trim());
        int p = Integer.parseInt(cost.getText().toString().trim());
        data.setCost(p);
        data.setImageId(System.currentTimeMillis()+"."+getExtension(imageUri));
        dbreff.push().setValue(data);

        StorageReference Ref = mStorageRef.child(System.currentTimeMillis()+"."+getExtension(imageUri));

        Ref.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        // Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Toast.makeText(add_stays.this,"Uploaded", Toast.LENGTH_LONG).show();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            imageUri = data.getData();
            img.setImageURI(imageUri);
        }
    }
}
