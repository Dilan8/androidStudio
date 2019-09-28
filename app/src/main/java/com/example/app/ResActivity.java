package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ResActivity extends AppCompatActivity implements ResAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView1;
    private ResAdapter mAdapter1;
    private EditText editText;
    private ProgressBar mProgressCircle1;
    private FirebaseStorage mStorage1;
    private DatabaseReference mDatabaseRef1;
    private ValueEventListener mDBListener1;
    private List<Upload_res> mUploads;
    private ArrayList<Upload_res>mUploadsFull1;


    private void openDetailActivity(String[] data) {
        Intent intent = new Intent(this, adminrest.class);
        intent.putExtra("Name_Key", data[3]);
        intent.putExtra("Description_Key", data[0]);
        intent.putExtra("Image_Key", data[1]);
        intent.putExtra("Url_Key", data[4]);
        intent.putExtra("ID_Key", data[2]);

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);

        mRecyclerView1 = findViewById(R.id.recycler_view1);
        mRecyclerView1.setHasFixedSize(true);
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(this));

        mProgressCircle1 = findViewById(R.id.progress_circle1);

        mUploads = new ArrayList<>();
        mUploadsFull1 = new ArrayList<>();

        mAdapter1 = new ResAdapter(ResActivity.this, mUploads);

        mRecyclerView1.setAdapter(mAdapter1);

        mAdapter1.setOnItemClickListener(ResActivity.this);


        mStorage1 = FirebaseStorage.getInstance();

        mDatabaseRef1 = FirebaseDatabase.getInstance().getReference("Restaurants");
        editText = findViewById(R.id.edittext12);
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

        mDBListener1 = mDatabaseRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUploads.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload_res upload = postSnapshot.getValue(Upload_res.class);
                    upload.setKey(postSnapshot.getKey());
                    mUploads.add(upload);
                }
                mAdapter1.notifyDataSetChanged();

                mProgressCircle1.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ResActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle1.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onItemClick(int position) {
//        Upload_res selectedItem=mUploads.get(position);
//        String[] uploadData = {selectedItem.getID(),selectedItem.getImageUrl(),selectedItem.getName(),selectedItem.getDes(),selectedItem.getUrl()};
//        openDetailActivity(uploadData);
//        Toast.makeText(this, "Normal click at position: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWhatEverClick(int position) {
        Upload_res selectedItem = mUploads.get(position);
        String[] uploadData = {selectedItem.getDes(), selectedItem.getImageUrl(), selectedItem.getID(), selectedItem.getName(), selectedItem.getUrl()};
        openDetailActivity(uploadData);
        //Toast.makeText(this, "Normal click at position: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(int position) {
        Upload_res selectedItem = mUploads.get(position);
        final String selectedKey = selectedItem.getKey();

        StorageReference imageRef = mStorage1.getReferenceFromUrl(selectedItem.getImageUrl());
        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mDatabaseRef1.child(selectedKey).removeValue();
                Toast.makeText(ResActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef1.removeEventListener(mDBListener1);
    }
    private void  filter(String text){

        ArrayList<Upload_res> filteredList = new ArrayList<>();
        for (Upload_res item :mUploads){
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        mUploadsFull1=new ArrayList<>(mUploads);
        mAdapter1.filterList(filteredList);
        mUploads=new ArrayList<>(filteredList);
    }

}