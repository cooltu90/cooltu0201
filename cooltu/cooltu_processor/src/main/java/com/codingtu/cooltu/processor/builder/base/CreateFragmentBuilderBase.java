package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class CreateFragmentBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected StringBuilder rPkg;
    protected StringBuilder toFullName;
    protected StringBuilder fragBaseFullName;
    protected StringBuilder baseFullName;
    protected StringBuilder resFullName;
    protected StringBuilder to;
    protected StringBuilder fragName;
    protected StringBuilder fragBase;
    protected StringBuilder layout;

    public CreateFragmentBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        rPkg = map.get("rPkg");
        toFullName = map.get("toFullName");
        fragBaseFullName = map.get("fragBaseFullName");
        baseFullName = map.get("baseFullName");
        resFullName = map.get("resFullName");
        to = map.get("to");
        fragName = map.get("fragName");
        fragBase = map.get("fragBase");
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
        lines.add("import [[fragBaseFullName]];");
        lines.add("");
        lines.add("import [[baseFullName]];");
        lines.add("import [[resFullName]];");
        lines.add("");
        lines.add("@[[to]]([[fragName]]Res.class)");
        lines.add("@[[fragBase]](layout = R.layout.[[layout]])");
        lines.add("public class [[fragName]] extends [[fragName]]Base {");
        lines.add("");
        lines.add("}");

        return lines;
    }
}
