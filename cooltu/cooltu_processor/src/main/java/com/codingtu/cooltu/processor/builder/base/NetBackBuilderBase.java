package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class NetBackBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder name;
    protected StringBuilder netBackFullName;
    protected java.util.Map<String, Boolean> fieldIfs;
    protected java.util.Map<String, Integer> fieldCounts;
    protected StringBuilder fieldSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> field;
    protected StringBuilder coreSendParamsFullName;
    protected java.util.Map<String, Boolean> acceptIfs;
    protected java.util.Map<String, Integer> acceptCounts;
    protected StringBuilder acceptSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> accept;

    public NetBackBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        name = map.get("name");
        netBackFullName = map.get("netBackFullName");
        fieldIfs = new java.util.HashMap<>();
        fieldCounts = new java.util.HashMap<>();
        fieldSb = map.get("field");
        field = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        coreSendParamsFullName = map.get("coreSendParamsFullName");
        acceptIfs = new java.util.HashMap<>();
        acceptCounts = new java.util.HashMap<>();
        acceptSb = map.get("accept");
        accept = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();

    }


    public void fieldIf(String type, String name) {
        addForMap(this.field, getIfKey("field"), type, name);
        fieldIfs.put(getIfKey("field"), true);
    }
    public void acceptIf(String stringToolFullName, String name, String jsonToolFullName, String method, String type) {
        addForMap(this.accept, getIfKey("accept"), stringToolFullName, name, jsonToolFullName, method, type);
        acceptIfs.put(getIfKey("accept"), true);
    }

    @Override
    protected void dealLinesInParent() {
        if (isIf(fieldIfs, getIfKey("field"))) {
            List<String> field0 = field.get(getIfKey("field"));
            addLnTag(fieldSb, "    public [type] [name];", field0.get(0), field0.get(1));
        }
        if (isIf(acceptIfs, getIfKey("accept"))) {
            List<String> accept0 = accept.get(getIfKey("accept"));
            addLnTag(acceptSb, "        if ([stringToolFullName].isNotBlank(json)) {", accept0.get(0));
            addLnTag(acceptSb, "            [name] = [jsonToolFullName].[method]([type].class, json);", accept0.get(1), accept0.get(2), accept0.get(3), accept0.get(4));
            addLnTag(acceptSb, "        }");
        }

    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
        lines.add("package core.net.back;");
        lines.add("");
        lines.add("import java.util.List;");
        lines.add("import okhttp3.ResponseBody;");
        lines.add("import retrofit2.adapter.rxjava2.Result;");
        lines.add("");
        lines.add("public class [[name]] extends [[netBackFullName]] {");
        lines.add("[[field]]");
        lines.add("    @Override");
        lines.add("    public void accept(String code, Result<ResponseBody> result, [[coreSendParamsFullName]] params, List objs) {");
        lines.add("        super.accept(code, result, params, objs);");
        lines.add("[[accept]]");
        lines.add("");
        lines.add("    }");
        lines.add("");
        lines.add("}");

        return lines;
    }
}
