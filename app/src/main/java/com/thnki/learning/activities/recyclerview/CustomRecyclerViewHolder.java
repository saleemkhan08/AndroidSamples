package com.thnki.learning.activities.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.thnki.learning.R;

public class CustomRecyclerViewHolder extends RecyclerView.ViewHolder {

    TextView titleView;
    TextView descriptionView;

    CustomRecyclerViewHolder(View view) {
        super(view);
        titleView = view.findViewById(R.id.title);
        descriptionView = view.findViewById(R.id.description);
    }
}
