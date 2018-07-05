package com.example.admin.eventbus.sticky;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.admin.eventbus.MainActivity;
import com.example.admin.eventbus.R;

import org.greenrobot.eventbus.EventBus;

public class StickyActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * eventBus 的粘性事件----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */
    private Button btn_Send;
    private EditText edt_Show;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky);
        //初始化控件
        initView();
    }

    private void initView() {
        btn_Send = findViewById(R.id.btn_Send);
        btn_Send.setOnClickListener(this);
        edt_Show = findViewById(R.id.edt_Show);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Send:
                //获得输入的值
                String content = edt_Show.getText().toString();
                //通过黏性事件将值传到MainActivity窗体中
                EventBus.getDefault().postSticky(new MessageEventSticky(content));
                Intent intent = new Intent(this, MainStickyActivity.class);
                startActivity(intent);
                break;
        }
    }
}
