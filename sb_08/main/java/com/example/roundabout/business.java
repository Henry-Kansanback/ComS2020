package com.example.roundabout;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Business owners home screen. Includes a recyclerView and a spinner for sorting.
 * @author JakobP
 */
public class business extends AppCompatActivity {
    String URLEvents = "http://coms-309-sb-08.cs.iastate.edu:8080/events";
    String selected;
    int s;
    private ArrayList<String> eventnside = new ArrayList<String>();
    private ArrayList<String> eventdescside = new ArrayList<String>();
    private ArrayList<String> cityside = new ArrayList<String>();
    private ArrayList<String> stateside = new ArrayList<String>();
    private ArrayList<String> eventtside = new ArrayList<String>();
    private ArrayList<String> eventmadeby = new ArrayList<String>();
    int bid;
    int eid = 0;
    Spinner spinner;
    Button sButton;
    JSONObject eventret = new JSONObject();
    RequestQueue queue;
    RecyclerView recyclerView;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business);
        sButton = findViewById(R.id.startButton);
        spinner = findViewById(R.id.businessSpinner);
        recyclerView = findViewById(R.id.businessPageRecycler);
        queue = Volley.newRequestQueue(this);
        ArrayList<String> arrayList = new ArrayList<>();
        Intent intent = getIntent();
        bid = intent.getIntExtra("id",0);

        arrayList.add("PLAN EVENTS");
        arrayList.add("CURRENT EVENTS");
        arrayList.add("OTHER");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                s = i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }



        }
        );

        sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (s == 0)
                {
                    Intent intent1 = new Intent(view.getContext(),newSignUp.class);

                    intent1.putExtra("BID",bid);
                    intent1.putExtra("EID",eid);
                    startActivity(intent1);
                }

                if (s == 1)
                {
                    initEventView();
                }

                if (s == 2)
                {

                }
            }
        });
        getEvents();

    }

    /**
     * Calls to getEvents(), intializes the recyclerView and then fills the recyclerview with data acquired from the getEvents call, and it sets the adapter.
     */
    private void initEventView(){

        //Log.d(TAG,"initrecyclerView");


        EventAdapter adapter = new EventAdapter(eventnside,eventdescside,cityside,stateside,eventtside, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    /**
     * Acquires all events currently on the server, places them into ArrayLists and adds the request to the RequestQueue
     */
    protected void getEvents(){
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URLEvents, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response){

                int i = 0;
                //userret = response;

                try {

                    for(;i < response.length();i++){
                        JSONObject mr1 = response.getJSONObject(i);
                        eventnside.add(mr1.optString("eventName"));
                        eventdescside.add(mr1.optString("eventDesc"));
                        cityside.add(mr1.optString("city"));
                        stateside.add(mr1.optString("state"));
                        eventtside.add(mr1.optString("eventTime"));
                        eventmadeby.add(mr1.optString("madeBy"));
                    }
                    initEventView();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //System.out.print(error.toString());
                //textView.setText(error.toString());
                error.printStackTrace();
            }
        });

        queue.add(request);
    }
}
