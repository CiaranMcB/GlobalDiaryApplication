package com.example.globaldiaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class userManual extends AppCompatActivity {

    private TextView mUserManualText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manual);

        mUserManualText = findViewById(R.id.user_manual_text);

        String userManualSteps = "User Manual/Steps\n\nCreating an account - once starting the app the user will be greeted by a login page. The user will then have the option to either login or register for the app using their email and password that must exceed 6 characters. The user will then be asked to confirm their password. Once the user clicks the login button after their account has been created step 2 will begin.\n\nMaking your first Diary Entry - The user will be asked to enter some text for the diary enter in which they will describe their days events and then will be prompted to add an image to their diary entry. Once the text and image have been entered thw user will need to press the upload button.\n\nViewing entries - After the application has prompted the user that the upload has been successful, the user will then need to press the show entries text which can be seen at the bottom right hand corner of the page.";

        mUserManualText.setText(userManualSteps);
    }
}
