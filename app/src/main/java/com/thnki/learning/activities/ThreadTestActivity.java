package com.thnki.learning.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.thnki.learning.R;

public class ThreadTestActivity extends AppCompatActivity {


    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_test);
        textView = findViewById(R.id.threadUpdate);
    }

    private boolean isCancelled = false;
    Thread myThread = new Thread() {
        private int i;

        @Override
        public void run() {
            for (i = 0; i < 1000; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(" i = " + i);
                    }
                });
                if (isCancelled)
                    break;
            }
        }
    };

    public void startThread(View view) {
        myThread.start();
        isCancelled = false;
    }

    public void stopThread(View view) {
        isCancelled = true;
    }
}