package com.codingtu.cooltu.lib4a.function;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import com.codingtu.cooltu.lib4j.function.OnError;
import com.codingtu.cooltu.lib4j.function.OnStart;

public abstract class OnErrorInUiThread implements OnError {
    private final Handler handler;

    public OnErrorInUiThread() {
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Throwable throwable = (Throwable) msg.obj;
                error(throwable);
            }
        };
    }

    public abstract void error(Throwable throwable);

    @Override
    public final void onError(Throwable throwable) {
        Message msg = Message.obtain();
        msg.obj = throwable;
        handler.sendMessage(msg);
    }
}
