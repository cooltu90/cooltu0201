package com.codingtu.cooltu.ui.base;

import android.Manifest;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.Permission;

import core.actbase.BaseWelcomeActivityBase;
import core.actres.BaseWelcomeActivityRes;
import core.tools.Permissions;

@To(BaseWelcomeActivityRes.class)
@ActBase(layout = R.layout.activity_welcome, base = CoreWelcomeActivity.class)
public class BaseWelcomeActivity extends BaseWelcomeActivityBase {

    @ClickView({R.id.tv1, R.id.tv3, R.id.tv})
    public void tv3Click() {
        Logs.i("ss");
        Permissions.checkInBaseWelcomeActivity(getAct());
    }

    @Permission({
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    })
    public void check(boolean isAllow) {
        Logs.i("isAllow:" + isAllow);
    }

}
