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
    TextView dataView;

    DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("uploads");

        calendarView = findViewById(R.id.calendarView);
        dataView = findViewById(R.id.dataView);

        calendarView.setOnDateChangeListener(new android.widget.CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(@NonNull android.widget.CalendarView view, int year, int month, int dayOfMonth) {
                Year = String.valueOf(year);
                Month = String.valueOf(month + 1);
                curDate = String.valueOf(dayOfMonth);
                formattedDate = String.format("%s-%s-%s", Year, Month, curDate);
                mDatabaseRef.orderByChild("date").equalTo(formattedDate).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String data = "";
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            //String title = snapshot.child("title").getValue().toString();
                            //String content = snapshot.child("content").getValue().toString();
                            //data += "Title: " + title + "\nContent: " + content + "\n\n";
                            Upload upload = snapshot.getValue(Upload.class);

                            Intent intent = new Intent(getApplicationContext(), editEntry.class);
                            intent.putExtra("name", upload.getName());
                            intent.putExtra("imageUrl", upload.getImageUrl());
                            startActivity(intent);
                            break;
                        }

                        if (data.equals("")) {
                            dataView.setText("No data available for selected date.");
                        } else {
                            dataView.setText(data);
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
