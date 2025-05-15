package com.codingtu.cooltu.lib4a.view.dialogview;

import android.app.Activity;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.tools.InflateTool;
import com.codingtu.cooltu.lib4a.tools.Margins;
import com.codingtu.cooltu.lib4a.tools.MobileTool;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.lib4a.view.layer.Layer;
import com.codingtu.cooltu.lib4a.view.layer.event.OnHiddenFinishedCallBack;
import com.codingtu.cooltu.lib4j.destory.Destroys;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;
import com.codingtu.cooltu.lib4j.tools.StringTool;

public final class EditDialog implements OnDestroy, View.OnClickListener {

    private Activity act;

    private String title;
    private String hint;
    private Integer inputType;
    private int layout;
    private boolean isStopAnimation;
    private Yes yes;
    private EdTextWatcher textWatcher;


    private Layer layer;
    private View inflate;
    private EditText et;
    private View noBt;
    private View yesBt;
    private Object obj;
    private View titleTv;

    public EditDialog destroys(Destroys destroys) {
        if (destroys != null) {
            destroys.add(this);
        }
        return this;
    }


    public EditDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public EditDialog setHint(String hint) {
        this.hint = hint;
        return this;
    }

    public EditDialog setInputType(Integer inputType) {
        this.inputType = inputType;
        return this;
    }

    public EditDialog setLayout(int layout) {
        this.layout = layout;
        return this;
    }


    public EditDialog stopAnimation() {
        isStopAnimation = true;
        return this;
    }

    public EditDialog setYes(Yes yes) {
        this.yes = yes;
        return this;
    }

    public EditDialog setTextWatcher(EdTextWatcher textWatcher) {
        this.textWatcher = textWatcher;
        return this;
    }


    public EditDialog(Activity act) {
        this.act = act;
    }

    public void setObject(Object obj) {
        this.obj = obj;
    }

    public void setEditText(String text) {
        ViewTool.setEditTextAndSelection(et, StringTool.toString(text));
    }

    public void setTitleTv(String title) {
        ViewTool.setText(titleTv, title);
    }

    public EditDialog build() {
        this.layer = new Layer(act);
        this.layer.setHiddenWhenBackClick(false);
        this.layer.setHiddenWhenShadowClick(false);
        ViewTool.addToAct(act, this.layer);
        ViewTool.gone(this.layer);
        this.inflate = InflateTool.inflate(act, layout);
        this.layer.addView(this.inflate, ViewTool.WRAP_CONTENT, ViewTool.WRAP_CONTENT);
        if (isStopAnimation)
            this.layer.stopAnimation();

        this.titleTv = this.inflate.findViewById(R.id.editDialogTitleTv);
        ViewTool.setText(titleTv, title);

        this.et = this.inflate.findViewById(R.id.editDialogTitleEt);
        this.et.setHint(hint);
        this.et.setImeOptions(EditorInfo.IME_ACTION_DONE);
        if (textWatcher != null) {
            textWatcher.setEditText(this.et);
            this.et.addTextChangedListener(textWatcher);
        }
        this.et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //处理搜索事件
                    if (yes == null) {
                        return false;
                    }
                    String text = et.getText().toString();
                    if (yes.yes(text, obj)) {
                        layer.hidden();
                    }
                    return true;
                }
                return false;
            }
        });

        if (inputType != null) {
            this.et.setInputType(inputType);
        }

        this.noBt = this.inflate.findViewById(R.id.editDialogNoBt);
        this.yesBt = this.inflate.findViewById(R.id.editDialogYesBt);
        this.yesBt.setOnClickListener(this);
        this.noBt.setOnClickListener(this);
        return this;
    }

    @Override
    public void destroy() {
        if (yesBt != null)
            yesBt.setOnClickListener(null);
        if (noBt != null)
            noBt.setOnClickListener(null);
        inflate = null;
        noBt = null;
        yesBt = null;
        et = null;
        yes = null;
        textWatcher = null;
        obj = null;
        ViewTool.removeFromAct(act, layer);
        if (layer != null)
            layer.destroy();
        layer = null;
        act = null;
        titleTv = null;
    }

    private Integer restHeight;

    public EditDialog show() {
        if (restHeight == null) {
            layer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    int windowVisibleDisplayH = MobileTool.getWindowVisibleDisplayH(act);
                    if (windowVisibleDisplayH < (MobileTool.getScreenHight() * 0.66)) {
                        //获取到了剩余高度
                        restHeight = windowVisibleDisplayH;
                        int dialogH = inflate.getHeight();
                        ViewTool.visible(layer);
                        if (dialogH != 0) {
                            layer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            int top = (restHeight - dialogH) / 2;
                            Margins.t(inflate, top);
                            layer.show();
                        }
                    }
                }
            });
        } else {
            layer.show();
        }
        ViewTool.inputShow(et);
        et.requestFocus();
        return this;
    }

    @Override
    public void onClick(View v) {
        int vId = v.getId();
        if (vId == R.id.editDialogYesBt) {
            clickYesBt(v);
            return;
        }

        if (vId == R.id.editDialogNoBt) {
            clickNoBt(v);
            return;
        }
    }

    private void clickNoBt(View v) {
        layer.hidden(getOnHiddenFinishedCallBack());
    }

    private void clickYesBt(View v) {
        if (yes == null) {
            return;
        }
        String text = et.getText().toString();
        if (yes.yes(text, obj)) {
            layer.hidden(getOnHiddenFinishedCallBack());
        }
    }

    private OnHiddenFinishedCallBack getOnHiddenFinishedCallBack() {
        return new OnHiddenFinishedCallBack() {
            @Override
            public void onHiddenFinished() {
                ViewTool.inputHidden(EditDialog.this.et);
                EditDialog.this.obj = null;
            }
        };
    }

    public static interface Yes {
        public boolean yes(String text, Object obj);
    }

    public static interface EdTextWatcher extends TextWatcher {
        public void setEditText(EditText et);
    }
}
