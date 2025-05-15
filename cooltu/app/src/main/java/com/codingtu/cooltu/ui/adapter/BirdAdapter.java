package com.codingtu.cooltu.ui.adapter;

import androidx.annotation.NonNull;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.lib4a.ui.adapter.CoreListAdapter;
import com.codingtu.cooltu.processor.annotation.ui.VH;

import core.vh.BirdVH;

@VH(layout = R.layout.item_bird, vh = BirdVH.class)
public class BirdAdapter extends CoreListAdapter<BirdVH, String> {
    @Override
    protected void onBindVH(@NonNull BirdVH vh, int position, String e) {

    }
}
