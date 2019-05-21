package com.example.stopwatch;

import android.graphics.Typeface;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

public class StopWatchAct extends AppCompatActivity {

    Button btnstart, btnstop;
    ImageView icanchor;
    Animation roundingalone;
    Chronometer timerHere;
    Boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        btnstart = findViewById(R.id.btnstart);
        btnstop = findViewById(R.id.btnstop);
        icanchor = findViewById(R.id.icanchor);
        timerHere = findViewById(R.id.timerHere);

        btnstop.setAlpha(0);

        roundingalone = AnimationUtils.loadAnimation(this, R.anim.roundingalone);
        roundingalone.setInterpolator(new LinearInterpolator());
        Typeface MMedium = Typeface.createFromAsset(getAssets(), "fonts/MMedium.ttf");

        btnstart.setTypeface(MMedium);
        btnstop.setTypeface(MMedium);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRunning) {
                    icanchor.clearAnimation();
                    timerHere.stop();
                    btnstart.setAlpha(0);
                    btnstart.setTranslationY(80);
                    btnstart.animate().alpha(1).translationY(-80).setDuration(300).start();
                    btnstop.animate().alpha(0).setDuration(300).start();
                } else {
                    icanchor.startAnimation(roundingalone);
                    btnstop.setAlpha(0);
                    btnstop.setTranslationY(80);
                    btnstop.animate().alpha(1).translationY(-80).setDuration(300).start();
                    btnstart.animate().alpha(0).setDuration(300).start();
                    timerHere.setBase(SystemClock.elapsedRealtime());
                    timerHere.start();
                }
                isRunning = !isRunning;
            }
        };
        btnstart.setOnClickListener(onClickListener);
        btnstop.setOnClickListener(onClickListener);
    }
}
