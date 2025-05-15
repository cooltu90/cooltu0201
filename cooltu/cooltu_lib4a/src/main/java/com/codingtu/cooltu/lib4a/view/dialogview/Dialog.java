package com.codingtu.cooltu.lib4a.view.dialogview;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.tools.InflateTool;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.lib4a.view.layer.Layer;
import com.codingtu.cooltu.lib4j.destory.Destroys;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;

public final class Dialog implements View.OnClickListener, OnDestroy {

    private Activity act;
    private String title;
    private String content;
    private Layer layer;
    private View rightBt;
    private View leftBt;
    private View inflate;
    private Object obj;
    private OnBtClick onBtClick;
    private int layout;
    private View contentTv;
    private String leftBtText;
    private String rightBtText;
    private View titleTv;


    public Dialog(Activity act) {
        this.act = act;
    }

    public Dialog destroys(Destroys destroys) {
        if (destroys == null) {
            destroys.add(this);
        }
        return this;
    }

    public Dialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public Dialog setContent(String content) {
        this.content = content;
        return this;
    }

    public Dialog setLayout(int layout) {
        this.layout = layout;
        return this;
    }

    public Dialog setOnBtClick(OnBtClick onBtClick) {
        this.onBtClick = onBtClick;
        return this;
    }

    public Dialog setLeftBtText(String text) {
        this.leftBtText = text;
        return this;
    }

    public Dialog setRighBtText(String text) {
        this.rightBtText = text;
        return this;
    }

    public Dialog build() {
        layer = new Layer(act);
        ViewTool.addToAct(act, layer);
        inflate = InflateTool.inflate(act, layout);
        layer.addView(inflate, ViewTool.WRAP_CONTENT, ViewTool.WRAP_CONTENT);
        ViewTool.gone(layer);

        titleTv = inflate.findViewById(R.id.dialogTitleTv);
        ViewTool.setText(titleTv, title);
        contentTv = inflate.findViewById(R.id.dialogContentTv);
        ViewTool.setText(contentTv, content);

        leftBt = inflate.findViewById(R.id.dialogLeftBt);
        rightBt = inflate.findViewById(R.id.dialogRightBt);
        ViewTool.setText(leftBt, leftBtText);
        ViewTool.setText(rightBt, rightBtText);
        leftBt.setOnClickListener(this);
        rightBt.setOnClickListener(this);
        ViewTool.inRelativeCenter(inflate);
        return this;
    }

    public void updateContent(String content) {
        ViewTool.setText(contentTv, content);
    }

    public void updateTitle(String title) {
        ViewTool.setText(titleTv, title);
    }

    @Override
    public void onClick(View v) {
        int vId = v.getId();
        if (vId == R.id.dialogRightBt) {
            clickRightBt(v);
            obj = null;
            return;
        }

        if (vId == R.id.dialogLeftBt) {
            clickLeftBt(v);
            obj = null;
            return;
        }
    }

    private void clickRightBt(View v) {
        layer.hidden();
        if (onBtClick != null) {
            onBtClick.onRightClick(obj);
        }
    }

    private void clickLeftBt(View v) {
        layer.hidden();
        if (onBtClick != null) {
            onBtClick.onLeftClick(obj);
        }
    }

    public Dialog setObject(Object obj) {
        this.obj = obj;
        return this;
    }

    @Override
    public void destroy() {
        if (rightBt != null) {
            rightBt.setOnClickListener(null);
        }
        if (leftBt != null) {
            leftBt.setOnClickListener(null);
        }
        rightBt = null;
        leftBt = null;
        inflate = null;
        onBtClick = null;
        obj = null;
        contentTv = null;
        titleTv = null;
        ViewTool.removeFromAct(act, layer);
        if (layer != null) {
            layer.destroy();
        }
        act = null;
        layer = null;
    }

    public void show() {
        layer.show();
    }

    public static interface OnBtClick {
        public void onLeftClick(Object obj);

        public void onRightClick(Object obj);
    }
}
