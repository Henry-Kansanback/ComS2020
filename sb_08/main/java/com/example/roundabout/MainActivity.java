package com.example.roundabout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.w3c.dom.Text;

/**
 * Deprecated main menu class that was originally devoted to being a main menu but will be
 * converted into an admin screen
 * @author HenryK
 */
public class MainActivity extends AppCompatActivity {
    //Recycle Views for lists, considered as component that has info, loop return from server, put as many items in it
    private static final String TAG = "MainActivity";
    Button getRequestButton;
    Button postRequestButton;
    Button eventSwitchButton;
    Button plannerSwitchButton;
    Button getByID;
    Button login;
    Button websocketBypass;
    Button userbypass;
    Button businessbypass;
    Button eventbypass;
    EditText userType;
    EditText info2;

    Integer idforbypass = 0;
    String UsernameForBypass;
    RequestQueue queue;
    TextView textView;
    public static final String EXTRA_MESSAGE = "com.example.roundabout.MESSAGE";
    //String URL = "https://coms-309-sb-08.cs.iastate.edu:8080";
    //String URLPost = "https://62dee20f-6f1e-4db7-94a7-8983537aa024.mock.pstmn.io/fget/post";//test
    //String URLGet = "https://62dee20f-6f1e-4db7-94a7-8983537aa024.mock.pstmn.io/fget/post";//test
    //String URLPlanner = "https://62dee20f-6f1e-4db7-94a7-8983537aa024.mock.pstmn.io/fget/post";
    String URLUsers = "http://coms-309-sb-08.cs.iastate.edu:8080/users";
    String URLPlanner = "http://coms-309-sb-08.cs.iastate.edu:8080/users/eventplanners";
    String URLBusiness = "http://coms-309-sb-08.cs.iastate.edu:8080/users/businesses";
    String URLPost = "http://coms-309-sb-08.cs.iastate.edu:8080/addUser";
    String URLGet = "http://coms-309-sb-08.cs.iastate.edu:8080/users";
    JSONObject user = new JSONObject();
    JSONObject userret = new JSONObject();
    Button newPageButton;

    /**
     * Performs findViewById on XML object and performs setOnClickListener on all buttons in XML
     * starts new activity based on selected button.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getRequestButton = (Button) findViewById(R.id.button);
        newPageButton = (Button) findViewById(R.id.loginLaunch);
        postRequestButton = (Button) findViewById(R.id.button2);
        eventSwitchButton = (Button) findViewById(R.id.button3);
        plannerSwitchButton = (Button) findViewById(R.id.planner);
        websocketBypass = (Button) findViewById(R.id.websocket);
        getByID = (Button) findViewById(R.id.button4);
        login = findViewById(R.id.loginLaunch);
        userType = (EditText) findViewById(R.id.userTypegiven);
        info2 = (EditText) findViewById(R.id.info2);
        textView = (TextView) findViewById(R.id.textView);
        userbypass = (Button) findViewById(R.id.userBypass);
        businessbypass = (Button) findViewById(R.id.businessBypass);
        eventbypass = (Button) findViewById(R.id.eventBypass);
        queue = Volley.newRequestQueue(this);

        newPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, secondpage.class));
            }
        });

        getRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendGetRequest(info2.getText().toString());
            }
        });
        postRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    sendPostRequest(user, userType.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
        getByID.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
            sendGetByIDRequest(info2.getText().toString());

            }
        });

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //secondpage n = new secondpage();
                Intent intent = new Intent(view.getContext(),secondpage.class);
                startActivity(intent);
            }
        });
        websocketBypass.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(view.getContext(),webSocketBypassClass.class));
            }
        });
        userbypass.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent1 = new Intent(view.getContext(),userSplash.class);
                if(Integer.parseInt(info2.getText().toString()) > -1){
                    idforbypass = Integer.parseInt(info2.getText().toString());
                    intent1.putExtra("id", idforbypass);
                }
                else{
                    intent1.putExtra("id",0);
                }
                if(userType.getText() != null && userType.getText().toString().length() > 0) {
                    UsernameForBypass = userType.getText().toString();
                    intent1.putExtra("username", UsernameForBypass);
                }
                else{
                    intent1.putExtra("username","Username");
                }
                startActivity(intent1);
            }

        });
        businessbypass.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent1 = new Intent(view.getContext(),businessSplash.class);
                if(Integer.parseInt(info2.getText().toString()) > -1){
                    idforbypass = Integer.parseInt(info2.getText().toString());
                    intent1.putExtra("id", idforbypass);
                }
                else{
                    intent1.putExtra("id",1);
                }
                if(userType.getText() != null && userType.getText().toString().length() > 0) {
                    UsernameForBypass = userType.getText().toString();
                    intent1.putExtra("username", UsernameForBypass);
                }
                else{
                    intent1.putExtra("username","Username1");
                }
            startActivity(intent1);
            }
        });
        eventbypass.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent1 = new Intent(view.getContext(),eventSplash.class);
                if(Integer.parseInt(info2.getText().toString()) > -1){
                    idforbypass = Integer.parseInt(info2.getText().toString());
                    intent1.putExtra("id", idforbypass);
                }
                else{
                    intent1.putExtra("id",2);
                }
                if(userType.getText() != null && userType.getText().toString().length() > 0) {
                    UsernameForBypass = userType.getText().toString();
                    intent1.putExtra("username", UsernameForBypass);
                }
                else{
                    intent1.putExtra("username","Username2");
                }
                startActivity(intent1);
            }
        });


    }

    /*public void switchToLogin(View view){

    }*/

    /**
     * Goes to DisplayUsersActivity screen which displays all users
     * @param view
     */
    public void switchToEvent(View view){//JSONArrayRequest ?
        Intent intent = new Intent(view.getContext(), DisplayUsersActivity.class);


        startActivity(intent);


    }

    /**
     * Sends view to DisplayPlannerActivity
     * @param view
     */
    public void switchToPlanner(View view) {
        Intent intent = new Intent(this, DisplayPlannerActivity.class);


        //Log.d(TAG, userret.toString());// Useful

        startActivity(intent);//intent.putExtra("userret",URLPlanner)
        //startActivity(intent.putExtra("userret",userret.toString()));


    }

    /**
     * Deprecated get request for user data from server
     * gets specific user data based on inputted id
     * @param s ID to be acquired from the server.
     */
    protected void sendGetByIDRequest(String s){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URLUsers + "/" + s, userret, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                textView.setText(response.toString());
                try {
                    userret.put("data",response);

                    //textView.setText(userret.);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    textView.setText(error.toString());
                }
        });
        queue.add(request);

    }

    /**
     * Deprecated code for getting user data from server
     * Attempts to get all users from the server
     * @param s no idea
     */
    protected void sendGetRequest(String s) {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URLGet,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response){

                    textView.setText(response.toString());
                try {
                    userret.put("json",response);
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

    /**
     * Deprecated code for sending user data to server
     * @param userr
     * @param username remnant of earlier version of this method, will be removed
     * @throws JSONException
     */
    protected void sendPostRequest(JSONObject userr, String username) throws JSONException {

        userr.put("username",username);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URLPost,userr, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //System.out.print(response.toString());
                //response.getJSONObject("username");

                textView.setText(response.toString());
                //Log.d("Help", response.toString());
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                //System.out.print(error.toString());
                textView.setText(error.toString());
            }
        });
        queue.add(request);
    }

}