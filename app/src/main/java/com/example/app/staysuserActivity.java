package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class staysuserActivity extends AppCompatActivity implements staysuserAdapter.OnItemClickListener {
    private RecyclerView mRecyclerView;
    private staysuserAdapter mAdapter;

    private ProgressBar mProgressCircle;

    private FirebaseStorage mStorage;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;
    private ArrayList<StaysUpload> mUploads;

    private void openDetailActivity (String[] data){
        Intent intent = new Intent(this,staysfulldetail.class);
        intent.putExtra("Name_Key",data[1]);
        intent.putExtra("Description_Key",data[2]);
        intent.putExtra("Image_Key",data[0]);
        intent.putExtra("Url_Key",data[3]);
       // intent.putExtra("ID_Key",data[0]);
        startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staysuser);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mProgressCircle = findViewById(R.id.progress_circle);

        mUploads = new ArrayList<>();

        mAdapter = new staysuserAdapter(staysuserActivity.this, mUploads);

        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(staysuserActivity.this);
        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("StaysData");

        EditText editText = findViewById(R.id.edittext);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        mDBListener=mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mUploads.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    StaysUpload upload = postSnapshot.getValue(StaysUpload.class);
                    upload.setKey(postSnapshot.getKey());
                    mUploads.add(upload);
                }

                mAdapter.notifyDataSetChanged();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(staysuserActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void filter(String text) {
        ArrayList<StaysUpload> filteredList = new ArrayList<>();

        for (StaysUpload item : mUploads) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        mAdapter.filterList(filteredList);
    }

    @Override
    public void onItemClick(int position) {

        StaysUpload selectedItem=mUploads.get(position);
        String[] uploadData = {selectedItem.getImageUrl(),selectedItem.getName(),selectedItem.getName3(),selectedItem.getName4()};
        openDetailActivity(uploadData);
        //Toast.makeText(this, "Normal click at position: " + position, Toast.LENGTH_SHORT).show();



    }


   // public void onWhatEverClick(int position) {

//        StaysUpload selectedItem=mUploads.get(position);
//        String[] uploadData = {selectedItem.getId(),selectedItem.getImageUrl(),selectedItem.getName(),selectedItem.getName3(),selectedItem.getName4()};
//        openDetailActivity(uploadData);
//        Toast.makeText(this, "position:" + position, Toast.LENGTH_SHORT).show();


   // }

//    @Override
//    public void onDeleteClick(int position) {
//        StaysUpload selectedItem = mUploads.get(position);
//        final String selectedKey = selectedItem.getKey();
//        StorageReference imageRef = mStorage.getReferenceFromUrl(selectedItem.getImageUrl());
//
//        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                mDatabaseRef.child(selectedKey).removeValue();
//                Toast.makeText(staysuserActivity.this, "Item Deleted", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }
}