package com.thnki.learning.broadcast.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import static com.thnki.learning.activities.BroadcastReceiverTestActivity.TEST_STRING;

public class MyReceiver1 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "MyReceiver1", Toast.LENGTH_SHORT).show();
        Log.d("OrderedBroadcast", "MyReceiver1 - 1");
        Log.d("OrderedBroadcast", "MyReceiver1 - 2");
        Log.d("OrderedBroadcast", "MyReceiver1 - 3");
        Log.d("OrderedBroadcast", "MyReceiver1 - 4");
        Log.d("OrderedBroadcast", "MyReceiver1 - 5");
        Log.d("OrderedBroadcast", "MyReceiver1 - 6");
        Log.d("OrderedBroadcast", "MyReceiver1 - 7");
        Log.d("OrderedBroadcast", "MyReceiver1 - 8");
        Log.d("OrderedBroadcast", "MyReceiver1 - 9");
        Log.d("OrderedBroadcast", "MyReceiver1 - 10");
        Log.d("OrderedBroadcast", "MyReceiver1 - 11");
        Log.d("OrderedBroadcast", "MyReceiver1 - 12");
        Log.d("OrderedBroadcast", "MyReceiver1 - 13");
        Log.d("OrderedBroadcast", "MyReceiver1 - 14");
        Log.d("OrderedBroadcast", "MyReceiver1 - 15");
        Bundle bundle = getResultExtras(true);
        String str = bundle.getString(TEST_STRING);
        str = str == null ? "MyReceiver1" : str;
        Log.d("OrderedBroadcast", "MyReceiver1 - " + str);
        bundle.putString(TEST_STRING, str + "MyReceiver1");
    }
}
