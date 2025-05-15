package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.data.value.IntValue;
import com.codingtu.cooltu.processor.builder.base.MsThreadTypeBuilderBase;

public class MsThreadTypeBuilder extends MsThreadTypeBuilderBase {

    private BaseEs<String> staticMethodNameTs;

    public MsThreadTypeBuilder(JavaInfo info) {
        super(info);
    }

    public void setStaticMethodNameEs(BaseEs<String> staticMethodNameTs) {
        this.staticMethodNameTs = staticMethodNameTs;
    }

    @Override
    protected void dealLines() {
        addTag(pkg, Pkg.CORE_MSTHREAD);
        addTag(baseName, javaInfo.name);
        IntValue num = IntValue.obtain();
        staticMethodNameTs.ls(new Es.EachEs<String>() {
            @Override
            public boolean each(int position, String s) {
                if (num.value == 0) {
                    addTag(content, "   ");
                }
                if (num.value == 2) {
                    addLnTag(content, " [s],", s);
                } else {
                    addTag(content, " [s],", s);
                }

                num.value++;
                if (num.value == 3) {
                    num.value = 0;
                }
                return false;
            }
        });
    }

}
/* model_temp_start
package [[pkg]];

public enum [[baseName]] {
[[content]]
}
model_temp_end */