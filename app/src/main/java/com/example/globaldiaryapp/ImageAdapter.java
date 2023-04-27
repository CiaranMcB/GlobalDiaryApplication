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
import com.google.firebase.database.DataSnapshot;
import com.squareup.picasso.Picasso;

import java.text.BreakIterator;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> implements View.OnClickListener {

    private Context mContext;
    private List<DataSnapshot> mSnapshots;
    FirebaseAuth auth;
    FirebaseUser user;

    public ImageAdapter(Context context, List<DataSnapshot> snapshots){
        mContext = context;
        mSnapshots = snapshots;
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

        DataSnapshot snapshot = mSnapshots.get(position);
        Upload uploadCurrent = snapshot.getValue(Upload.class);
        System.out.println("User UploadCurrent " + uploadCurrent.getUserID());
        System.out.println("User Current " + user.getUid());
        if(uploadCurrent.getUserID() != null && uploadCurrent.getUserID().equals(user.getUid()))
        {
            holder.textViewDate.setText(uploadCurrent.getDate());
            holder.textViewName.setText(uploadCurrent.getName());
            if(uploadCurrent.getMood()==null){
                holder.textViewMood.setText("");
            } else { holder.textViewMood.setText(uploadCurrent.getMood()); }
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
        return mSnapshots.size();
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        DataSnapshot snapshot = mSnapshots.get(position);
        Upload upload = snapshot.getValue(Upload.class);

        Intent intent = new Intent(mContext, editEntry.class);
        intent.putExtra("name", upload.getName());
        intent.putExtra("imageUrl", upload.getImageUrl());
        intent.putExtra("key", snapshot.getKey());
        mContext.startActivity(intent);
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public TextView textViewDate;
        public ImageView imageView;
        public TextView textViewMood;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewMood = itemView.findViewById(R.id.textViewMood);
            imageView = itemView.findViewById(R.id.imageViewUpload);
        }
    }
}
