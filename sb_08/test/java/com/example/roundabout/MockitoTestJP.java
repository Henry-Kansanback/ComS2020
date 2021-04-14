package com.example.roundabout;
import android.content.Context;

import com.android.volley.toolbox.Volley;
import com.example.roundabout.*;
import androidx.test.core.app.ApplicationProvider;

import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import org.junit.Test;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.android.volley.RequestQueue;

import java.util.ArrayList;
@RunWith(MockitoJUnitRunner.class)
public class MockitoTestJP {
    @Mock
    private Context context;
    @Mock
    private View testingView;
    private Object JSONObject;

   @Mock
   Button button;


    /*@Test
           public void test1 (){
        secondpage test = mock(secondpage.class);
        Object username;
        when(test.get(username), ass);

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("USER");
        arrayList.add("BUSINESS");

    }*/

    /*@Test
    public void test1{
       StringOps sops = mock(secondpage.class);
       ArrayList<String> arrayList = new ArrayList<String>();
       when(arrayList.get(0)).thenReturn("User Page");

    }*/

    @Test
    public void test2(){
        secondpage n = mock(secondpage.class);
        ArrayList<String> arrayList = new ArrayList<String>();
        when(arrayList.get(1)).thenReturn("Business Page");

    }
/* //these are Jakobs tests
    @Test
    public void test3(){
        secondpage n = mock(secondpage.class);
       onView(withId(R.id.usertxt)).perform(typeText("User"));
       onView(withId(R.id.passtxt)).perform(typeText("pword"));
       onView(withId(R.id.goButton)).perform(click());
       onView(withText("User")).check(matches(usertxt));
    }

    @Test
    public void test4(){
        onView(withId(R.id.passtxt)).perform(typeText("pword"));
        onView(withId(R.id.goButton)).perform(click());
        onView(withText("pword")).check(matches(password));
    }
*/
    @Test
    public void getEventTest() throws JSONException {
        userpage n = mock(userpage.class);

        JSONObject m = mock(JSONObject.class);

        doNothing().when(m.put("john",m));

        n.getEvents(m);
    }






}
