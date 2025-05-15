package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class ActBackIntentBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected java.util.Map<String, Boolean> methodIfs;
    protected java.util.Map<String, Integer> methodCounts;
    protected StringBuilder methodSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> method;

    public ActBackIntentBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        methodIfs = new java.util.HashMap<>();
        methodCounts = new java.util.HashMap<>();
        methodSb = map.get("method");
        method = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();

    }

    public int methodParamCount(int i0) {
        return count(methodCounts, getForKey("methodParam", i0));
    }
    public void methodParam(int i0, int i1) {
        addForMap(this.method, getForKey("methodParam", i0, i1));
        countAdd(methodCounts, getForKey("methodParam", i0));
    }
    public int methodCount() {
        return count(methodCounts, getForKey("method"));
    }
    public void method(int i0, String methodName, String params) {
        addForMap(this.method, getForKey("method", i0), methodName, params);
        countAdd(methodCounts, getForKey("method"));
    }

    public void methodParamOtherIf(int i0, int i1, String passFullName, String field, String param) {
        addForMap(this.method, getIfKey("methodParamOther", i0, i1), passFullName, field, param);
        methodIfs.put(getIfKey("methodParamOther", i0, i1), true);
    }
    public void methodParamBeanIf(int i0, int i1, String passFullName, String field, String jsonToolFullName, String param) {
        addForMap(this.method, getIfKey("methodParamBean", i0, i1), passFullName, field, jsonToolFullName, param);
        methodIfs.put(getIfKey("methodParamBean", i0, i1), true);
    }

    @Override
    protected void dealLinesInParent() {
        for (int i0 = 0; i0 < count(methodCounts, getForKey("method")); i0++) {
            List<String> method0 = method.get(getForKey("method", i0));
            addLnTag(methodSb, "    public static Intent [methodName]([params]) {", method0.get(0), method0.get(1));
            addLnTag(methodSb, "        Intent intent = new Intent();");
            for (int i1 = 0; i1 < count(methodCounts, getForKey("methodParam", i0)); i1++) {
                List<String> method1 = method.get(getForKey("methodParam", i0, i1));
                if (isIf(methodIfs, getIfKey("methodParamOther", i0, i1))) {
                    List<String> method2 = method.get(getIfKey("methodParamOther", i0, i1));
                    addLnTag(methodSb, "        intent.putExtra([passFullName].[field], [param]);", method2.get(0), method2.get(1), method2.get(2));
                }
                if (isIf(methodIfs, getIfKey("methodParamBean", i0, i1))) {
                    List<String> method2 = method.get(getIfKey("methodParamBean", i0, i1));
                    addLnTag(methodSb, "        intent.putExtra([passFullName].[field], [jsonToolFullName].toJson([param]));", method2.get(0), method2.get(1), method2.get(2), method2.get(3));
                }
            }
            addLnTag(methodSb, "        return intent;");
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
        lines.add("public class ActBackIntent {");
        lines.add("[[method]]");
        lines.add("}");

        return lines;
    }
}
