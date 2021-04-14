package com.example.roundabout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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
 * Deprecated class used for displaying users in a recyclerView.
 * @author HenryK
 */
public class DisplayUsersActivity extends AppCompatActivity {
    private static final String TAG = "DisplayUsersActivity";
    //private RecyclerView recyclerView;
    //private RecyclerView.Adapter mAdapter;
    //private RecyclerView.LayoutManager layoutManager;
    private RequestQueue queue;
    private ArrayList<String> userside = new ArrayList<>();
    private ArrayList<String> passside = new ArrayList<>();
    private ArrayList<String> cityside = new ArrayList<>();
    private ArrayList<String> stateside = new ArrayList<>();
    private ArrayList<String> userTside = new ArrayList<>();
    private String URLUsers = "http://coms-309-sb-08.cs.iastate.edu:8080/users";
    JSONObject userret = new JSONObject();

    /**
     * onCreate method that initializes RequestQueue and then calls to initdata()
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_users);
        Log.d(TAG,"onCreate:DisplayUsersActivity");
        queue = Volley.newRequestQueue(this);

        Intent intent = getIntent();
        String userretString = intent.getStringExtra("userret"); //may need to remove

        Log.d(TAG,"onCreate:getIntent");


        //Log.d(TAG,"onCreate:Received userretString");


        initdataArray();
        //initdata();
    }



    protected void initdataArray() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URLUsers, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int i = 0;

                try {

                    //JSONArray userretString = userret.getJSONArray("json");
                    for(;i < response.length();i++){
                        JSONObject mr1 = response.getJSONObject(i);
                        userside.add(mr1.optString("username"));
                        passside.add(mr1.optString("password"));
                        cityside.add(mr1.optString("city"));
                        stateside.add(mr1.optString("state"));
                        userTside.add(mr1.optString("typeOfUser"));
                        //Log.d(TAG, Integer.toString(userside.size()));

                    }
                    initrecyclerView();
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
     * initializes UserAdapter and sets inputted data to initialized RecyclerView.
     */
    protected void initrecyclerView(){
        Log.d(TAG,"initrecyclerView");
        RecyclerView recyclerView = findViewById(R.id.recycler);
        UserAdapter adapter = new UserAdapter(userside,passside,cityside,stateside,userTside, this);
        Log.d(TAG, Integer.toString(userside.size()));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}