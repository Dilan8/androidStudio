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

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class   restAct extends AppCompatActivity implements ResUserAdapter.OnItemClickListener, NavigationView.OnNavigationItemSelectedListener {


    //Drawer
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    FirebaseAuth firebaseAuth;


    private RecyclerView mRecyclerView2;
    private ResUserAdapter mAdapter2;

    private ProgressBar mProgressCircle2;
    private FirebaseStorage mStorage2;
    private DatabaseReference mDatabaseRef2;
    private ValueEventListener mDBListener2;
    private List<Upload_res> mUploads1;

    private void openDetailActivity (String[] data){
        Intent intent = new Intent(this,RestaurantsinDetailed.class);
        intent.putExtra("Name_Key",data[1]);
        intent.putExtra("Description_Key",data[2]);
        intent.putExtra("Image_Key",data[0]);
        intent.putExtra("Url_Key",data[3]);

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_act);


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
        actionBar.setTitle("Restaurants");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        mRecyclerView2 = findViewById(R.id.recycler_view2);
        mRecyclerView2.setHasFixedSize(true);
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(this));

        mProgressCircle2 = findViewById(R.id.progress_circle2);

        mUploads1 = new ArrayList<>();


        mAdapter2 = new ResUserAdapter(restAct.this, mUploads1);

        mRecyclerView2.setAdapter(mAdapter2);

        mAdapter2.setOnItemClickListener(restAct.this);


        mStorage2 = FirebaseStorage.getInstance();

        mDatabaseRef2 = FirebaseDatabase.getInstance().getReference("Restaurants");

        mDBListener2 = mDatabaseRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUploads1.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload_res upload = postSnapshot.getValue(Upload_res.class);
                    upload.setKey(postSnapshot.getKey());
                    mUploads1.add(upload);
                }
                mAdapter2.notifyDataSetChanged();

                mProgressCircle2.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(restAct.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle2.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Upload_res selectedItem=mUploads1.get(position);
        String[] uploadData = {selectedItem.getImageUrl(),selectedItem.getName(),selectedItem.getDes(),selectedItem.getUrl()};
        openDetailActivity(uploadData);
        Toast.makeText(this, "Normal click at position: " + position, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();
        mDatabaseRef2.removeEventListener(mDBListener2);
    }

    //Drawer
    private void checkUserStatus(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
        }
        else{
            startActivity(new Intent(restAct.this, MainActivity.class));
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
            startActivity(new Intent(restAct.this, IndexActivity.class));
        }
        if(id == R.id.nav_addv){
            startActivity(new Intent(restAct.this, AdventureActivity.class));
        }
        if(id == R.id.nav_stays){
            startActivity(new Intent(restAct.this, staysuserActivity.class));
        }
        if(id == R.id.nav_restaurant){
            startActivity(new Intent(this, restAct.class));
        }
        if(id == R.id.nav_profile){
            startActivity(new Intent(restAct.this, ProfileActivity.class));
        }
        if(id == R.id.nav_contact){
            startActivity(new Intent(restAct.this, ContactForm.class));
        }
        if(id == R.id.nav_feedback){
            startActivity(new Intent(restAct.this, feedback.class));
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