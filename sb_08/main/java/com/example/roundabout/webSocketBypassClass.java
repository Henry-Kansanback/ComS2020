package com.example.roundabout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProviderClient;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author HenryK
 */
public class webSocketBypassClass extends AppCompatActivity {
    EditText callerID;
    EditText receiverID;
    Button gotowebsocket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_socket_bypass_class);
        gotowebsocket = findViewById(R.id.websocketgo);
        callerID = findViewById(R.id.callerID);
        receiverID = findViewById(R.id.receiverID);

        gotowebsocket.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(webSocketBypassClass.this, communicationWebSocket.class);
            intent.putExtra("eid",callerID.getText().toString());
            intent.putExtra("username",receiverID.getText().toString());
            startActivity(intent);
            }
        });
    }
}