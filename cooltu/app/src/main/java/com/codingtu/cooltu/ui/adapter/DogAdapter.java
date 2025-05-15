package com.codingtu.cooltu.ui.adapter;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.lib4a.ui.adapter.CoreMoreListAdapter;
import com.codingtu.cooltu.processor.annotation.ui.VH;

import core.vh.DogVH;

@VH(layout = R.layout.item_dog, vh = DogVH.class)
public abstract class DogAdapter extends CoreMoreListAdapter<DogVH, String> {
    @Override
    protected void onBindVH(DogVH vh, int position, String s) {

    }
}
