package com.codingtu.cooltu.ui.adapter;

import androidx.annotation.NonNull;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.lib4a.ui.adapter.CoreListAdapter;
import com.codingtu.cooltu.processor.annotation.ui.VH;

import core.vh.CatVH;

@VH(layout = R.layout.item_cat, vh = CatVH.class)
public class CatAdapter extends CoreListAdapter<CatVH, String> {
    @Override
    protected void onBindVH(@NonNull CatVH vh, int position, String e) {
        Logs.i("s:"+ e);
        ViewTool.setText(vh.tv, e);
    }
}
