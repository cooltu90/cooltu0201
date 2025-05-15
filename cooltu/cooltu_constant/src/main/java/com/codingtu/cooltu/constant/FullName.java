package com.codingtu.cooltu.constant;

import javax.swing.text.View;

/**************************************************
 *
 * 如果更改框架结构。可能会修改这里
 *
 **************************************************/
public class FullName {

    public static String BASE_ACT;
    public static String BASE_FRAGMENT;

    public static final String PROCESS_CORE_BUILDER = Pkg.PROCESSOR_BUILDER_CORE + ".CoreBuilder";
    public static final String PROCESS_CORE_BUILDER_FIELD = Pkg.PROCESSOR_BUILDER_CORE + ".FieldCoreBuilder";
    public static final String PROCESS_CORE_BUILDER_METHOD = Pkg.PROCESSOR_BUILDER_CORE + ".MethodCoreBuilder";
    public static final String PROCESS_CORE_BUILDER_TYPE = Pkg.PROCESSOR_BUILDER_CORE + ".TypeCoreBuilder";
    public static final String BIND_TOOL = "com.codingtu.cooltu.lib4a.bind.BindTool";
    public static final String BASE_PATH = "com.codingtu.cooltu.lib4a.path.BasePath";
    public static final String CORE_CONFIGS = "com.codingtu.cooltu.lib4a.CoreConfigs";
    public static final String LIST_VALUE_MAP = "com.codingtu.cooltu.lib4j.data.map.ListValueMap";
    public static final String JAVA_INFO = "com.codingtu.cooltu.lib4j.data.java.JavaInfo";
    public static final String PROCESSOR_PARAMS = "com.codingtu.cooltu.processor.lib.param.Params";
    public static final String SUB_BUILDER = "com.codingtu.cooltu.processor.bean.SubBuilder";
    public static final String ANNOTATION_VH = "com.codingtu.cooltu.processor.annotation.ui.VH";
    public static final String FORM_TYPE = "com.codingtu.cooltu.processor.annotation.form.FormType";
    public static final String CORE_ADAPTER_VH = "com.codingtu.cooltu.lib4a.ui.adapter.viewholder.CoreAdapterVH";
    public static final String CORE_LIST_ADAPTER_SHORT_NAME = "CoreListAdapter";
    public static final String CORE_LIST_ADAPTER = "com.codingtu.cooltu.lib4a.ui.adapter." + CORE_LIST_ADAPTER_SHORT_NAME;
    public static final String CORE_MORE_LIST_ADAPTER_SHORT_NAME = "CoreMoreListAdapter";
    public static final String CORE_MORE_LIST_ADAPTER = "com.codingtu.cooltu.lib4a.ui.adapter." + CORE_MORE_LIST_ADAPTER_SHORT_NAME;
    //config
    public static final String RECYCLER_VIEW_DEFAULT_CONFIG = "com.codingtu.cooltu.lib4a.ui.recyclerview.DefaultConfig";
    //view
    public static final String HANDLER_TEXT_WATCHER = "com.codingtu.cooltu.lib4a.view.textview.HandlerTextWatcher";
    public static final String HANDLER_ON_SELECT_CHANGE = "com.codingtu.cooltu.lib4a.view.combine.HandlerOnSelectChange";
    public static final String HANDLER_ON_SEEK_BAR_CHANGE_LISTENER = "com.codingtu.cooltu.lib4a.view.combine.HandlerOnSeekBarChangeListener";
    public static final String RADIO_GROUP = "com.codingtu.cooltu.lib4a.view.combine.RadioGroup";
    //form
    public static final String DEFAULT_EDIT_TEXT_PUSH = Pkg.LIB4A_FORM_PUSH + ".DefaultEditTextPush";
    public static final String DEFAULT_RADIO_GROUP_PUSH = Pkg.LIB4A_FORM_PUSH + ".DefaultRadioGroupPush";
    public static final String DEFAULT_SEEK_BAR_PUSH = Pkg.LIB4A_FORM_PUSH + ".DefaultSeekBarPush";
    public static final String FORM_TOOL = Pkg.LIB4A_FORM + ".FormTool";
    public static final String FORM_HANDLER = Pkg.LIB4A_FORM + ".FormHandler";
    public static final String FORM_HANDLE_CALL_BACK = Pkg.LIB4A_FORM + ".FormHandleCallBack";
    //lib4j_tools
    public static final String STRING_TOOL = Pkg.LIB4J_TOOLS + ".StringTool";
    public static final String COUNT_TOOL = Pkg.LIB4J_TOOLS + ".CountTool";

    public static final String ON_DESTROY = Pkg.LIB4J_DESTORY + ".OnDestroy";
    public static final String DESTROYS = Pkg.LIB4J_DESTORY + ".Destroys";

    //lib4a_tools
    public static final String MOBILE_TOOL = Pkg.LIB4A_TOOLS + ".MobileTool";
    public static final String INFLATE_TOOL = Pkg.LIB4A_TOOLS + ".InflateTool";
    public static final String SDCARD_TOOL = Pkg.LIB4A_TOOLS + ".SDCardTool";
    public static final String ACT_TOOL = Pkg.LIB4A_TOOLS + ".ActTool";
    public static final String VIEW_TOOL = Pkg.LIB4A_TOOLS + ".ViewTool";
    public static final String DESTORY_TOOL = Pkg.LIB4A_TOOLS + ".DestoryTool";
    public static final String RESOURCE_TOOL = Pkg.LIB4A_TOOLS + ".ResourceTool";
    public static final String HANDLER_TOOL = Pkg.LIB4A_TOOLS + ".HandlerTool";
    public static final String PERMISSION_TOOL = Pkg.LIB4A_PERMISSION + ".PermissionTool";
    //permission
    public static final String PERMISSIONS_SHORT_NAME = "Permissions";
    public static final String PERMISSIONS = Pkg.CORE_TOOLS + "." + PERMISSIONS_SHORT_NAME;
    //to
    public static final String TO_SHORT_NAME = "To";
    public static final String TO = Pkg.ANNOTATION_TOOLS + "." + TO_SHORT_NAME;
    //actbase
    public static final String ACT_BASE_SHORT_NAME = "ActBase";
    public static final String ACT_BASE = Pkg.ANNOTATION_UI + "." + ACT_BASE_SHORT_NAME;
    //actbase
    public static final String FRAGMENT_BASE_SHORT_NAME = "FragmentBase";
    public static final String FRAGMENT_BASE = Pkg.ANNOTATION_UI + "." + FRAGMENT_BASE_SHORT_NAME;
    //res
    public static final String RES_FOR_SHORT_NAME = "ResFor";
    public static final String RES_FOR = Pkg.ANNOTATION_RES + "." + RES_FOR_SHORT_NAME;
    //res
    public static final String RES_FOR_FRAGMENT_SHORT_NAME = "ResForFragment";
    public static final String RES_FOR_FRAGMENT = Pkg.ANNOTATION_RES + "." + RES_FOR_FRAGMENT_SHORT_NAME;
    //net
    public static final String NET_TOOL = Pkg.LIB4A_NET + ".NetTool";
    public static final String CORE_SEND_PARAMS = Pkg.LIB4A_NET + ".bean.CoreSendParams";
    public static final String API = Pkg.LIB4A_NET + ".api.API";
    public static final String NET_BACK_I = Pkg.LIB4A_NET + ".netback.NetBackI";
    public static final String NET_BACK = Pkg.LIB4A_NET + ".netback.NetBack";
    //core_tools
    public static final String PASS = Pkg.CORE_TOOLS + ".Pass";
    public static final String ACT_START = Pkg.CORE_TOOLS + ".ActStart";
    public static final String NET = Pkg.CORE_NET + ".Net";
    public static final String CODE_4_REQUEST = Pkg.CORE_TOOLS + ".Code4Request";
    public static final String ACT_BACK_INTENT = Pkg.CORE_TOOLS + ".ActBackIntent";
    //Ts
    public static final String BASE_TS = Pkg.LIB4J_TS + ".BaseTs";
    public static final String TS = Pkg.LIB4J_TS + ".Ts";
    //PATH
    public static final String PATH_TEXT_FILE = Pkg.LIB4A_PATH + ".PathTextFile";
    public static final String PATH_IMAGE_FILE = Pkg.LIB4A_PATH + ".PathImageFile";
    public static final String PATH_BEAN_FILE = Pkg.LIB4A_PATH + ".PathBeanFile";
    public static final String PATH_BEAN_LIST_FILE = Pkg.LIB4A_PATH + ".PathBeanListFile";
    //JSON
    public static final String JO = Pkg.LIB4J_JSON + ".base.JO";
    public static final String JSON_TOOL = Pkg.LIB4J_JSON + ".JsonTool";
    //dilog
    public static final String TOAST_DIALOG = Pkg.LIB4A_DIALOG_VIEW + ".ToastDialog";
    public static final String NOTICE_DIALOG = Pkg.LIB4A_DIALOG_VIEW + ".NoticeDialog";
    public static final String DIALOG = Pkg.LIB4A_DIALOG_VIEW + ".Dialog";
    public static final String DIALOG_ON_BT_CLICK = DIALOG + ".OnBtClick";
    public static final String EDIT_DIALOG = Pkg.LIB4A_DIALOG_VIEW + ".EditDialog";
    public static final String ED_TEXT_WATCHER = EDIT_DIALOG + ".EdTextWatcher";
    public static final String ON_HIDDEN_FINISHED = Pkg.LIB4A_LAYER_VIEW + ".listener.OnHiddenFinished";
    public static final String ON_HIDDEN_FINISHED_CALLBACK = Pkg.LIB4A_LAYER + ".event.OnHiddenFinishedCallBack";
    //不变的
    public static final String VIEW = "android.view.View";
    public static final String EDIT_TEXT = "android.widget.EditText";
    public static final String TEXT_VIEW = "android.widget.TextView";
    public static final String VIEW_GROUP = "android.view.ViewGroup";
    public static final String STRING = "java.lang.String";
    public static final String RECYCLER_VIEW = "androidx.recyclerview.widget.RecyclerView";
    public static final String RETROFIT_GET = "retrofit2.http.GET";
    public static final String RETROFIT_POST = "retrofit2.http.POST";
    public static final String RETROFIT_PUT = "retrofit2.http.PUT";
    public static final String RETROFIT_HEADER = "retrofit2.http.Header";
    public static final String RETROFIT_PATH = "retrofit2.http.Path";
    public static final String RETROFIT_QUERY = "retrofit2.http.Query";
    public static final String RETROFIT_BODY = "retrofit2.http.Body";
    public static final String OKHTTP_REQUEST_BODY = "okhttp3.RequestBody";


}