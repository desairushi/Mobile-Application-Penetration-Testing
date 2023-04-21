package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


//Here, Y.D. (2021) Secure your API using NDK, Medium. Medium.
//Available at: https://medium.com/@yourdeveloperhere001/secure-your-api-using-ndk-f78e2a34bed1 (Accessed: January 14, 2023).

public class MainActivity extends AppCompatActivity {

    private Button btnLogOut;
    private Button btdevices;
    private Button msn;
    private Button edu;
    private Button about;
    FirebaseAuth mAuth;

    static {
        System.loadLibrary("keys");
    }

    public native String api();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        btnLogOut = (Button)findViewById(R.id.btnLogout);
        btdevices = (Button)findViewById(R.id.btDevices);
        msn=(Button)findViewById(R.id.btMeasurement);
        edu=(Button)findViewById(R.id.btEducation);
        about =(Button)findViewById(R.id.btAbout);

        mAuth = FirebaseAuth.getInstance();

        btnLogOut.setOnClickListener(view ->{
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });

        btdevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, bluetooth.class);
                startActivity(intent);
                finish();

            }
        });

        msn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Calorie.class);
                startActivity(intent);

            }
        });
        edu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UploadActivity.class);
                startActivity(intent);
                finish();

            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }
// The code uses the guideline of android developer website;
//Authenticate with firebase using password-based accounts on Android Google. Google.
//Available at: https://firebase.google.com/docs/auth/android/password-auth (Accessed: January 13, 2023).
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }
} 