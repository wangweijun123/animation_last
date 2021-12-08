package com.example.android.animatordemo;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity_XXX";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jumpExecption(View view) {
        startActivity(new Intent(getApplicationContext(), ExeceptionActivity.class));
    }

    public void propertyAnimator(View view) {
        // 范围
        ValueAnimator animation = ValueAnimator.ofFloat(0f, 100f);
        // 周期
        animation.setDuration(1000);
        // 默认是加速后减速  AccelerateDecelerateInterpolator  LinearInterpolator匀速
        animation.setInterpolator(new LinearInterpolator());
        animation.start();
        // animatorTemp   animation
        animation.addUpdateListener( animatorTemp -> {
            float animatedValue = (float) animatorTemp.getAnimatedValue();
            long currentPlayTime = animatorTemp.getCurrentPlayTime();
            Log.i(TAG, "animatedValue: " + animatedValue + ", currentPlayTime:" + currentPlayTime);
        });
    }

    public void propertyAnimator2(View view) {
        MyPoint startValue = new MyPoint(0, 0);
        MyPoint endValue = new MyPoint(100, 100);
        // 范围
        ValueAnimator animation = ValueAnimator.ofObject(new MyTypeEvaluator(), startValue, endValue);
        // 周期
        animation.setDuration(1000);
        // 默认是加速后减速  AccelerateDecelerateInterpolator  LinearInterpolator匀速
        animation.setInterpolator(new LinearInterpolator());
        animation.start();
        // animatorTemp   animation
        animation.addUpdateListener( animatorTemp -> {
            MyPoint animatedValue = (MyPoint) animatorTemp.getAnimatedValue();
            long currentPlayTime = animatorTemp.getCurrentPlayTime();
            Log.i(TAG, "animatedValue: " + animatedValue + ", currentPlayTime:" + currentPlayTime);
        });
    }

    class MyTypeEvaluator implements TypeEvaluator<MyPoint> {
        MyPoint temp = new MyPoint();
        // evaluate 求值
        @Override
        public MyPoint evaluate(float fraction, MyPoint startValue, MyPoint endValue) {
            temp.x = (int) ((endValue.x - startValue.x) * fraction);
            temp.y = (int) ((endValue.y - startValue.y) * fraction);
            Log.i(TAG, "temp : " + temp);
            return temp;
        }
    }
}