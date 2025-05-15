package com.codingtu.cooltu.processor.lib.path;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FileType;
import com.codingtu.cooltu.constant.Module;
import com.codingtu.cooltu.constant.Path;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.constant.Suffix;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.file.FileTool;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.processor.BuilderType;
import com.codingtu.cooltu.processor.builder.impl.ActBaseBuilder;
import com.codingtu.cooltu.processor.builder.impl.FragmentBaseBuilder;
import com.codingtu.cooltu.processor.lib.BuilderMap;

public class CurrentPath {

    /**************************************************
     *
     * 目录
     *
     **************************************************/
    public static String mainDir() {
        return PathTools.mainDir(Module.CURRENT);
    }

    public static String javaDir() {
        return PathTools.javaDir(Module.CURRENT);
    }

    public static String pkgDir(String pkg) {
        return PathTools.pkgDir(Module.CURRENT, pkg);
    }

    /**************************************************
     *
     * layout
     *
     **************************************************/
    public static String layoutDir() {
        return FileTool.getProjectDir()
                + Constant.SEPARATOR
                + Module.CURRENT
                + Path.SRC_MAIN_RES_LAYOUT;
    }


    public static String layout(String layoutName) {
        return layoutDir()
                + Constant.SEPARATOR
                + layoutName
                + FileType.d_XML;
    }

    /**************************************************
     *
     * javaPath
     *
     **************************************************/
    public static String javaPath(String pkg, String name) {
        return PathTools.javaPath(Module.CURRENT, pkg, name);
    }

    /**************************************************
     *
     * JavaInfo
     *
     **************************************************/
    public static JavaInfo javaInfo(String pkg, String typeName) {
        return PathTools.javaInfo(Module.CURRENT, pkg, typeName);
    }

    public static JavaInfo javaInfo(String fullName) {
        return PathTools.javaInfo(Module.CURRENT, fullName);
    }

    /**************************************************
     *
     * act
     *
     **************************************************/
    public static String actStaticName(String actFullName) {
        JavaInfo actJavaInfo = javaInfo(actFullName);
        return ConvertTool.toStaticType(actJavaInfo.name);
    }

    public static JavaInfo act(String packages, String name) {
        String resName = ConvertTool.toClassType(name) + Suffix.ACTIVITY;
        return javaInfo(packages + "." + resName);
    }

    /**************************************************
     *
     * actRes
     *
     **************************************************/
    public static JavaInfo actRes(String packages, String name) {
        String pkg = Pkg.ACT_RES;
//        String pkg = Pkg.ACT_RES + packages.substring(Pkg.ACT.length());
        String resName = ConvertTool.toClassType(name) + Suffix.ACTIVITY_RES;
        return javaInfo(pkg + "." + resName);
    }


    /**************************************************
     *
     * actBase
     *
     **************************************************/
    public static JavaInfo actBase(String actFullName) {
        JavaInfo actJavaInfo = javaInfo(actFullName);
        String pkg = Pkg.ACT_BASE;
//        String pkg = Pkg.ACT_BASE + actJavaInfo.pkg.substring(Pkg.ACT.length());
        return javaInfo(pkg, actJavaInfo.name + Suffix.ACT_BASE);
    }

    public static ActBaseBuilder actBaseBuilder(String actFullName) {
        JavaInfo javaInfo = actBase(actFullName);
        return BuilderMap.find(BuilderType.actBase, javaInfo.fullName);
    }


    /**************************************************
     *
     * Fragment
     *
     **************************************************/
    public static String fragStaticName(String actFullName) {
        JavaInfo actJavaInfo = javaInfo(actFullName);
        return ConvertTool.toStaticType(actJavaInfo.name);
    }

    public static JavaInfo frag(String packages, String name) {
        String resName = ConvertTool.toClassType(name) + Suffix.FRAGMENT;
        return javaInfo(packages + "." + resName);
    }

    /**************************************************
     *
     * FragmentRes
     *
     **************************************************/
    public static JavaInfo fragRes(String packages, String name) {
        String pkg = Pkg.FRAGMENT_RES;
//        String pkg = Pkg.FRAGMENT_RES + packages.substring(Pkg.ACT.length());
        String resName = ConvertTool.toClassType(name) + Suffix.FRAGMENT_RES;
        return javaInfo(pkg + "." + resName);
    }

    /**************************************************
     *
     * FragmentBase
     *
     **************************************************/
    public static JavaInfo fragBase(String fragFullName) {
        JavaInfo fragJavaInfo = javaInfo(fragFullName);
//        String pkg = Pkg.FRAGMENT_BASE + fragJavaInfo.pkg.substring(Pkg.ACT.length());
        String pkg = Pkg.FRAGMENT_BASE;
        return javaInfo(pkg, fragJavaInfo.name + Suffix.FRAGMENT_BASE);
    }


    public static FragmentBaseBuilder fragBaseBuilder(String actFullName) {
        JavaInfo javaInfo = fragBase(actFullName);
        return BuilderMap.find(BuilderType.fragment, javaInfo.fullName);
    }


    /**************************************************
     *
     * adapter
     *
     **************************************************/
    public static JavaInfo adapter(String packages, String name) {
        String resName = ConvertTool.toClassType(name) + Suffix.ADAPTER;
        return javaInfo(packages + "." + resName);
    }

    /**************************************************
     *
     * sendParams
     *
     **************************************************/
    public static String sendParamsFullName(String methodName) {
        return Pkg.CORE_NET_PARAMS
                + "."
                + ConvertTool.toClassType(methodName)
                + Suffix.NET_PARAMS;
    }

    public static JavaInfo sendParams(String methodName) {
        return javaInfo(sendParamsFullName(methodName));
    }


    /**************************************************
     *
     * apiService
     *
     **************************************************/
    public static String apiServiceFullName(String apiName) {
        return Pkg.CORE_NET_API + "." + apiName + Suffix.API_SERVICE;
    }

    public static JavaInfo apiService(String apiName) {
        return javaInfo(apiServiceFullName(apiName));
    }

    /**************************************************
     *
     * netBack
     *
     **************************************************/
    public static String netBackFullName(String methodName) {
        return Pkg.CORE_NET_BACK
                + "."
                + ConvertTool.toClassType(methodName)
                + Suffix.NET_BACK;
    }

    public static JavaInfo netBack(String methodName) {
        return javaInfo(netBackFullName(methodName));
    }

    /**************************************************
     *
     * vh
     *
     **************************************************/
    public static String vhFullName(String name) {
        return Pkg.CORE_VH
                + "."
                + ConvertTool.toClassType(name)
                + Suffix.VH;
    }

    public static JavaInfo vh(String name) {
        return javaInfo(vhFullName(name));
    }

    /**************************************************
     *
     **************************************************/
    public static String msThreadTypeFullName(String name) {
        return Pkg.CORE_MSTHREAD
                + "."
                + name
                + Suffix.MSTHREAD_TYPE;
    }

    public static JavaInfo msThreadType(String name) {
        return javaInfo(msThreadTypeFullName(name));
    }

    /**************************************************
     *
     **************************************************/
    public static String msThreadInterfaceFullName(String name) {
        return Pkg.CORE_MSTHREAD
                + "."
                + name
                + Suffix.MSTHREAD_INTERFACE;
    }

    public static JavaInfo msThreadInterface(String name) {
        return javaInfo(msThreadInterfaceFullName(name));
    }
    /**************************************************
     *
     **************************************************/
    public static String msThreadFullName(String name) {
        return Pkg.CORE_MSTHREAD
                + "."
                + name
                + Suffix.MSTHREAD;
    }

    public static JavaInfo msThread(String name) {
        return javaInfo(msThreadFullName(name));
    }
    /**************************************************
     *
     **************************************************/
    public static String msThreadBaseFullName(String name) {
        return Pkg.CORE_MSTHREAD
                + "."
                + name
                + Suffix.MS_THREAD_BASE;
    }

    public static JavaInfo msThreadBase(String name) {
        return javaInfo(msThreadBaseFullName(name));
    }

    /**************************************************
     *
     * manifest
     *
     **************************************************/
    public static String manifest() {
        return mainDir()
                + Constant.SEPARATOR
                + "AndroidManifest.xml";
    }

    /**************************************************
     *   ┏━━━━━━━━━━┓
     *  ┃   目录   ┃
     * ┗━━━━━━━━━━┛
     * 【main目录】{@link #mainDir()}
     * 【java目录】{@link #javaDir()}
     * 【pkg目录】{@link #pkgDir(String)}
     *   ┏━━━━━━━━━━━━━━━━━┓
     *  ┃   layout目录   ┃
     * ┗━━━━━━━━━━━━━━━━━┛
     * {@link #layoutDir()}
     * {@link #layout(String)}
     *   ┏━━━━━━━━━━━━━━━┓
     *  ┃   .java路径   ┃
     * ┗━━━━━━━━━━━━━━━━┛
     * {@link #javaPath(String, String)}
     *   ┏━━━━━━━━━━━━━━━┓
     *  ┃   JavaInfo   ┃
     * ┗━━━━━━━━━━━━━━━━┛
     * 【包名、类名】{@link #javaInfo(String, String)}
     * 【全类名】{@link #javaInfo(String)}
     *   ┏━━━━━━━━━━━━━━━┓
     *  ┃   Activity   ┃
     * ┗━━━━━━━━━━━━━━━━┛
     * 【Act的静态名】{@link #actStaticName(String)}
     * 【Act的JavaInfo】{@link #act(String, String)}
     * 【ActRes的JavaInfo】{@link #actRes(String, String)}
     * 【ActBase的JavaInfo】{@link #actBase(String)}
     * 【查找ActBaseBuilder】{@link #actBaseBuilder(String)}
     *   ┏━━━━━━━━━━━━━━━┓
     *  ┃   Fragment   ┃
     * ┗━━━━━━━━━━━━━━━━┛
     * 【Fragment的静态名】{@link #fragStaticName(String)}
     * 【Fragment的JavaInfo】{@link #frag(String, String)}
     * 【FragmentRes的JavaInfo】{@link #fragRes(String, String)}
     * 【FragmentBase的JavaInfo】{@link #fragBase(String)}
     * 【FragmentRes的JavaInfo】{@link #fragBaseBuilder(String)}
     *   ┏━━━━━━━━━━━━━━┓
     *  ┃   Adapter   ┃
     * ┗━━━━━━━━━━━━━━━┛
     * 【Adapter的JavaInfo】{@link #adapter(String, String)}
     *
     *   ┏━━━━━━━━━━━━━━━━━━┓
     *  ┃   SendParams   ┃
     * ┗━━━━━━━━━━━━━━━━━━┛
     * JavaInfo：{@link #sendParams(String)}
     * FullName：{@link #sendParamsFullName(String)}
     *   ┏━━━━━━━━━━━━━━━━━━┓
     *  ┃   ApiService   ┃
     * ┗━━━━━━━━━━━━━━━━━━┛
     * JavaInfo：{@link #apiService(String)}
     * FullName：{@link #apiServiceFullName(String)}
     *   ┏━━━━━━━━━━━━━━┓
     *  ┃   NetBack   ┃
     * ┗━━━━━━━━━━━━━━━┛
     * JavaInfo：{@link #netBack(String)}
     * FullName：{@link #netBackFullName(String)}
     *   ┏━━━━━━━━━┓
     *  ┃   vh   ┃
     * ┗━━━━━━━━━┛
     * JavaInfo：{@link #vh(String)}
     * FullName：{@link #vhFullName(String)}
     *   ┏━━━━━━━━━━━━━━━┓
     *  ┃   manifest   ┃
     * ┗━━━━━━━━━━━━━━━━┛
     * {@link #manifest()}
     *
     **************************************************/
}
