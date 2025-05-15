package com.codingtu.cooltu.lib4a.function;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import com.codingtu.cooltu.lib4j.function.OnStart;

public abstract class OnStartInUiThread implements OnStart {
    private final Handler handler;

    public OnStartInUiThread() {
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                start();
            }
        };
    }

    public abstract void start();

    @Override
    public final void onStart() {
        Message msg = Message.obtain();
        handler.sendMessage(msg);
    }
}
