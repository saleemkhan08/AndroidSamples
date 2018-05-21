package com.thnki.learning.activities.listview;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.thnki.learning.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CustomBaseAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {

    private Context context;
    private List<ActivityInfo> activityInfoList;

    public CustomBaseAdapter(Context context, List<ActivityInfo> list) {
        activityInfoList = list;
        this.context = context;

    }

    @Override
    public int getCount() {
        return activityInfoList.size();
    }

    @Override
    public ActivityInfo getItem(int position) {
        return activityInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View rootView, @NonNull ViewGroup parent) {

        ListViewHolder holder;
        if (rootView == null) {
            rootView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            holder = new ListViewHolder(rootView);
            rootView.setTag(holder);
        } else {
            holder = (ListViewHolder) rootView.getTag();
        }
        String activityName[] = activityInfoList.get(position).name.replace(".", "#").split("#");
        String name = activityName[activityName.length - 1];
        String packageName = activityInfoList.get(position).name.replace("." + name, "");
        holder.titleView.setText(name);
        holder.descriptionView.setText(packageName);
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ComponentName componentName = new ComponentName(activityInfoList.get(position).packageName,
                activityInfoList.get(position).name);
        Intent intent = new Intent();
        intent.setComponent(componentName);
        context.startActivity(intent);
    }
}
