package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.constant.Suffix;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.processor.annotation.net.Default;
import com.codingtu.cooltu.processor.annotation.net.Param;
import com.codingtu.cooltu.processor.annotation.net.ParamType;
import com.codingtu.cooltu.processor.bean.NetInfo;
import com.codingtu.cooltu.processor.builder.base.NetBuilderBase;
import com.codingtu.cooltu.processor.lib.param.Params;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.lang.model.element.VariableElement;

public class NetBuilder extends NetBuilderBase {

    public static final NetBuilder BUILDER = new NetBuilder();

    private HashMap<String, String> nameMap = new HashMap<>();

    private List<NetInfo> infos = new ArrayList<>();

    public NetBuilder() {
        super(CurrentPath.javaInfo(FullName.NET));
    }


    public void addNetInfo(NetInfo info) {
        infos.add(info);
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
        addTag(pkg, Pkg.CORE_NET);
        Es.es(infos).ls(new Es.EachEs<NetInfo>() {
            @Override
            public boolean each(int position, NetInfo netInfo) {
                addField(ConvertTool.toStaticType(netInfo.methodName), netInfo.methodName + Suffix.NET_BACK);
                return false;
            }
        });
        Es.es(infos).ls(new Es.EachEs<NetInfo>() {
            @Override
            public boolean each(int methodIndex, NetInfo netInfo) {
                String baseUrl = FullName.CORE_CONFIGS + ".configs().getBaseUrl()";
                if (StringTool.isNotBlank(netInfo.apisBaseUrl)) {
                    baseUrl = ConvertTool.toStaticType(netInfo.apisName);
                    addField(baseUrl, netInfo.apisBaseUrl);
                }

                if (StringTool.isNotBlank(netInfo.methodBaseUrl)) {
                    baseUrl = ConvertTool.toStaticType(netInfo.apisName) + "_" + ConvertTool.toStaticType(netInfo.methodName);
                    addField(baseUrl, netInfo.methodBaseUrl);
                }

                boolean hasParams = !CountTool.isNull(netInfo.params);
                if (hasParams) {
                    String sendParamsFullName = CurrentPath.sendParamsFullName(netInfo.methodName);
                    sendParamsIf(methodIndex, sendParamsFullName);
                    sendParamsGetIf(methodIndex, sendParamsFullName);
                }

                if (netInfo.isJsonBody) {
                    postJsonBodyIf(methodIndex, FullName.JO, FullName.JSON_TOOL);
                }

                Params params = Params.obtain(null);
                ElementTools.getVariableElements(netInfo.params).ls(new Es.EachEs<VariableElement>() {
                    @Override
                    public boolean each(int paramIndex, VariableElement ve) {
                        Default aDefault = ve.getAnnotation(Default.class);
                        Param param = ve.getAnnotation(Param.class);
                        KV<String, String> kv = ElementTools.getFieldKv(ve);
                        if (aDefault == null) {
                            params.add(kv);
                            sendParamsSet(methodIndex, paramIndex, kv.v, kv.v);
                        } else {
                            sendParamsSet(methodIndex, paramIndex, kv.v, "\"" + aDefault.value() + "\"");
                        }

                        if (netInfo.isJsonBody) {
                            postJsonBodySet(methodIndex, paramIndex, kv.v, kv.v);
                        } else if (param.type() == ParamType.JSON_BODY) {
                            methodParams(methodIndex, 0, FullName.NET_TOOL + ".toJsonBody(" + FullName.JSON_TOOL + ".toJson(paramsGet." + kv.v + "))", "");
                        } else {
                            methodParams(methodIndex, paramIndex, "paramsGet." + kv.v, paramIndex != CountTool.count(netInfo.params) - 1 ? "," : "");
                        }
                        return false;
                    }
                });

                if (netInfo.isJsonBody) {
                    methodParams(methodIndex, 0, FullName.NET_TOOL + ".toJsonBody(jo.toJson())", "");
                }

                String serviceFullName = CurrentPath.apiServiceFullName(netInfo.apisName);
                method(methodIndex, FullName.API, netInfo.methodName, params.getMethodParams(), FullName.NET_TOOL,
                        serviceFullName, ConvertTool.toStaticType(netInfo.methodName), baseUrl,
                        CountTool.isNull(netInfo.params) ? "null" : "params");

                return false;
            }
        });
    }

    private void addField(String name, String value) {
        if (!nameMap.containsKey(name)) {
            field(CountTool.count(nameMap), name, value);
            nameMap.put(name, name);
        }
    }

}
/* model_temp_start
package [[pkg]];

public class Net {
                                                                                                    [<sub>][for][field]
    private static final String [name] = "[value]";
                                                                                                    [<sub>][for][field]
                                                                                                    [<sub>][for][method]
    public static [apiFullName] [methodName]([methodParams]) {
                                                                                                    [<sub>][if][sendParams]
        [sendParamFullName] params = new [sendParamFullName]();
                                                                                                    [<sub>][for][sendParamsSet]
        params.[name] = [value];
                                                                                                    [<sub>][for][sendParamsSet]
                                                                                                    [<sub>][if][sendParams]
        return [netToolFullName].api((retrofit, ps) -> {
                                                                                                    [<sub>][if][sendParamsGet]
            [sendParamFullName] paramsGet = ([sendParamFullName]) ps;
                                                                                                    [<sub>][if][sendParamsGet]
                                                                                                    [<sub>][if][postJsonBody]
            [joFullName] jo = [jsonToolFullName].createJO();
                                                                                                    [<sub>][for][postJsonBodySet]
            jo.put("[key]", paramsGet.[name]);
                                                                                                    [<sub>][for][postJsonBodySet]
                                                                                                    [<sub>][if][postJsonBody]

            return retrofit.create([serviceFullName].class).[methodName](
                                                                                                    [<sub>][for][methodParams]
                    [name][divider]
                                                                                                    [<sub>][for][methodParams]
            );
        }, [methodTag], [baseUrl], [params]);
    }
                                                                                                    [<sub>][for][method]

}

model_temp_end */

