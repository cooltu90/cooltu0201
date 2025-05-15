package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.processor.builder.base.CreateFragmentBuilderBase;

import java.util.List;

public class CreateFragmentBuilder extends CreateFragmentBuilderBase {
    private final JavaInfo actResJavaInfo;
    private final JavaInfo actBaseJavaInfo;
    private String layoutName;


    public CreateFragmentBuilder(JavaInfo info, String layout, JavaInfo actResJavaInfo, JavaInfo actBaseJavaInfo) {
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
        addTag(rPkg, Pkg.R);
        addTag(toFullName, FullName.TO);
        addTag(fragBaseFullName, FullName.FRAGMENT_BASE);
        addTag(baseFullName, actBaseJavaInfo.fullName);
        addTag(resFullName, actResJavaInfo.fullName);

        addTag(fragName, javaInfo.name);
        addTag(fragBase, FullName.FRAGMENT_BASE_SHORT_NAME);
        addTag(to, FullName.TO_SHORT_NAME);
        addTag(layout, layoutName);

    }
}
/* model_temp_start
package [[pkg]];

import [[rPkg]].R;
import [[toFullName]];
import [[fragBaseFullName]];

import [[baseFullName]];
import [[resFullName]];

@[[to]]([[fragName]]Res.class)
@[[fragBase]](layout = R.layout.[[layout]])
public class [[fragName]] extends [[fragName]]Base {

}
model_temp_end */
