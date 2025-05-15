package com.codingtu.cooltu.lib4j.data.java;

public class JavaInfo {
    public String path;
    public String name;
    public String pkg;
    public String fullName;

    public JavaInfo(String pkg, String name) {
        this.pkg = pkg;
        this.name = name;
        this.fullName = pkg + "." + name;
    }

    public JavaInfo(String fullName) {
        this.fullName = fullName;
        int lastIndexOf = fullName.lastIndexOf(".");
        this.pkg = fullName.substring(0, lastIndexOf);
        this.name = fullName.substring(lastIndexOf + 1);
    }

    @Override
    public String toString() {
        return "JavaInfo{" +
                "path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", pkg='" + pkg + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
