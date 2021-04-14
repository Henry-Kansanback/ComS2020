package com.example.roundabout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

/**
 * Class associated with registration screen. Calls to addUserReg.
 * @author HenryK
 */
public class registerScreen extends AppCompatActivity {
    addUserReg n = new addUserReg();
    EditText regUsername;
    EditText regPassword;
    EditText regCity;
    EditText regState;
    Spinner regUserType;
    Button regButton;
    RequestQueue queue;//Remember to start passing queue's through intents. For the moment this works...
    String user;

    /**
     * onCreate method for the registerScreen
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen2);
        regButton = findViewById(R.id.registrationButton);
        regUsername = findViewById(R.id.registrationUsername);
        regPassword = findViewById(R.id.registrationPassword);
        regCity = findViewById(R.id.registrationCity);
        regState = findViewById(R.id.registrationState);

        regUserType = (Spinner) findViewById(R.id.registerUserType);
        queue = Volley.newRequestQueue(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.userT, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regUserType.setAdapter(adapter);

        regUserType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * onItemSelected:
             * acts on the result of user input on regUserType api element
             * Will register new user as user if first item is selected, business for second item, and planner for third.
             * @param adapterView
             * @param view
             * @param i
             * @param l
             */
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if(i==0){
                //user
                user = "user";
            }
            else if(i==1){
                //business
                user = "business";
            }
            else if(i==2){
                //event planner
                user = "planner";
            }
            }

            /**
             * On a lack of user selection this method sets user as the default value to the String user.
             * @param adapterView
             */
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                user = "user";
            }
        });
        regButton.setOnClickListener(new View.OnClickListener(){
            /**
             * sends the inputted information to the sendPostRequest class when user selects the registration button
             * to register the information with the server.
             * @param view
             */
            @Override
            public void onClick(View view) {
                try {
                    n.sendPostRequest(queue,regUsername.getText().toString(),regPassword.getText().toString(),
                            regCity.getText().toString(),regState.getText().toString(),user);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}