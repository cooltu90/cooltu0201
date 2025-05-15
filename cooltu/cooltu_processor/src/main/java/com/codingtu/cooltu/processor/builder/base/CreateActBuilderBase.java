package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class CreateActBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected StringBuilder rPkg;
    protected StringBuilder toFullName;
    protected StringBuilder actBaseFullName;
    protected StringBuilder baseFullName;
    protected StringBuilder resFullName;
    protected StringBuilder to;
    protected StringBuilder actName;
    protected StringBuilder actBase;
    protected StringBuilder layout;

    public CreateActBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        rPkg = map.get("rPkg");
        toFullName = map.get("toFullName");
        actBaseFullName = map.get("actBaseFullName");
        baseFullName = map.get("baseFullName");
        resFullName = map.get("resFullName");
        to = map.get("to");
        actName = map.get("actName");
        actBase = map.get("actBase");
        layout = map.get("layout");

    }



    @Override
    protected void dealLinesInParent() {

    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("import [[rPkg]].R;");
        lines.add("import [[toFullName]];");
        lines.add("import [[actBaseFullName]];");
        lines.add("");
        lines.add("import [[baseFullName]];");
        lines.add("import [[resFullName]];");
        lines.add("");
        lines.add("@[[to]]([[actName]]Res.class)");
        lines.add("@[[actBase]](layout = R.layout.[[layout]])");
        lines.add("public class [[actName]] extends [[actName]]Base {");
        lines.add("}");
        lines.add("");

        return lines;
    }
}
