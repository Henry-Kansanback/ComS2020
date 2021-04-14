package com.example.roundabout;

import android.content.Context;
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
 * Adapter class for displaying RecyclerView for users.
 * @author HenryK
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private static final String TAG = "UserAdapter";
    //private String[] mDataset;
    //private ArrayList<String> mDataset = new ArrayList<>();
    int i = -1;
    private ArrayList<String> muser = new ArrayList<>();
    private ArrayList<String> mpass = new ArrayList<>();
    private ArrayList<String> mcity = new ArrayList<>();
    private ArrayList<String> mstate = new ArrayList<>();
    private ArrayList<String> musertype = new ArrayList<>();
    private Context mContext;

    public UserAdapter(ArrayList<String> user,ArrayList<String> pass,ArrayList<String> city,ArrayList<String> state,ArrayList<String> usertype, Context context) {
        //mDataset = Dataset;
        muser = user;
        mpass = pass;
        mcity = city;
        mstate = state;
        musertype = usertype;
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


    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        //TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);//original
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,parent,false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Log.d(TAG, "onBindViewHolder: Called");
        holder.username.setText(muser.get(position));
        holder.password.setText(mpass.get(position));
        holder.city.setText(mcity.get(position));
        holder.state.setText(mstate.get(position));
        holder.usertype.setText(musertype.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked on: " + muser.get(position));
                i = position;
                Toast.makeText(mContext, muser.get(position), Toast.LENGTH_SHORT).show();

            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return muser.size();
    }

}
