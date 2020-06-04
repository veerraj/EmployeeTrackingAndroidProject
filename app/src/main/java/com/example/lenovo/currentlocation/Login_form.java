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

public class Login_form extends AppCompatActivity {
EditText txt_email,txt_password;
Button btn_login;
ActionBar actionBar;
private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
       txt_email=findViewById(R.id.txt_email);
       txt_password=findViewById(R.id.txt_password);
        actionBar=getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF9C33")));
       btn_login=findViewById(R.id.btn_login);
       firebaseAuth=FirebaseAuth.getInstance();
        getSupportActionBar().setTitle("Login");
       btn_login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               String email=txt_email.getText().toString().trim();
               String password=txt_password.getText().toString().trim();
               EmployeeDetail.setEmail(email);
               if(TextUtils.isEmpty(email)){
                   Toast.makeText(Login_form.this,"Please Enter Email",Toast.LENGTH_SHORT).show();
                   return;
               }
               if(TextUtils.isEmpty(password)){
                   Toast.makeText(Login_form.this,"Please Enter Password",Toast.LENGTH_SHORT).show();
                   return;
               }
               if(password.length()<6){
                   Toast.makeText(Login_form.this,"Password too short",Toast.LENGTH_SHORT).show();
               }
               firebaseAuth.signInWithEmailAndPassword(email, password)
                       .addOnCompleteListener(Login_form.this, new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               if (task.isSuccessful()) {
                                   startActivity(new Intent(getApplicationContext(),DrawerActivity.class));

                               } else {
                                Toast.makeText(Login_form.this,"Login Failed",Toast.LENGTH_SHORT).show();

                               }

                               // ...
                           }
                       });
           }
       });
    }
    public void btn_SignUpForm(View view){
        startActivity(new Intent(getApplicationContext(),Signup_Form.class));
    }
}
