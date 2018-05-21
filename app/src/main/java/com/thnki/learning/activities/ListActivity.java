package com.thnki.learning.activities;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.thnki.learning.R;
import com.thnki.learning.activities.listview.CustomArrayAdapter;
import com.thnki.learning.activities.listview.CustomBaseAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    ListView listView;
    List<ActivityInfo> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = findViewById(R.id.simpleList);
        items = getActivitiesInfoList();
        //CustomArrayAdapter adapter = new CustomArrayAdapter(this, items);
        CustomBaseAdapter adapter = new CustomBaseAdapter(this, items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(adapter);
    }

    public List<ActivityInfo> getActivitiesInfoList() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(
                    getPackageName(), PackageManager.GET_ACTIVITIES);
            return Arrays.asList(packageInfo.activities);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
