package com.example.admin.eventbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.eventbus.sticky.MessageEventSticky;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // 参考学习：https://blog.csdn.net/u012317510/article/details/78935720
    ///          https://blog.csdn.net/dubuwucool/article/details/53524964
    private TextView mTvMessage;
    public static final String TAG = "TEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initContentView();

        EventBus.getDefault().register(this);//注册
    }

    private void initContentView() {
        Button btnStart = findViewById(R.id.btn_main_start_activity);
        mTvMessage = findViewById(R.id.tv_main_message);
        btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_main_start_activity) {
            SecondActivity.start(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventMain(MessageEvent event) {
        Log.i(TAG, "2:--onMessageEventMain(), current thread is " + Thread.currentThread().getName() + ":" + event.getMessage());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventMain2(MessageEvents event) {
        Log.i(TAG, "2:--onMessageEventMain(), current thread is " + Thread.currentThread().getName() + ":" + event.getMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }





}
