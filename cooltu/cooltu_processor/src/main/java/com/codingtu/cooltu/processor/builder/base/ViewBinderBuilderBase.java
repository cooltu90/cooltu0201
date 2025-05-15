package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class ViewBinderBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {

    public ViewBinderBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);

    }



    @Override
    protected void dealLinesInParent() {

    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();

        return lines;
    }
}
