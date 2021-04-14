package com.example.roundabout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Deprecated class originally used for modularizing the getEvents calls
 * @author HenryK
 */
public class getEventsClass {
    ArrayList<String> eventnside = new ArrayList<String>();
    ArrayList<String> eventdescside = new ArrayList<String>();
    ArrayList<String> cityside = new ArrayList<String>();
    ArrayList<String> stateside = new ArrayList<String>();
    ArrayList<String> eventtside = new ArrayList<String>();
    ArrayList<String> eventmadeby = new ArrayList<String>();
    String URLEvents = "https://coms-309-sb-08.cs.iastate.edu:8080/events";
    JSONObject eventret = new JSONObject();
    ArrayList<ArrayList<String>> j = new ArrayList<>();
    protected ArrayList<ArrayList<String>> getAllEvents(RequestQueue queue){

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URLEvents, eventret, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response){

                int i = 0;
                //userret = response;
                try {
                    eventret.put("json",response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    JSONArray eventretString = eventret.getJSONArray("json");
                    for(;i < eventretString.length();i++){
                        JSONObject mr1 = eventretString.getJSONObject(i);
                        eventnside.add(mr1.optString("eventName"));
                        eventdescside.add(mr1.optString("eventDesc"));
                        cityside.add(mr1.optString("city"));
                        stateside.add(mr1.optString("state"));
                        eventtside.add(mr1.optString("eventTime"));
                        eventmadeby.add(mr1.optString("madeBy"));
                    }
                    j.add(eventnside);
                    j.add(eventdescside);
                    j.add(cityside);
                    j.add(stateside);
                    j.add(eventtside);
                    j.add(eventmadeby);
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
        return j;
    }
}
