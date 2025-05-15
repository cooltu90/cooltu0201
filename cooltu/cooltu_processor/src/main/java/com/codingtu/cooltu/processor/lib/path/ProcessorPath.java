package com.codingtu.cooltu.processor.lib.path;

import com.codingtu.cooltu.constant.Module;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;

/**************************************************
 *
 *   ┏━━━━━━━━━━━━━━┓
 *  ┃   java目录   ┃
 * ┗━━━━━━━━━━━━━━━┛
 * {@link #javaDir()}
 *
 *   ┏━━━━━━━━━━━━━┓
 *  ┃   pkg目录   ┃
 * ┗━━━━━━━━━━━━━━┛
 * {@link #pkgDir(String)}
 *
 *   ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 *  ┃   Builder的Impl目录   ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 * {@link #builderImplDir()}
 *
 *   ┏━━━━━━━━━━━━━━━┓
 *  ┃   .java路径   ┃
 * ┗━━━━━━━━━━━━━━━━┛
 * {@link #javaPath(String, String)}
 *
 *   ┏━━━━━━━━━━━━━━━┓
 *  ┃   JavaInfo   ┃
 * ┗━━━━━━━━━━━━━━━━┛
 * {@link #javaInfo(String, String)}
 * {@link #javaInfo(String)}
 * {@link #javaInfo(Object)}
 *
 **************************************************/
public class ProcessorPath {

    public static String javaDir() {
        return PathTools.javaDir(Module.CORE_PROCESSOR);
    }


    public static String pkgDir(String pkg) {
        return PathTools.pkgDir(Module.CORE_PROCESSOR, pkg);
    }

    public static String builderImplDir() {
        return pkgDir(Pkg.PROCESSOR_BUILDER_IMPL);
    }

    public static String javaPath(String pkg, String name) {
        return PathTools.javaPath(Module.CORE_PROCESSOR, pkg, name);
    }

    public static JavaInfo javaInfo(String fullName) {
        return PathTools.javaInfo(Module.CORE_PROCESSOR, fullName);
    }

    public static JavaInfo javaInfo(Object obj) {
        return PathTools.javaInfo(Module.CORE_PROCESSOR, obj.getClass().getCanonicalName());
    }

    public static JavaInfo javaInfo(String pkg, String typeName) {
        return PathTools.javaInfo(Module.CORE_PROCESSOR, pkg, typeName);
    }

}
