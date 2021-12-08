package com.example.android.animatordemo;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ExeceptionActivity extends AppCompatActivity {
    private static final String TAG = "ExeceptionActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exeception);
//        try {
            int index = 1 / 0;
            Log.i(TAG, "onCreate: index:"+index);
        /*} catch (Exception ex) {
            ex.printStackTrace();
        }*/

    }
}
