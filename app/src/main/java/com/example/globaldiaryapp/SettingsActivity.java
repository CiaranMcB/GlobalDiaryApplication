package com.example.globaldiaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    ImageButton calendar;
    ImageButton entryOverview;
    ImageButton writeEntry;
    ImageButton settings;
    Button button;
    FirebaseAuth auth;
    TextView textView;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        calendar = findViewById(R.id.calendar);
        entryOverview = findViewById(R.id.entryOverview);
        settings = findViewById(R.id.settings);
        writeEntry = findViewById(R.id.writeEntry);
        button = findViewById(R.id.logout);

        auth = FirebaseAuth.getInstance();
        textView = findViewById(R.id.user_details);
        user = auth.getCurrentUser();
        if (user == null){
            Intent intent = new Intent(getApplication(), Login.class);
            startActivity(intent);
            finish();
        }
        else{
            textView.setText(user.getEmail());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplication(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CalendarView.class);
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

        writeEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void openUserManualActivity(View view) {
        Intent intent = new Intent(this, userManual.class);
        startActivity(intent);
    }
}