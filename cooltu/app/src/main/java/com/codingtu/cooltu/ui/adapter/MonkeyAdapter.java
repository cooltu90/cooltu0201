package com.codingtu.cooltu.ui.adapter;

import androidx.annotation.NonNull;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.lib4a.ui.adapter.CoreListAdapter;
import com.codingtu.cooltu.processor.annotation.ui.VH;

import core.vh.MonkeyVH;

@VH(layout = R.layout.item_monkey, vh = MonkeyVH.class)
public class MonkeyAdapter extends CoreListAdapter<MonkeyVH, String> {
    @Override
    protected void onBindVH(@NonNull MonkeyVH vh, int position, String e) {

    }
}
