package com.example.lenovo.currentlocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup_Form extends AppCompatActivity {
  EditText txt_email,txt_password,txt_confirm_password;
  Button btn_register;
    ActionBar actionBar;
  private FirebaseAuth  firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);
        getSupportActionBar().setTitle("SignUp Form");
        txt_email=findViewById(R.id.txt_email);
        txt_password=findViewById(R.id.txt_password);
        txt_confirm_password=findViewById(R.id.txt_confirm_password);
        btn_register=findViewById(R.id.btn_register);
        firebaseAuth=FirebaseAuth.getInstance();
        actionBar=getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF9C33")));
        getSupportActionBar().setTitle("SignIn");
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=txt_email.getText().toString().trim();
                String password=txt_password.getText().toString().trim();
                String confirmpassword=txt_confirm_password.getText().toString().trim();
                EmployeeDetail.setEmail(email);
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Signup_Form.this,"Please Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Signup_Form.this,"Please Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(confirmpassword)){
                    Toast.makeText(Signup_Form.this,"Please Enter Confirm Password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<6){
                    Toast.makeText(Signup_Form.this,"Password too short",Toast.LENGTH_SHORT).show();
                }
                if(password.equals(confirmpassword)){
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Signup_Form.this, new OnCompleteListener<AuthResult>() {
                                @Override

                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                      startActivity(new Intent(getApplicationContext(),DrawerActivity.class));
                                      Toast.makeText(Signup_Form.this,"Reigstration Complete",Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(Signup_Form.this,"Authentication Failed",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }); }
            }
        });
    }
}
