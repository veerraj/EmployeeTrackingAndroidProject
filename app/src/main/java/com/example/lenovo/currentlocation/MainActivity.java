package com.example.lenovo.currentlocation;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.Menu;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // actionBar=getSupportActionBar();
     //   actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF9C33")));
    }
    public boolean onCreateMenuOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void logout(View view) {
        startActivity(new Intent(this, Login_form.class));
    }

    public void btnCurrentLocation(View view) {
            startActivity(new Intent(this, MapsActivity.class));
        }
            public void  MessageNotification(View view){
                startActivity(new Intent(this,msg.class));
            }

    public void  Drawer(View view){
        startActivity(new Intent(this,DrawerActivity.class));
    }
}

