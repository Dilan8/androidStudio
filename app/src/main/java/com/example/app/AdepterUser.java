package com.example.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.*;

public class AdepterUser extends RecyclerView.Adapter<AdepterUser.MyHolder> {

    Context context;
    List<ModelUsers> usersList;

    public AdepterUser(Context context, List<ModelUsers> usersList) {
        this.context = context;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.row_users, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        String userImage = usersList.get(position).getImage();
        String userName = usersList.get(position).getName();
        final String userEmail = usersList.get(position).getEmail();

        holder.mnameTv.setText(userName);
        holder.mEmailTv.setText(userEmail);
        try{
            Picasso.get().load(userImage)
                    .placeholder(R.drawable.ic_default_img)
                    .into(holder.mAvatarIv);
        }
        catch (Exception e){

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, ""+userEmail, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    class MyHolder extends ViewHolder{


        ImageView mAvatarIv;
        TextView mnameTv, mEmailTv;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            mAvatarIv = itemView.findViewById(R.id.avatarIv);
            mnameTv = itemView.findViewById(R.id.nameTv);
            mEmailTv = itemView.findViewById(R.id.emailTv);
        }
    }
}
