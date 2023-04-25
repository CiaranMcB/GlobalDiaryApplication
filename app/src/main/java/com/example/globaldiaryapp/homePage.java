package com.example.globaldiaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class homePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    public void openMakeEntryActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openViewEntriesActivity(View view) {
        Intent intent = new Intent(this, ImagesActivity.class);
        startActivity(intent);
    }

    public void openViewCalendarActivity(View view) {
        Intent intent = new Intent(this, CalendarView.class);
        startActivity(intent);
    }

    public void openUserManualActivity(View view) {
        Intent intent = new Intent(this, userManual.class);
        startActivity(intent);
    }

}