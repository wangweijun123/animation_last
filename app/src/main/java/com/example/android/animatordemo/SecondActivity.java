package com.example.android.animatordemo;

import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

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

        rocketImage.setOnClickListener(view -> rocketAnimation.start());

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

    public void jumpCrossFade(View view) {
        startActivity(new Intent(getApplicationContext(), CrossfadeActivity.class));
    }

    public void testHashMap(View view) {
        HashMap<Person, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < 14; i++) {
            hashMap.put(new Person(i), i);
        }
    }

    public void testArrayMap(View view) {
        ArrayMap<Person, Integer> hashMap = new ArrayMap<>();
        Person lastPerson = null;
        for (int i = 0; i < 14; i++) {
            lastPerson = new Person(i);
            hashMap.put(lastPerson, i);
        }
        Integer integer = hashMap.get(lastPerson);
        Log.i(TAG, "testArrayMap: value = " + integer);
    }

}
