package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.processor.builder.base.PassBuilderBase;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PassBuilder extends PassBuilderBase {

    public static PassBuilder BUILDER = new PassBuilder();

    private List<KV<String, String>> kvs = new ArrayList<>();

    private HashMap<String, String> names = new HashMap<>();


    public PassBuilder() {
        super(CurrentPath.javaInfo(FullName.PASS));
        add(new KV<>(FullName.STRING, Constant.FROM_ACT));
    }

    public void add(KV<String, String> kv) {
        if (names.get(kv.v) == null) {
            names.put(kv.v, kv.v);
            kvs.add(kv);
        }
    }

    @Override
    protected boolean isBuild() {
        return true;
    }

    @Override
    protected boolean isGetLines() {
        return true;
    }


    @Override
    protected void beforeBuild(List<String> lines) {
        super.beforeBuild(lines);
        //Logs.i(lines);
    }

    @Override
    protected void dealLines() {
        addTag(pkg, Pkg.CORE_TOOLS);

        Es.es(kvs).ls(new Es.EachEs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                String fieldName = ConvertTool.toStaticType(kv.v);
                field(position, fieldName, kv.v);
                if (ClassTool.isInt(kv.k)) {
                    method(position, "int", kv.v);
                    isOtherIf(position, "Int", fieldName, ", -1");
                } else if (ClassTool.isString(kv.k)) {
                    method(position, "String", kv.v);
                    isOtherIf(position, "String", fieldName, "");
                } else if (ClassTool.isLong(kv.k)) {
                    method(position, "long", kv.v);
                    isOtherIf(position, "Long", fieldName, ", -1");
                } else if (ClassTool.isDouble(kv.k)) {
                    method(position, "double", kv.v);
                    isOtherIf(position, "Double", fieldName, ", -1");
                } else if (ClassTool.isFloat(kv.k)) {
                    method(position, "float", kv.v);
                    isOtherIf(position, "Float", fieldName, ", -1");
                } else if (ClassTool.isBoolean(kv.k)) {
                    method(position, "boolean", kv.v);
                    isOtherIf(position, "Boolean", fieldName, ", false");
                } else if (ClassTool.isList(kv.k)) {
                    String beanType = StringTool.getSub(kv.k, "List", "<", ">");
                    method(position, kv.k, kv.v);
                    isBeanListIf(position, FullName.JSON_TOOL, beanType, fieldName);
                } else {
                    method(position, kv.k, kv.v);
                    isBeanIf(position, FullName.JSON_TOOL, kv.k, fieldName);
                }

                return false;
            }
        });

    }
}
/* model_temp_start
package [[pkg]];

import android.content.Intent;

public class Pass {
                                                                                                    [<sub>][for][field]
    public static final String [name] = "[value]";
                                                                                                    [<sub>][for][field]
                                                                                                    [<sub>][for][method]
    public static final [type] [methodName](Intent data) {
                                                                                                    [<sub>][if][isBean]
        return [jsonToolFullName].toBean([beanClass].class, data.getStringExtra([fieldName]));
                                                                                                    [<sub>][if][isBean]
                                                                                                    [<sub>][if][isBeanList]
        return [jsonToolFullName].toBeanList([beanClass].class, data.getStringExtra([fieldName]));
                                                                                                    [<sub>][if][isBeanList]
                                                                                                    [<sub>][if][isOther]
        return data.get[methodType]Extra([fieldName][defaultValue]);
                                                                                                    [<sub>][if][isOther]
    }
                                                                                                    [<sub>][for][method]

}

model_temp_end */