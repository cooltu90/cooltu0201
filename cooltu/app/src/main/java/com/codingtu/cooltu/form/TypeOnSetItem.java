package com.codingtu.cooltu.form;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.codingtu.cooltu.lib4a.view.combine.RadioGroup;

public class TypeOnSetItem implements RadioGroup.OnSetItem {
    @Override
    public void setSelected(View view) {
        ((TextView) view).setTextColor(Color.RED);
    }

    @Override
    public void setSelectno(View view) {
        ((TextView) view).setTextColor(Color.BLACK);
    }
}
