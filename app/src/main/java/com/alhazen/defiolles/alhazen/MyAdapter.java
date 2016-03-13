package com.alhazen.defiolles.alhazen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by vadoreau on 08/03/16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


    private ArrayAdapter<String> mDataAdapter;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView mTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.nomEtAdresseAppareilBluetooth);
        }

    }



    public MyAdapter(ArrayAdapter<String> myDataAdapter) {
        mDataAdapter = myDataAdapter;
    }


    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_my_list_item, parent, false);



        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataAdapter.getItem(position));

    }

    @Override
    public int getItemCount() {
        return mDataAdapter.getCount();

    }
}
