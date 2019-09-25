package com.example.app;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ResUserAdapter extends RecyclerView.Adapter<ResUserAdapter.ImageViewHolder> {
    private Context mContext1;
    private List<Upload_res> mUploads1;
    private OnItemClickListener mListener2;

    public ResUserAdapter(Context context, List<Upload_res> uploads) {
        mContext1 = context;
        mUploads1 = uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext1).inflate(R.layout.restaur, parent, false);
        return new ImageViewHolder(v);
    }

    @NonNull


    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Upload_res uploadCurrent = mUploads1.get(position);
        holder.textViewName.setText(uploadCurrent.getName());
        Picasso.with(mContext1)
                .load(uploadCurrent.getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mUploads1.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        public TextView textViewName;
        public ImageView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_name2);
            imageView = itemView.findViewById(R.id.image_view_upload2);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener2 != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener2.onItemClick(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (mListener2 != null) {
                int position = getAdapterPosition();

            }
            return false;
        }
    }
    public interface OnItemClickListener {
        void onItemClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener2 = listener;
    }
}