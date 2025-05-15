package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.processor.builder.base.FragResBuilderBase;

public class FragResBuilder extends FragResBuilderBase {
    private final JavaInfo fragJavaInfo;

    public FragResBuilder(JavaInfo info, JavaInfo fragJavaInfo) {
        super(info);
        this.fragJavaInfo = fragJavaInfo;
    }

    @Override
    protected boolean isBuild() {
        return true;
    }

    @Override
    protected boolean isForce() {
        return false;
    }

    @Override
    protected void dealLines() {
        addTag(pkg, javaInfo.pkg);
        addTag(resForFullName, FullName.RES_FOR_FRAGMENT);
        addTag(ResForFragmentName, FullName.RES_FOR_FRAGMENT_SHORT_NAME);
        addTag(fragFullName, fragJavaInfo.fullName);
        addTag(fragName, fragJavaInfo.name);
    }
}
/* model_temp_start
package [[pkg]];

import [[resForFullName]];
import [[fragFullName]];

@[[ResForFragmentName]]([[fragName]].class)
public class [[fragName]]Res {
}
model_temp_end */
