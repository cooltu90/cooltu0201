package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class ActResBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected StringBuilder resForFullName;
    protected StringBuilder actFullName;
    protected StringBuilder ResForName;
    protected StringBuilder actName;

    public ActResBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        resForFullName = map.get("resForFullName");
        actFullName = map.get("actFullName");
        ResForName = map.get("ResForName");
        actName = map.get("actName");

    }



    @Override
    protected void dealLinesInParent() {

    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("import [[resForFullName]];");
        lines.add("import [[actFullName]];");
        lines.add("");
        lines.add("@[[ResForName]]([[actName]].class)");
        lines.add("public class [[actName]]Res {");
        lines.add("}");

        return lines;
    }
}
