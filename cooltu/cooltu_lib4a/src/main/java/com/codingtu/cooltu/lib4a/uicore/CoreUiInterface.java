package com.codingtu.cooltu.lib4a.uicore;

import android.app.Activity;
import android.content.Intent;

import com.codingtu.cooltu.lib4a.permission.PermissionBack;
import com.codingtu.cooltu.lib4j.destory.Destroys;

public interface CoreUiInterface extends Destroys, PermissionBack {

    CoreUiBase getBase();

    void toast(String msg);

    Activity getAct();

    void beforeFinish();

    void superFinish();

    void setFinishAnimation();

    void afterFinish();

    void initStatusBar(Activity act);

    void forbidKeyBack();

    void onViewInitComplete();

    void onCreateComplete();

    void setResultOk();

    void setResultOk(Intent data);
}
