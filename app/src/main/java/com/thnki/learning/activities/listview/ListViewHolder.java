package com.thnki.learning.activities.listview;

import android.view.View;
import android.widget.TextView;

import com.thnki.learning.R;

public class ListViewHolder {

    TextView titleView;
    TextView descriptionView;

    ListViewHolder(View view) {
        titleView = view.findViewById(R.id.title);
        descriptionView = view.findViewById(R.id.description);
    }

}
