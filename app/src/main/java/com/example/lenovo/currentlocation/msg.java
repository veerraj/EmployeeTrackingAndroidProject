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

public class msg extends AppCompatActivity {
 // TextView textView;
  String mapid;
  EditText editText;
  String scode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);

    //    textView=findViewById(R.id.textview);
        editText=findViewById(R.id.editText);

//        Toast.makeText(msg.this,"Employee Task Notification",Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(getApplicationContext(),"Enter Verification Code",Toast.LENGTH_SHORT).show();
                                mapid=document.getId();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(),"Error Occurs",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void verify(View view)
    {
        scode=editText.getText().toString();
        FirebaseFirestore.getInstance()
                .collection("MeetingDetails")
                .whereEqualTo("EmpId", mapid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                               // Toast.makeText(getApplicationContext(),document.get("email").toString(),Toast.LENGTH_SHORT).show();
                                //mapid=document.getId();
                                String code=document.get("Statuscode").toString();
                             //   Toast.makeText(getApplicationContext(),code,Toast.LENGTH_SHORT).show();
                              //  Toast.makeText(getApplicationContext(),scode,Toast.LENGTH_SHORT).show();

                                if(scode.trim().equals(code.trim()))
                                {
                                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                                    DocumentReference contact = db.collection("EmployeeData").document(mapid);

                                    contact.update("code",scode)
                                            .addOnSuccessListener(new OnSuccessListener< Void >() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(getApplicationContext(),"Verified",Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(getApplicationContext(),"Firebase Error",Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                    editText.setText("");
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Not Verified please enter right verication code",Toast.LENGTH_SHORT).show();

                                }
                            }
                        } else {
                            Toast.makeText(getApplicationContext(),"Error Occurs",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}
