package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class FragResBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected StringBuilder resForFullName;
    protected StringBuilder fragFullName;
    protected StringBuilder ResForFragmentName;
    protected StringBuilder fragName;

    public FragResBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        resForFullName = map.get("resForFullName");
        fragFullName = map.get("fragFullName");
        ResForFragmentName = map.get("ResForFragmentName");
        fragName = map.get("fragName");

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
        lines.add("import [[fragFullName]];");
        lines.add("");
        lines.add("@[[ResForFragmentName]]([[fragName]].class)");
        lines.add("public class [[fragName]]Res {");
        lines.add("}");

        return lines;
    }
}
