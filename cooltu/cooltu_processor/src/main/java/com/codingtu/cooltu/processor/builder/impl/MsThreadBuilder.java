package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.data.map.ValueMap;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.processor.annotation.msthread.MainThread;
import com.codingtu.cooltu.processor.annotation.msthread.SubThread;
import com.codingtu.cooltu.processor.builder.base.MsThreadBuilderBase;
import com.codingtu.cooltu.processor.lib.param.Params;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import java.util.Map;
import java.util.Set;

import javax.lang.model.element.ExecutableElement;

public class MsThreadBuilder extends MsThreadBuilderBase {

    private final String interfaceNameStr;
    private final String typeNameStr;
    private Set<Integer> subThreadNumSet;
    private Map<String, ExecutableElement> mainMethodMap;
    private ValueMap<Integer, Map<String, ExecutableElement>> subMethodMap;
    private Map<Integer, String> startTypeMap;

    public MsThreadBuilder(JavaInfo info, String interfaceName, String typeName) {
        super(info);
        this.interfaceNameStr = interfaceName;
        this.typeNameStr = typeName;
    }

    public void addSubThreadNumSet(Set<Integer> subThreadNumSet) {
        this.subThreadNumSet = subThreadNumSet;
    }

    public void addMainMethodMap(Map<String, ExecutableElement> mainMethodMap) {
        this.mainMethodMap = mainMethodMap;
    }

    public void addSubMethodMap(ValueMap<Integer, Map<String, ExecutableElement>> subMethodMap) {
        this.subMethodMap = subMethodMap;
    }

    public void addStartTypeMap(Map<Integer, String> startTypeMap) {
        this.startTypeMap = startTypeMap;
    }

    @Override
    protected void dealLines() {
        addTag(pkg, Pkg.CORE_MSTHREAD);
        addTag(name, javaInfo.name);
        addTag(interfaceName, interfaceNameStr);
        addTag(typeName, typeNameStr);

        Es.es(subThreadNumSet).ls(new Es.EachEs<Integer>() {
            @Override
            public boolean each(int position, Integer index) {

                addLnTag(subFields, "    private Handler subHandler[0];", index);

                addLnTag(createSubHandler, "        new Thread(new Runnable() {");
                addLnTag(createSubHandler, "            @Override");
                addLnTag(createSubHandler, "            public void run() {");
                addLnTag(createSubHandler, "                createSubHandler[0]();", index);
                addLnTag(createSubHandler, "            }");
                addLnTag(createSubHandler, "        }).start();");

                addLnTag(createSubHandlerMethods, "");
                addLnTag(createSubHandlerMethods, "    private void createSubHandler[0]() {", index);
                addLnTag(createSubHandlerMethods, "        Looper.prepare();");
                addLnTag(createSubHandlerMethods, "        subHandler[0] = new Handler(Looper.myLooper()) {", index);
                addLnTag(createSubHandlerMethods, "            @Override");
                addLnTag(createSubHandlerMethods, "            public void handleMessage(Message msg) {");
                addLnTag(createSubHandlerMethods, "                super.handleMessage(msg);");
                addLnTag(createSubHandlerMethods, "                handleMessageInThread[0](msg);", index);
                addLnTag(createSubHandlerMethods, "            }");
                addLnTag(createSubHandlerMethods, "        };");
                addLnTag(createSubHandlerMethods, "        sendMessage(subHandler[0], subThread[0]StartType(), 0l);", index, index);
                addLnTag(createSubHandlerMethods, "        Looper.loop();");
                addLnTag(createSubHandlerMethods, "    }");

                addLnTag(checkThreadMethods, "");
                addLnTag(checkThreadMethods, "    protected boolean isSubThread[0]() {", index);
                addLnTag(checkThreadMethods, "        return Thread.currentThread() == subHandler[0].getLooper().getThread();", index);
                addLnTag(checkThreadMethods, "    }");


                Map<String, ExecutableElement> subMethodMap1 = subMethodMap.get(index);
                String typeName = startTypeMap.get(index);

                addLnTag(subThreadMethods, "");
                addLnTag(subThreadMethods, "    ///////////////////////////////////////////////////////");
                addLnTag(subThreadMethods, "    //");
                addLnTag(subThreadMethods, "    // 线程[0]的消息处理", index);
                addLnTag(subThreadMethods, "    //");
                addLnTag(subThreadMethods, "    ///////////////////////////////////////////////////////");
                addLnTag(subThreadMethods, "    private int subThread[0]StartType() {", index);
                addLnTag(subThreadMethods, "        return type([SubThreadActivityMsThreadType].[DEAL_DATA_START]);", typeNameStr, typeName);
                addLnTag(subThreadMethods, "    }");
                addLnTag(subThreadMethods, "");
                addLnTag(subThreadMethods, "    private void handleMessageInThread[0](Message msg) {", index);

                StringBuilder sendMessageMethodsForSub = new StringBuilder();

                dealSubThread(subMethodMap1,
                        subThreadMethods, sendMessageMethodsForSub,
                        "isSubThread" + index, "subHandler" + index, new DelayInfoGetter() {
                            @Override
                            public DelayInfo obtainDelayInfo(ExecutableElement ee) {
                                SubThread subThread = ee.getAnnotation(SubThread.class);
                                DelayInfo delayInfo = new DelayInfo();
                                delayInfo.isDelay = subThread.isDelay();
                                delayInfo.delayMillis = subThread.defaultDelayMillis();
                                return delayInfo;
                            }
                        });

                addLnTag(subThreadMethods, "    }");
                addLnTag(subThreadMethods, sendMessageMethodsForSub.toString());

                addLnTag(stop, "        subHandler[0].getLooper().quitSafely();", index);

                return false;
            }
        });

        dealSubThread(mainMethodMap,
                dealMainMessage, sendMessageMethodsForMain,
                "isMainThread", "mainHandler", new DelayInfoGetter() {
                    @Override
                    public DelayInfo obtainDelayInfo(ExecutableElement ee) {
                        MainThread mainThread = ee.getAnnotation(MainThread.class);
                        DelayInfo delayInfo = new DelayInfo();
                        delayInfo.isDelay = mainThread.isDelay();
                        delayInfo.delayMillis = mainThread.defaultDelayMillis();
                        return delayInfo;
                    }
                });


    }

    public static class DelayInfo {
        public boolean isDelay;
        public long delayMillis;
    }

    public static interface DelayInfoGetter {
        public DelayInfo obtainDelayInfo(ExecutableElement ee);
    }

    private void dealSubThread(Map<String, ExecutableElement> methodMap,
                               StringBuilder dealMessageSb, StringBuilder sendMessageMethodsSb,
                               String checkThread, String handlerName, DelayInfoGetter delayInfoGetter) {
        Es.maps(methodMap).ls(new Es.MapEach<String, ExecutableElement>() {
            @Override
            public boolean each(String type, ExecutableElement element) {

                addLnTag(dealMessageSb, "        if (msg.what == type([SubThreadActivityMsThreadType].[DEAL_TOAST])) {", typeNameStr, type);

                String methodName = ElementTools.simpleName(element);
                Params params = ElementTools.getMethodParamKvs(element);
                if (params.count() == 0) {
                    addLnTag(dealMessageSb, "            dealer.[dealToast]();", methodName);
                } else if (params.count() == 1) {
                    KV<String, String> kv = params.getKvs().get(0);
                    addLnTag(dealMessageSb, "            dealer.[dealToast](([String]) msg.obj);", methodName, kv.k);
                } else {
                    addLnTag(dealMessageSb, "            Object[] objects = (Object[]) msg.obj;");
                    String param = params.getParam(new Params.Convert() {
                        @Override
                        public String convert(int index, KV<String, String> kv) {
                            return "(" + kv.k + ") objects[" + index + "]";
                        }
                    });
                    addLnTag(dealMessageSb, "            dealer.[dealCheckData]([params]);", methodName, param);

                }

                addLnTag(dealMessageSb, "            return;");
                addLnTag(dealMessageSb, "        }");

                String methodParams = params.getMethodParams();

                DelayInfo delayInfo = delayInfoGetter.obtainDelayInfo(element);
                StringBuilder delayParamSb = new StringBuilder();
                StringBuilder delayParamSb1 = new StringBuilder();

                long delayMillis = 0;
                if (delayInfo.isDelay) {
                    if (delayInfo.delayMillis < 0) {
                        delayParamSb.append("long delayMillis");
                        delayParamSb1.append(", delayMillis");
                        if (StringTool.isNotBlank(methodParams)) {
                            delayParamSb.append(", ");
                        }
                    } else {
                        delayParamSb1.append(", ").append(delayInfo.delayMillis).append("l");
                        delayMillis = delayInfo.delayMillis;
                    }
                } else {
                    delayParamSb1.append(", 0l");
                }

                addLnTag(sendMessageMethodsSb, "");
                addLnTag(sendMessageMethodsSb, "    public boolean sendMessageFor[DealToast]([delayParam][String str]) {",
                        ConvertTool.toClassType(methodName), delayParamSb.toString(), methodParams);
                addLnTag(sendMessageMethodsSb, "        if (![isMainThread]()) {", checkThread);

                addLnTag(sendMessageMethodsSb, "            sendMessage([mainHandler], type([FtpPlayActivityMSThreadType].[DEAL_TOAST])[delayParam][, str]);"
                        , handlerName, typeNameStr, type, delayParamSb1.toString(), params.getParams(true, false));
                addLnTag(sendMessageMethodsSb, "            return true;");
                addLnTag(sendMessageMethodsSb, "        }");

                if (delayMillis > 0) {
                    addLnTag(sendMessageMethodsSb, "        try {");
                    addLnTag(sendMessageMethodsSb, "            Thread.sleep([delayMills]l);", delayMillis);
                    addLnTag(sendMessageMethodsSb, "        } catch (Exception e) {");
                    addLnTag(sendMessageMethodsSb, "        }");
                }


                addLnTag(sendMessageMethodsSb, "        return false;");
                addLnTag(sendMessageMethodsSb, "    }");

                return false;
            }
        });
    }


}
/* model_temp_start
package [[pkg]];

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.codingtu.cooltu.lib4a.msthread.CoreMultiMsThread;

public class [[name]] extends CoreMultiMsThread {

    ///////////////////////////////////////////////////////
    //
    // 创建方法
    //
    ///////////////////////////////////////////////////////
    private Handler mainHandler;
[[subFields]]
    public void start() {
        createMainHandler();
[[createSubHandler]]
    }

    private void createMainHandler() {
        mainHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                handleMessageInMain(msg);
            }
        };
    }
[[createSubHandlerMethods]]
    public void stop() {
[[stop]]
    }
    ///////////////////////////////////////////////////////
    //
    // 初始化方法
    //
    ///////////////////////////////////////////////////////
    private [[interfaceName]] dealer;

    public static [[name]] obtain() {
        return new [[name]]();
    }

    public [[name]] dealer([[interfaceName]] dealer) {
        this.dealer = dealer;
        return this;
    }

    private int type([[typeName]] type) {
        return type.ordinal();
    }
[[checkThreadMethods]]

    ///////////////////////////////////////////////////////
    //
    // 主线程的消息处理
    //
    ///////////////////////////////////////////////////////
    private void handleMessageInMain(Message msg) {
[[dealMainMessage]]
    }
[[sendMessageMethodsForMain]]
[[subThreadMethods]]
}

model_temp_end */