package com.example.admin.eventbus;

public class MessageEvent {
    // 定义事件。事件可以是任意普通的JAVA对象
    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
