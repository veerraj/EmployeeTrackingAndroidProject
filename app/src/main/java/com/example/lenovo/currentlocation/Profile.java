package com.example.lenovo.currentlocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Profile extends AppCompatActivity {
    TextView textview,textviewPhone,textview2,textview3;

ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle("Profile");
        actionBar=getSupportActionBar();
           actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF9C33")));
           textview=findViewById(R.id.textView);
           textviewPhone=findViewById(R.id.textViewMobile);
           textview2=findViewById(R.id.textViewadd);
           textview3=findViewById(R.id.textViewname);
//                Toast.makeText(getApplicationContext(),EmployeeDetail.getEmail(),Toast.LENGTH_SHORT).show();

           }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseFirestore.getInstance()
                .collection("EmployeeData")
                .whereEqualTo("email", EmployeeDetail.getEmail())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Toast.makeText(getApplicationContext(),document.get("email").toString(),Toast.LENGTH_SHORT).show();
                                textview.setText(document.get("email").toString());
                                textviewPhone.setText(document.get("phoneNo").toString());
                                textview2.setText(document.get("Address").toString());
                                textview3.setText(document.get("firstName").toString()+" "+document.get("lastName").toString());

                            }
                        } else {
                            Toast.makeText(getApplicationContext(),"Error Occurs",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}

