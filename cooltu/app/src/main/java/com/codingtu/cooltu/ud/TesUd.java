package com.codingtu.cooltu.ud;


import com.codingtu.cooltu.processor.annotation.msthread.MainThread;
import com.codingtu.cooltu.processor.annotation.msthread.MsThread;
import com.codingtu.cooltu.processor.annotation.msthread.SubThread;
import com.codingtu.cooltu.test.TestUdBase;

import core.msthread.TesUdBaseForMsThread;

@MsThread(base = TestUdBase.class)
public class TesUd extends TesUdBaseForMsThread<TesUd> {

    @SubThread(isStart = true)
    public void subStart() {

    }

    @MainThread
    public void toast(String msg) {

    }

}
