package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.processor.annotation.res.ResForFragment;
import com.codingtu.cooltu.processor.builder.core.UiBaseBuilder;
import com.codingtu.cooltu.processor.deal.base.ResForBaseDeal;
import com.codingtu.cooltu.processor.lib.tools.BaseTools;

import javax.lang.model.element.TypeElement;

public class ResForFragmentDeal extends ResForBaseDeal {
    @Override
    protected void dealTypeElement(TypeElement te) {
        super.dealTypeElement(te);
    }

    @Override
    protected BaseTools.GetThis<UiBaseBuilder> getUiBaseBuilderGetter() {
        return BaseTools.getFragBaseChildGetter();
    }

    @Override
    protected String getUiClass(TypeElement te) {
        return ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return te.getAnnotation(ResForFragment.class).value();
            }
        });
    }
}
