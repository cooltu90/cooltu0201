package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.processor.builder.base.DefaultListAdapterBuilderBase;

public class DefaultListAdapterBuilder extends DefaultListAdapterBuilderBase {
    private final JavaInfo vhJavaInfo;
    private final String layoutSimpleName;

    public DefaultListAdapterBuilder(JavaInfo info, JavaInfo vhJavaInfo, String layoutSimpleName) {
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
        addTag(coreListAdapterFullName, FullName.CORE_LIST_ADAPTER);
        addTag(vhPkg, Pkg.CORE_VH);
        addTag(vhName, vhJavaInfo.name);
        addTag(name, javaInfo.name);
        addTag(coreListAdapterName, FullName.CORE_LIST_ADAPTER_SHORT_NAME);
        addTag(rPkg, Pkg.R);
        addTag(annotationVhFullName, FullName.ANNOTATION_VH);
        addTag(layoutName, layoutSimpleName);
        //addTag(BaseTsFullName, FullName.BASE_TS);
    }
}
/* model_temp_start
package [[pkg]];

import androidx.annotation.NonNull;

import [[rPkg]].R;
import [[coreListAdapterFullName]];
import [[annotationVhFullName]];

import [[vhPkg]].[[vhName]];

@VH(layout = R.layout.item_[[layoutName]], vh = [[vhName]].class)
public class [[name]] extends [[coreListAdapterName]]<[[vhName]], String> {
    @Override
    protected void onBindVH(@NonNull [[vhName]] vh, int position, String s) {

    }
}
model_temp_end */
