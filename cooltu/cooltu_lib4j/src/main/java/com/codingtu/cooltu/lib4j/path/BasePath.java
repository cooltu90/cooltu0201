package com.codingtu.cooltu.lib4j.path;

import com.codingtu.cooltu.lib4j.data.bean.CoreBean;
import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;

import java.io.File;

public class BasePath extends CoreBean {

    public static final String SEPARATOR = File.separator;

    public static String addPrexSeparator(String dir) {
        return SEPARATOR + dir;
    }

    protected String root;
    private File ROOT_FILE;

    public BasePath(String root) {
        this.root = root;
    }

    public String child(String name) {
        return this.root + addPrexSeparator(name);
    }

    public BaseEs<File> getFileTs() {
        return Es.es(rootFile().listFiles());
    }

    public <T> BaseEs<T> list(Es.Convert<File, T> convert) {
        return getFileTs().convert(convert);
    }

    public String root() {
        return this.root;
    }

    public File rootFile() {
        if (ROOT_FILE == null) {
            ROOT_FILE = new File(root());
        }
        return ROOT_FILE;
    }
}
