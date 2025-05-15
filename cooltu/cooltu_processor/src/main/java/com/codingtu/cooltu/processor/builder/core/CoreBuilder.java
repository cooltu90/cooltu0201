package com.codingtu.cooltu.processor.builder.core;

import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.map.ListValueMap;
import com.codingtu.cooltu.lib4j.data.map.StringBuilderValueMap;
import com.codingtu.cooltu.lib4j.data.symbol.Symbol;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.file.write.FileWriter;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tools.TagTools;
import com.codingtu.cooltu.processor.BuilderType;
import com.codingtu.cooltu.processor.lib.BuilderMap;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class CoreBuilder implements Symbol {
    public boolean isForce;
    protected JavaInfo javaInfo;

    protected StringBuilderValueMap<String> map = new StringBuilderValueMap();


    public CoreBuilder(JavaInfo info) {
        this.javaInfo = info;
        BuilderMap.add(getBuilderType(), this);
    }

    protected BuilderType getBuilderType() {
        return BuilderType.DEFAULT;
    }

    @Override
    public String obtainSymbol() {
        return getClass().getCanonicalName();
    }

    public void create() {
        if (javaInfo == null) {
            return;
        }
        File file = new File(javaInfo.path);
        if (isGetLines() && (isForce() || !file.exists())) {
            List<String> lines = getLines();
            beforeBuild(lines);
            if (isBuild() && !CountTool.isNull(lines)) {
                FileWriter.to(file).cover().write(lines);
            }
        }
    }

    protected void beforeBuild(List<String> lines) {
    }

    protected boolean isGetLines() {
        return true;
    }

    protected boolean isBuild() {
        return true;
    }

    protected boolean isForce() {
        return true;
    }


    private List<String> getLines() {
        dealLines();
        dealLinesInParent();
        return TagTools.dealLines(map, getTempLines());
    }

    protected void dealLinesInParent() {

    }

    protected abstract void dealLines();

    protected abstract List<String> getTempLines();

    /**************************************************
     *
     *
     *
     **************************************************/

    protected void countAdd(Map<String, Integer> counts, String key) {
        Integer count = counts.get(key);
        if (count == null) {
            count = 0;
        }
        counts.put(key, count + 1);
    }

    protected int count(Map<String, Integer> counts, String key) {
        Integer count = counts.get(key);
        if (count == null) {
            count = 0;
        }
        return count;
    }

    protected String getForKey(String tag, int... ii) {
        return getKey("for-" + tag, ii);
    }

    protected String getIfKey(String tag, int... ii) {
        return getKey("if-" + tag, ii);
    }

    private String getKey(String tag, int... ii) {
        StringBuilder sb = new StringBuilder();
        sb.append(tag);
        Es.ints(ii).ls(new Es.EachEs<Integer>() {
            @Override
            public boolean each(int position, Integer integer) {
                sb.append("-").append(integer);
                return false;
            }
        });
        return sb.toString();
    }


    protected void addForMap(ListValueMap<String, String> map, String key, String... strs) {
        List<String> list = map.get(key);
        Es.es(strs).ls(new Es.EachEs<String>() {
            @Override
            public boolean each(int position, String s) {
                list.add(s);
                return false;
            }
        });
    }


    protected void addForMap(java.util.Map<String, Integer> counts, ListValueMap<String, String> map,
                             String key, int[] keys, String... strs) {
        key = getForKey(key, keys);
        int count = count(counts, key);
        List<String> list = map.get(keyAppend(key, count));
        Es.es(strs).ls(new Es.EachEs<String>() {
            @Override
            public boolean each(int position, String s) {
                list.add(s);
                return false;
            }
        });
        countAdd(counts, key);
    }

    private String keyAppend(String key, int i) {
        return key + "-" + i;
    }

    protected boolean isIf(Map<String, Boolean> ifs, String key) {
        Boolean aBoolean = ifs.get(key);
        return aBoolean == null ? false : aBoolean;
    }

    protected String getParams(List<String> params) {
        return getParams(params, false, false);
    }

    protected String getParams(List<String> params, boolean hasPre, boolean hasNext) {
        if (params == null) {
            params = new ArrayList<>();
        }
        StringBuilder sb = new StringBuilder();
        int count = CountTool.count(params);

        if (hasPre && count != 0) {
            sb.append(", ");
        }
        Es.es(params).ls(new Es.EachEs<String>() {
            @Override
            public boolean each(int position, String param) {
                if (position != 0) {
                    sb.append(", ");
                }
                sb.append(param);
                return false;
            }
        });
        if (hasNext) {
            if (hasPre || count > 0) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    protected List<String> getMethodIntParams(int num) {
        ArrayList<String> params = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            params.add("int i" + i);
        }
        return params;
    }

    protected List<String> getUseMethodIntParams(int num) {
        ArrayList<String> params = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            params.add("i" + i);
        }
        return params;
    }

    public void addLnTag(StringBuilder tag, String line, Object... tags) {
        tag.append(dealLine(line, tags)).append("\r\n");
    }

    public void addTag(StringBuilder tag, String line, Object... tags) {
        tag.append(dealLine(line, tags));
    }

    private String dealLine(String line, Object... values) {
        return TagTools.dealLine(line, values);
    }

}
