package com.example.myapplication;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MedicationDataActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_data);

        listView = findViewById(R.id.listView);

        MedicationDataDisplayer medicationDataDisplayer = new MedicationDataDisplayer(this, listView);
        medicationDataDisplayer.retrieveData();
    }
}
