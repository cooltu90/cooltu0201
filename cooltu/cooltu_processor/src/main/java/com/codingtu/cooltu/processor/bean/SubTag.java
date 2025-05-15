package com.codingtu.cooltu.processor.bean;

import com.codingtu.cooltu.processor.constant.Tags;
import com.codingtu.cooltu.lib4j.tools.TagTools;

import java.util.List;

public class SubTag {

    public String type;
    public String tag;
    public String full;
    public List<Integer> forLevels;
    public String parentTag;

    public static SubTag start(String line) {
        List<String> tags = TagTools.getTags(Tags.SINGLE_START, Tags.SINGLE_END, line);
        SubTag subTag = new SubTag();
        subTag.type = tags.get(1);
        subTag.full = line;
        subTag.tag = tags.get(2);
        return subTag;
    }

    public SubTag getEnd() {
        SubTag subTag = new SubTag();
        subTag.tag = this.tag;
        subTag.type = this.type;
        subTag.full = this.full;
        return subTag;
    }
}
