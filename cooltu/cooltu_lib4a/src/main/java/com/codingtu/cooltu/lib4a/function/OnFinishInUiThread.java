package com.codingtu.cooltu.lib4a.function;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import com.codingtu.cooltu.lib4j.function.OnFinish;

public abstract class OnFinishInUiThread<T> implements OnFinish<T> {

    private final Handler handler;

    public OnFinishInUiThread() {
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                finish((T) msg.obj);
            }
        };
    }

    public abstract void finish(T t);

    @Override
    public void onFinish(T t) {
        Message msg = Message.obtain();
        msg.obj = t;
        handler.sendMessage(msg);
    }

}
