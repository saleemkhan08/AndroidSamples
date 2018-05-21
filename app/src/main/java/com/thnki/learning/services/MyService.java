package com.thnki.learning.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "FromMyService";

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "Service bound");
        return new MyServiceBinder();
    }

    public class MyServiceBinder extends Binder {
        public int getRandomNumber() {
            Log.d(TAG, "Service getRandomNumber");
            return MyService.this.getRandomNumber();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Service Started");
        return Service.START_STICKY;
    }

    public int getRandomNumber() {
        Log.d(TAG, "Service getRandomNumber");
        return (int) (Math.random() * 100);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "Service reBound");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "Service unBound");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "Service Destroyed");
        super.onDestroy();
    }
}
