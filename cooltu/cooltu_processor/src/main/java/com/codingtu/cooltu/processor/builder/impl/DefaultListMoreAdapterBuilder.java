package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.processor.builder.base.DefaultListMoreAdapterBuilderBase;

public class DefaultListMoreAdapterBuilder extends DefaultListMoreAdapterBuilderBase {
    private final JavaInfo vhJavaInfo;
    private final String layoutSimpleName;

    public DefaultListMoreAdapterBuilder(JavaInfo info, JavaInfo vhJavaInfo, String layoutSimpleName) {
        super(info);
        this.vhJavaInfo = vhJavaInfo;
        this.layoutSimpleName = layoutSimpleName;
    }

    @Override
    protected boolean isBuild() {
        return true;
    }

    @Override
    protected void dealLines() {
        addTag(pkg, javaInfo.pkg);
        addTag(rPkg, Pkg.R);
        addTag(coreMoreListAdapterFullName, FullName.CORE_MORE_LIST_ADAPTER);
        addTag(annotationVhFullName, FullName.ANNOTATION_VH);
        addTag(vhPkg, Pkg.CORE_VH);
        addTag(vhName, vhJavaInfo.name);
        addTag(layoutName, layoutSimpleName);
        addTag(name, javaInfo.name);
        addTag(coreMoreListAdapterName, FullName.CORE_MORE_LIST_ADAPTER_SHORT_NAME);
        //addTag(BaseTsFullName, FullName.BASE_TS);
    }
}
/* model_temp_start
package [[pkg]];

import [[rPkg]].R;
import [[coreMoreListAdapterFullName]];
import [[annotationVhFullName]];

import [[vhPkg]].[[vhName]];

@VH(layout = R.layout.item_[[layoutName]], vh = [[vhName]].class)
public abstract class [[name]] extends [[coreMoreListAdapterName]]<[[vhName]], String> {
    @Override
    protected void onBindVH([[vhName]] vh, int position, String s) {

    }
}
model_temp_end */