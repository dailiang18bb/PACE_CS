package com.kachi.demo.customview;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    SimpleClockReferenceView mClockView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mClockView = findViewById(R.id.clock);
    }


    public void onStartClicked(View view) {
        mClockView.start();
    }

    public void onStopClicked(View view) {
        mClockView.stop();
    }
}
