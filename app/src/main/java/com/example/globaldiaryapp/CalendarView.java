package com.example.globaldiaryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class CalendarView extends AppCompatActivity {

    String Year;
    String Month;
    String curDate;
    String formattedDate = "";

    android.widget.CalendarView calendarView;

    ImageButton writeEntry;
    ImageButton entryOverview;
    ImageButton settings;
    DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");;

        calendarView = findViewById(R.id.calendarView);

        entryOverview = findViewById(R.id.entryOverview);
        writeEntry = findViewById(R.id.writeEntry);
        settings = findViewById(R.id.settings);

        writeEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        entryOverview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ImagesActivity.class);
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

        calendarView.setOnDateChangeListener(new android.widget.CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(@NonNull android.widget.CalendarView view, int year, int month, int dayOfMonth) {
                Year = String.valueOf(year);
                Month = String.valueOf(month + 1);
                if(month+1<10) { Month = "0" + Month; }
                curDate = String.valueOf(dayOfMonth);
                if(dayOfMonth<10) { curDate = "0" + curDate; }
                formattedDate = String.format("%s-%s-%s", Year, Month, curDate);
                mDatabaseRef.orderByChild("date").equalTo(formattedDate).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.getChildrenCount()==0){
                            Toast.makeText(getApplicationContext(),"No entries for this date",Toast.LENGTH_SHORT).show();
                        } else {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Upload upload = snapshot.getValue(Upload.class);

                                Intent intent = new Intent(getApplicationContext(), editEntry.class);
                                intent.putExtra("name", upload.getName());
                                intent.putExtra("imageUrl", upload.getImageUrl());
                                startActivity(intent);
                                break;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }





}
