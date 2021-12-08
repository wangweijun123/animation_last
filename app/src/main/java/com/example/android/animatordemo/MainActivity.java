package com.example.android.animatordemo;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.LinearInterpolator;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity_XXX";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        propertyAnimator();
    }

    private void propertyAnimator() {
        // 范围
        ValueAnimator animation = ValueAnimator.ofFloat(0f, 100f);
        // 周期
        animation.setDuration(1000);
//      默认是加速后减速  AccelerateDecelerateInterpolator  LinearInterpolator匀速
        animation.setInterpolator(new LinearInterpolator());
        animation.start();
        // animatorTemp   animation
        animation.addUpdateListener( animatorTemp -> {
            float animatedValue = (float) animatorTemp.getAnimatedValue();
            long currentPlayTime = animatorTemp.getCurrentPlayTime();
            Log.i(TAG, "animatedValue: " + animatedValue + ", currentPlayTime:" + currentPlayTime);
        });
    }
}