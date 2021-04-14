package com.example.roundabout;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Helper class associated with the registration screen. Contains actual post request action.
 * @author HenryK
 */
public class addUserReg {
    String URLPost = "http://coms-309-sb-08.cs.iastate.edu:8080/addUser";
    JSONObject userr = new JSONObject();

    /**
     * Creates user JSON object and sends a post request, adding a new user to the server
     * @param queue inputted RequestQueue; is created elsewhere
     * @param username String of username, to be sent to server
     * @param password String of password, to be sent to server
     * @param city String of city associated with user, to be sent to server
     * @param state String of state associated with user, to be sent to server
     * @param UserType Type of user
     * @throws JSONException
     */

    public void sendPostRequest(RequestQueue queue, String username, String password, String city, String state, String UserType) throws JSONException {
        userr.put("username",username);
        userr.put("password",password);
        userr.put("city",city);
        userr.put("state",state);
        userr.put("typeOfUser",UserType);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URLPost,userr, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){

            }
        });
        if(queue != null)
        queue.add(request);
    }

    public void sendPostRequestBusiness(RequestQueue queue, String username, String password, String city, String state, String UserType,
                                        String Q1,String Q2,String Q3, String Q4, String Q5) throws JSONException {
        userr.put("username",username);
        userr.put("password",password);
        userr.put("city",city);
        userr.put("state",state);
        userr.put("typeOfUser",UserType);
        userr.put("Q1",Q1);
        userr.put("Q2",Q2);
        userr.put("Q3",Q3);
        userr.put("Q4",Q4);
        userr.put("Q5",Q5);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URLPost,userr, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){

            }
        });
        queue.add(request);
    }
    public void sendPostRequestPlanner(RequestQueue queue, String username, String password, String city, String state, String UserType,
                                       String Q1,String Q2,String Q3, String Q4, String Q5, String Q6, String Q7, String Q8,
                                       String Q9, String Q10, String Q11) throws JSONException {
        userr.put("username",username);
        userr.put("password",password);
        userr.put("city",city);
        userr.put("state",state);
        userr.put("typeOfUser",UserType);
        userr.put("Q1",Q1);
        userr.put("Q2",Q2);
        userr.put("Q3",Q3);
        userr.put("Q4",Q4);
        userr.put("Q5",Q5);
        userr.put("Q6",Q6);
        userr.put("Q7",Q7);
        userr.put("Q8",Q8);
        userr.put("Q9",Q9);
        userr.put("Q10",Q10);
        userr.put("Q11",Q11);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URLPost,userr, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){

            }
        });
        queue.add(request);

    }
}
