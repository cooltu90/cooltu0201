package com.codingtu.cooltu.processor.lib.tools;

import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;

import javax.lang.model.element.VariableElement;

public class BeanTools {


    public static KV<String, String> getBeanKv(String className, String name) {
        KV<String, String> kv = new KV<>();
        kv.k = className;
        if (StringTool.isBlank(name)) {
            name = ConvertTool.toMethodType(CurrentPath.javaInfo(className).name);
        }
        kv.v = name;
        return kv;
    }

    public static KV<String, String> getBeanKv(VariableElement ve, String name) {
        KV<String, String> kv = ElementTools.getFieldKv(ve);
        if (StringTool.isNotBlank(name)) {
            kv.v = name;
        }
        return kv;
    }

}
