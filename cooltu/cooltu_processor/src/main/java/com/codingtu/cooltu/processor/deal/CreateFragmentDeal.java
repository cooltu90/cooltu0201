package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.exception.FileCopyException;
import com.codingtu.cooltu.lib4j.file.copy.FileCopy;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.processor.annotation.create.CreateFragment;
import com.codingtu.cooltu.processor.builder.core.UiBaseBuilder;
import com.codingtu.cooltu.processor.builder.impl.CreateFragmentBuilder;
import com.codingtu.cooltu.processor.builder.impl.FragResBuilder;
import com.codingtu.cooltu.processor.builder.impl.FragmentBaseBuilder;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.IdTools;

import java.io.File;

import javax.lang.model.element.TypeElement;

public class CreateFragmentDeal extends TypeBaseDeal {
    @Override
    protected void dealTypeElement(TypeElement te) {
        CreateFragment createFragment = te.getAnnotation(CreateFragment.class);
        String packages = createFragment.packages();
        String name = createFragment.name();

        JavaInfo fragmentJavaInfo = CurrentPath.frag(packages, name);
        if (new File(fragmentJavaInfo.path).exists())
            return;


        //创建layout
        IdTools.Id layoutTempId = IdTools.elementToId(te, CreateFragment.class, createFragment.layoutTemp());
        String layoutName = "fragment_" + name;
        try {
            FileCopy
                    .src(CurrentPath.layout(layoutTempId.rName))
                    .to(new File(CurrentPath.layout(layoutName)));
        } catch (FileCopyException e) {
            e.printStackTrace();
        }
        //创建ActRes
        JavaInfo fragmentResJavaInfo = CurrentPath.fragRes(packages, name);
        new FragResBuilder(fragmentResJavaInfo, fragmentJavaInfo);
        //创建ActBase
        JavaInfo fragmentBaseJavaInfo = CurrentPath.fragBase(fragmentJavaInfo.fullName);
        FragmentBaseBuilder builder = new FragmentBaseBuilder(fragmentBaseJavaInfo);
        UiBaseBuilder uiBaseBuilder = builder.getUiBaseBuilder();
        uiBaseBuilder.baseClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return createFragment.baseClass();
            }
        });
        uiBaseBuilder.layout = new IdTools.Id(Pkg.R, "layout", layoutName);
        uiBaseBuilder.uiFullName = fragmentJavaInfo.fullName;
        //创建Act
        new CreateFragmentBuilder(fragmentJavaInfo, layoutName, fragmentResJavaInfo, fragmentBaseJavaInfo);

    }
}
