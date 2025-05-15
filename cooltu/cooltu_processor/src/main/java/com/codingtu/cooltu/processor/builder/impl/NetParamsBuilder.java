package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.processor.builder.base.NetParamsBuilderBase;
import com.codingtu.cooltu.processor.lib.param.Params;

import java.util.List;

public class NetParamsBuilder extends NetParamsBuilderBase {
    private final Params params;

    public NetParamsBuilder(JavaInfo info, Params params) {
        super(info);
        this.params = params;
    }

    @Override
    protected boolean isBuild() {
        return true;
    }

    @Override
    protected boolean isGetLines() {
        return true;
    }

    @Override
    protected void beforeBuild(List<String> lines) {
        super.beforeBuild(lines);
        //Logs.i(lines);
    }

    @Override
    protected void dealLines() {
        addTag(pkg, Pkg.CORE_NET_PARAMS);
        addTag(coreSendParamsFullName, FullName.CORE_SEND_PARAMS);
        addTag(name, javaInfo.name);

        Es.es(params.getKvs()).ls(new Es.EachEs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                field(position, kv.k, kv.v);
                return false;
            }
        });
    }
}
/* model_temp_start
package [[pkg]];

public class [[name]] extends [[coreSendParamsFullName]]{
                                                                                                    [<sub>][for][field]
    public [type] [name];
                                                                                                    [<sub>][for][field]
}
model_temp_end */
