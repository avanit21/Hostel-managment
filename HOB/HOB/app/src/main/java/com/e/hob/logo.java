package com.e.hob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class logo extends AppCompatActivity {

    private ProgressBar _progressbar;
    private int loading=0;
    private int bump_loading=0;
    private Timer _timer = new Timer();
    private Intent set_home = new Intent();
    private TimerTask timer1;
    private TimerTask timer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon);

        _progressbar = findViewById(R.id._progressbar);
        bump_loading = getRandom(45,68);

        timer1 = new TimerTask() {
            @Override
            public void run() {
                loading++;
                _progressbar.setProgress(loading);

                if(loading==bump_loading)
                {
                    timer1.cancel();
                }
            }
        };
        _timer.scheduleAtFixedRate(timer1,0,20);

        timer2 = new TimerTask() {
            @Override
            public void run() {
                loading++;
                _progressbar.setProgress(loading);
                if(loading==99)
                {
                    set_home.setClass(getApplicationContext(),login.class);
                    startActivity(set_home);
                    finish();
                }
            }
        };
        _timer.scheduleAtFixedRate(timer2,1000,25);
    }
    public int getRandom(int _min, int _max)
    {
        Random random = new Random();
        return random.nextInt(_max - -_min + 1) + _min;
    }
}
