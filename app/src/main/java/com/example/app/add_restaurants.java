package com.example.app;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


public class add_restaurants extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private Button mButtonChooseImage1;
    private Button mButtonUpload1;
    private TextView mTextViewShowUploads1;
    private EditText mEditTextFileName1;
    private EditText mEditTextFileName2;
    private EditText mEditTextFileName3;
    private ImageView mImageView1;
    private ProgressBar mProgressBar1;

    private Uri mImageUri;

    private StorageReference mStorageRef1;
    private DatabaseReference mDatabaseRef1;

    private StorageTask mUploadTask1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurants);

        mButtonChooseImage1 = findViewById(R.id.button_choose_image1);
        mButtonUpload1 = findViewById(R.id.button_upload1);
        mTextViewShowUploads1 = findViewById(R.id.text_view_show_uploads1);
        mEditTextFileName1 = findViewById(R.id.edit_text_file_name1);
        mEditTextFileName2 = findViewById(R.id.edit_text_file_name2);
        mEditTextFileName3 = findViewById(R.id.edit_text_file_name3);
        mImageView1 = findViewById(R.id.image_view1);
        mProgressBar1 = findViewById(R.id.progress_bar1);

        mStorageRef1 = FirebaseStorage.getInstance().getReference("Restaurants");
        mDatabaseRef1 = FirebaseDatabase.getInstance().getReference("Restaurants");

        mButtonChooseImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        mButtonUpload1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask1 != null && mUploadTask1.isInProgress()) {
                    Toast.makeText(add_restaurants.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    addFile();
                }
            }
        });

        mTextViewShowUploads1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagesAct();
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Picasso.get().load(mImageUri).into(mImageView1);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void addFile() {
        if (mEditTextFileName1.getText().toString().equals("")){
            mEditTextFileName1.setError("must filled");
            Toast.makeText(add_restaurants.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
        }

        else if (mEditTextFileName2.getText().toString().equals("")){
            mEditTextFileName2.setError("must filled");
            Toast.makeText(add_restaurants.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
        }

        else if (mEditTextFileName3.getText().toString().equals("")){
            mEditTextFileName3.setError("must filled");
            Toast.makeText(add_restaurants.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
        }

        else if (mImageUri != null) {
            final StorageReference fileReference = mStorageRef1.child(System.currentTimeMillis() + "." +
                    getFileExtension(mImageUri));
            mUploadTask1 = fileReference.putFile(mImageUri)

                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        private static final String TAG = "Restaurants";

                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar1.setProgress(0);
                                }
                            }, 500);
                            Toast.makeText(add_restaurants.this, "Upload Successful", Toast.LENGTH_LONG).show();
//***************************************************************************************************************
                            Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!urlTask.isSuccessful()) ;
                            Uri downloadUrl = urlTask.getResult();

                            String uploadID = mDatabaseRef1.push().getKey();

                            Log.d(TAG, "onSuccess: firebase download url: " + downloadUrl.toString());
                            Upload_res upload = new Upload_res(uploadID,mEditTextFileName1.getText().toString().trim(), downloadUrl.toString(),mEditTextFileName2.getText().toString().trim(),mEditTextFileName3.getText().toString().trim());


                            mDatabaseRef1.child(uploadID).setValue(upload);

//**************************************************************************************************************
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(add_restaurants.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = 100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount();
                            mProgressBar1.setProgress((int) progress);
                        }
                    });
        } else {
            Toast.makeText(this, "No Image selected", Toast.LENGTH_SHORT).show();
        }
    }
    private void openImagesAct() {
        Intent intent = new Intent(this, ResActivity.class);
        startActivity(intent);
    }
}