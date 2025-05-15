package core.actbase;

import android.view.View;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class SubThreadActivityBase extends com.codingtu.cooltu.ui.base.BaseActivity implements View.OnClickListener, View.OnLongClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI, core.msthread.SubThreadActivityMsThreadInterface
{
    protected core.msthread.SubThreadActivityMsThread subThreadActivityMsThread;

    public String baseClassName = "SubThreadActivityBase";

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.codingtu.cooltu.R.layout.activity_sub_thread);








        String nowBaseClassName = getClass().getSimpleName() + "Base";
        if (nowBaseClassName.equals(baseClassName)) {
            onCreateComplete();
        }
    }

    @Override
    public void onCreateComplete() {
        super.onCreateComplete();



        subThreadActivityMsThread = core.msthread.SubThreadActivityMsThread.obtain().dealer(this);
        subThreadActivityMsThread.start();


    }


    @Override
    public void onClick(View v) {

        try {
            switch (v.getId()) {

            }
        } catch (Exception e) {
            com.codingtu.cooltu.lib4a.log.Logs.e(e);
            if (!(e instanceof com.codingtu.cooltu.lib4a.exception.NotToastException)) {
                toast(e.getMessage());
            }
        }
    }



    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {

        }

        return false;

    }


    @Override
    public void accept(String code, Result<ResponseBody> result, com.codingtu.cooltu.lib4a.net.bean.CoreSendParams params, List objs) {



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == android.app.Activity.RESULT_OK) {

        }
    }

    @Override
    public void back(int requestCode, String[] permissions, int[] grantResults) {
        super.back(requestCode, permissions, grantResults);

    }








    /**************************************************
     * MsThread
     **************************************************/
    @Override
    public void dealDataStart0() {
    }

    protected boolean sendMessageForDealDataStart0() {
        if (subThreadActivityMsThread != null) {
            return subThreadActivityMsThread.sendMessageForDealDataStart0();
        }
        return true;
    }
    @Override
    public void dealDataStart1() {
    }

    protected boolean sendMessageForDealDataStart1() {
        if (subThreadActivityMsThread != null) {
            return subThreadActivityMsThread.sendMessageForDealDataStart1();
        }
        return true;
    }
    @Override
    public void method0() {
    }

    protected boolean sendMessageForMethod0() {
        if (subThreadActivityMsThread != null) {
            return subThreadActivityMsThread.sendMessageForMethod0();
        }
        return true;
    }
    @Override
    public void method1(java.lang.String name) {
    }

    protected boolean sendMessageForMethod1(java.lang.String name) {
        if (subThreadActivityMsThread != null) {
            return subThreadActivityMsThread.sendMessageForMethod1(name);
        }
        return true;
    }
    @Override
    public void dealToast(java.lang.String str) {
    }

    protected boolean sendMessageForDealToast(java.lang.String str) {
        if (subThreadActivityMsThread != null) {
            return subThreadActivityMsThread.sendMessageForDealToast(str);
        }
        return true;
    }
    @Override
    public void dealToast(java.lang.String str, int age) {
    }

    protected boolean sendMessageForDealToast(java.lang.String str, int age) {
        if (subThreadActivityMsThread != null) {
            return subThreadActivityMsThread.sendMessageForDealToast(str, age);
        }
        return true;
    }
    protected void stopMsThread() {
        if (subThreadActivityMsThread != null)
            subThreadActivityMsThread.stop();
        subThreadActivityMsThread = null;
    }


}

