package com.example.android.animatordemo;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";
    private  AnimationDrawable rocketAnimation;
    private AnimatedVectorDrawable vectorDrawable;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ImageView rocketImage = (ImageView) findViewById(R.id.rocket_image);
        rocketImage.setBackgroundResource(R.drawable.rocket_thrust);
        rocketAnimation = (AnimationDrawable) rocketImage.getBackground();

        rocketImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rocketAnimation.start();
            }
        });

        ImageView vector_image = findViewById(R.id.vector_image);
//        vector_image.setBackgroundResource(R.drawable.animatorvectordrawable);
        vectorDrawable = (AnimatedVectorDrawable) vector_image.getBackground();

        vector_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vectorDrawable.start();
            }
        });



    }

    /**
     *
     * 要从 Activity 中的 onStart() 方法进行调用，该方法会在 Android
     * 在屏幕上呈现视图时调用
     */
    @Override
    protected void onStart() {
        super.onStart();
        // 如果要自动启动动画,请在Activity.onStart()中调用
        // rocketAnimation.start();
    }
}
