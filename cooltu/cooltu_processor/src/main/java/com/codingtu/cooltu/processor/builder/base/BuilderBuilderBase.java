package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class BuilderBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected StringBuilder name;
    protected StringBuilder base;
    protected StringBuilder fileds;
    protected StringBuilder JavaInfo;
    protected StringBuilder inits;
    protected StringBuilder forCounts;
    protected StringBuilder fors;
    protected StringBuilder ifs;
    protected StringBuilder dealLinesInParent;
    protected StringBuilder tempLines;

    public BuilderBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        name = map.get("name");
        base = map.get("base");
        fileds = map.get("fileds");
        JavaInfo = map.get("JavaInfo");
        inits = map.get("inits");
        forCounts = map.get("forCounts");
        fors = map.get("fors");
        ifs = map.get("ifs");
        dealLinesInParent = map.get("dealLinesInParent");
        tempLines = map.get("tempLines");

    }



    @Override
    protected void dealLinesInParent() {

    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("import java.util.ArrayList;");
        lines.add("import java.util.List;");
        lines.add("");
        lines.add("public abstract class [[name]] extends [[base]] {");
        lines.add("[[fileds]]");
        lines.add("    public [[name]]([[JavaInfo]] info) {");
        lines.add("        super(info);");
        lines.add("[[inits]]");
        lines.add("    }");
        lines.add("[[forCounts]]");
        lines.add("[[fors]]");
        lines.add("[[ifs]]");
        lines.add("    @Override");
        lines.add("    protected void dealLinesInParent() {");
        lines.add("[[dealLinesInParent]]");
        lines.add("    }");
        lines.add("");
        lines.add("    @Override");
        lines.add("    protected List<String> getTempLines() {");
        lines.add("        List<String> lines = new ArrayList<>();");
        lines.add("[[tempLines]]");
        lines.add("        return lines;");
        lines.add("    }");
        lines.add("}");

        return lines;
    }
}
