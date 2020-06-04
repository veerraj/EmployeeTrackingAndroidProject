package com.example.lenovo.currentlocation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class GenerateOtp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_otp);
        Toast.makeText(GenerateOtp.this,"Otp Generated Successfully For The Meeting",Toast.LENGTH_SHORT).show();
    }
}
