package com.codingtu.cooltu.processor.lib.path;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FileType;
import com.codingtu.cooltu.constant.Path;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.file.FileTool;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;

/**************************************************
 *
 *   ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 *  ┃   模块下的 main 目录   ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 * {@link #mainDir(String)}
 *
 *   ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 *  ┃   模块下的 java 目录   ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 * {@link #javaDir(String)}
 *
 *   ┏━━━━━━━━━━━━━━━━━━━━━━━┓
 *  ┃   模块下的 pkg 目录   ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━┛
 * {@link #pkgDir(String, String)}
 *
 *   ┏━━━━━━━━━━━━━━━━━━━━━━━━━┓
 *  ┃   模块下的 .java 路径   ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━━┛
 * {@link #javaPath(String, String, String)}
 *
 *   ┏━━━━━━━━━━━━━━━━━━━━━━┓
 *  ┃   模块下的JavaInfo   ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━┛
 * {@link #javaInfo(String, JavaInfo)}
 * {@link #javaInfo(String, String, String)}
 * {@link #javaInfo(String, String)}
 *
 **************************************************/
public class PathTools extends StringTool {

    /**************************************************
     *
     * 模块下的 main 目录
     *
     **************************************************/
    protected static String mainDir(String module) {
        return FileTool.getProjectDir()
                + Constant.SEPARATOR
                + module
                + Path.SRC_MAIN;
    }

    /**************************************************
     *
     * 模块下的 java 目录
     *
     **************************************************/
    protected static String javaDir(String module) {
        return FileTool.getProjectDir()
                + Constant.SEPARATOR
                + module
                + Path.SRC_MAIN_JAVA;
    }


    /**************************************************
     *
     * 模块下的 pkg 目录
     *
     **************************************************/
    protected static String pkgDir(String module, String pkg) {
        return javaDir(module) + ConvertTool.pkgToPath(pkg);
    }

    /**************************************************
     *
     *  模块下的 .java 路径
     *
     **************************************************/
    protected static String javaPath(String module, String pkg, String name) {
        return pkgDir(module, pkg)
                + Constant.SEPARATOR
                + name
                + FileType.d_JAVA;
    }

    /**************************************************
     *
     * 模块下的JavaInfo
     *
     **************************************************/
    private static JavaInfo javaInfo(String module, JavaInfo info) {
        info.path = javaPath(module, info.pkg, info.name);
        return info;
    }

    protected static JavaInfo javaInfo(String module, String pkg, String typeName) {
        return javaInfo(module, new JavaInfo(pkg, typeName));
    }

    protected static JavaInfo javaInfo(String module, String fullName) {
        return javaInfo(module, new JavaInfo(fullName));
    }

}
