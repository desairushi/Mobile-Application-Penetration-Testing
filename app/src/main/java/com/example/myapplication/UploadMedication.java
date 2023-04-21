package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UploadMedication extends AppCompatActivity {

    EditText topicEditText;
    EditText doseEditText;
    EditText frequencyEditText;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);

        topicEditText = findViewById(R.id.uploadTopic);
        doseEditText = findViewById(R.id.uploadDose);
        frequencyEditText = findViewById(R.id.uploadFreq);
        submitButton = findViewById(R.id.uploadData);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String topic = topicEditText.getText().toString();
                String dose = doseEditText.getText().toString();
                String frequency = frequencyEditText.getText().toString();

                if (topic.isEmpty() || dose.isEmpty() || frequency.isEmpty() ) {
                    Toast.makeText(UploadMedication.this, "Please enter all required fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Connect your app to firebase Google. Google.
                // Available at: https://firebase.google.com/docs/database/android/start (Accessed: January 13, 2023).

                DataClass dataClass = new DataClass(topic, dose, frequency);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Medication Database").child(topic);
                myRef.setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(UploadMedication.this, "Saved", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UploadMedication.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
