package com.example.globaldiaryapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> implements View.OnClickListener {

    private Context mContext;
    private List<Upload> mUploads;
    FirebaseAuth auth;
    FirebaseUser user;

    public ImageAdapter(Context context, List<Upload> uploads){
        mContext = context;
        mUploads = uploads;
    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        Upload uploadCurrent = mUploads.get(position);
        System.out.println("User UploadCurrent " + uploadCurrent.getUserID());
        System.out.println("User Current " + user.getUid());
        if(uploadCurrent.getUserID().equals(user.getUid()))
        {
            holder.textViewName.setText(uploadCurrent.getName());
            Picasso.with(mContext)
                    .load(uploadCurrent.getImageUrl())
                    .fit()
                    .centerCrop()
                    .into(holder.imageView);

        }

        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        Upload upload = mUploads.get(position);

        Intent intent = new Intent(mContext, editEntry.class);
        intent.putExtra("name", upload.getName());
        intent.putExtra("imageUrl", upload.getImageUrl());
        mContext.startActivity(intent);
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            imageView = itemView.findViewById(R.id.imageViewUpload);
        }
    }
}
