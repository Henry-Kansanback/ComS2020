package com.example.roundabout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

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
 * @author HenryK
 */
public class businessSignedup extends AppCompatActivity {
    String URLEvents = "http://coms-309-sb-08.cs.iastate.edu:8080/events/madeBy";
    String URLBusinessesFor = "http://coms-309-sb-08.cs.iastate.edu:8080/Businesses/for";

    private ArrayList<String> userside = new ArrayList<>();
    //private ArrayList<String> passside = new ArrayList<>();
    private ArrayList<String> cityside = new ArrayList<>();
    private ArrayList<String> stateside = new ArrayList<>();
    private ArrayList<String> eventid = new ArrayList<String>();
    private ArrayList<String> businessesAssociatedW = new ArrayList<String>();
    int id; //need to acquire from previous screens
    RecyclerView recyclerView;
    RequestQueue queue;
    //Note to self: add a spinner that has all events associated with user for sorting purposes, have spinner or related element collect ID data
    //              as well from call to get events for use with the getBusinessesFor method!
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_signedup);
        queue = Volley.newRequestQueue(this);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        getEventsMadeBy();
    }

    private void initEventViewN(){
        recyclerView = findViewById(R.id.businessSignedupRecycler);
        UserAdapter adapter = new UserAdapter(userside,null,cityside,stateside, null, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * acquires event ids to be used by the getBusinessesFor method to get data of businesses that have signed on to said events.
     * @return int 1
     */
    protected int getEventsMadeBy(){
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URLEvents+"/"+id, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response){

                int i = 0;
                //userret = response;

                try {
                    for(;i < response.length();i++){
                        JSONObject mr1 = response.getJSONObject(i);
                        eventid.add(mr1.optString("id"));
                    }
                    getBusinessesFor(eventid);
                    //initEventViewN();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(request);
        return 1;
    }
    protected ArrayList<String> getBusinessesFor(ArrayList<String> eid){
        ArrayList<Integer> j = new ArrayList<Integer>();
        for(int q = 0; q < eid.size(); q++)
        {
            j.add(Integer.parseInt(eid.get(q)));
        }
        for(int i = 0; i < eid.size(); i++) {
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URLBusinessesFor + "/" + j.get(i), null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    int g = 0;
                    //userret = response;

                    try {
                        for (; g < response.length(); g++) {
                            JSONObject mr1 = response.getJSONObject(g);
                            userside.add(mr1.optString("username"));
                            //eventdescside.add(mr1.optString("eventDesc"));
                            cityside.add(mr1.optString("city"));
                            stateside.add(mr1.optString("state"));
                            //eventtside.add(mr1.optString("eventTime"));
                            businessesAssociatedW.add(mr1.optString("id"));
                        }
                        initEventViewN();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            queue.add(request);
        }


        return businessesAssociatedW;
    }
}