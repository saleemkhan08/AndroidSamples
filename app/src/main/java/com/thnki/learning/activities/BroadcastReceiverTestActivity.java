package com.thnki.learning.activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.thnki.learning.R;
import com.thnki.learning.services.MyService;

public class BroadcastReceiverTestActivity extends AppCompatActivity {

    public static final String TEST_BUNDLE = "TEST_BUNDLE";
    public static final String TEST_STRING = "TEST_STRING";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver_test);
        textView = findViewById(R.id.threadUpdate);
    }

    public void sendBroadcast(View view) {
        Intent intent = new Intent("com.thnki.br.action");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        sendOrderedBroadcast(intent, null, new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle bundle = getResultExtras(true);
                String str = bundle.getString(TEST_STRING);
                Log.d("OrderedBroadcast", "ActivityReceiver - " + str);
            }
        }, null, Activity.RESULT_OK, null, null);


    }
}