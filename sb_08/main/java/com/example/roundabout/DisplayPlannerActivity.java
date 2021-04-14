package com.example.roundabout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Person;
import android.os.Bundle;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

/**
 * Deprecated class used for displaying users
 * @author HenryK
 */
public class DisplayPlannerActivity extends AppCompatActivity {
    private static final String TAG = "DisplayPlannerActivity";
    private ArrayList<String> userside = new ArrayList<String>();
    private ArrayList<String> passside = new ArrayList<String>();
    private ArrayList<String> cityside = new ArrayList<String>();
    private ArrayList<String> stateside = new ArrayList<String>();
    private ArrayList<String> userTside = new ArrayList<String>();
    private String URLUsers = "http://coms-309-sb-08.cs.iastate.edu:8080/users/eventplanners";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_planner);
        Log.d(TAG,"onCreate:DisplayPlannerActivity");

        ArrayList<String> userretString = getIntent().getStringArrayListExtra("userret");
        Log.d(TAG,"onCreate:Received userretString");
        initdata(userretString);
    }

    /**
     * Deprecated method that performs get request to server for user data, then adding the acquired data to ArrayLists to be used
     * in a RecyclerView. Adds to RequestQueue then sends data to initrecyclerView()
     */

    private void initdata(ArrayList<String> userretString){


        initrecyclerView();
    }

    /**
     * initializes UserAdapter and sets inputted data to initialized RecyclerView.
     */
    private void initrecyclerView(){
        Log.d(TAG,"initrecyclerView");
        RecyclerView recyclerView = findViewById(R.id.recyclerp);
        UserAdapter adapter = new UserAdapter(userside,passside,cityside,stateside,userTside, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}