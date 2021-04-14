package com.example.roundabout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * @author HenryK
 */
public class communicationAdapter extends RecyclerView.Adapter<communicationAdapter.ViewHolder> {
    private ArrayList<String> callerList = new ArrayList<>();
    private ArrayList<String> receiverList = new ArrayList<>();
    private Context mContext;

    public communicationAdapter(ArrayList<String> callerL, ArrayList<String> receiverL, Context context){
        callerList = callerL;
        receiverList = receiverL;
        mContext =context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public TextView callerT;
        public TextView receiverT;
        public RelativeLayout parentLayout;

        /**
         * Perfoms onCreate-like operations such as findViewById on TextViews
         * @param v
         */
        public ViewHolder(View v) { //was originally TextView v
            super(v);
            callerT = v.findViewById(R.id.caller);
            receiverT = v.findViewById(R.id.receiver);
            parentLayout = v.findViewById(R.id.parent_layout);
        }
    }

    @NonNull
    @Override
    public communicationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_communication,parent,false);
        communicationAdapter.ViewHolder vh = new communicationAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull communicationAdapter.ViewHolder holder, int position) {
        holder.callerT.setText(callerList.get(position));
        holder.receiverT.setText(receiverList.get(position));
    }

    @Override
    public int getItemCount() {
        return callerList.size();
    }
}
