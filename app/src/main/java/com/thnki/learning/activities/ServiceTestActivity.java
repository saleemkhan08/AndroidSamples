package com.thnki.learning.activities;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.thnki.learning.R;
import com.thnki.learning.services.MyService;

public class ServiceTestActivity extends AppCompatActivity {

    private TextView textView;
    private Intent serviceIntent;
    private MyService.MyServiceBinder myServiceBinder;
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myServiceBinder = ((MyService.MyServiceBinder) service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test);
        textView = findViewById(R.id.threadUpdate);
        serviceIntent = new Intent(this, MyService.class);
    }

    public void startService(View view) {
        startService(serviceIntent);

    }

    public void stopService(View view) {
        stopService(serviceIntent);
    }

    public void getRandomNumber(View view) {
        String str = "Is binder alive :" + myServiceBinder.isBinderAlive() + "\n"
                + "ServiceConnection :" + connection + "\n"
                + "getRandomNum :" + myServiceBinder.getRandomNumber();

        textView.setText(str);
    }

    public void bindService(View view) {
        bindService(serviceIntent, connection, BIND_AUTO_CREATE);
    }

    public void unBindService(View view) {
        unbindService(connection);
    }
}