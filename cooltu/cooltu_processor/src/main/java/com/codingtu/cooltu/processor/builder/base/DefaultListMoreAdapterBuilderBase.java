package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class DefaultListMoreAdapterBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected StringBuilder rPkg;
    protected StringBuilder coreMoreListAdapterFullName;
    protected StringBuilder annotationVhFullName;
    protected StringBuilder vhPkg;
    protected StringBuilder vhName;
    protected StringBuilder layoutName;
    protected StringBuilder name;
    protected StringBuilder coreMoreListAdapterName;

    public DefaultListMoreAdapterBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        rPkg = map.get("rPkg");
        coreMoreListAdapterFullName = map.get("coreMoreListAdapterFullName");
        annotationVhFullName = map.get("annotationVhFullName");
        vhPkg = map.get("vhPkg");
        vhName = map.get("vhName");
        layoutName = map.get("layoutName");
        name = map.get("name");
        coreMoreListAdapterName = map.get("coreMoreListAdapterName");

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
        lines.add("import [[coreMoreListAdapterFullName]];");
        lines.add("import [[annotationVhFullName]];");
        lines.add("");
        lines.add("import [[vhPkg]].[[vhName]];");
        lines.add("");
        lines.add("@VH(layout = R.layout.item_[[layoutName]], vh = [[vhName]].class)");
        lines.add("public abstract class [[name]] extends [[coreMoreListAdapterName]]<[[vhName]], String> {");
        lines.add("    @Override");
        lines.add("    protected void onBindVH([[vhName]] vh, int position, String s) {");
        lines.add("");
        lines.add("    }");
        lines.add("}");

        return lines;
    }
}
