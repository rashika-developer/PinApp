package com.rashika.pinapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private int pinCount = 42;
    private static final String KEY_PIN_COUNT = "pin_count";
    private static final String TAG = "PinApp Lifecycle";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            pinCount = savedInstanceState.getInt(KEY_PIN_COUNT,0);
            Log.d(TAG, "1. onCreate() - RESTORED pinCount: " + pinCount);
        }
        else {
            Log.d(TAG, "1. onCreate() - NEW pinCount: " + pinCount);
        }

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_PIN_COUNT, pinCount);
        Log.d(TAG, "4. onSaveInstanceState - saved pinCount: " + pinCount);
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "2. onStart() called");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "3. onResume() called <--App is ACTIVE");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "4. onPause() called");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG,"5. onStop() called");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG, "6. onRestart() called");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "7. onDestroy() called");
    }
}