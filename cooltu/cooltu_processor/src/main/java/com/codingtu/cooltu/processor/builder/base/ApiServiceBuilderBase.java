package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class ApiServiceBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected StringBuilder name;
    protected java.util.Map<String, Boolean> methodIfs;
    protected java.util.Map<String, Integer> methodCounts;
    protected StringBuilder methodSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> method;

    public ApiServiceBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        name = map.get("name");
        methodIfs = new java.util.HashMap<>();
        methodCounts = new java.util.HashMap<>();
        methodSb = map.get("method");
        method = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();

    }

    public int methodParamCount(int i0) {
        return count(methodCounts, getForKey("methodParam", i0));
    }
    public void methodParam(int i0, int i1, String anno, String type, String name, String divider) {
        addForMap(this.method, getForKey("methodParam", i0, i1), anno, type, name, divider);
        countAdd(methodCounts, getForKey("methodParam", i0));
    }
    public int methodCount() {
        return count(methodCounts, getForKey("method"));
    }
    public void method(int i0, String netType, String apiUrl, String methodName) {
        addForMap(this.method, getForKey("method", i0), netType, apiUrl, methodName);
        countAdd(methodCounts, getForKey("method"));
    }

    public void isAnnoValueName(int i0, int i1, boolean is) {
        methodIfs.put(getIfKey("annoValueName", i0, i1), is);
    }
    public void isAnnoEncode(int i0, int i1, boolean is) {
        methodIfs.put(getIfKey("annoEncode", i0, i1), is);
    }
    public void annoInfoIf(int i0, int i1, String value) {
        addForMap(this.method, getIfKey("annoInfo", i0, i1), value);
        methodIfs.put(getIfKey("annoInfo", i0, i1), true);
    }

    @Override
    protected void dealLinesInParent() {
        for (int i0 = 0; i0 < count(methodCounts, getForKey("method")); i0++) {
            List<String> method0 = method.get(getForKey("method", i0));
            addLnTag(methodSb, "");
            addLnTag(methodSb, "    @[netType](\"[apiUrl]\")", method0.get(0), method0.get(1));
            addLnTag(methodSb, "    Flowable<Result<ResponseBody>> [methodName](", method0.get(2));
            for (int i1 = 0; i1 < count(methodCounts, getForKey("methodParam", i0)); i1++) {
                List<String> method1 = method.get(getForKey("methodParam", i0, i1));
                StringBuilder annoInfoSb = new StringBuilder();
                if (isIf(methodIfs, getIfKey("annoInfo", i0, i1))) {
                    List<String> method2 = method.get(getIfKey("annoInfo", i0, i1));
                    StringBuilder annoValueNameSb = new StringBuilder();
                    if (isIf(methodIfs, getIfKey("annoValueName", i0, i1))) {
                        List<String> method3 = method.get(getIfKey("annoValueName", i0, i1));
                        addTag(annoValueNameSb, "value = ");
                    }
                    StringBuilder annoEncodeSb = new StringBuilder();
                    if (isIf(methodIfs, getIfKey("annoEncode", i0, i1))) {
                        List<String> method3 = method.get(getIfKey("annoEncode", i0, i1));
                        addTag(annoEncodeSb, ", encoded = true");
                    }
                    addTag(annoInfoSb, "([annoValueName]\"[value]\"[annoEncode])", annoValueNameSb.toString(), method2.get(0), annoEncodeSb.toString());
                }
                addLnTag(methodSb, "            @[anno][annoInfo] [type] [name][divider]", method1.get(0), annoInfoSb.toString(), method1.get(1), method1.get(2), method1.get(3));
            }
            addLnTag(methodSb, "    );");
        }

    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("import io.reactivex.Flowable;");
        lines.add("import okhttp3.ResponseBody;");
        lines.add("import retrofit2.adapter.rxjava2.Result;");
        lines.add("");
        lines.add("public interface [[name]] {");
        lines.add("[[method]]");
        lines.add("");
        lines.add("}");

        return lines;
    }
}
