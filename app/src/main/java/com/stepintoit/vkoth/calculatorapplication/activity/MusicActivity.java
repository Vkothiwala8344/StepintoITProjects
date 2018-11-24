package com.stepintoit.vkoth.calculatorapplication.activity;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.stepintoit.vkoth.calculatorapplication.R;
import com.stepintoit.vkoth.calculatorapplication.service.MusicService;
import com.stepintoit.vkoth.calculatorapplication.service.ServiceConstants;

public class MusicActivity extends AppCompatActivity {

    public static String MAIN_ACTION = "com.stepintoit.vkoth.calculatorapplication.activity.mainactivity.main";
    public static String PREV_ACTION = "com.stepintoit.vkoth.calculatorapplication.activity.mainactivity.prev";
    public static String PLAY_ACTION = "com.stepintoit.vkoth.calculatorapplication.activity.mainactivity.play";
    public static String NEXT_ACTION = "com.stepintoit.vkoth.calculatorapplication.activity.mainactivity.next";
    Button btnStart,btnStop;
    TextView tvStatus;
    IntentFilter intentFilter;
    MusicBroadcasterReceiver musicBroadcasterReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        btnStart = (Button)findViewById(R.id.btn_start_music);
        btnStop = (Button)findViewById(R.id.btn_stop_music);
        tvStatus = (TextView)findViewById(R.id.tv_status);

        intentFilter = new IntentFilter();
        intentFilter.addAction(MAIN_ACTION);
        intentFilter.addAction(PREV_ACTION);
        intentFilter.addAction(PLAY_ACTION);
        intentFilter.addAction(NEXT_ACTION);

        musicBroadcasterReceiver = new MusicBroadcasterReceiver();



        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startMusicIntent = new Intent(MusicActivity.this, MusicService.class);
                startMusicIntent.setAction(ServiceConstants.ACTION.STARTFOREGROUND_ACTION);
                startService(startMusicIntent);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stopMusicIntent = new Intent(MusicActivity.this, MusicService.class);
                stopMusicIntent.setAction(ServiceConstants.ACTION.STOPFOREGROUND_ACTION);
                startService(stopMusicIntent);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(musicBroadcasterReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
       // unregisterReceiver(musicBroadcasterReceiver);
    }

    public class MusicBroadcasterReceiver extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent) {
            String status = intent.getStringExtra("status");
            tvStatus.append(status);
        }
    }
}
