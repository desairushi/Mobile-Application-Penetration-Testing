package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Calorie extends AppCompatActivity {

    EditText ageEditText;
    EditText genderEditText;
    EditText weightEditText;
    EditText heightEditText;
    Button submitButton;
    Button retrieveButton;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie);

        ageEditText = findViewById(R.id.ageEditText);
        genderEditText = findViewById(R.id.genderEditText);
        weightEditText = findViewById(R.id.weightEditText);
        heightEditText = findViewById(R.id.heightEditText);
        submitButton = findViewById(R.id.submitButton);
        retrieveButton = findViewById(R.id.retrieveButton);
        resultTextView = findViewById(R.id.resultTextView);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ageString = ageEditText.getText().toString();
                String genderString = genderEditText.getText().toString();
                String weightString = weightEditText.getText().toString();
                String heightString = heightEditText.getText().toString();

                if (ageString.isEmpty() || genderString.isEmpty() || weightString.isEmpty() || heightString.isEmpty()) {
                    Toast.makeText(Calorie.this, "Please enter all required fields", Toast.LENGTH_SHORT).show();
                    return;
                }


                int age = Integer.parseInt(ageString);
                int weight = Integer.parseInt(weightString);
                int height = Integer.parseInt(heightString);

            //The code uses the formula taken from reference listed below
                // Zając, A. and Mucha, M. (2022) Harris-Benedict Calculator (total daily energy expenditure), Omni Calculator. Omni Calculator.
                // Available at: https://www.omnicalculator.com/health/bmr-harris-benedict-equation (Accessed: January 13, 2023).

                double bmr = 0;
                if (genderString.equalsIgnoreCase("male")) {

                    bmr = 10 * weight + 6.25 * height - 5 * age + 5;
                } else if (genderString.equalsIgnoreCase("female")) {

                    bmr = 10 * weight + 6.25 * height - 5 * age - 161;
                }
                double dailyCaloricNeeds = bmr * 1.2;

                resultTextView.setText(String.format("Your daily caloric needs are %.2f calories.", dailyCaloricNeeds));
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("required_calories");
                myRef.setValue(dailyCaloricNeeds);
                Toast.makeText(Calorie.this, "Log Sent to the Database", Toast.LENGTH_SHORT).show();

                ageEditText.setText("");
                genderEditText.setText("");
                weightEditText.setText("");
                heightEditText.setText("");

            }
        });
        //Connect your app to firebase Google. Google.
// Available at: https://firebase.google.com/docs/database/android/start (Accessed: January 13, 2023).
        retrieveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase.getInstance().getReference("required_calories").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        resultTextView.setText(String.format("Latest data shows the requried calories are %.2f .", dataSnapshot.getValue(Double.class)));
                    }
                    @Override
                    public void onCancelled(DatabaseError error) {
                        Toast.makeText(Calorie.this, "Failed to retrieve data from the database", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}