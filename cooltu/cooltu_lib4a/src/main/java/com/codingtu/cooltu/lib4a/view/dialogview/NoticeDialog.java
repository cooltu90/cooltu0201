package com.codingtu.cooltu.lib4a.view.dialogview;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.tools.InflateTool;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.lib4a.view.layer.Layer;
import com.codingtu.cooltu.lib4a.view.layer.event.OnHiddenFinishedCallBack;
import com.codingtu.cooltu.lib4j.destory.Destroys;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;

public final class NoticeDialog implements OnDestroy, View.OnClickListener {

    private Activity act;
    private Layer layer;
    private int layout;
    private View inflate;
    private View contentTv;
    private View.OnClickListener onClickListener;
    private Object data;

    public NoticeDialog(Activity act) {
        this.act = act;
    }


    @Override
    public void destroy() {
        contentTv = null;
        inflate = null;
        ViewTool.removeFromAct(act, layer);
        if (layer != null)
            layer.destroy();
        layer = null;
        act = null;
        onClickListener = null;
        data = null;
    }

    public NoticeDialog setLayout(int layout) {
        this.layout = layout;
        return this;
    }

    public NoticeDialog destroys(Destroys destroys) {
        if (destroys != null)
            destroys.add(this);
        return this;
    }

    public NoticeDialog onClick(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    public NoticeDialog data(Object data) {
        this.data = data;
        return this;
    }

    public Object obtainData() {
        return this.data;
    }

    public NoticeDialog build() {
        layer = new Layer(act);
        layer.setHiddenWhenBackClick(false);
        layer.setHiddenWhenShadowClick(false);
        ViewTool.addToAct(act, layer);
        ViewTool.gone(layer);
        inflate = InflateTool.inflate(act, layout);
        layer.addView(inflate, ViewTool.WRAP_CONTENT, ViewTool.WRAP_CONTENT);
        contentTv = inflate.findViewById(R.id.dialogContentTv);
        inflate.findViewById(R.id.noticeDialogYesBt).setOnClickListener(onClickListener == null ? this : onClickListener);

        ViewTool.inRelativeCenter(inflate);
        return this;
    }

    public NoticeDialog setContent(String text) {
        ViewTool.setText(contentTv, text);
        return this;
    }

    public String getContent() {
        return ((TextView) contentTv).getText().toString();
    }

    public void show() {
        layer.show();
    }

    public void hidden(OnHiddenFinishedCallBack callBack) {
        layer.hidden(callBack);
    }

    public void hidden() {
        hidden(null);
    }

    @Override
    public void onClick(View v) {
        hidden();
    }

}
