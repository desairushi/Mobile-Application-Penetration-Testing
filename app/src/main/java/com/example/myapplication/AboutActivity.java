package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView tvAbout = findViewById(R.id.tvAbout);

        String aboutText = "Welcome to my mHealth app!\n\n"
                + "this app is designed to help you track and manage your health more effectively. With this app, you can:\n\n"
                + "- Scan the calorie count to calculate your daily intake\n"
                + "- Enter your medication data and retrieve it from the database\n"
                + "- Connect to other health devices via Bluetooth\n\n"
                + "I hope the app will be a valuable resource for you on your journey to better health. Thank you for choosing the App!";

        tvAbout.setText(aboutText);
    }

}
