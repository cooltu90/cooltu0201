package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class VhBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected StringBuilder name;
    protected StringBuilder coreAdapterVHFullName;
    protected java.util.Map<String, Boolean> fieldIfs;
    protected java.util.Map<String, Integer> fieldCounts;
    protected StringBuilder fieldSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> field;
    protected StringBuilder layoutName;
    protected java.util.Map<String, Boolean> findViewIfs;
    protected java.util.Map<String, Integer> findViewCounts;
    protected StringBuilder findViewSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> findView;

    public VhBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        name = map.get("name");
        coreAdapterVHFullName = map.get("coreAdapterVHFullName");
        fieldIfs = new java.util.HashMap<>();
        fieldCounts = new java.util.HashMap<>();
        fieldSb = map.get("field");
        field = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        layoutName = map.get("layoutName");
        findViewIfs = new java.util.HashMap<>();
        findViewCounts = new java.util.HashMap<>();
        findViewSb = map.get("findView");
        findView = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();

    }

    public int fieldCount() {
        return count(fieldCounts, getForKey("field"));
    }
    public void field(int i0, String type, String name) {
        addForMap(this.field, getForKey("field", i0), type, name);
        countAdd(fieldCounts, getForKey("field"));
    }
    public int findViewCount() {
        return count(findViewCounts, getForKey("findView"));
    }
    public void findView(int i0, String fieldName, String parent, String rPkg, String id) {
        addForMap(this.findView, getForKey("findView", i0), fieldName, parent, rPkg, id);
        countAdd(findViewCounts, getForKey("findView"));
    }


    @Override
    protected void dealLinesInParent() {
        for (int i0 = 0; i0 < count(fieldCounts, getForKey("field")); i0++) {
            List<String> field0 = field.get(getForKey("field", i0));
            addLnTag(fieldSb, "    public [type] [name];", field0.get(0), field0.get(1));
        }
        for (int i0 = 0; i0 < count(findViewCounts, getForKey("findView")); i0++) {
            List<String> findView0 = findView.get(getForKey("findView", i0));
            addLnTag(findViewSb, "        [fieldName] = [parent]findViewById([rPkg].R.id.[id]);", findView0.get(0), findView0.get(1), findView0.get(2), findView0.get(3));
        }

    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("import android.view.ViewGroup;");
        lines.add("");
        lines.add("public class [[name]] extends [[coreAdapterVHFullName]] {");
        lines.add("[[field]]");
        lines.add("");
        lines.add("");
        lines.add("    public [[name]](ViewGroup parent) {");
        lines.add("        super([[layoutName]], parent);");
        lines.add("[[findView]]");
        lines.add("    }");
        lines.add("}");

        return lines;
    }
}
