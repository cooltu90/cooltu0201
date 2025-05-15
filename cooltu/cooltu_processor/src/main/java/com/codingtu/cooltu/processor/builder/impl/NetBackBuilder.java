package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.processor.builder.base.NetBackBuilderBase;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;

import java.util.List;

import javax.lang.model.element.ExecutableElement;

public class NetBackBuilder extends NetBackBuilderBase {

    private final ExecutableElement ee;

    public NetBackBuilder(JavaInfo info, ExecutableElement ee) {
        super(info);
        this.ee = ee;
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
        addTag(name, javaInfo.name);
        addTag(netBackFullName, FullName.NET_BACK);
        addTag(coreSendParamsFullName, FullName.CORE_SEND_PARAMS);

        String typeName = ee.getReturnType().toString();
        if (ClassTool.isType(typeName, void.class, Void.class)) {

        } else if (ClassTool.isList(typeName)) {
            String beanType = StringTool.getSub(typeName, "List", "<", ">");
            String name = ConvertTool.toMethodType(CurrentPath.javaInfo(beanType).name) + "s";
            fieldIf(typeName, name);
            acceptIf(FullName.STRING_TOOL, name, FullName.JSON_TOOL, "toBeanList", beanType);
        } else if (ClassTool.isString(typeName)) {

        } else {
            String name = ConvertTool.toMethodType(CurrentPath.javaInfo(typeName).name);
            fieldIf(typeName, name);
            acceptIf(FullName.STRING_TOOL, name, FullName.JSON_TOOL, "toBean", typeName);
        }

    }
}
/* model_temp_start
package core.net.back;

import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public class [[name]] extends [[netBackFullName]] {
                                                                                                    [<sub>][if][field]
    public [type] [name];
                                                                                                    [<sub>][if][field]
    @Override
    public void accept(String code, Result<ResponseBody> result, [[coreSendParamsFullName]] params, List objs) {
        super.accept(code, result, params, objs);
                                                                                                    [<sub>][if][accept]
        if ([stringToolFullName].isNotBlank(json)) {
            [name] = [jsonToolFullName].[method]([type].class, json);
        }
                                                                                                    [<sub>][if][accept]

    }

}
model_temp_end */
