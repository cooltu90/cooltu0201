package com.codingtu.cooltu.constant;

import java.io.File;

public class Constant {
    /**********************************************
     * name名类型：以（com.a.b.c.TestClassActivity为例）
     * allName：
     *     说明：什么类型的名字都行
     *     例子：test_class_activity,TestClassActivity,TEST_CLASS_ACTIVITY,testClassActivity
     * allBaseName：
     *     说明：什么类型的基础名字都行,不包括后缀
     *     例子：test_class,TestClass,TEST_CLASS,testClass
     * typeName：
     *     说明：类型名
     *     例子：TestClassActivity
     * typeBaseName：
     *     说明：基础类型名
     *     列子：TestClass
     * staticName：
     *     说明：静态名字
     *     例子：TEST_CLASS_ACTIVITY
     * staticBaseName：
     *     说明：基础静态名字
     *     例子：TEST_CLASS
     * methodName：
     *     说明：方法名字
     *     例子：testClassActivity
     * methodBaseName：
     *     说明：方法基础名字
     *     例子：testClass
     * layoutName：
     *     说明：布局样式名字
     *     例子：test_class_activity
     * layoutBaseName：
     *     说明：布局样式基础名字
     *     例子：test_class
     * fullName：
     *     说明：全名，包括包名
     *     例子：com.a.b.c.TestClassActivity
     **********************************************/
    //文件分割
    public static final String SEPARATOR = File.separator;

    public static final String SIGN_TEMP_START = "/* model_temp_start";
    public static final String SIGN_TEMP_END = "model_temp_end */";

    public static final String FROM_ACT = "fromAct";

    public static final String SIGN_PROTECTED = "protected";
    public static final String SIGN_PUBLIC = "public";
    public static final String SIGN_PRIVATE = "private";

    public static String DEFAULT_TOAST_DIALOG_LAYOUT = Pkg.LIB4A + ".R.layout.default_dialog_toast";
    public static String DEFAULT_EDIT_DIALOG_LAYOUT = Pkg.LIB4A + ".R.layout.default_dialog_edit";
    public static String DEFAULT_NOTICE_DIALOG_LAYOUT = Pkg.LIB4A + ".R.layout.default_dialog_notice";
    public static String DEFAULT_DIALOG_LAYOUT = Pkg.LIB4A + ".R.layout.default_dialog";
    public static String DEFAULT_MENU_DIALOG_LAYOUT = Pkg.LIB4A + ".R.layout.default_dialog_menu";
    public static String DEFAULT_MENU_DIALOG_ITEM_LAYOUT = Pkg.LIB4A + ".R.layout.default_dialog_menu_item";
}
