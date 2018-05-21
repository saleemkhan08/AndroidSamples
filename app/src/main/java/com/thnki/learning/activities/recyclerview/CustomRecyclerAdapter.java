package com.thnki.learning.activities.recyclerview;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thnki.learning.R;

import java.util.List;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerViewHolder> implements View.OnClickListener {

    private List<ActivityInfo> activityInfoList;

    public CustomRecyclerAdapter(List<ActivityInfo> list) {
        this.activityInfoList = list;
    }

    @NonNull
    @Override
    public CustomRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomRecyclerViewHolder(LayoutInflater.
                from(parent.getContext()).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomRecyclerViewHolder holder, int position) {
        String activityName[] = activityInfoList.get(position).name.replace(".", "#").split("#");
        String name = activityName[activityName.length - 1];
        String packageName = activityInfoList.get(position).name.replace("." + name, "");
        holder.titleView.setText(name);
        holder.descriptionView.setText(packageName);
        holder.itemView.setOnClickListener(this);
        holder.itemView.setId(position);
    }

    @Override
    public int getItemCount() {
        return activityInfoList.size();
    }

    @Override
    public void onClick(View itemView) {

        int position = itemView.getId();
        ComponentName componentName = new ComponentName(activityInfoList.get(position).packageName,
                activityInfoList.get(position).name);
        Intent intent = new Intent();
        intent.setComponent(componentName);
        itemView.getContext().startActivity(intent);
    }
}
