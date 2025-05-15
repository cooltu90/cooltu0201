package com.codingtu.cooltu.lib4a.function;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import com.codingtu.cooltu.lib4j.data.progress.Progress;
import com.codingtu.cooltu.lib4j.function.OnProgress;

public abstract class OnProgressInUiThread implements OnProgress {
    private final Handler handler;

    public OnProgressInUiThread() {
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Progress progress = (Progress) msg.obj;
                progress(progress.totalLen, progress.currentLen);
            }
        };
    }

    public abstract void progress(long totalLen, long currentLen);

    @Override
    public final void onProgress(long totalLen, long zipedLen) {
        Message msg = Message.obtain();
        msg.obj = new Progress(totalLen, zipedLen);
        handler.sendMessage(msg);
    }
}
