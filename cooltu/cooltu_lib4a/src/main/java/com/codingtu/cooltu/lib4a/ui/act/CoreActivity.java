package com.codingtu.cooltu.lib4a.ui.act;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.codingtu.cooltu.lib4a.uicore.CoreUiBase;
import com.codingtu.cooltu.lib4a.uicore.CoreActInterface;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;
import com.codingtu.cooltu.lib4a.tools.ActTool;

public class CoreActivity extends AppCompatActivity implements CoreActInterface {

    protected CoreUiBase base;

    @Override
    public CoreUiBase getBase() {
        return this.base;
    }

    @Override
    public Activity getAct() {
        return this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        base = new CoreUiBase();
        base.onCreate(this, this);
    }

    @Override
    public void onCreateComplete() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getBase().onDestroy(this);
    }


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        getBase().setContentView(this);
    }

    @Override
    public void onViewInitComplete() {

    }

    @Override
    public void forbidKeyBack() {
        getBase().forbidKeyBack();
    }

    /********************************
     *
     * 对finish方法做扩展
     *
     ********************************/
    @Override
    public void finish() {
        getBase().finish(this);
    }

    protected void finishToNewPage() {
        getBase().finishToNewPage(this);
    }

    //finish之前调用
    @Override
    public void beforeFinish() {

    }

    @Override
    public void superFinish() {
        super.finish();
    }

    //设置finish动画
    @Override
    public void setFinishAnimation() {
        ActTool.actRightOut(this);
    }

    //finish之后调用
    @Override
    public void afterFinish() {

    }

    @Override
    public void initStatusBar(Activity act) {
        getBase().initStatusBar(act);
    }

    /************************************************
     *
     * setResultOk
     *
     ************************************************/
    @Override
    public void setResultOk() {
        getBase().setResultOk(this);
    }

    @Override
    public void setResultOk(Intent data) {
        getBase().setResultOk(this, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        getBase().onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void back(int requestCode, String[] permissions, int[] grantResults) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getBase().onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean b = getBase().onKeyDown(keyCode, event);
        return b ? b : super.onKeyDown(keyCode, event);
    }

    @Override
    public void toast(String str) {
        getBase().toast(str);
    }

    @Override
    public void add(OnDestroy onDestroy) {
        getBase().add(onDestroy);
    }

    @Override
    public void destroyAll() {
        getBase().destroyAll();
    }

}
