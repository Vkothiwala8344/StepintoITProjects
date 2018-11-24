package com.stepintoit.vkoth.calculatorapplication.activity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.stepintoit.vkoth.calculatorapplication.R;
import com.stepintoit.vkoth.calculatorapplication.service.MusicService;
import com.stepintoit.vkoth.calculatorapplication.service.ServiceConstants;

public class MusicActivity extends AppCompatActivity {

    Button btnStart,btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        btnStart = (Button)findViewById(R.id.btn_start_music);
        btnStop = (Button)findViewById(R.id.btn_stop_music);

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
}
