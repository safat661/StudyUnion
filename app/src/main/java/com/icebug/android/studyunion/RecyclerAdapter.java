package com.icebug.android.studyunion;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nafis on 23-Aug-17.
 */

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

   private ArrayList<String> arrayList;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_child,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        holder.cardType.setText(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public RecyclerAdapter(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cardType;

        public ViewHolder(View itemView) {
            super(itemView);
            cardType = (TextView)itemView.findViewById(R.id.card_type);
            itemView.setOnClickListener(this);
        }


        //make new activity for editing or adding events to cards
        @Override
        public void onClick(View v) {

        }
    }
}
