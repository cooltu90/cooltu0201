package com.codingtu.cooltu.lib4j.data.progress;

import com.codingtu.cooltu.lib4j.data.bean.CoreBean;

public class Progress extends CoreBean {
    public long totalLen;
    public long currentLen;

    public Progress() {
    }

    public Progress(long totalLen, long currentLen) {
        this.totalLen = totalLen;
        this.currentLen = currentLen;
    }
}
