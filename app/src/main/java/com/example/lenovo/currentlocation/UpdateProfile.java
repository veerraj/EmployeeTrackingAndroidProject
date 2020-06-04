package com.example.lenovo.currentlocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class UpdateProfile extends AppCompatActivity {
TextView textview,textview1;
EditText editText,editText1;
String Mapid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        textview=findViewById(R.id.textViewName);
        textview1=findViewById(R.id.textViewemail);
        editText=findViewById(R.id.EditTextMob);
        editText1=findViewById(R.id.EditTextAdd);
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

                                textview1.setText(document.get("email").toString());
                                textview.setText(document.get("firstName").toString()+" "+document.get("lastName").toString());

                            }
                        } else {
                            Toast.makeText(getApplicationContext(),"Error Occurs",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void UpdateData(View view)
    {
        String PhoneNo=editText.getText().toString();
        String Address=editText1.getText().toString();
        Mapid=EmployeeDetail.getId();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference contact = db.collection("EmployeeData").document(Mapid);

        contact.update("PhoneNo",PhoneNo);
        contact.update("Address",Address)
                .addOnSuccessListener(new OnSuccessListener< Void >() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Profile Updated",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Data not saved",Toast.LENGTH_SHORT).show();
                    }
                });
        editText.setText("");
        editText1.setText("");
    }
}
