package com.example.roundabout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Login screen used for traveling between the multiple home screens, includes access to registration screen and a spinner to navigate between the home screens
 * @author JakobP
 */
public class secondpage extends AppCompatActivity {
    String URLLogin = "http://coms-309-sb-08.cs.iastate.edu:8080/login";
    String selected;
    EditText username;
    EditText password;
    Boolean verLogin = false;
    String usename;
    String pword;
    Button sButton;
    Button rButton;
    Button mButton;
    Spinner spinner;
    int retid = 0;
    int s;
    RequestQueue q;

    //Map<String, String> params = new HashMap<>();
    JSONObject parameters = new JSONObject();
    //SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondpage);
        q = Volley.newRequestQueue(this);
        sButton = findViewById(R.id.loginButton);
        rButton = findViewById(R.id.register_button);
        username = findViewById(R.id.loginText);
        password = findViewById(R.id.passwordText);
        mButton = findViewById(R.id.oldMain);
       // usename = sp.getString("username", null);
        //pword = sp.getString("password", null);
        //final String users = username.getText().toString();
        //final String passw = password.getText().toString();
        spinner = findViewById(R.id.usergiven);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("USER");
        arrayList.add("BUSINESS");
        arrayList.add("EVENT PLANNER");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                s = i;


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                s=0;
            }

}
        );
        rButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(view.getContext(), registerScreen.class));
            }
        });
        sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (username.getText() != null && password.getText() != null && username.length() > 0 && password.length() > 0)
                {
                    Log.d("secondpage Got to sButton", username.getText().toString());
                    try {
                        parameters.put("username", username.getText().toString());
                        parameters.put("password", password.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                checkUser();
                if (verLogin) {
                    if (s == 0) {
                        Intent intent1 = new Intent(view.getContext(), userSplash.class);
                        intent1.putExtra("id",retid);
                        intent1.putExtra("username",username.getText().toString());
                        startActivity(intent1);
                    }
                    if (s == 1) {
                        Intent intent1 = new Intent(view.getContext(), businessSplash.class);
                        intent1.putExtra("id",retid);
                        intent1.putExtra("username",username.getText().toString());
                        startActivity(intent1);
                    }

                    if (s == 2) {
                        Intent intent1 = new Intent(view.getContext(), eventSplash.class);
                        intent1.putExtra("id",retid);
                        intent1.putExtra("username",username.getText().toString());
                        startActivity(intent1);
                    }
                }
        }

        });
        mButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(view.getContext(), MainActivity.class));
            }
        });

  }

  protected void loginFin(Boolean tr, int si, View view){
        Log.d("secondpage","Looking at login");

      if (tr) {
          if (s == 0) {
              Intent intent1 = new Intent(getApplicationContext(), userSplash.class);
              intent1.putExtra("id",si);
              intent1.putExtra("username",username.getText().toString());
              startActivity(intent1);
          }
          if (s == 1) {
              Intent intent1 = new Intent(getApplicationContext(), businessSplash.class);
              intent1.putExtra("id",si);
              intent1.putExtra("username",username.getText().toString());
              startActivity(intent1);
          }

          if (s == 2) {
              Intent intent1 = new Intent(getApplicationContext(), eventSplash.class);
              intent1.putExtra("id",si);
              intent1.putExtra("username",username.getText().toString());
              startActivity(intent1);
          }
      }
  }

    protected void checkUser()
    {
        Log.d("secondpage Show output", parameters.toString());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URLLogin, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                if(response.isNull("id") == false) {
                    verLogin = true;
                    loginFin(true, response.optInt("id",0), null);
                }
                else{
                    verLogin = false;
                    Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_SHORT).show();
                }

                Log.d("secondpage Got to request",response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Incorrect Username or Password", Toast.LENGTH_SHORT).show();


            }
        });

        q.add(request);
    }
}