package com.example.roundabout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Adapted from example code provided
 * @author HenryK
 */
public class communicationWebSocket extends AppCompatActivity {
    private WebSocketClient mwebSocketClient;
    protected Button sendButton;
    protected Button connectButton;
    protected Button disconnectButton;
    EditText inputText;
    RecyclerView webSocketRecycler;//Will use textview in its place if it doesn't work.
    ArrayList<String> callerArr = new ArrayList<>();
    ArrayList<String> receiverArr = new ArrayList<>();
    String eid;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication_web_socket);
        Intent intent = getIntent();
        eid = intent.getStringExtra("eid");
        username = intent.getStringExtra("username");
        sendButton = findViewById(R.id.messageButton);
        connectButton = findViewById(R.id.callTo);
        disconnectButton = findViewById(R.id.stopTo);
        inputText = findViewById(R.id.messageInput);


        connectButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                connectWebSocket();
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String gottenM = inputText.getText().toString();
                if (gottenM != null && gottenM.length() > 0){
                    mwebSocketClient.send(gottenM);
                    callerArr.add(gottenM);
                }
            }
        });

        disconnectButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(mwebSocketClient.isOpen()) {
                    mwebSocketClient.close();
                }
            }
        });

    }
    @Override
    public void onDestroy(){
        super.onDestroy();

        mwebSocketClient.close();
    }

    private void initEventViewN(){
        webSocketRecycler = findViewById(R.id.webSocketView);
        communicationAdapter adapter = new communicationAdapter(callerArr,receiverArr,this);
        //adapter.notifyDataSetChanged();
        webSocketRecycler.setAdapter(adapter);
        webSocketRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    public void connectWebSocket(){
        URI uri;
        try {
            uri = new URI("ws://coms-309-sb-08.cs.iastate.edu:8080/chat/" + username);//{eventid}/{username}
            Log.d("communication",uri.toString());
        } catch (URISyntaxException e){
            e.printStackTrace();
            return;
        }
        mwebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                Log.i("Websocket","Opened");
            }

            @Override
            public void onMessage(String message) {
                Log.i("Websocket","Message Received");
                receiverArr.add(message);
                Log.d("Websocket", message);
                initEventViewN();
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.i("Websocket","Closed" + reason);
            }

            @Override
            public void onError(Exception ex) {
                Log.i("Websocket","Error " + ex.getMessage());
            }
        };
        Log.d("communication",mwebSocketClient.getURI().toString());
        mwebSocketClient.connect();
        }
}