package com.example.globaldiaryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import kotlin.collections.ArrayDeque;

public class ImagesActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;

    private ProgressBar mProgressCircle;

    private DatabaseReference mDatabaseRef;
    private FirebaseUser user;
    private FirebaseAuth auth;
    private List<DataSnapshot> mSnapshots;

    ImageButton calendar;
    ImageButton writeEntry;
    ImageButton settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        calendar = findViewById(R.id.calendar);
        writeEntry = findViewById(R.id.writeEntry);
        settings = findViewById(R.id.settings);

        mRecyclerView = findViewById(R.id.recyclerView);
        //Improves performance
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mProgressCircle = findViewById(R.id.progressCircle);


        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        mDatabaseRef.orderByChild("userID").equalTo(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mSnapshots = new ArrayList<>();
                for (DataSnapshot postSnapshot : snapshot.getChildren()){
                    //Upload upload = postSnapshot.getValue(Upload.class);
                    mSnapshots.add(0, postSnapshot);
                }

                mAdapter = new ImageAdapter(ImagesActivity.this, mSnapshots);

                mRecyclerView.setAdapter(mAdapter);

                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ImagesActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });

        // Event handlers for buttons
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CalendarView.class);
                startActivity(intent);
            }
        });

        writeEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });

    }
}