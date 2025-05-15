package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.constant.AdapterType;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.exception.FileCopyException;
import com.codingtu.cooltu.lib4j.file.copy.FileCopy;
import com.codingtu.cooltu.processor.annotation.create.CreateAdapter;
import com.codingtu.cooltu.processor.builder.impl.DefaultListAdapterBuilder;
import com.codingtu.cooltu.processor.builder.impl.DefaultListMoreAdapterBuilder;
import com.codingtu.cooltu.processor.builder.impl.VhBuilder;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.IdTools;

import java.io.File;

import javax.lang.model.element.TypeElement;

public class CreateAdapterDeal extends TypeBaseDeal {
    @Override
    protected void dealTypeElement(TypeElement te) {
        CreateAdapter createAdapter = te.getAnnotation(CreateAdapter.class);
        String name = createAdapter.name();
        JavaInfo adapterJavaInfo = CurrentPath.adapter(createAdapter.packages(), name);
        if (new File(adapterJavaInfo.path).exists())
            return;
        //创建layout
        IdTools.Id layoutTempId = IdTools.elementToId(te, CreateAdapter.class, createAdapter.layoutTemp());
        String layoutName = "item_" + name;
        try {
            FileCopy
                    .src(CurrentPath.layout(layoutTempId.rName))
                    .to(new File(CurrentPath.layout(layoutName)));
        } catch (FileCopyException e) {
            e.printStackTrace();
        }
        //生成VH
        IdTools.Id layoutId = new IdTools.Id(Pkg.R, "layout", layoutName);
        JavaInfo vh = CurrentPath.vh(name);
        new VhBuilder(CurrentPath.vh(name), layoutId);
        //生成adapter
        AdapterType type = createAdapter.type();
        if (type == AdapterType.DEFAULT_LIST) {
            new DefaultListAdapterBuilder(adapterJavaInfo, vh, name);
        } else if (type == AdapterType.DEFAULT_MORE_LIST) {
            new DefaultListMoreAdapterBuilder(adapterJavaInfo, vh, name);
        }
    }
}
