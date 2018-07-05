package com.example.admin.eventbus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.admin.eventbus.sticky.MessageEventSticky;
import com.example.admin.eventbus.sticky.StickyActivity;

import org.greenrobot.eventbus.EventBus;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    public static void start(Context context) {
        Intent intent = new Intent(context, SecondActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initContentView();
    }

    private void initContentView() {
        findViewById(R.id.btn_second_post_event).setOnClickListener(this);
        findViewById(R.id.btn_second_sticky2_event).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_second_post_event) {
            new Thread("posting") {
                @Override
                public void run() {
                    EventBus.getDefault().post(new MessageEvent("My EventBus"));
                }
            }.start();
        } else if (v.getId() == R.id.btn_second_sticky2_event) {
            startActivity(new Intent(this, StickyActivity.class));
        }
    }
}
