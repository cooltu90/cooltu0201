package com.codingtu.cooltu.ud;

import com.codingtu.cooltu.processor.annotation.msthread.MainThread;
import com.codingtu.cooltu.processor.annotation.msthread.MsThread;

import core.msthread.TestMsThreadBaseForMsThread;

@MsThread
public class TestMsThread extends TestMsThreadBaseForMsThread {

    @MainThread(isDelay = true)
    public void toast() {
        if (sendMessageForToast(100)) {
            return;
        }
    }

    @MainThread(isDelay = true, defaultDelayMillis = 300)
    public void toast(String msg) {
        if (sendMessageForToast(msg)) {
            return;
        }
    }

    @MainThread
    public void toast(int age){

    }

}
