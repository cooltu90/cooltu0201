package com.codingtu.cooltu.ui;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.lib4a.view.layer.event.OnShowFinishedCallBack;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.es.impl.StringEs;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;

import core.actbase.StepOneActivityBase;
import core.actres.StepOneActivityRes;

@To(StepOneActivityRes.class)
@ActBase(layout = R.layout.activity_step_one)
public class StepOneActivity extends StepOneActivityBase {


    @Override
    public void onCreateComplete() {
        super.onCreateComplete();
//        StringEs stringEs = Es.strs("cat1", "cat2", "cat3");
//        catAdapter.updateItems(stringEs);
        catAdapter.updateItems("dog1", "dog2", "dog3");
    }

    @Override
    protected void dogAdapterLoadMore(int page) {
    }

}
