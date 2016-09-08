package com.example.ehar.stopwatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TextView count = null;
    Button start = null;
    Timer t = null;
    CounterTask task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.count = (TextView) findViewById(R.id.count);
        this.start = (Button) findViewById(R.id.start);

        this.t = new Timer();
        this.task = new CounterTask(0);

        this.start.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                t.scheduleAtFixedRate(MainActivity.this.task, 0, 1000);
            }
        });

    }

    /* -------------------------- */
    class CounterTask extends TimerTask {

        long count = 0;
        public CounterTask(long i) { this.count = i; }

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    MainActivity.this.count.setText(Long.toString(count++));
                }
            });
        }
    }
}
