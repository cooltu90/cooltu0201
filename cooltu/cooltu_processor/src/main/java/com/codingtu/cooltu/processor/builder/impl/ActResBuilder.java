package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.processor.builder.base.ActResBuilderBase;

import java.util.List;

public class ActResBuilder extends ActResBuilderBase {
    private final JavaInfo actJavaInfo;

    public ActResBuilder(JavaInfo info, JavaInfo actJavaInfo) {
        super(info);
        this.actJavaInfo = actJavaInfo;
    }

    @Override
    protected boolean isBuild() {
        return true;
    }

    @Override
    protected void beforeBuild(List<String> lines) {
        super.beforeBuild(lines);
        //Logs.i(lines);
    }

    @Override
    protected boolean isForce() {
        return false;
    }

    @Override
    protected void dealLines() {
        addTag(pkg, javaInfo.pkg);
        addTag(resForFullName, FullName.RES_FOR);
        addTag(ResForName, FullName.RES_FOR_SHORT_NAME);

        addTag(actFullName, actJavaInfo.fullName);
        addTag(actName, actJavaInfo.name);
    }
}
/* model_temp_start
package [[pkg]];

import [[resForFullName]];
import [[actFullName]];

@[[ResForName]]([[actName]].class)
public class [[actName]]Res {
}
model_temp_end */
