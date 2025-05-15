package com.codingtu.cooltu.ui;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;

import core.actbase.FromActivityBase;
import core.actres.FromActivityRes;

@To(FromActivityRes.class)
@ActBase(layout = R.layout.activity_from, base = FromBaseActivity.class)
public class FromActivity extends FromActivityBase {
}

