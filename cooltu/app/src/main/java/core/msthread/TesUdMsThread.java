package core.msthread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.codingtu.cooltu.lib4a.msthread.CoreMultiMsThread;

public class TesUdMsThread extends CoreMultiMsThread {

    ///////////////////////////////////////////////////////
    //
    // 创建方法
    //
    ///////////////////////////////////////////////////////
    private Handler mainHandler;
    private Handler subHandler0;

    public void start() {
        createMainHandler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                createSubHandler0();
            }
        }).start();

    }

    private void createMainHandler() {
        mainHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                handleMessageInMain(msg);
            }
        };
    }

    private void createSubHandler0() {
        Looper.prepare();
        subHandler0 = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                handleMessageInThread0(msg);
            }
        };
        sendMessage(subHandler0, subThread0StartType(), 0l);
        Looper.loop();
    }

    public void stop() {
        subHandler0.getLooper().quitSafely();

    }
    ///////////////////////////////////////////////////////
    //
    // 初始化方法
    //
    ///////////////////////////////////////////////////////
    private TesUdMsThreadInterface dealer;

    public static TesUdMsThread obtain() {
        return new TesUdMsThread();
    }

    public TesUdMsThread dealer(TesUdMsThreadInterface dealer) {
        this.dealer = dealer;
        return this;
    }

    private int type(TesUdMsThreadType type) {
        return type.ordinal();
    }

    protected boolean isSubThread0() {
        return Thread.currentThread() == subHandler0.getLooper().getThread();
    }


    ///////////////////////////////////////////////////////
    //
    // 主线程的消息处理
    //
    ///////////////////////////////////////////////////////
    private void handleMessageInMain(Message msg) {
        if (msg.what == type(TesUdMsThreadType.TOAST_0)) {
            dealer.toast((java.lang.String) msg.obj);
            return;
        }

    }

    public boolean sendMessageForToast(java.lang.String msg) {
        if (!isMainThread()) {
            sendMessage(mainHandler, type(TesUdMsThreadType.TOAST_0), 0l, msg);
            return true;
        }
        return false;
    }


    ///////////////////////////////////////////////////////
    //
    // 线程0的消息处理
    //
    ///////////////////////////////////////////////////////
    private int subThread0StartType() {
        return type(TesUdMsThreadType.SUB_START_0);
    }

    private void handleMessageInThread0(Message msg) {
        if (msg.what == type(TesUdMsThreadType.SUB_START_0)) {
            dealer.subStart();
            return;
        }
    }

    public boolean sendMessageForSubStart() {
        if (!isSubThread0()) {
            sendMessage(subHandler0, type(TesUdMsThreadType.SUB_START_0), 0l);
            return true;
        }
        return false;
    }


}

