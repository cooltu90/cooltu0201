package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.exception.FileCopyException;
import com.codingtu.cooltu.lib4j.file.copy.FileCopy;
import com.codingtu.cooltu.lib4j.file.deal.FileLineDealer;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.processor.annotation.create.CreateAct;
import com.codingtu.cooltu.processor.builder.core.UiBaseBuilder;
import com.codingtu.cooltu.processor.builder.impl.ActBaseBuilder;
import com.codingtu.cooltu.processor.builder.impl.ActResBuilder;
import com.codingtu.cooltu.processor.builder.impl.CreateActBuilder;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.IdTools;

import java.io.File;
import java.util.List;

import javax.lang.model.element.TypeElement;

public class CreateActDeal extends TypeBaseDeal {
    @Override
    protected void dealTypeElement(TypeElement te) {
        CreateAct createAct = te.getAnnotation(CreateAct.class);
        String name = createAct.name();
        String packages = createAct.packages();

        JavaInfo actJavaInfo = CurrentPath.act(packages, name);
        if (new File(actJavaInfo.path).exists())
            return;


        //创建layout
        IdTools.Id layoutTempId = IdTools.elementToId(te, CreateAct.class, createAct.layoutTemp());
        String layoutName = "activity_" + name;
        try {
            FileCopy
                    .src(CurrentPath.layout(layoutTempId.rName))
                    .to(new File(CurrentPath.layout(layoutName)));
        } catch (FileCopyException e) {
            e.printStackTrace();
        }
        //创建ActRes
        JavaInfo actResJavaInfo = CurrentPath.actRes(packages, name);
        new ActResBuilder(actResJavaInfo, actJavaInfo);
        //创建ActBase
        JavaInfo actBaseJavaInfo = CurrentPath.actBase(actJavaInfo.fullName);
        ActBaseBuilder builder = new ActBaseBuilder(actBaseJavaInfo);
        UiBaseBuilder uiBaseBuilder = builder.getUiBaseBuilder();
        uiBaseBuilder.baseClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return createAct.baseClass();
            }
        });
        uiBaseBuilder.layout = new IdTools.Id(Pkg.R, "layout", layoutName);
        uiBaseBuilder.uiFullName = actJavaInfo.fullName;
        //创建Act
        new CreateActBuilder(actJavaInfo, layoutName, actResJavaInfo, actBaseJavaInfo);
        //创建Manifest
        String manifestPath = CurrentPath.manifest();
        File manifestFile = new File(manifestPath);
        FileLineDealer.create(manifestFile).deal(new FileLineDealer.Dealer() {
            @Override
            public void before(String line, List<String> lines) {
                if (line.trim().equals("</application>")) {
                    lines.add("        <activity");
                    if (StringTool.isBlank(createAct.screenOrientation())) {
                        lines.add("            android:name=\"" + actJavaInfo.fullName + "\" />");
                    } else {
                        lines.add("            android:name=\"" + actJavaInfo.fullName + "\"");
                        lines.add("            android:screenOrientation=\"" + createAct.screenOrientation() + "\" />");
                    }
                }
            }

            @Override
            public String deal(String line) {
                return line;
            }
        });

        ResForDeal.HAS_START_MAP.put(actJavaInfo.fullName, actJavaInfo.fullName);

        //创建ActStart
        //ActStartBuilder.BUILDER.add(actJavaInfo.fullName);
        //创建Code4Request
        //Code4RequestBuilder.BUILDER.addAct(actJavaInfo.fullName);
    }
}
