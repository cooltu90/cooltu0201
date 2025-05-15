package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.data.map.ListValueMap;
import com.codingtu.cooltu.lib4j.data.map.ValueMap;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.processor.annotation.ui.StartGroup;
import com.codingtu.cooltu.processor.builder.base.ActStartBuilderBase;
import com.codingtu.cooltu.processor.deal.ResForDeal;
import com.codingtu.cooltu.processor.lib.param.Params;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.BaseTools;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.VariableElement;

public class ActStartBuilder extends ActStartBuilderBase {
    public static final ActStartBuilder BUILDER = new ActStartBuilder();

    private ValueMap<String, ListValueMap<Integer, KV<String, String>>> map = new ValueMap<String, ListValueMap<Integer, KV<String, String>>>() {
        @Override
        protected ListValueMap<Integer, KV<String, String>> newValue() {
            return new ListValueMap<>();
        }
    };

    public ActStartBuilder() {
        super(CurrentPath.javaInfo(FullName.ACT_START));
    }

    private void add(String actFullName, int index, KV<String, String> kv) {
        map.get(actFullName).get(index).add(kv);
    }

    private void add(String actFullName) {
        map.get(actFullName).get(0);
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
        //
        Es.es(ResForDeal.HAS_START_MAP.keySet()).ls(new Es.EachEs<String>() {
            @Override
            public boolean each(int position, String actClass) {
                List<VariableElement> startGroups = new ArrayList<>();

                BaseTools.getThisWithParents(actClass, new BaseTools.GetParent<String>() {
                    @Override
                    public String getParent(String actClass) {
                        ActBaseBuilder builder = CurrentPath.actBaseBuilder(actClass);
                        if (builder != null) {
                            return builder.getUiBaseBuilder().baseClass;
                        } else {
                            return null;
                        }
                    }
                }, new Es.EachEs<String>() {
                    @Override
                    public boolean each(int position, String actClass) {
                        startGroups.addAll(ResForDeal.START_MAP.get(actClass));
                        return false;
                    }
                });

                if (CountTool.isNull(startGroups)) {
                    add(actClass);
                } else {
                    Es.es(startGroups).ls(new Es.EachEs<VariableElement>() {
                        @Override
                        public boolean each(int position, VariableElement ve) {
                            StartGroup startGroup = ve.getAnnotation(StartGroup.class);
                            KV<String, String> kv = ElementTools.getFieldKv(ve);
                            if (CountTool.isNull(startGroup.value())) {
                                add(actClass, 0, kv);
                            } else {
                                Es.ints(startGroup.value()).ls(new Es.EachEs<Integer>() {
                                    @Override
                                    public boolean each(int position, Integer integer) {
                                        add(actClass, integer, kv);
                                        return false;
                                    }
                                });
                            }
                            return false;
                        }
                    });
                }
                return false;
            }
        });
        //


        int[] index = {0};

        Es.es(map.keySet()).ls(new Es.EachEs<String>() {
            @Override
            public boolean each(int position, String actFullName) {
                ListValueMap<Integer, KV<String, String>> actMap = map.get(actFullName);
                if (!actMap.containsKey(0)) {
                    actMap.get(0);
                }

                Es.es(actMap.keySet()).ls(new Es.EachEs<Integer>() {
                    @Override
                    public boolean each(int position, Integer integer) {
                        List<KV<String, String>> kvs = actMap.get(integer);
                        Es.es(kvs).ls(new Es.EachEs<KV<String, String>>() {
                            @Override
                            public boolean each(int position, KV<String, String> kv) {
                                if (ClassTool.isBaseClass(kv.k)) {
                                    methodIntent(index[0], position, ConvertTool.toStaticType(kv.v), kv.v);
                                } else {
                                    methodIntent(index[0], position, ConvertTool.toStaticType(kv.v),
                                            FullName.JSON_TOOL + ".toJson(" + kv.v + ")");
                                }

                                return false;
                            }
                        });
                        Params params = Params.obtain(kvs);
                        String methodParams = params.getMethodParams(true, false);
                        method(index[0], ConvertTool.toMethodType(CurrentPath.javaInfo(actFullName).name), methodParams, actFullName, FullName.ACT_TOOL, CurrentPath.actStaticName(actFullName));
                        index[0]++;
                        return false;
                    }
                });
                return false;
            }
        });
    }
}
/* model_temp_start
package [[pkg]];

import android.app.Activity;
import android.content.Intent;

public class ActStart {
                                                                                                    [<sub>][for][method]
    public static final void [methodName](Activity act[param]) {
        Intent intent = new Intent(act, [actFullName].class);
        intent.putExtra(Pass.FROM_ACT, act.getClass().getCanonicalName());
                                                                                                    [<sub>][for][methodIntent]
        intent.putExtra(Pass.[fieldName], [value]);
                                                                                                    [<sub>][for][methodIntent]
        [actToolFullName].startActivityForResult(act, intent, Code4Request.[codeName]);
    }
                                                                                                    [<sub>][for][method]
}
model_temp_end */