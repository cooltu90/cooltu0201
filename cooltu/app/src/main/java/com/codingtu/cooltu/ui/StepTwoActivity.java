package com.codingtu.cooltu.ui;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.thread.OnceThread;
import com.codingtu.cooltu.lib4j.zip.Zip;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;

import core.actbase.StepTwoActivityBase;
import core.actres.StepTwoActivityRes;

@To(StepTwoActivityRes.class)
@ActBase(layout = R.layout.activity_step_two)
public class StepTwoActivity extends StepTwoActivityBase {

    @Override
    public void onCreateComplete() {
        super.onCreateComplete();

        OnceThread.sub(new Runnable() {
            @Override
            public void run() {
                Zip.src("/storage/emulated/0/LdarData/建档/默认空间/csqy_测试企业/任务2")
                        .desc("/storage/emulated/0/LdarData/建档/默认空间/csqy_测试企业/任务2.zip")
                        .zip();
            }
        }).main(new OnceThread.MainRunnable() {
            @Override
            public void run(Throwable throwable) {
                Logs.i("finish");
            }
        }).start();
    }
}

