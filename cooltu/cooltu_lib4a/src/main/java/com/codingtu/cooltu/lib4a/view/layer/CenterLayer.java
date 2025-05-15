package com.codingtu.cooltu.lib4a.view.layer;

import android.content.Context;
import android.util.AttributeSet;

public class CenterLayer extends Layer {
    public CenterLayer(Context context) {
        super(context);
    }

    public CenterLayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CenterLayer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CenterLayer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayoutView() {
        int h = getMeasuredHeight();
        int w = getMeasuredWidth();
        int dialogW = dialogView.getMeasuredWidth();
        int dialogH = dialogView.getMeasuredHeight();
        int top = (h - dialogH) / 2;
        int left = (w - dialogW) / 2;
        dialogView.layout(left, top, left + dialogW, top + dialogH);
    }
}
