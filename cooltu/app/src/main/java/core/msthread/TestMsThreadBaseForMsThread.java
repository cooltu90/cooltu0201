package core.msthread;

public class TestMsThreadBaseForMsThread implements TestMsThreadMsThreadInterface {

    protected TestMsThreadMsThread testMsThreadMsThread;

    public void start() {
        testMsThreadMsThread = TestMsThreadMsThread.obtain().dealer(this);
        testMsThreadMsThread.start();
    }

    @Override
    public void toast() {
    }

    protected boolean sendMessageForToast(long delayMillis) {
        if (testMsThreadMsThread != null) {
            return testMsThreadMsThread.sendMessageForToast(delayMillis);
        }
        return true;
    }
    @Override
    public void toast(java.lang.String msg) {
    }

    protected boolean sendMessageForToast(java.lang.String msg) {
        if (testMsThreadMsThread != null) {
            return testMsThreadMsThread.sendMessageForToast(msg);
        }
        return true;
    }
    @Override
    public void toast(int age) {
    }

    protected boolean sendMessageForToast(int age) {
        if (testMsThreadMsThread != null) {
            return testMsThreadMsThread.sendMessageForToast(age);
        }
        return true;
    }


    protected void stopMsThread() {
        if (testMsThreadMsThread != null)
            testMsThreadMsThread.stop();
        testMsThreadMsThread = null;
    }

}
