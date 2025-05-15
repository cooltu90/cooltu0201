package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class MsThreadBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected StringBuilder name;
    protected StringBuilder subFields;
    protected StringBuilder createSubHandler;
    protected StringBuilder createSubHandlerMethods;
    protected StringBuilder stop;
    protected StringBuilder interfaceName;
    protected StringBuilder typeName;
    protected StringBuilder checkThreadMethods;
    protected StringBuilder dealMainMessage;
    protected StringBuilder sendMessageMethodsForMain;
    protected StringBuilder subThreadMethods;

    public MsThreadBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        name = map.get("name");
        subFields = map.get("subFields");
        createSubHandler = map.get("createSubHandler");
        createSubHandlerMethods = map.get("createSubHandlerMethods");
        stop = map.get("stop");
        interfaceName = map.get("interfaceName");
        typeName = map.get("typeName");
        checkThreadMethods = map.get("checkThreadMethods");
        dealMainMessage = map.get("dealMainMessage");
        sendMessageMethodsForMain = map.get("sendMessageMethodsForMain");
        subThreadMethods = map.get("subThreadMethods");

    }



    @Override
    protected void dealLinesInParent() {

    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("import android.os.Handler;");
        lines.add("import android.os.Looper;");
        lines.add("import android.os.Message;");
        lines.add("");
        lines.add("import com.codingtu.cooltu.lib4a.msthread.CoreMultiMsThread;");
        lines.add("");
        lines.add("public class [[name]] extends CoreMultiMsThread {");
        lines.add("");
        lines.add("    ///////////////////////////////////////////////////////");
        lines.add("    //");
        lines.add("    // 创建方法");
        lines.add("    //");
        lines.add("    ///////////////////////////////////////////////////////");
        lines.add("    private Handler mainHandler;");
        lines.add("[[subFields]]");
        lines.add("    public void start() {");
        lines.add("        createMainHandler();");
        lines.add("[[createSubHandler]]");
        lines.add("    }");
        lines.add("");
        lines.add("    private void createMainHandler() {");
        lines.add("        mainHandler = new Handler(Looper.getMainLooper()) {");
        lines.add("            @Override");
        lines.add("            public void handleMessage(Message msg) {");
        lines.add("                super.handleMessage(msg);");
        lines.add("                handleMessageInMain(msg);");
        lines.add("            }");
        lines.add("        };");
        lines.add("    }");
        lines.add("[[createSubHandlerMethods]]");
        lines.add("    public void stop() {");
        lines.add("[[stop]]");
        lines.add("    }");
        lines.add("    ///////////////////////////////////////////////////////");
        lines.add("    //");
        lines.add("    // 初始化方法");
        lines.add("    //");
        lines.add("    ///////////////////////////////////////////////////////");
        lines.add("    private [[interfaceName]] dealer;");
        lines.add("");
        lines.add("    public static [[name]] obtain() {");
        lines.add("        return new [[name]]();");
        lines.add("    }");
        lines.add("");
        lines.add("    public [[name]] dealer([[interfaceName]] dealer) {");
        lines.add("        this.dealer = dealer;");
        lines.add("        return this;");
        lines.add("    }");
        lines.add("");
        lines.add("    private int type([[typeName]] type) {");
        lines.add("        return type.ordinal();");
        lines.add("    }");
        lines.add("[[checkThreadMethods]]");
        lines.add("");
        lines.add("    ///////////////////////////////////////////////////////");
        lines.add("    //");
        lines.add("    // 主线程的消息处理");
        lines.add("    //");
        lines.add("    ///////////////////////////////////////////////////////");
        lines.add("    private void handleMessageInMain(Message msg) {");
        lines.add("[[dealMainMessage]]");
        lines.add("    }");
        lines.add("[[sendMessageMethodsForMain]]");
        lines.add("[[subThreadMethods]]");
        lines.add("}");
        lines.add("");

        return lines;
    }
}
