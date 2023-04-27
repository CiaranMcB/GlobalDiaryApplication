package com.example.globaldiaryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class editEntry extends AppCompatActivity {

    private ImageView mImageView;
    private TextView mTextViewName;
    private Button mButtonDelete;

    private String mImageUrl;
    private String mName;
    private String mKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry);

        mImageView = findViewById(R.id.image_view_delete);
        mTextViewName = findViewById(R.id.image_name_textview);
        mButtonDelete = findViewById(R.id.delete_button);

        Intent intent = getIntent();
        mImageUrl = intent.getStringExtra("imageUrl");
        mName = intent.getStringExtra("name");
        mKey = intent.getStringExtra("key");

        mTextViewName.setText(mName);
        Picasso.with(this)
                .load(mImageUrl)
                .fit()
                .centerCrop()
                .into(mImageView);

        mButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItem();
            }
        });
    }

    private void deleteItem() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("uploads");
        ref.orderByKey().equalTo(mKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() == 0) {
                    Toast.makeText(getApplicationContext(), "Entry not found", Toast.LENGTH_SHORT).show();
                } else {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        snapshot.getRef().removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(editEntry.this, "Entry deleted successfully", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(editEntry.this, "Failed to delete entry", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}