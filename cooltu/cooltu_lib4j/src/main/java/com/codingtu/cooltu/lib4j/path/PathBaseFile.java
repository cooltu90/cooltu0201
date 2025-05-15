package com.codingtu.cooltu.lib4j.path;

import java.io.File;

public class PathBaseFile {

    protected String root;

    private File ROOT_FILE;

    protected String type;

    public PathBaseFile(String root, String type) {
        this.root = root;
        this.type = type;
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

    public String type() {
        return type;
    }
}
