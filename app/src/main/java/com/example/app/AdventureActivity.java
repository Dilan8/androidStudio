package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class AdventureActivity extends AppCompatActivity implements AdventureAdapter.OnItemClickListener, NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView mRecyclerView3;
    private AdventureAdapter mAdapter3;

    private ProgressBar mProgressCircle3;

    private FirebaseStorage mStorage3;
    private DatabaseReference mDatabaseRef3;
    private ValueEventListener mDBListener3;
    private List<upload> mUploads3;

    //Drawer
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    FirebaseAuth firebaseAuth;

    private void openDetailActivity (String[] data){
        Intent intent = new Intent(this,adventureDetails.class);
        intent.putExtra("Name_Key",data[1]);
        intent.putExtra("Description_Key",data[2]);
        intent.putExtra("Image_Key",data[0]);
        intent.putExtra("Url_Key",data[3]);
     //   intent.putExtra("ID_Key",data[0]);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure2);

        //Drawer
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView)findViewById(R.id.navagation_view);
        navigationView.setNavigationItemSelectedListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Adventures");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        mRecyclerView3 = findViewById(R.id.recycler_view3);
        mRecyclerView3.setHasFixedSize(true);
        mRecyclerView3.setLayoutManager(new LinearLayoutManager(this));

        mProgressCircle3 = findViewById(R.id.progress_circle3);

        mUploads3 = new ArrayList<>();

        mAdapter3 = new AdventureAdapter(AdventureActivity.this, mUploads3);

        mRecyclerView3.setAdapter(mAdapter3);

        mAdapter3.setOnItemClickListener(AdventureActivity.this);
        mStorage3 = FirebaseStorage.getInstance();
        mDatabaseRef3 = FirebaseDatabase.getInstance().getReference("uploads");

        mDBListener3=mDatabaseRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mUploads3.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    upload upload = postSnapshot.getValue(upload.class);
                    upload.setKey(postSnapshot.getKey());
                    mUploads3.add(upload);
                }

                mAdapter3.notifyDataSetChanged();
                mProgressCircle3.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AdventureActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle3.setVisibility(View.INVISIBLE);
            }
        });
    }



    @Override
    public void onItemClick(int position) {
        upload selectedItem=mUploads3.get(position);
        String[] uploadData = {selectedItem.getImageUrl(),selectedItem.getName(),selectedItem.getName3(),selectedItem.getName4()};
        openDetailActivity(uploadData);
        Toast.makeText(this, "Normal click at position: " + position, Toast.LENGTH_SHORT).show();

    }


//    public void onWhatEverClick(int position) {
//
//        upload selectedItem=mUploads3.get(position);
//        String[] uploadData = {selectedItem.getId(),selectedItem.getImageUrl(),selectedItem.getName(),selectedItem.getName3(),selectedItem.getName4()};
//        openDetailActivity(uploadData);
//        Toast.makeText(this, "position:" + position, Toast.LENGTH_SHORT).show();
//
//
//    }
//
//    @Override
//    public void onDeleteClick(int position) {
//        upload selectedItem = mUploads3.get(position);
//        final String selectedKey = selectedItem.getKey();
//        StorageReference imageRef = mStorage3.getReferenceFromUrl(selectedItem.getImageUrl());
//
//        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                mDatabaseRef3.child(selectedKey).removeValue();
////                Toast.makeText(ImageActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef3.removeEventListener(mDBListener3);
    }

    //Drawer
    private void checkUserStatus(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            //mProfileTv.setText(user.getEmail());
        }
        else{
            startActivity(new Intent(AdventureActivity.this, MainActivity.class));
            finish();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    @Override
    protected void onStart() {
        checkUserStatus();
        super.onStart();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_main){
            startActivity(new Intent(AdventureActivity.this, IndexActivity.class));
        }
        if(id == R.id.nav_addv){
            startActivity(new Intent(AdventureActivity.this, AdventureActivity.class));
        }
        if(id == R.id.nav_stays){
            startActivity(new Intent(AdventureActivity.this, ProfileActivity.class));
        }
        if(id == R.id.nav_restaurant){
            startActivity(new Intent(AdventureActivity.this, restAct.class));
        }
        if(id == R.id.nav_profile){
            startActivity(new Intent(AdventureActivity.this, ProfileActivity.class));
        }
        if(id == R.id.nav_contact){
            startActivity(new Intent(AdventureActivity.this, ContactForm.class));
        }
        if(id == R.id.nav_feedback){
            startActivity(new Intent(AdventureActivity.this, feedback.class));
        }
        if(id == R.id.action_logout){
            firebaseAuth.signOut();
            checkUserStatus();
            finish();
            return true;
        }
        return false;
    }

}