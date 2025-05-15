package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.processor.builder.base.Code4RequestBuilderBase;
import com.codingtu.cooltu.processor.deal.ResForDeal;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Code4RequestBuilder extends Code4RequestBuilderBase {

    public static final Code4RequestBuilder BUILDER = new Code4RequestBuilder();

    public Map<String, String> fullNames = new HashMap<>();

    public Code4RequestBuilder() {
        super(CurrentPath.javaInfo(FullName.CODE_4_REQUEST));
    }


    public void addAct(String classFullName) {
        String s = CurrentPath.actStaticName(classFullName);
        fullNames.put(s, s);
    }

    public void add(String name) {
        fullNames.put(name, name);
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
    }

    @Override
    protected void dealLines() {
        addTag(pkg, Pkg.CORE_TOOLS);

        Es.es(ResForDeal.HAS_START_MAP.keySet()).ls(new Es.EachEs<String>() {
            @Override
            public boolean each(int position, String s) {
                //field(position, CurrentPath.actStaticName(s), position + "");
                fullNames.put(CurrentPath.actStaticName(s), "");
                return false;
            }
        });


        Es.es(fullNames.keySet()).ls(new Es.EachEs<String>() {
            @Override
            public boolean each(int position, String s) {
                field(position, s, position + "");
                return false;
            }
        });

//        Ts.ts(ResForDeal.HAS_START_MAP.keySet()).ls(new BaseTs.EachTs<String>() {
//            @Override
//            public boolean each(int position, String s) {
//                field(position, CurrentPath.actStaticName(s), position + "");
//                return false;
//            }
//        });
    }

}
/* model_temp_start
package [[pkg]];

public class Code4Request {
                                                                                                    [<sub>][for][field]
    public static final int [name] = [value];
                                                                                                    [<sub>][for][field]

}
model_temp_end */

