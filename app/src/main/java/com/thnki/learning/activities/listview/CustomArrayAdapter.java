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
import android.widget.ArrayAdapter;

import com.thnki.learning.R;

import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter<ActivityInfo> implements AdapterView.OnItemClickListener {

    private Context context;
    private List<ActivityInfo> activityInfoList;

    public CustomArrayAdapter(@NonNull Context context, List<ActivityInfo> list) {
        super(context, android.R.layout.simple_list_item_1, list);
        this.context = context;
        this.activityInfoList = list;
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
        String packageName = activityInfoList.get(position).name.replace("."+name, "");
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
