package com.codingtu.cooltu.ui.adapter;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.lib4a.ui.adapter.CoreMoreListAdapter;
import com.codingtu.cooltu.processor.annotation.ui.VH;

import core.vh.DuckVH;

@VH(layout = R.layout.item_duck, vh = DuckVH.class)
public abstract class DuckAdapter extends CoreMoreListAdapter<DuckVH, String> {
    @Override
    protected void onBindVH(DuckVH vh, int position, String s) {

    }
}
