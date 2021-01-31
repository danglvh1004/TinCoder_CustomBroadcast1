package com.example.custombroadcast1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String MY_ACCTION = "com.haidang.ACTION";
    private static final String MY_TEXT = "com.haidang.TEXT";

    private Button btnSendBroadast;
    private TextView tvReceived;

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (MY_ACCTION.equals(intent.getAction())) {
                String text = intent.getStringExtra(MY_TEXT);
                tvReceived.setText(text);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSendBroadast = findViewById(R.id.btn_send_broadcast);
        tvReceived = findViewById(R.id.tv_received);

        btnSendBroadast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSendBoradcast();
            }
        });
    }

    private void clickSendBoradcast() {
        Intent intent = new Intent(MY_ACCTION);
        intent.putExtra(MY_TEXT, "This is Hai Dang");
        sendBroadcast(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter intentFilter = new IntentFilter(MY_ACCTION);
        registerReceiver(mBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(mBroadcastReceiver);
    }
}