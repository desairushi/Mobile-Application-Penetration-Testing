package com.example.myapplication;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MedicationDataDisplayer {


    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    Context mContext;

    public MedicationDataDisplayer(Context context, ListView listView) {
        this.listView = listView;
        this.mContext = context;
    }

//Connect your app to firebase Google. Google.
// Available at: https://firebase.google.com/docs/database/android/start (Accessed: January 13, 2023).

    public void retrieveData() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Medication Database");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrayList.clear();
                arrayAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, arrayList);

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    DataClass dataClass = ds.getValue(DataClass.class);
                    String data = "Topic: " + dataClass.getDataTitle() + "\nDose: " + dataClass.getDataDose() + "\nFrequency: " + dataClass.getDataFreq();
                    arrayList.add(data);
                }
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {

                Toast.makeText(mContext, "Failed to read value.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
