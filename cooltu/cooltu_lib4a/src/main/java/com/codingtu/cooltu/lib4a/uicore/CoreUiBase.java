package com.codingtu.cooltu.lib4a.uicore;

import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.codingtu.cooltu.lib4a.permission.PermissionBack;
import com.codingtu.cooltu.lib4a.tools.ScreenAdaptationTool;
import com.codingtu.cooltu.lib4a.tools.StatusBarTool;
import com.codingtu.cooltu.lib4a.tools.ToastTool;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;
import com.codingtu.cooltu.lib4j.es.Es;

import java.util.ArrayList;
import java.util.List;

public class CoreUiBase {

    /**************************************************
     *
     * Permission
     *
     **************************************************/
    protected List<PermissionBack> permissionBacks;

    public List<PermissionBack> getPermissionBacks() {
        if (permissionBacks == null)
            permissionBacks = new ArrayList<PermissionBack>();
        return permissionBacks;
    }

    public void addPermissionBack(PermissionBack back) {
        getPermissionBacks().add(back);

    }

    public void removePermissionBack(PermissionBack back) {
        getPermissionBacks().remove(back);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        Es.es(getPermissionBacks()).ls(new Es.EachEs<PermissionBack>() {
            @Override
            public boolean each(int position, PermissionBack back) {
                back.back(requestCode, permissions, grantResults);
                return false;
            }
        });
    }

    /**************************************************
     *
     * OnActBack
     *
     **************************************************/
    protected List<OnActBack> onActBacks;

    public List<OnActBack> getOnActBacks() {
        if (onActBacks == null)
            onActBacks = new ArrayList<OnActBack>();
        return onActBacks;
    }

    public void addOnActBack(OnActBack onActBack) {
        getOnActBacks().add(onActBack);
    }

    public void removeOnActBack(OnActBack onActBack) {
        getOnActBacks().remove(onActBack);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Es.es(getOnActBacks()).ls(new Es.EachEs<OnActBack>() {
            @Override
            public boolean each(int position, OnActBack back) {
                back.onActivityResult(requestCode, resultCode, data);
                return false;
            }
        });
    }

    /**************************************************
     *
     * WhenKeyDown
     *
     **************************************************/
    protected List<WhenKeyDown> whenKeyDowns;

    protected List<WhenKeyDown> getWhenKeyDowns() {
        if (whenKeyDowns == null)
            whenKeyDowns = new ArrayList<WhenKeyDown>();
        return whenKeyDowns;
    }

    public void addWhenKeyDown(WhenKeyDown whenKeyDown) {
        getWhenKeyDowns().add(whenKeyDown);
    }

    public void removeWhenKeyDown(WhenKeyDown whenKeyDown) {
        getWhenKeyDowns().remove(whenKeyDown);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        final boolean[] b = {false};
        List<WhenKeyDown> whenKeyDowns = getWhenKeyDowns();
        Es.es(whenKeyDowns).ls(new Es.EachEs<WhenKeyDown>() {
            @Override
            public boolean each(int position, WhenKeyDown whenKeyDown) {
                if (whenKeyDown.onKeyDown(keyCode, event)) {
                    b[0] = true;
                }
                return false;
            }
        });
        return b[0];
    }

    /**************************************************
     *
     * OnDestroy
     *
     **************************************************/
    protected List<OnDestroy> onDestroys;

    public List<OnDestroy> getOnDestroys() {
        if (onDestroys == null)
            onDestroys = new ArrayList<OnDestroy>();
        return onDestroys;
    }

    public void add(OnDestroy onDestroy) {
        getOnDestroys().add(onDestroy);
    }

    public void destroyAll() {
        Es.es(getOnDestroys()).ls(new Es.EachEs<OnDestroy>() {
            @Override
            public boolean each(int position, OnDestroy onDestroy) {
                onDestroy.destroy();
                return false;
            }
        });
    }


    /**************************************************
     *
     *
     *
     **************************************************/
    public void toast(String str) {
        ToastTool.toast(str);
    }

    public void finish(CoreUiInterface coreUi) {
        coreUi.beforeFinish();
        coreUi.superFinish();
        coreUi.setFinishAnimation();
        coreUi.afterFinish();
    }

    public void finishToNewPage(CoreUiInterface coreUi) {
        coreUi.beforeFinish();
        coreUi.superFinish();
        coreUi.afterFinish();
    }

    public void setResultOk(CoreUiInterface coreUi) {
        coreUi.getAct().setResult(Activity.RESULT_OK);
    }

    public void setResultOk(CoreUiInterface coreUi, Intent data) {
        coreUi.getAct().setResult(Activity.RESULT_OK, data);
    }

    public void onCreate(CoreUiInterface coreUi, PermissionBack back) {
        addPermissionBack(back);
        ScreenAdaptationTool.setCustomDensity(coreUi.getAct());
        coreUi.initStatusBar(coreUi.getAct());
        ActivityManager.getInstance().add(coreUi.getAct());
    }

    public void initStatusBar(Activity act) {
        StatusBarTool.translucentAndDark(act);
    }

    public void onDestroy(CoreUiInterface coreUi) {
        destroyAll();
        ActivityManager.getInstance().remove(coreUi.getAct());
    }

    public void forbidKeyBack() {
        addWhenKeyDown(new WhenBackKeyDown() {
            @Override
            public boolean onBack(KeyEvent event) {
                return true;
            }
        });
    }

    private ViewGroup rootView;

    public void setContentView(CoreUiInterface coreUi) {
        rootView = ViewTool.getRootViewGroup(coreUi.getAct());
        rootView.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        coreUi.onViewInitComplete();
                        rootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
    }

}
