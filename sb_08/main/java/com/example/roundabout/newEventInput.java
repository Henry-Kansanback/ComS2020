package com.example.roundabout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class related to the new event input screen for submitting new events to the server.
 * @author HenryK
 */
public class newEventInput extends AppCompatActivity {
    String URLEvent = "http://coms-309-sb-08.cs.iastate.edu:8080/addEvent";
    RequestQueue queue;
    EditText eventname;
    EditText eventdesc;
    EditText eventcity;
    EditText eventstate;
    EditText eventtime;
    Button submit;
    JSONObject userr = new JSONObject();

    /**
     * onCreate handles all necessary initialization (findViewById's, newRequestQueue,... etc) and sets onClickListener
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event_input2);
        eventname = findViewById(R.id.eventName);
        eventdesc = findViewById(R.id.eventDesc);
        eventcity = findViewById(R.id.eventCity);
        eventstate = findViewById(R.id.eventState);
        eventtime = findViewById(R.id.eventTime);
        submit = findViewById(R.id.eventSubmit);
        queue = Volley.newRequestQueue(this);
        Intent intent = getIntent();
        final int madeBy = intent.getIntExtra("id", 0);
        submit.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View view){
            try {
                sendEventPost(userr,eventname.getText().toString(), eventdesc.getText().toString(),
                        eventcity.getText().toString(), eventstate.getText().toString(),eventtime.getText().toString(), madeBy, URLEvent);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        });


    }

    /**
     * Puts together new event JsonObject using inputs and sends new event to the server
     * @param userr JSONObject to contain the other inputted data
     * @param inputname String Name of the event
     * @param inputdesc String description of the event
     * @param inputcity String city of the event
     * @param inputstate String (geopolitical) state of the event
     * @param inputtime String time/data of the event
     * @param madeBy integer id associated with inputting user
     * @param PrURL URL to be used to send to server
     * @return confirmation int
     * @throws JSONException
     */
    public int sendEventPost(JSONObject userr, String inputname,String inputdesc, String inputcity,
                              String inputstate, String inputtime, int madeBy, String PrURL) throws JSONException {
        userr.put("eventName",inputname);
        userr.put("eventDesc",inputdesc);
        userr.put("city",inputcity);
        userr.put("state",inputstate);
        userr.put("eventTime",inputtime);
        userr.put("madeBy",madeBy);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, PrURL,userr, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //System.out.print(response.toString());
                //response.getJSONObject("username");


                //Log.d("Help", response.toString());
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                //System.out.print(error.toString());

            }
        });
        queue.add(request);
        return 1;
    }
}