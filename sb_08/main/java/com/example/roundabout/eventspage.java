package com.example.roundabout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

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
 * Class related to the home page of the event planner.
 * @author HenryK
 */
public class eventspage extends AppCompatActivity {
    private static final String TAG = "eventspage";
    Spinner eventsSpinner;
    String URLEvents = "http://coms-309-sb-08.cs.iastate.edu:8080/events";
    protected ArrayList<String> eventnside = new ArrayList<String>();
    protected ArrayList<String> eventdescside = new ArrayList<String>();
    protected ArrayList<String> cityside = new ArrayList<String>();
    protected ArrayList<String> stateside = new ArrayList<String>();
    protected ArrayList<String> eventtside = new ArrayList<String>();
    protected ArrayList<String> eventmadeby = new ArrayList<String>();
    Button events;
    Button myevents;
    Button newEvent;
    JSONObject eventret = new JSONObject();
    RequestQueue queue;
    int eventlength;
    getEventsClass f = new getEventsClass();
    ArrayList<ArrayList<String>> j;
    int y = 0;
    RecyclerView recyclerView;
    EventAdapter adapter;
    int eid = 0;

    /**
     * onCreate method for eventspage. Instantiates and 
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventspage);
        Intent intent = getIntent();
        eid = intent.getIntExtra("id",0);
        events = findViewById(R.id.allEvents);
        myevents = findViewById(R.id.myEvents);
        newEvent = findViewById(R.id.newevent);
        eventsSpinner = (Spinner) findViewById(R.id.eventspageSpinner);
        queue = Volley.newRequestQueue(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.eventSort, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eventsSpinner.setAdapter(adapter);
        //eventsSpinner.setOnItemSelectedListener(new PlannerSpinnerActivity()); //old implementation, was not dynamic enough. It also didn't work...
        eventsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
                y = i;
                   /*//For inevitable parsing of Dates
                String str = editText.getText().toString();
                DateFormat formatter = new SimpleDateFormat("hh:mm:ss a");
                Date date = formatter.parse(str);
                   */
                if(y == 6 && eventtside.size() > 1){//Time (Ascending)
                    //TODO Move this whole function to it's own method.
                    int j = 1;

                    //int hour = Integer.parseInt(eventtside.get(0).substring(0,eventtside.get(0).indexOf(":")));
                    //int minute = Integer.parseInt(eventtside.get(0).substring(eventtside.get(0).indexOf(":"))+1);
                    //eventnside,eventdescside,cityside,stateside,eventtside
                    for(j = 1; j < eventtside.size(); j++) {
                        for(int k = j; k > 0; k--) {
                            int v = Integer.parseInt(eventtside.get(j).substring(0, eventtside.get(j).indexOf(":")));
                            int vh1 = Integer.parseInt(eventtside.get(j-1).substring(0, eventtside.get(j-1).indexOf(":")));
                            if (v > vh1) {
                                switchPos(j, j-1);
                                //switch the positions of current hour and v's associated elements in all the lists

                            } else if (v == vh1) {
                                int vm = Integer.parseInt(eventtside.get(j).substring(eventtside.get(j).indexOf(":")))+1;
                                int vm1 = Integer.parseInt(eventtside.get(j-1).substring(eventtside.get(j-1).indexOf(":")))+1;
                                if (vm > vm1) {
                                    switchPos(j, j-1);
                                    //switch the positions of current minute and vm's associated elements in all the lists
                                }
                            }
                        }
                    }
                }
                else if(y == 7 && eventtside.size() > 1){//Time (Descending)
                    int j = 1;

                    //int hour = Integer.parseInt(eventtside.get(0).substring(0,eventtside.get(0).indexOf(":")));
                    //int minute = Integer.parseInt(eventtside.get(0).substring(eventtside.get(0).indexOf(":"))+1);
                    //eventnside,eventdescside,cityside,stateside,eventtside
                    for(j = 1; j < eventtside.size(); j++) {
                        for(int k = j; k > 0; k--) {
                            int v = Integer.parseInt(eventtside.get(j).substring(0, eventtside.get(j).indexOf(":")));
                            int vh1 = Integer.parseInt(eventtside.get(j-1).substring(0, eventtside.get(j-1).indexOf(":")));
                            if (v < vh1) {
                                switchPos(j, j-1);
                                //switch the positions of current hour and v's associated elements in all the lists

                            } else if (v == vh1) {
                                int vm = Integer.parseInt(eventtside.get(j).substring(eventtside.get(j).indexOf(":")))+1;
                                int vm1 = Integer.parseInt(eventtside.get(j-1).substring(eventtside.get(j-1).indexOf(":")))+1;
                                if (vm < vm1) {
                                    switchPos(j, j-1);
                                    //switch the positions of current minute and vm's associated elements in all the lists
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });
        Log.d(TAG, "onCreate:setAdapter");


        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEvents();
                //f.getAllEvents(queue);
                //initEventView();
                //updateEventView();
            }
        });
        myevents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEventsMadeBy(eid); //needs to acquire this data from user login
            }
        });
        newEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            Intent intent= new Intent(view.getContext(), newEventInput.class);
            intent.putExtra("id",1);
            startActivity(intent);
            }
        });
        //j = f.getAllEvents(queue); //For modularization
        getEvents();
    }

    /**
     * Switches the data held in all the relevant ArrayList.get(j)s with ArrayList.get(j1)s
     * Does this for all ArrayLists associated with an event
     * @param j the place of event item 1 in the ArrayLists
     * @param j1 the place of event item 2 in the ArrayLists
     * @return Confirmation that everything went alright
     */
    protected String switchPos(int j, int j1)//eventnside,eventdescside,cityside,stateside,eventtside
    {
        String tmp;

        tmp = eventnside.get(j-1);
        eventnside.remove(j-1);
        eventnside.add(j-1,eventnside.get(j));
        eventnside.remove(j);
        eventnside.add(j,tmp);

        tmp = eventdescside.get(j-1);
        eventdescside.remove(j-1);
        eventdescside.add(j-1,eventdescside.get(j));
        eventdescside.remove(j);
        eventdescside.add(j,tmp);

        tmp = cityside.get(j-1);
        cityside.remove(j-1);
        cityside.add(j-1,cityside.get(j));
        cityside.remove(j);
        cityside.add(j,tmp);

        tmp = stateside.get(j-1);
        stateside.remove(j-1);
        stateside.add(j-1,stateside.get(j));
        stateside.remove(j);
        stateside.add(j,tmp);

        tmp = eventtside.get(j-1);
        eventtside.remove(j-1);
        eventtside.add(j-1,eventtside.get(j));
        eventtside.remove(j);
        eventtside.add(j,tmp);

        tmp = eventmadeby.get(j-1);
        eventmadeby.remove(j-1);
        eventmadeby.add(j-1,eventmadeby.get(j));
        eventmadeby.remove(j);
        eventmadeby.add(j,tmp);
        return tmp;
    }

    protected String switchPos(ArrayList<String> k, int j,int j1)//eventnside,eventdescside,cityside,stateside,eventtside
    {
        String tmp;

        tmp = k.get(j-1);
        //k.remove(j-1);
        k.add(j-1,k.get(j));
        //k.remove(j);
        k.add(j,tmp);
        return k.get(j);
    }

    /**
     * Calls getEvents() to acquire the events from the server and Initializes the recyclerview with the acquired data.
     */
    private void initEventViewN(){
        recyclerView = findViewById(R.id.eventspageRecycler);
        EventAdapter adapter = new EventAdapter(eventnside,eventdescside,cityside,stateside,eventtside, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    /*private void updateEventView(){
        j.clear();
        j = f.getAllEvents(queue);
        adapter = new EventAdapter(j.get(0),j.get(1),j.get(2),j.get(3),j.get(4), this);
        
        adapter.notifyDataSetChanged();
    }*/



    /**
     * Gets all events currently on the server and places them in ArrayList objects to be used in a recyclerView.
     * @return int to confirm result
     */
    private void getEvents(){
        Log.d(TAG, "getEvents");
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

                    initEventViewN();
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
        return;
    }


    /**
     * Gets events made by current user (represented by id input) and displays them in a recyclerView
     * @param id ID of current user (in theory, for testing purposes this value is set as a magic number)
     */
    protected void getEventsMadeBy(int id){

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URLEvents + "/madeBy/" + id, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response){

                int i = 0;
                //userret = response;

                try {
                    //JSONArray eventretString = eventret.getJSONArray("json");
                    for(;i < response.length();i++){
                        JSONObject mr1 = response.getJSONObject(i);
                        eventnside.add(mr1.optString("eventName"));
                        eventdescside.add(mr1.optString("eventDesc"));
                        cityside.add(mr1.optString("city"));
                        stateside.add(mr1.optString("state"));
                        eventtside.add(mr1.optString("eventTime"));
                        eventmadeby.add(mr1.optString("madeBy"));
                    }
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