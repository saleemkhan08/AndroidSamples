package com.thnki.learning.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.thnki.learning.R;

public class SingleInstanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_instance);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Toast.makeText(this, "OnNewIntent", Toast.LENGTH_SHORT).show();
    }

    public void launchSingleTop(View view) {
        startActivity(new Intent(this, SingleTopActivity.class));
    }

    public void launchSingleInstance(View view) {
        startActivity(new Intent(this, SingleInstanceActivity.class));
    }

    public void launchSingleTask(View view) {
        startActivity(new Intent(this, SingleTaskActivity.class));
    }

    public void launchStandard(View view) {
        startActivity(new Intent(this, StandardActivity.class));
    }
}
