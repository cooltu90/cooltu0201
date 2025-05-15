package com.codingtu.cooltu.processor;

import com.codingtu.cooltu.constant.FileType;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.constant.Suffix;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.file.list.FileLister;
import com.codingtu.cooltu.lib4j.file.list.ListFile;
import com.codingtu.cooltu.lib4j.log.LibLogs;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.processor.builder.impl.BuilderBuilder;
import com.codingtu.cooltu.processor.deal.base.BaseDeal;
import com.codingtu.cooltu.processor.lib.App;
import com.codingtu.cooltu.processor.lib.BuilderMap;
import com.codingtu.cooltu.processor.lib.path.ProcessorPath;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.google.auto.service.AutoService;
import com.sun.source.util.Trees;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

@AutoService(Processor.class)
public class AppProcessor extends AbstractProcessor {

    private Set<String> supportTypes = new HashSet<>();
    private Class[] types;
    private List<String> builderBaseTempLines;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        IdTools.trees = Trees.instance(processingEnv);
        IdTools.rScanner = new IdTools.RScanner();
        App.init(processingEnv);
        types = SupportTypes.types();
        deal();
        dealSupportTypes();
    }

    private void deal() {
        String coreProcessorJavaDir = ProcessorPath.javaDir();
        FileLister.dir(ProcessorPath.builderImplDir()).list(new ListFile() {
            @Override
            public void list(File file) {
                String path = file.getAbsolutePath().substring(coreProcessorJavaDir.length());
                path = StringTool.cutSuffix(path, FileType.d_JAVA);
                String classFullName = ConvertTool.pathToPkg(path);
                JavaInfo builderJavaInfo = ProcessorPath.javaInfo(classFullName);
                JavaInfo builderBaseJavaInfo = ProcessorPath.javaInfo(Pkg.PROCESSOR_BUILDER_BASE, builderJavaInfo.name + Suffix.PROCESS_BUILDER_BASE);
                new BuilderBuilder(builderJavaInfo, builderBaseJavaInfo);
            }
        });

    }

    public void dealSupportTypes() {
        Es.es(types).ls(2, new Es.EachEs<Class>() {
            @Override
            public boolean each(int position, Class annoClass) {
                supportTypes.add(annoClass.getCanonicalName());
                return false;
            }
        });
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return supportTypes;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnv) {
        try {
            Es.es(types).ls(2, new Es.EachEs<Class>() {
                @Override
                public boolean each(int position, Class annoClass) {
                    Class dealClass = types[position + 1];
                    Set<Element> es = roundEnv.getElementsAnnotatedWith(annoClass);
                    Es.es(es).ls(new Es.EachEs<Element>() {
                        @Override
                        public boolean each(int position, Element element) {
                            try {
                                BaseDeal deal = (BaseDeal) dealClass.getConstructor().newInstance();
                                deal.dealElement(element);
                            } catch (Exception e) {
                                LibLogs.i(e);
                            }
                            return false;
                        }
                    });
                    return false;
                }
            });
            BuilderMap.create();
        } catch (Exception e) {
            LibLogs.i(e);
            throw new RuntimeException("报错了");
        }
        return true;
    }
}
/* model_temp_start
package [[pkg]];
import java.util.ArrayList;
import java.util.List;

public abstract class [[name]] extends [[base]] {
[[fileds]]
    public [[name]]([[JavaInfo]] info) {
        super(info);
[[getStringBuilder]]
    }

    @Override
    protected void dealLinesInParent() {
[[dealLinesInParent]]
    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
[[tempLines]]
        return lines;
    }
}
model_temp_end */
