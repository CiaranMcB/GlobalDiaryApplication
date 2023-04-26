package com.example.globaldiaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class CalendarView extends AppCompatActivity {

    ImageButton entryOverview;
    ImageButton writeEntry;
    ImageButton settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);

        entryOverview = findViewById(R.id.entryOverview);
        writeEntry = findViewById(R.id.writeEntry);
        settings = findViewById(R.id.settings);

        // Event handlers for buttons
        entryOverview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ImagesActivity.class);
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
                //Intent intent = new Intent(getApplicationContext(), Settings.class);
                //startActivity(intent);
                Toast.makeText(CalendarView.this, "Clicked settings", Toast.LENGTH_LONG).show();
            }
        });

    }
}