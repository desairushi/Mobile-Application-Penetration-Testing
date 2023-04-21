package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class UploadActivity extends AppCompatActivity {


    private Button measure, fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        fab = findViewById(R.id.fab);
        measure = findViewById(R.id.meaure);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UploadActivity.this, UploadMedication.class);
                startActivity(intent);
            }
        });
        measure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UploadActivity.this, MedicationDataActivity.class);
                startActivity(intent);
            }
        });


    }
}
