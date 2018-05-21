package com.thnki.learning;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.thnki.learning.activities.recyclerview.CustomRecyclerAdapter;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView view = findViewById(R.id.activitiesList);
        CustomRecyclerAdapter adapter = new CustomRecyclerAdapter(getActivitiesInfoList());
        view.setAdapter(adapter);
        view.setLayoutManager(new LinearLayoutManager(this));
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
