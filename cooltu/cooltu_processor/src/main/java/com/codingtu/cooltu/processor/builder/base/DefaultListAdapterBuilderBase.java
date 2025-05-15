package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class DefaultListAdapterBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected StringBuilder rPkg;
    protected StringBuilder coreListAdapterFullName;
    protected StringBuilder annotationVhFullName;
    protected StringBuilder vhPkg;
    protected StringBuilder vhName;
    protected StringBuilder layoutName;
    protected StringBuilder name;
    protected StringBuilder coreListAdapterName;

    public DefaultListAdapterBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        rPkg = map.get("rPkg");
        coreListAdapterFullName = map.get("coreListAdapterFullName");
        annotationVhFullName = map.get("annotationVhFullName");
        vhPkg = map.get("vhPkg");
        vhName = map.get("vhName");
        layoutName = map.get("layoutName");
        name = map.get("name");
        coreListAdapterName = map.get("coreListAdapterName");

    }



    @Override
    protected void dealLinesInParent() {

    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("import androidx.annotation.NonNull;");
        lines.add("");
        lines.add("import [[rPkg]].R;");
        lines.add("import [[coreListAdapterFullName]];");
        lines.add("import [[annotationVhFullName]];");
        lines.add("");
        lines.add("import [[vhPkg]].[[vhName]];");
        lines.add("");
        lines.add("@VH(layout = R.layout.item_[[layoutName]], vh = [[vhName]].class)");
        lines.add("public class [[name]] extends [[coreListAdapterName]]<[[vhName]], String> {");
        lines.add("    @Override");
        lines.add("    protected void onBindVH(@NonNull [[vhName]] vh, int position, String s) {");
        lines.add("");
        lines.add("    }");
        lines.add("}");

        return lines;
    }
}
