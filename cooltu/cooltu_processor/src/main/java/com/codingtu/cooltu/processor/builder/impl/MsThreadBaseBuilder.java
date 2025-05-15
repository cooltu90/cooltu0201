package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.processor.annotation.msthread.MainThread;
import com.codingtu.cooltu.processor.annotation.msthread.SubThread;
import com.codingtu.cooltu.processor.builder.base.MsThreadBaseBuilderBase;
import com.codingtu.cooltu.processor.lib.param.Params;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import javax.lang.model.element.ExecutableElement;

public class MsThreadBaseBuilder extends MsThreadBaseBuilderBase {
    private final String interfaceNameStr;
    private final String msThreadTypeStr;
    private final String msThreadFieldName;
    public BaseEs<ExecutableElement> msThreadMethodEs = Es.es();
    public String baseFullName;

    public MsThreadBaseBuilder(JavaInfo info, String interfaceName, String typeName) {
        super(info);
        this.interfaceNameStr = interfaceName;
        this.msThreadTypeStr = typeName;
        this.msThreadFieldName = ConvertTool.toMethodType(msThreadTypeStr);
    }

//    @Override
//    protected boolean isBuild() {
//        return false;
//    }
//
//    @Override
//    protected void beforeBuild(List<String> lines) {
//        super.beforeBuild(lines);
//        Logs.i(lines);
//    }

    @Override
    protected void dealLines() {
        addTag(pkg, Pkg.CORE_MSTHREAD);
        addTag(name, javaInfo.name);
        addTag(interfaceType, interfaceNameStr);
        addTag(msThreadType, msThreadTypeStr);
        addTag(msThreadName, ConvertTool.toMethodType(msThreadTypeStr));

        msThreadMethodEs.ls(new Es.EachEs<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement element) {
                Params params = ElementTools.getMethodParamKvs(element);

                String simpleName = ElementTools.simpleName(element);
                String methodParams = params.getMethodParams();
                String params1 = params.getParams();
                String sendMethodName = ConvertTool.toClassType(simpleName);

                boolean isDelay = false;
                long defaultDelayMillis = -1;
                MainThread mainThread = element.getAnnotation(MainThread.class);
                if (mainThread != null) {
                    isDelay = mainThread.isDelay();
                    defaultDelayMillis = mainThread.defaultDelayMillis();
                }

                SubThread subThread = element.getAnnotation(SubThread.class);
                if (subThread != null) {
                    isDelay = subThread.isDelay();
                    defaultDelayMillis = subThread.defaultDelayMillis();
                }

                StringBuilder delayParamSb = new StringBuilder();
                StringBuilder delayParamSb1 = new StringBuilder();
                if (isDelay) {
                    if (defaultDelayMillis < 0) {
                        delayParamSb.append("long delayMillis");
                        delayParamSb1.append("delayMillis");
                        if (StringTool.isNotBlank(methodParams)) {
                            delayParamSb.append(", ");
                        }
                        if (StringTool.isNotBlank(params1)) {
                            delayParamSb1.append(", ");
                        }
                    }
                }

                addLnTag(methods, "    @Override");
                addLnTag(methods, "    public void [dealDataStart]([params]) {",
                        simpleName, methodParams);
                addLnTag(methods, "    }");
                addLnTag(methods, "");

                addLnTag(methods, "    protected boolean sendMessageFor[DealToast]([delayParam][String str]) {",
                        sendMethodName, delayParamSb.toString(), methodParams);
                addLnTag(methods, "        if ([ftpPlayActivityMSThread] != null) {", msThreadFieldName);
                addLnTag(methods, "            return [ftpPlayActivityMSThread].sendMessageFor[DealToast]([delayParam][str]);",
                        msThreadFieldName, sendMethodName, delayParamSb1.toString(), params1);
                addLnTag(methods, "        }");
                addLnTag(methods, "        return true;");
                addLnTag(methods, "    }");

                return false;
            }
        });

        if (StringTool.isNotBlank(baseFullName) && ClassTool.isNotVoid(baseFullName)) {
            //
            addTag(base, "<THIS extends [CheckUploadNewBaseForMsThread]> extends [CheckUploadBase]<THIS>",
                    javaInfo.name, baseFullName);
        }


    }
}
/* model_temp_start
package [[pkg]];

public class [[name]][[base]] implements [[interfaceType]] {

    protected [[msThreadType]] [[msThreadName]];

    public void start() {
        [[msThreadName]] = [[msThreadType]].obtain().dealer(this);
        [[msThreadName]].start();
    }

[[methods]]

    protected void stopMsThread() {
        if ([[msThreadName]] != null)
            [[msThreadName]].stop();
        [[msThreadName]] = null;
    }

}
model_temp_end */