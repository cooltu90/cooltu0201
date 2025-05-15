package com.codingtu.cooltu.ui.adapter;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.lib4a.ui.adapter.CoreMoreListAdapter;
import com.codingtu.cooltu.processor.annotation.ui.VH;

import core.vh.HorseVH;

@VH(layout = R.layout.item_horse, vh = HorseVH.class)
public abstract class HorseAdapter extends CoreMoreListAdapter<HorseVH, String> {
    @Override
    protected void onBindVH(HorseVH vh, int position, String s) {

    }
}
