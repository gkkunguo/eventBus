package com.example.admin.eventbus.sticky;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.eventbus.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainStickyActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_ok;
    private TextView txtShow;
    boolean isTrue = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_main);
        //初始化控件
        initView();
    }

    private void initView() {
        btn_ok = findViewById(R.id.btn_ok);
        txtShow = findViewById(R.id.txtShow);
        btn_ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                //加一个标识,进行判断,让用户不会因多次点击按钮而注册,从而导致崩溃
                if (isTrue) {
                    //你一旦注册eventBus就会接收消息
                    EventBus.getDefault().register(this);
                    isTrue = false;
                }
                break;
        }
    }

    //接收消息  添加注解 如果是黏性事件 将stick设置为true
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receiveMessage(MessageEventSticky eventBusStickMessage) {
        txtShow.setText(eventBusStickMessage.getMessage());
    }

    //在onDestory()方法中取消订阅：防止内存溢出
    @Override
    protected void onDestroy() {
        //移除所有黏性事件
        EventBus.getDefault().removeAllStickyEvents();
        //销毁EventBus
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
