package com.example.app;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AdventureAdapter extends RecyclerView.Adapter <AdventureAdapter.ImageViewHolder> {

    private Context mContext3;
    private List<upload> mUploads3;
    private OnItemClickListener mListener3;

    public AdventureAdapter(Context context, List<upload> uploads) {
        mContext3 = context;
        mUploads3 = uploads;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext3).inflate(R.layout.adventure_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        upload uploadCurrent = mUploads3.get(position);
        holder.textViewName.setText(uploadCurrent.getName());
        Picasso.with(mContext3)
                .load(uploadCurrent.getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageView)

        ;
    }

    @Override
    public int getItemCount() {
        return mUploads3.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        public TextView textViewName;
        public ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name3);
            imageView = itemView.findViewById(R.id.image_view_upload3);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View view) {

            if (mListener3 != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener3.onItemClick(position);
                }
            }


        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//            menu.setHeaderTitle("Select Action");
//            MenuItem Edit = menu.add(Menu.NONE, 1, 1, "Edit  ");
//            MenuItem delete = menu.add(Menu.NONE, 2, 2, "Delete");
//
//            Edit.setOnMenuItemClickListener(this);
//            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (mListener3 != null) {
                int position = getAdapterPosition();
//                if (position != RecyclerView.NO_POSITION) {
//                switch (item.getItemId()) {
//                    case 1:
//                        mListener3.onWhatEverClick(position);
//                        return true;
//                    case 2:
//                        mListener3.onDeleteClick(position);
//                        return true;
//                }
//            }
        }
            return false;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

//        void onWhatEverClick(int position);
//
//        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener3 = listener;
    }

}