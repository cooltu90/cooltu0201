package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.processor.annotation.net.Param;
import com.codingtu.cooltu.processor.annotation.net.ParamType;
import com.codingtu.cooltu.processor.annotation.net.method.GET;
import com.codingtu.cooltu.processor.annotation.net.method.POST;
import com.codingtu.cooltu.processor.annotation.net.method.PUT;
import com.codingtu.cooltu.processor.builder.base.ApiServiceBuilderBase;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

public class ApiServiceBuilder extends ApiServiceBuilderBase {

    private List<ExecutableElement> methods = new ArrayList<>();

    public ApiServiceBuilder(JavaInfo info) {
        super(info);
    }

    public void addMethod(ExecutableElement ee) {
        methods.add(ee);
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
        addTag(pkg, Pkg.CORE_NET_API);
        addTag(name, javaInfo.name);

        int[] count = {0};
        Es.es(methods).ls(new Es.EachEs<ExecutableElement>() {
            @Override
            public boolean each(int methodIndex, ExecutableElement ee) {
                GET getMethod = ee.getAnnotation(GET.class);
                if (getMethod != null) {
                    method(count[0]++, FullName.RETROFIT_GET, getMethod.value(), ElementTools.simpleName(ee));

                    List<? extends VariableElement> parameters = ee.getParameters();
                    int paramCount = CountTool.count(parameters);

                    ElementTools.getVariableElements(parameters).ls(new Es.EachEs<VariableElement>() {
                        @Override
                        public boolean each(int paramIndex, VariableElement element) {
                            KV<String, String> kv = ElementTools.getFieldKv(element);
                            Param param = element.getAnnotation(Param.class);
                            String anno = FullName.RETROFIT_QUERY;

                            switch (param.type()) {
                                case ParamType.PATH:
                                    anno = FullName.RETROFIT_PATH;
                                    break;
                                case ParamType.HEADER:
                                    anno = FullName.RETROFIT_HEADER;
                                    break;
                            }

                            methodParam(methodIndex, paramIndex, anno, kv.k, kv.v, paramIndex != paramCount - 1 ? "," : "");
                            isAnnoValueName(methodIndex, paramIndex, param.encoded());
                            //annoInfoIf(methodIndex, paramIndex, param.value());
                            annoInfoIf(methodIndex, paramIndex, StringTool.isBlank(param.value()) ? kv.v : param.value());
                            isAnnoEncode(methodIndex, paramIndex, param.encoded());
                            return false;
                        }
                    });

                }

                POST postMethod = ee.getAnnotation(POST.class);
                if (postMethod != null) {
                    extracted(methodIndex, ee, postMethod.isJsonBody(), FullName.RETROFIT_POST, postMethod.value());
                }

                PUT putMethod = ee.getAnnotation(PUT.class);
                if (putMethod != null) {
                    extracted(methodIndex, ee, putMethod.isJsonBody(), FullName.RETROFIT_PUT, putMethod.value());
                }


                return false;
            }

            private void extracted(int methodIndex, ExecutableElement ee, boolean isJsonBody, String netType, String apiUrl) {
                if (isJsonBody) {
                    method(count[0]++, netType, apiUrl, ElementTools.simpleName(ee));
                    methodParam(methodIndex, 0, FullName.RETROFIT_BODY, FullName.OKHTTP_REQUEST_BODY, "body", "");
                } else {
                    method(count[0]++, netType, apiUrl, ElementTools.simpleName(ee));
                    List<? extends VariableElement> parameters = ee.getParameters();
                    int paramCount = CountTool.count(parameters);


                    ElementTools.getVariableElements(parameters).ls(new Es.EachEs<VariableElement>() {
                        @Override
                        public boolean each(int paramIndex, VariableElement element) {
                            KV<String, String> kv = ElementTools.getFieldKv(element);
                            Param param = element.getAnnotation(Param.class);
                            String anno = FullName.RETROFIT_QUERY;
                            String type = kv.k;
                            String value = kv.v;

                            switch (param.type()) {
                                case ParamType.PATH:
                                    anno = FullName.RETROFIT_PATH;
                                    break;
                                case ParamType.HEADER:
                                    anno = FullName.RETROFIT_HEADER;
                                    break;
                                case ParamType.JSON_BODY:
                                    anno = FullName.RETROFIT_BODY;
                                    type = FullName.OKHTTP_REQUEST_BODY;
                                    value = "body";
                                    break;
                            }

                            methodParam(methodIndex, paramIndex, anno, type, value, paramIndex != paramCount - 1 ? "," : "");
                            isAnnoValueName(methodIndex, paramIndex, param.encoded());
                            if (param.type() != ParamType.JSON_BODY) {
                                annoInfoIf(methodIndex, paramIndex, StringTool.isBlank(param.value()) ? kv.v : param.value());
                            }
                            isAnnoEncode(methodIndex, paramIndex, param.encoded());
                            return false;
                        }
                    });
                }
            }
        });
    }

}
/* model_temp_start
package [[pkg]];

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public interface [[name]] {
                                                                                                    [<sub>][for][method]

    @[netType]("[apiUrl]")
    Flowable<Result<ResponseBody>> [methodName](
                                                                                                    [<sub>][for][methodParam]
            @[anno][if:annoInfo]([if:annoValueName]value = [if:annoValueName]"[value]"[if:annoEncode], encoded = true[if:annoEncode])[if:annoInfo] [type] [name][divider]
                                                                                                    [<sub>][for][methodParam]
    );
                                                                                                    [<sub>][for][method]

}
model_temp_end */
