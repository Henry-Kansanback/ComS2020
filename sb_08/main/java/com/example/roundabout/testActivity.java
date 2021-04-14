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
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class tentatively to be used as a second or primary admin page
 * @author HenryK
 */
public class testActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button getRequestButton;
    Button postRequestButton;
    Button eventSwitchButton;
    Button plannerSwitchButton;
    Button getByID;
    EditText userType;
    EditText info2;

    RequestQueue queue;
    TextView textView;
    public static final String EXTRA_MESSAGE = "com.example.roundabout.MESSAGE";
    //String URL = "https://coms-309-sb-08.cs.iastate.edu:8080";
    //String URLPost = "https://62dee20f-6f1e-4db7-94a7-8983537aa024.mock.pstmn.io/fget/post";//test
    //String URLGet = "https://62dee20f-6f1e-4db7-94a7-8983537aa024.mock.pstmn.io/fget/post";//test
    //String URLPlanner = "https://62dee20f-6f1e-4db7-94a7-8983537aa024.mock.pstmn.io/fget/post";
    String URLUsers = "https://coms-309-sb-08.cs.iastate.edu:8080/users";
    String URLPlanner = "https://coms-309-sb-08.cs.iastate.edu:8080/users/eventplanners";
    String URLBusiness = "https://coms-309-sb-08.cs.iastate.edu:8080/users/businesses";
    String URLPost = "http://coms-309-sb-08.cs.iastate.edu:8080/addUser";
    String URLGet = "http://coms-309-sb-08.cs.iastate.edu:8080/users";
    JSONObject user = new JSONObject();
    JSONObject userret = new JSONObject();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getRequestButton = (Button) findViewById(R.id.button);
        postRequestButton = (Button) findViewById(R.id.button2);
        eventSwitchButton = (Button) findViewById(R.id.button3);
        plannerSwitchButton = (Button) findViewById(R.id.planner);
        getByID = (Button) findViewById(R.id.button4);
        userType = (EditText) findViewById(R.id.userType);
        info2 = (EditText) findViewById(R.id.info2);
        textView = (TextView) findViewById(R.id.textView);
        queue = Volley.newRequestQueue(this);

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




    }

    public void switchToEvent(View view){//JSONArrayRequest ?
        Intent intent = new Intent(view.getContext(), DisplayUsersActivity.class);


        startActivity(intent);
        //In the next Activity


        //startActivity(intent.putStringArrayListExtra("userret",userret));


    }
    public void switchToPlanner(View view) {
        Intent intent = new Intent(this, DisplayPlannerActivity.class);


        //Log.d(TAG, userret.toString());// Useful

        startActivity(intent);//intent.putExtra("userret",URLPlanner)
        //startActivity(intent.putExtra("userret",userret.toString()));


    }

    public void sendGetByIDRequest(String s){
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
    public void sendGetRequest(String s) {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URLGet,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response){

                textView.setText(response.keys().toString());
                //textView.setText(response.getString("username"));
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
                //System.out.print(error.toString());
                //textView.setText(error.toString());
                error.printStackTrace();
            }
        });

        queue.add(request);
    }


    public void sendPostRequest(JSONObject userr, String username) throws JSONException {



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
