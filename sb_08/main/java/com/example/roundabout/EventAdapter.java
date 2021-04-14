package com.example.roundabout;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
//import com.example.roundabout.DisplayUsersActivity;

/**
 * Acts as an adapter for the Event RecyclerView
 * @author HenryK
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> implements View.OnClickListener {
    private static final String TAG = "EventAdapter";
    int vij = -1;
    //private String[] mDataset;
    //private ArrayList<String> mDataset = new ArrayList<>();
    private ArrayList<String> mevent = new ArrayList<>();
    private ArrayList<String> mdesc = new ArrayList<>();
    private ArrayList<String> mcity = new ArrayList<>();
    private ArrayList<String> mstate = new ArrayList<>();
    private ArrayList<String> meventT = new ArrayList<>();
    private Context mContext;
    private int eiditem;
    private int biditem;
    private int userT;
    /**
     * Initializes the EventAdapter
     * @param event name of the event
     * @param desc description of the event
     * @param city city of the event
     * @param state (geopolitical, meaning not code) state of the event
     * @param time date (or time) of event
     * @param context the context of the
     */
    public EventAdapter(ArrayList<String> event,ArrayList<String> desc,ArrayList<String> city,ArrayList<String> state,ArrayList<String> time, Context context) {
        //mDataset = Dataset;
        mevent = event;
        mdesc = desc;
        mcity = city;
        mstate = state;
        meventT = time;
        mContext = context;

    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public TextView username;
        public TextView password;
        public TextView city;
        public TextView state;
        public TextView usertype;
        public RelativeLayout parentLayout;

        /**
         * Perfoms onCreate-like operations such as findViewById on TextViews
         * @param v
         */
        public ViewHolder(View v) { //was originally TextView v
            super(v);
            username = v.findViewById(R.id.nametxt);
            password = v.findViewById(R.id.passtxt);
            city = v.findViewById(R.id.citytxt);
            state = v.findViewById(R.id.statetxt);
            usertype = v.findViewById(R.id.usertxt);
            parentLayout = v.findViewById(R.id.parent_layout);
        }
    }

    /**
     *  creates a new View, and a new ViewHolder from said new View
     * @param parent
     * @param viewType
     * @return vh, the newly created ViewHolder, made from the new View
     */
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        //TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);//original
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    /**
     * This method places all of the information into the RecyclerView
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Log.d(TAG, "onBindViewHolder: Called");
        holder.username.setText(mevent.get(position));
        holder.password.setText(mdesc.get(position));
        holder.city.setText(mcity.get(position));
        holder.state.setText(mstate.get(position));
        holder.usertype.setText(meventT.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked on: " + mevent.get(position));
                Toast.makeText(mContext, mevent.get(position), Toast.LENGTH_SHORT).show();


                //Call to something that provides the specific eid and bid for this selected position
                /*
                Intent intent1 = new Intent(view.getContext(),newSignUp.class);
                intent1.putExtra("eventid",eiditem);
                intent1.putExtra("businessid",biditem);
                mContext.startActivity(intent1);

                 */
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)

    /**
     *
     * @return length of the list of events received (from the server)
     */
    @Override
    public int getItemCount() {
        return mevent.size();
    }

    @Override
    public void onClick(View view){
        Intent intent1 = new Intent(view.getContext(),newSignUp.class);
        intent1.putExtra("BID",1);
        intent1.putExtra("EID",1);
        mContext.startActivity(intent1);
    }
}
