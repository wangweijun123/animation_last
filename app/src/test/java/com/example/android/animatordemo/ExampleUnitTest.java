package com.example.android.animatordemo;

import android.util.ArrayMap;

import org.junit.Test;

import java.util.HashMap;
import java.util.Objects;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void testHashMap() {
        HashMap<Person, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < 14; i++) {
            hashMap.put(new Person(i), i);
        }
    }


}