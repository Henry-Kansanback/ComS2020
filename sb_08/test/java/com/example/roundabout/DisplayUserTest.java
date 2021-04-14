package com.example.roundabout;

import android.content.Context;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 * @author HenryK
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class DisplayUserTest {
    public static final String EXTRA_MESSAGE = "com.example.roundabout.MESSAGE";
    //private JSONObject user = new JSONObject();
    //private JSONObject userret = new JSONObject();
    //private Context context = ApplicationProvider.getApplicationContext();
    @Mock
    Context contextMock;
    @Mock
    View testingViewMock;
    JSONObject jsonitem;
    eventspage eventsPageMock;
    ArrayList<String> ideaMock;
    RequestQueue testQueue;
    communicationAdapter.ViewHolder viewHolder;


    @Test
    public void communicationMockTest(){
        communicationAdapter mockcomm = mock(communicationAdapter.class);
        when(mockcomm.getItemCount()).thenReturn(10);
        
    }


    @Test
    public void eventsPageMock(){
        //eventspage eventp = new eventspage();
        eventspage neventp = mock(eventspage.class);

        neventp.getEventsMadeBy(anyInt());
        verify(neventp).getEventsMadeBy(anyInt());
    }

    @Test
    public void testingMain() throws JSONException{
        MainActivity mainTest = new MainActivity();
        MainActivity nmainTest = spy(mainTest);
        testingViewMock = mock(View.class);
        nmainTest.getIntent();
        verify(nmainTest).getIntent();

        nmainTest.switchToEvent(testingViewMock);
        verify(nmainTest).switchToEvent(testingViewMock);
        testingViewMock = mock(View.class);

        nmainTest.switchToPlanner(testingViewMock);
        verify(nmainTest).switchToPlanner(testingViewMock);

    }

    @Test
    public void getEventsTest() throws JSONException{
        getEventsClass esplashclass = new getEventsClass();
        getEventsClass getclass = spy(esplashclass);
        testQueue = mock(RequestQueue.class);
        getclass.getAllEvents(testQueue);
        verify(getclass).getAllEvents(testQueue);

    }

    @Test
    public void simpleregisterScreenTest() throws JSONException, VolleyError {
        newSignUp newsplash = new newSignUp();
        newSignUp nnewsplash = spy(newsplash);
        testQueue =  mock(RequestQueue.class);
        jsonitem = mock(JSONObject.class);
        nnewsplash.signUp(jsonitem,100, 0,1,"",testQueue);
        verify(nnewsplash).signUp(jsonitem,100, 0,1,"",testQueue);
    }

    @Test
    public void onViewLogin() throws JSONException {
        addUserReg newsplash = new addUserReg();
        addUserReg nnewsplash = spy(newsplash);
        RequestQueue n = mock(RequestQueue.class);


        nnewsplash.URLPost = "";
        nnewsplash.userr = mock(JSONObject.class);
        String y = "1";
        String s = "2";
        String t = "3";
        String u = "4";
        String v = "5";
        //Context cn = ;
        nnewsplash.sendPostRequest(n,s,t,u,v,y);
        verify(nnewsplash).sendPostRequest(n,s,t,u,v,y);



    }
    @Test
    public void intentIssued(){
        //eventSplash s = spy(new eventSplash());
        eventspage i = mock(eventspage.class);
        i.getIntent();
        verify(i).getIntent();
    }

    @Test
    public void switchCheck(){

        eventspage eventsFage = new eventspage();
        eventspage spyfage = spy(eventsFage);
        ArrayList<String> inp = new ArrayList<>();
        inp.add("input1");
        inp.add("input2");
        spyfage.switchPos(inp,1,0);
        verify(spyfage).switchPos(inp,1,0);
    }
    //RequestQueue queue = Volley.newRequestQueue(context);

    @Test
    public void presenterTest(){
        UserAdapter display = mock(UserAdapter.class);
        display.getItemCount();
        verify(display, times(1)).getItemCount();
    }

    @Test
    public void addEvent() throws JSONException {
        //MainActivity n = new MainActivity();
        newEventInput mockObj = mock(newEventInput.class);
        //JSONObject mockObject = mock(JSONObject.class);
        String mockURL =".";
        JSONObject obj = null;
        String userside = "yellow";
        String passside = "mrk";
        String cityside = "Minneapolis";
        String stateside = "MN";
        String userTside =  "String;";
        //doNothing().when(obj.put("eventName",userside));
        when(mockObj.sendEventPost(obj, userside, passside, cityside, stateside, userTside, 0, mockURL)).thenReturn(1);
    }



    @Test
    public void eventsSwitch() {
        eventspage n = mock(eventspage.class);

        when(n.switchPos(0,1)).thenReturn("anything");

    }
}