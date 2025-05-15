package com.codingtu.cooltu.processor.lib;

import com.codingtu.cooltu.lib4j.data.map.ListValueMap;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.processor.BuilderType;
import com.codingtu.cooltu.processor.builder.core.CoreBuilder;

import java.util.List;
import java.util.Map;

public class BuilderMap {
    public static final Map<Integer, List<CoreBuilder>> MAP = new ListValueMap<>();

    /**************************************************
     *
     * 添加方法
     *
     **************************************************/

    public static void add(CoreBuilder builder) {
        add(BuilderType.DEFAULT, builder);
    }

    public static void add(BuilderType type, CoreBuilder builder) {
        MAP.get(type.ordinal()).add(builder);
    }

    /**************************************************
     *
     * 查找
     *
     **************************************************/
    public static <E> E find(BuilderType type, String id) {
        return (E) Es.es(MAP.get(type.ordinal())).getBySymbol(id);
    }

    /**************************************************
     *
     * 创建
     *
     **************************************************/
    public static void create() {
        Es.es(BuilderType.values()).ls(new Es.EachEs<BuilderType>() {
            @Override
            public boolean each(int position, BuilderType modelType) {
                create(modelType.ordinal());
                return false;
            }
        });
        MAP.clear();
    }

    private static void create(int type) {
        List<CoreBuilder> models = MAP.get(type);
        Es.es(models).ls(new Es.EachEs<CoreBuilder>() {
            @Override
            public boolean each(int position, CoreBuilder model) {
                model.create();
                return false;
            }
        });
    }
}
