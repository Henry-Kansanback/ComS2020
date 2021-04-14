package com.example.roundabout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author HenryK
 */
public class businessSplash extends AppCompatActivity {
    Button eventList;
    //Button businessesSignup;
    TextView welcomeSign;
    String username;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_splash);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        id = intent.getIntExtra("id",0);
        eventList = findViewById(R.id.eventsListBusiness);
        //businessesSignup = findViewById(R.id.businessSignup);
        welcomeSign = findViewById(R.id.userIdentityB);

        eventList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(view.getContext(), business.class);
                intent1.putExtra("id", id);
                startActivity(intent1);
            }
        });

        welcomeSign.setText("Welcome, " + username);
    }
}