package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.data.map.ListValueMap;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.processor.annotation.res.ResFor;
import com.codingtu.cooltu.processor.annotation.ui.NoStart;
import com.codingtu.cooltu.processor.annotation.ui.StartGroup;
import com.codingtu.cooltu.processor.builder.core.UiBaseBuilder;
import com.codingtu.cooltu.processor.builder.impl.ActBaseBuilder;
import com.codingtu.cooltu.processor.builder.impl.PassBuilder;
import com.codingtu.cooltu.processor.deal.base.ResForBaseDeal;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.BaseTools;

import java.util.HashMap;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

public class ResForDeal extends ResForBaseDeal {
    protected boolean hasStartGroup;
    protected boolean noStart;
    public static final ListValueMap<String, VariableElement> START_MAP = new ListValueMap<>();
    public static final HashMap<String, String> HAS_START_MAP = new HashMap<>();

    @Override
    protected void dealTypeElement(TypeElement te) {
        noStart = te.getAnnotation(NoStart.class) != null;
        super.dealTypeElement(te);
        if (!noStart) {
            HAS_START_MAP.put(uiClass, uiClass);
        }
    }

    protected void dealField(String fullName, VariableElement ve, KV<String, String> kv,
                             BaseTools.GetThis<UiBaseBuilder> uiBaseBuilderGetter,
                             UiBaseBuilder uiBaseBuilder) {
        super.dealField(fullName, ve, kv, uiBaseBuilderGetter, uiBaseBuilder);
        StartGroup startGroup = ve.getAnnotation(StartGroup.class);
        ActBaseBuilder builder = CurrentPath.actBaseBuilder(fullName);
        if (startGroup != null) {
            START_MAP.get(fullName).add(ve);
            PassBuilder.BUILDER.add(kv);
            if (CountTool.isNull(builder.starts)) {
                builder.starts.add(new KV<>(FullName.STRING, Constant.FROM_ACT));
            }
            builder.starts.add(kv);
        }
    }


    @Override
    protected BaseTools.GetThis<UiBaseBuilder> getUiBaseBuilderGetter() {
        return BaseTools.getActBaseChildGetter();
    }

    @Override
    protected String getUiClass(TypeElement te) {
        return ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return te.getAnnotation(ResFor.class).value();
            }
        });
    }
}
