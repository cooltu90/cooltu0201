package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.processor.annotation.ui.VH;
import com.codingtu.cooltu.processor.builder.impl.VhBuilder;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.IdTools;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.TypeElement;

public class VHDeal extends TypeBaseDeal {

    public static final Map<String, String> vhMap = new HashMap<>();

    @Override
    protected void dealTypeElement(TypeElement te) {
        VH vh = te.getAnnotation(VH.class);
        IdTools.Id id = IdTools.elementToId(te, VH.class, vh.layout());
        String vhClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return vh.vh();
            }
        });
        JavaInfo vhJavaInfo = CurrentPath.javaInfo(vhClass);

        String type = ElementTools.getType(te);
        vhMap.put(type, vhClass);

        new VhBuilder(vhJavaInfo, id);
    }
}
