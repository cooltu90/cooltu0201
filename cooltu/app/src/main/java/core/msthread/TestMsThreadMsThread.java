package core.msthread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.codingtu.cooltu.lib4a.msthread.CoreMultiMsThread;

public class TestMsThreadMsThread extends CoreMultiMsThread {

    ///////////////////////////////////////////////////////
    //
    // 创建方法
    //
    ///////////////////////////////////////////////////////
    private Handler mainHandler;

    public void start() {
        createMainHandler();

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

    public void stop() {

    }
    ///////////////////////////////////////////////////////
    //
    // 初始化方法
    //
    ///////////////////////////////////////////////////////
    private TestMsThreadMsThreadInterface dealer;

    public static TestMsThreadMsThread obtain() {
        return new TestMsThreadMsThread();
    }

    public TestMsThreadMsThread dealer(TestMsThreadMsThreadInterface dealer) {
        this.dealer = dealer;
        return this;
    }

    private int type(TestMsThreadMsThreadType type) {
        return type.ordinal();
    }


    ///////////////////////////////////////////////////////
    //
    // 主线程的消息处理
    //
    ///////////////////////////////////////////////////////
    private void handleMessageInMain(Message msg) {
        if (msg.what == type(TestMsThreadMsThreadType.TOAST_1)) {
            dealer.toast((java.lang.String) msg.obj);
            return;
        }
        if (msg.what == type(TestMsThreadMsThreadType.TOAST_0)) {
            dealer.toast();
            return;
        }
        if (msg.what == type(TestMsThreadMsThreadType.TOAST_2)) {
            dealer.toast((int) msg.obj);
            return;
        }

    }

    public boolean sendMessageForToast(java.lang.String msg) {
        if (!isMainThread()) {
            sendMessage(mainHandler, type(TestMsThreadMsThreadType.TOAST_1), 300l, msg);
            return true;
        }
        try {
            Thread.sleep(300l);
        } catch (Exception e) {
        }
        return false;
    }

    public boolean sendMessageForToast(long delayMillis) {
        if (!isMainThread()) {
            sendMessage(mainHandler, type(TestMsThreadMsThreadType.TOAST_0), delayMillis);
            return true;
        }
        return false;
    }

    public boolean sendMessageForToast(int age) {
        if (!isMainThread()) {
            sendMessage(mainHandler, type(TestMsThreadMsThreadType.TOAST_2), 0l, age);
            return true;
        }
        return false;
    }


}

