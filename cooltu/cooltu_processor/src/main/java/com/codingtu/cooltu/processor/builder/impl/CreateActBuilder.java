package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.processor.builder.base.CreateActBuilderBase;

import java.util.List;

public class CreateActBuilder extends CreateActBuilderBase {
    private final JavaInfo actResJavaInfo;
    private final JavaInfo actBaseJavaInfo;
    private String layoutName;

    public CreateActBuilder(JavaInfo info, String layout, JavaInfo actResJavaInfo, JavaInfo actBaseJavaInfo) {
        super(info);
        this.layoutName = layout;
        this.actResJavaInfo = actResJavaInfo;
        this.actBaseJavaInfo = actBaseJavaInfo;
    }

    @Override
    protected boolean isBuild() {
        return true;
    }

    @Override
    protected boolean isGetLines() {
        return true;
    }

    @Override
    protected boolean isForce() {
        return false;
    }

    @Override
    protected void beforeBuild(List<String> lines) {
        super.beforeBuild(lines);
        //Logs.i(lines);
    }

    @Override
    protected void dealLines() {
        addTag(pkg, javaInfo.pkg);
        addTag(actName, javaInfo.name);
        addTag(rPkg, Pkg.R);
        addTag(actBaseFullName, FullName.ACT_BASE);
        addTag(actBase, FullName.ACT_BASE_SHORT_NAME);
        addTag(toFullName, FullName.TO);
        addTag(to, FullName.TO_SHORT_NAME);
        addTag(layout, layoutName);
        addTag(baseFullName, actBaseJavaInfo.fullName);
        addTag(resFullName, actResJavaInfo.fullName);

    }
}
/* model_temp_start
package [[pkg]];

import [[rPkg]].R;
import [[toFullName]];
import [[actBaseFullName]];

import [[baseFullName]];
import [[resFullName]];

@[[to]]([[actName]]Res.class)
@[[actBase]](layout = R.layout.[[layout]])
public class [[actName]] extends [[actName]]Base {
}

model_temp_end */