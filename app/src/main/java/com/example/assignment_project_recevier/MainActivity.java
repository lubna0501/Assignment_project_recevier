package com.example.assignment_project_recevier;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
@SuppressLint("MissingInflatedId")
public class MainActivity extends AppCompatActivity {
    private BroadcastReceiver callReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callReceiver = new CallReceiver();

        // Register BroadcastReceiver to listen for phone state changes
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PHONE_STATE");
        registerReceiver(callReceiver, intentFilter);

        // Set up button click listener
         Button simulateButton = findViewById(R.id.simulateButton);
        simulateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"simulating incoming call ",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister the BroadcastReceiver when the activity is destroyed
        unregisterReceiver(callReceiver);
    }



    // BroadcastReceiver to handle incoming call events
    public class CallReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null && intent.getAction().equals("android.intent.action.PHONE_STATE")) {
                String state = intent.getStringExtra(String.valueOf(TelephonyManager.EXTRA_STATE));
                if (state != null && state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                    // Display toast message for incoming call
                    Toast.makeText(getApplicationContext(), "Incoming Call project is completed", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}