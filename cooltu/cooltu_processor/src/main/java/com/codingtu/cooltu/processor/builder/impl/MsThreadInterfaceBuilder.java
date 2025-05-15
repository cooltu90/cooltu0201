package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.processor.builder.base.MsThreadInterfaceBuilderBase;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import javax.lang.model.element.ExecutableElement;

public class MsThreadInterfaceBuilder extends MsThreadInterfaceBuilderBase {

    BaseEs<ExecutableElement> allMethodEs = Es.es();

    @Override
    protected boolean isBuild() {
        return true;
    }

    public MsThreadInterfaceBuilder(JavaInfo info) {
        super(info);
    }

    public void add(ExecutableElement element) {
        allMethodEs.add(element);
    }

    public void addMethods(BaseEs<ExecutableElement> methodEs) {
        allMethodEs.add(methodEs);
    }

    @Override
    protected void dealLines() {
        addTag(pkg, Pkg.CORE_MSTHREAD);
        addTag(name, javaInfo.name);
        allMethodEs.ls(new Es.EachEs<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement element) {
                addLnTag(methods, "");
                addLnTag(methods, "    void [dealCheckData]([String name, int age]);",
                        ElementTools.simpleName(element), ElementTools.getMethodParamKvs(element).getMethodParams());
                return false;
            }
        });
    }
}
/* model_temp_start
package [[pkg]];

public interface [[name]] {
[[methods]]
}
model_temp_end */