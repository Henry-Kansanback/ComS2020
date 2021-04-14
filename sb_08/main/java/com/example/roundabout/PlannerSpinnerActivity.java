package com.example.roundabout;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Helper method for spinners. Currently not in use.
 * @author HenryK
 */
public class PlannerSpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

    //Spinner spinner = (Spinner) findViewById(R.id.eventspageSpinner);
    //spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener();
    //Spinner spinner;
    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        //parent.getItemAtPosition(pos);


        // TODO need to have this do some work on the recyclerView on the same page
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}