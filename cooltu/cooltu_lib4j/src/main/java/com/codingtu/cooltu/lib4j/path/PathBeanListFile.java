package com.codingtu.cooltu.lib4j.path;

import com.codingtu.cooltu.lib4j.json.JsonTool;

import java.util.List;

public class PathBeanListFile<T> extends PathTextFile {

    private final Class<T> beanClass;

    public PathBeanListFile(String root, String type, Class beanClass) {
        super(root, type);
        this.beanClass = beanClass;
    }

    public List<T> list() {
        return JsonTool.toBeanList(beanClass, getText());
    }

    public void list(List<T> list) {
        setText(JsonTool.toJson(list));
    }
}
