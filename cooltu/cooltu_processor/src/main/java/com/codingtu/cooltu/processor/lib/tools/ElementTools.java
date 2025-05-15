package com.codingtu.cooltu.processor.lib.tools;

import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.processor.lib.param.Params;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

public class ElementTools {

    public static String getType(Element e) {
        return e.asType().toString();
    }

    public static String getParentType(Element e) {
        return getType(e.getEnclosingElement());
    }

    public static String simpleName(Element e) {
        return e.getSimpleName().toString();
    }

    public static String staticSimpleName(Element e) {
        return ConvertTool.toStaticType(simpleName(e));
    }

    public static KV<String, String> getFieldKv(VariableElement ve) {
        return new KV<>(getType(ve), simpleName(ve));
    }

    public static Params getMethodParamKvs(ExecutableElement ee) {
        return Params.obtain(Es.es(ee.getParameters()).convert((index, ve) -> {
            return getFieldKv(ve);
        }).toList());
    }

    public static BaseEs<VariableElement> getVariableElements(ExecutableElement ee) {
        return Es.es(ee.getParameters()).convert((index, element) -> {
            return (VariableElement) element;
        });
    }

    public static BaseEs<VariableElement> getVariableElements(List<? extends VariableElement> vs) {
        return Es.es(vs).convert((index, element) -> {
            return (VariableElement) element;
        });
    }

    public static BaseEs<VariableElement> getVariableElements(TypeElement ee) {
        return Es.es(ee.getEnclosedElements()).convert((index, element) -> {
            if (element instanceof VariableElement) {
                return (VariableElement) element;
            }
            return null;
        });
    }

    public static BaseEs<ExecutableElement> getExecutableElements(TypeElement ee) {
        return Es.es(ee.getEnclosedElements()).convert((index, element) -> {
            if (element instanceof ExecutableElement) {
                return (ExecutableElement) element;
            }
            return null;
        });
    }

    public static void ls(List<? extends Element> es, Es.EachEs<Element> eachEs) {
        Es.es(es).ls((position, element) -> {
            eachEs.each(position, element);
            return false;
        });

    }
}
