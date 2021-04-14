package com.example.roundabout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Creates a new Signup object on server
 * Takes in businesses inputs and creates something for users to sign up for.
 * @author HenryK
 */
public class newSignUp extends AppCompatActivity {
    String URLSubmit = "http://coms-309-sb-08.cs.iastate.edu:8080/addSignUp";
    Button submitSign;
    TextView eventSign;
    EditText enterSpots;
    RequestQueue queue;
    EditText eID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sign_up);
        queue = Volley.newRequestQueue(this);
        Intent intent1 = getIntent();
        //String name = intent1.getStringExtra("EventName");
        //final int eid = intent1.getIntExtra("EID",0);
        final int bid = intent1.getIntExtra("BID",0);
        eventSign = findViewById(R.id.eventToBeSigned);
        submitSign = findViewById(R.id.sendSignup);
        enterSpots = findViewById(R.id.totalParking);
        eID = findViewById(R.id.eventid);


        submitSign.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                JSONObject toServer = new JSONObject();
                eventSign.setText("Event: " + eID.getText().toString());
                try {
                    signUp(toServer,Integer.parseInt(enterSpots.getText().toString()),Integer.parseInt(eID.getText().toString()),bid,URLSubmit,queue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        //come to this page after onClick from EventAdapter. Signup with event id, business id, and total spots

    }

    /**
     * Used by Business users to create new signup "sheets"
     * @param toServer
     * @param spots
     * @param eid
     * @param bid
     * @param usedURL
     * @param qi
     * @throws JSONException
     * @author HenryK
     */
    protected void signUp(JSONObject toServer,int spots, int eid, int bid,String usedURL,RequestQueue qi) throws JSONException {
        if(this.queue != null){
            qi = queue;
        }

        toServer.put("eid",eid);
        toServer.put("bid",bid);
        toServer.put("spots",spots);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,URLSubmit ,toServer , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
            }
        });
        qi.add(request);
    }


}