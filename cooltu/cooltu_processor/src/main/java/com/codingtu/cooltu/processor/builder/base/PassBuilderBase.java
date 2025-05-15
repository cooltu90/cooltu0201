package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class PassBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected java.util.Map<String, Boolean> fieldIfs;
    protected java.util.Map<String, Integer> fieldCounts;
    protected StringBuilder fieldSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> field;
    protected java.util.Map<String, Boolean> methodIfs;
    protected java.util.Map<String, Integer> methodCounts;
    protected StringBuilder methodSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> method;

    public PassBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        fieldIfs = new java.util.HashMap<>();
        fieldCounts = new java.util.HashMap<>();
        fieldSb = map.get("field");
        field = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        methodIfs = new java.util.HashMap<>();
        methodCounts = new java.util.HashMap<>();
        methodSb = map.get("method");
        method = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();

    }

    public int fieldCount() {
        return count(fieldCounts, getForKey("field"));
    }
    public void field(int i0, String name, String value) {
        addForMap(this.field, getForKey("field", i0), name, value);
        countAdd(fieldCounts, getForKey("field"));
    }
    public int methodCount() {
        return count(methodCounts, getForKey("method"));
    }
    public void method(int i0, String type, String methodName) {
        addForMap(this.method, getForKey("method", i0), type, methodName);
        countAdd(methodCounts, getForKey("method"));
    }

    public void isBeanIf(int i0, String jsonToolFullName, String beanClass, String fieldName) {
        addForMap(this.method, getIfKey("isBean", i0), jsonToolFullName, beanClass, fieldName);
        methodIfs.put(getIfKey("isBean", i0), true);
    }
    public void isBeanListIf(int i0, String jsonToolFullName, String beanClass, String fieldName) {
        addForMap(this.method, getIfKey("isBeanList", i0), jsonToolFullName, beanClass, fieldName);
        methodIfs.put(getIfKey("isBeanList", i0), true);
    }
    public void isOtherIf(int i0, String methodType, String fieldName, String defaultValue) {
        addForMap(this.method, getIfKey("isOther", i0), methodType, fieldName, defaultValue);
        methodIfs.put(getIfKey("isOther", i0), true);
    }

    @Override
    protected void dealLinesInParent() {
        for (int i0 = 0; i0 < count(fieldCounts, getForKey("field")); i0++) {
            List<String> field0 = field.get(getForKey("field", i0));
            addLnTag(fieldSb, "    public static final String [name] = \"[value]\";", field0.get(0), field0.get(1));
        }
        for (int i0 = 0; i0 < count(methodCounts, getForKey("method")); i0++) {
            List<String> method0 = method.get(getForKey("method", i0));
            addLnTag(methodSb, "    public static final [type] [methodName](Intent data) {", method0.get(0), method0.get(1));
            if (isIf(methodIfs, getIfKey("isBean", i0))) {
                List<String> method1 = method.get(getIfKey("isBean", i0));
                addLnTag(methodSb, "        return [jsonToolFullName].toBean([beanClass].class, data.getStringExtra([fieldName]));", method1.get(0), method1.get(1), method1.get(2));
            }
            if (isIf(methodIfs, getIfKey("isBeanList", i0))) {
                List<String> method1 = method.get(getIfKey("isBeanList", i0));
                addLnTag(methodSb, "        return [jsonToolFullName].toBeanList([beanClass].class, data.getStringExtra([fieldName]));", method1.get(0), method1.get(1), method1.get(2));
            }
            if (isIf(methodIfs, getIfKey("isOther", i0))) {
                List<String> method1 = method.get(getIfKey("isOther", i0));
                addLnTag(methodSb, "        return data.get[methodType]Extra([fieldName][defaultValue]);", method1.get(0), method1.get(1), method1.get(2));
            }
            addLnTag(methodSb, "    }");
        }

    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("import android.content.Intent;");
        lines.add("");
        lines.add("public class Pass {");
        lines.add("[[field]]");
        lines.add("[[method]]");
        lines.add("");
        lines.add("}");
        lines.add("");

        return lines;
    }
}
