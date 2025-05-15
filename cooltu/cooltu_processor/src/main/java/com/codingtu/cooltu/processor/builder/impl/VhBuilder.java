package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.processor.builder.base.VhBuilderBase;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.lib.tools.LayoutTools;

import java.util.List;

public class VhBuilder extends VhBuilderBase {

    private final IdTools.Id layoutId;

    public VhBuilder(JavaInfo info, IdTools.Id layoutId) {
        super(info);
        this.layoutId = layoutId;
        List<LayoutTools.ViewInfo> viewInfos = LayoutTools.convert(layoutId.rName);
        Es.es(viewInfos).ls(new Es.EachEs<LayoutTools.ViewInfo>() {
            @Override
            public boolean each(int position, LayoutTools.ViewInfo viewInfo) {
                field(fieldCount(), viewInfo.tag, viewInfo.fieldName);

                String parent = "itemView.";
                if (!viewInfo.fieldName.equals(viewInfo.id)) {
                    parent = viewInfo.parent.fieldName + ".";
                }
                findView(findViewCount(), viewInfo.fieldName, parent, Pkg.R, viewInfo.id);
                return false;
            }
        });
    }

    @Override
    protected boolean isBuild() {
        return true;
    }


    @Override
    protected void dealLines() {
        addTag(pkg, Pkg.CORE_VH);
        addTag(name, javaInfo.name);
        addTag(coreAdapterVHFullName, FullName.CORE_ADAPTER_VH);
        addTag(layoutName, layoutId.toString());
    }
}
/* model_temp_start
package [[pkg]];

import android.view.ViewGroup;

public class [[name]] extends [[coreAdapterVHFullName]] {
                                                                                                    [<sub>][for][field]
    public [type] [name];
                                                                                                    [<sub>][for][field]


    public [[name]](ViewGroup parent) {
        super([[layoutName]], parent);
                                                                                                    [<sub>][for][findView]
        [fieldName] = [parent]findViewById([rPkg].R.id.[id]);
                                                                                                    [<sub>][for][findView]
    }
}
model_temp_end */
