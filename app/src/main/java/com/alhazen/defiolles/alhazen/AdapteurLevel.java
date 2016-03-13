package com.alhazen.defiolles.alhazen;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

/**
 * Created by PAYS on 12/03/2016.
 */
public class AdapteurLevel extends RecyclerView.Adapter<AdapteurLevel.ViewHolder> {


    private ArrayAdapter<String> mDataAdapter;
    private ArrayAdapter<Boolean> mDataAdapterbool;

    public static class ViewHolder extends RecyclerView.ViewHolder{


        Button button;
        public ViewHolder(View itemView) {
            super(itemView);
            button =(Button)itemView.findViewById(R.id.buttonSelectionLevel);
        }
    }

    public AdapteurLevel(ArrayAdapter<String> myDataAdapter,ArrayAdapter<Boolean> bool) {
        mDataAdapter = myDataAdapter;
        mDataAdapterbool = bool;
    }

    @Override
    public AdapteurLevel.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_level, parent, false);



        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final AdapteurLevel.ViewHolder holder, int position) {
        holder.button.setText(mDataAdapter.getItem(position));
        holder.button.setEnabled(mDataAdapterbool.getItem(position));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(),MainActivity.class);
                intent.putExtra("NewLevel",Integer.parseInt(holder.button.getText().toString()));
                holder.itemView.getContext().startActivity(intent);
                ((Activity)holder.itemView.getContext()).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataAdapter.getCount();
    }
}
