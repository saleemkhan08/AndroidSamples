package com.thnki.learning.broadcast.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import static com.thnki.learning.activities.BroadcastReceiverTestActivity.TEST_STRING;

public class MyReceiver2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "MyReceiver2", Toast.LENGTH_SHORT).show();
        Log.d("OrderedBroadcast", "MyReceiver2 - 1");
        Log.d("OrderedBroadcast", "MyReceiver2 - 2");
        Log.d("OrderedBroadcast", "MyReceiver2 - 3");
        Log.d("OrderedBroadcast", "MyReceiver2 - 4");
        Log.d("OrderedBroadcast", "MyReceiver2 - 5");
        Log.d("OrderedBroadcast", "MyReceiver2 - 6");
        Log.d("OrderedBroadcast", "MyReceiver2 - 7");
        Log.d("OrderedBroadcast", "MyReceiver2 - 8");
        Log.d("OrderedBroadcast", "MyReceiver2 - 9");
        Log.d("OrderedBroadcast", "MyReceiver2 - 10");
        Log.d("OrderedBroadcast", "MyReceiver2 - 11");
        Log.d("OrderedBroadcast", "MyReceiver2 - 12");
        Log.d("OrderedBroadcast", "MyReceiver2 - 13");
        Log.d("OrderedBroadcast", "MyReceiver2 - 14");
        Log.d("OrderedBroadcast", "MyReceiver2 - 15");

        Bundle bundle = getResultExtras(true);
        String str = bundle.getString(TEST_STRING);
        str = str == null ? "MyReceiver2" : str;
        Log.d("OrderedBroadcast", "MyReceiver2 - " + str);
        bundle.putString(TEST_STRING, str + "MyReceiver2");
    }
}
