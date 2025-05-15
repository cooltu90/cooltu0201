package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class ActStartBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected java.util.Map<String, Boolean> methodIfs;
    protected java.util.Map<String, Integer> methodCounts;
    protected StringBuilder methodSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> method;

    public ActStartBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        methodIfs = new java.util.HashMap<>();
        methodCounts = new java.util.HashMap<>();
        methodSb = map.get("method");
        method = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();

    }

    public int methodIntentCount(int i0) {
        return count(methodCounts, getForKey("methodIntent", i0));
    }
    public void methodIntent(int i0, int i1, String fieldName, String value) {
        addForMap(this.method, getForKey("methodIntent", i0, i1), fieldName, value);
        countAdd(methodCounts, getForKey("methodIntent", i0));
    }
    public int methodCount() {
        return count(methodCounts, getForKey("method"));
    }
    public void method(int i0, String methodName, String param, String actFullName, String actToolFullName, String codeName) {
        addForMap(this.method, getForKey("method", i0), methodName, param, actFullName, actToolFullName, codeName);
        countAdd(methodCounts, getForKey("method"));
    }


    @Override
    protected void dealLinesInParent() {
        for (int i0 = 0; i0 < count(methodCounts, getForKey("method")); i0++) {
            List<String> method0 = method.get(getForKey("method", i0));
            addLnTag(methodSb, "    public static final void [methodName](Activity act[param]) {", method0.get(0), method0.get(1));
            addLnTag(methodSb, "        Intent intent = new Intent(act, [actFullName].class);", method0.get(2));
            addLnTag(methodSb, "        intent.putExtra(Pass.FROM_ACT, act.getClass().getCanonicalName());");
            for (int i1 = 0; i1 < count(methodCounts, getForKey("methodIntent", i0)); i1++) {
                List<String> method1 = method.get(getForKey("methodIntent", i0, i1));
                addLnTag(methodSb, "        intent.putExtra(Pass.[fieldName], [value]);", method1.get(0), method1.get(1));
            }
            addLnTag(methodSb, "        [actToolFullName].startActivityForResult(act, intent, Code4Request.[codeName]);", method0.get(3), method0.get(4));
            addLnTag(methodSb, "    }");
        }

    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("import android.app.Activity;");
        lines.add("import android.content.Intent;");
        lines.add("");
        lines.add("public class ActStart {");
        lines.add("[[method]]");
        lines.add("}");

        return lines;
    }
}
