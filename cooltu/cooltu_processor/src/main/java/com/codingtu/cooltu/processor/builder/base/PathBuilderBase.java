package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class PathBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected StringBuilder name;
    protected StringBuilder basePath;
    protected java.util.Map<String, Boolean> filedsIfs;
    protected java.util.Map<String, Integer> filedsCounts;
    protected StringBuilder filedsSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> fileds;
    protected java.util.Map<String, Boolean> fileFiledsIfs;
    protected java.util.Map<String, Integer> fileFiledsCounts;
    protected StringBuilder fileFiledsSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> fileFileds;
    protected java.util.Map<String, Boolean> obtainIfs;
    protected java.util.Map<String, Integer> obtainCounts;
    protected StringBuilder obtainSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> obtain;
    protected java.util.Map<String, Boolean> initDirsIfs;
    protected java.util.Map<String, Integer> initDirsCounts;
    protected StringBuilder initDirsSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> initDirs;
    protected java.util.Map<String, Boolean> initFilesIfs;
    protected java.util.Map<String, Integer> initFilesCounts;
    protected StringBuilder initFilesSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> initFiles;
    protected java.util.Map<String, Boolean> dirsMethodIfs;
    protected java.util.Map<String, Integer> dirsMethodCounts;
    protected StringBuilder dirsMethodSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> dirsMethod;
    protected java.util.Map<String, Boolean> dirListIfs;
    protected java.util.Map<String, Integer> dirListCounts;
    protected StringBuilder dirListSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> dirList;
    protected java.util.Map<String, Boolean> filesMethodIfs;
    protected java.util.Map<String, Integer> filesMethodCounts;
    protected StringBuilder filesMethodSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> filesMethod;
    protected java.util.Map<String, Boolean> listMethodIfs;
    protected java.util.Map<String, Integer> listMethodCounts;
    protected StringBuilder listMethodSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> listMethod;
    protected java.util.Map<String, Boolean> fileListIfs;
    protected java.util.Map<String, Integer> fileListCounts;
    protected StringBuilder fileListSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> fileList;

    public PathBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        name = map.get("name");
        basePath = map.get("basePath");
        filedsIfs = new java.util.HashMap<>();
        filedsCounts = new java.util.HashMap<>();
        filedsSb = map.get("fileds");
        fileds = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        fileFiledsIfs = new java.util.HashMap<>();
        fileFiledsCounts = new java.util.HashMap<>();
        fileFiledsSb = map.get("fileFileds");
        fileFileds = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        obtainIfs = new java.util.HashMap<>();
        obtainCounts = new java.util.HashMap<>();
        obtainSb = map.get("obtain");
        obtain = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        initDirsIfs = new java.util.HashMap<>();
        initDirsCounts = new java.util.HashMap<>();
        initDirsSb = map.get("initDirs");
        initDirs = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        initFilesIfs = new java.util.HashMap<>();
        initFilesCounts = new java.util.HashMap<>();
        initFilesSb = map.get("initFiles");
        initFiles = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        dirsMethodIfs = new java.util.HashMap<>();
        dirsMethodCounts = new java.util.HashMap<>();
        dirsMethodSb = map.get("dirsMethod");
        dirsMethod = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        dirListIfs = new java.util.HashMap<>();
        dirListCounts = new java.util.HashMap<>();
        dirListSb = map.get("dirList");
        dirList = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        filesMethodIfs = new java.util.HashMap<>();
        filesMethodCounts = new java.util.HashMap<>();
        filesMethodSb = map.get("filesMethod");
        filesMethod = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        listMethodIfs = new java.util.HashMap<>();
        listMethodCounts = new java.util.HashMap<>();
        listMethodSb = map.get("listMethod");
        listMethod = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        fileListIfs = new java.util.HashMap<>();
        fileListCounts = new java.util.HashMap<>();
        fileListSb = map.get("fileList");
        fileList = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();

    }

    public int filedsCount() {
        return count(filedsCounts, getForKey("fileds"));
    }
    public void fileds(int i0, String type, String name) {
        addForMap(this.fileds, getForKey("fileds", i0), type, name);
        countAdd(filedsCounts, getForKey("fileds"));
    }
    public int fileFiledsCount() {
        return count(fileFiledsCounts, getForKey("fileFileds"));
    }
    public void fileFileds(int i0, String type, String name) {
        addForMap(this.fileFileds, getForKey("fileFileds", i0), type, name);
        countAdd(fileFiledsCounts, getForKey("fileFileds"));
    }
    public int obtainsCount() {
        return count(obtainCounts, getForKey("obtains"));
    }
    public void obtains(int i0, String className, String methodName, String params, String configs, String useParams) {
        addForMap(this.obtain, getForKey("obtains", i0), className, methodName, params, configs, configs, useParams);
        countAdd(obtainCounts, getForKey("obtains"));
    }
    public int addObtainRootCount() {
        return count(obtainCounts, getForKey("addObtainRoot"));
    }
    public void addObtainRoot(int i0, String path) {
        addForMap(this.obtain, getForKey("addObtainRoot", i0), path);
        countAdd(obtainCounts, getForKey("addObtainRoot"));
    }
    public int initDirsCount() {
        return count(initDirsCounts, getForKey("initDirs"));
    }
    public void initDirs(int i0, String filedName, String filedType, String dirName) {
        addForMap(this.initDirs, getForKey("initDirs", i0), filedName, filedType, dirName);
        countAdd(initDirsCounts, getForKey("initDirs"));
    }
    public int initFilesCount() {
        return count(initFilesCounts, getForKey("initFiles"));
    }
    public void initFiles(int i0, String filedName, String filedType, String fileName, String fileType) {
        addForMap(this.initFiles, getForKey("initFiles", i0), filedName, filedType, fileName, fileType);
        countAdd(initFilesCounts, getForKey("initFiles"));
    }
    public int dirsMethodCount() {
        return count(dirsMethodCounts, getForKey("dirsMethod"));
    }
    public void dirsMethod(int i0, String fieldType, String methodName, String value) {
        addForMap(this.dirsMethod, getForKey("dirsMethod", i0), fieldType, methodName, value, fieldType, value);
        countAdd(dirsMethodCounts, getForKey("dirsMethod"));
    }
    public int dirListParamCount(int i0) {
        return count(dirListCounts, getForKey("dirListParam", i0));
    }
    public void dirListParam(int i0, int i1, String type, String name, String divider) {
        addForMap(this.dirList, getForKey("dirListParam", i0, i1), type, name, divider);
        countAdd(dirListCounts, getForKey("dirListParam", i0));
    }
    public int dirListFilterCount(int i0) {
        return count(dirListCounts, getForKey("dirListFilter", i0));
    }
    public void dirListFilter(int i0, int i1, String type) {
        addForMap(this.dirList, getForKey("dirListFilter", i0, i1), type, type);
        countAdd(dirListCounts, getForKey("dirListFilter", i0));
    }
    public int dirListCount() {
        return count(dirListCounts, getForKey("dirList"));
    }
    public void dirList(int i0, String tListTsFullName, String fieldType, String fieldName, String filterFullName, String tsFullName) {
        addForMap(this.dirList, getForKey("dirList", i0), tListTsFullName, fieldType, fieldName, filterFullName, filterFullName, tsFullName, fieldName);
        countAdd(dirListCounts, getForKey("dirList"));
    }
    public int filesMethodCount() {
        return count(filesMethodCounts, getForKey("filesMethod"));
    }
    public void filesMethod(int i0, String fieldType, String filedName, String value, String fileType) {
        addForMap(this.filesMethod, getForKey("filesMethod", i0), fieldType, filedName, value, fieldType, value, fileType);
        countAdd(filesMethodCounts, getForKey("filesMethod"));
    }
    public int fileListParamCount(int i0) {
        return count(fileListCounts, getForKey("fileListParam", i0));
    }
    public void fileListParam(int i0, int i1, String type, String name, String divider) {
        addForMap(this.fileList, getForKey("fileListParam", i0, i1), type, name, divider);
        countAdd(fileListCounts, getForKey("fileListParam", i0));
    }
    public int filterParamCount(int i0) {
        return count(fileListCounts, getForKey("filterParam", i0));
    }
    public void filterParam(int i0, int i1, String name) {
        addForMap(this.fileList, getForKey("filterParam", i0, i1), name, name);
        countAdd(fileListCounts, getForKey("filterParam", i0));
    }
    public int fileListCount() {
        return count(fileListCounts, getForKey("fileList"));
    }
    public void fileList(int i0, String tListTsFullName, String fieldType, String fieldName, String filterFullName, String tsFullName) {
        addForMap(this.fileList, getForKey("fileList", i0), tListTsFullName, fieldType, fieldName, filterFullName, filterFullName, tsFullName, fieldName);
        countAdd(fileListCounts, getForKey("fileList"));
    }

    public void obtainIf(String name, String params, String sDCardToolFullName) {
        addForMap(this.obtain, getIfKey("obtain"), name, params, sDCardToolFullName);
        obtainIfs.put(getIfKey("obtain"), true);
    }
    public void initFilesParamIf(int i0, String othersParam) {
        addForMap(this.initFiles, getIfKey("initFilesParam", i0), othersParam);
        initFilesIfs.put(getIfKey("initFilesParam", i0), true);
    }
    public void filesMethodParamIf(int i0, String others) {
        addForMap(this.filesMethod, getIfKey("filesMethodParam", i0), others);
        filesMethodIfs.put(getIfKey("filesMethodParam", i0), true);
    }
    public void listMethodIf(String listMethod) {
        addForMap(this.listMethod, getIfKey("listMethod"), listMethod);
        listMethodIfs.put(getIfKey("listMethod"), true);
    }

    @Override
    protected void dealLinesInParent() {
        for (int i0 = 0; i0 < count(filedsCounts, getForKey("fileds")); i0++) {
            List<String> fileds0 = fileds.get(getForKey("fileds", i0));
            addLnTag(filedsSb, "    public [type] [name];", fileds0.get(0), fileds0.get(1));
        }
        for (int i0 = 0; i0 < count(fileFiledsCounts, getForKey("fileFileds")); i0++) {
            List<String> fileFileds0 = fileFileds.get(getForKey("fileFileds", i0));
            addLnTag(fileFiledsSb, "    public [type] [name];", fileFileds0.get(0), fileFileds0.get(1));
        }
        if (isIf(obtainIfs, getIfKey("obtain"))) {
            List<String> obtain0 = obtain.get(getIfKey("obtain"));
            for (int i0 = 0; i0 < count(obtainCounts, getForKey("obtains")); i0++) {
                List<String> obtain1 = obtain.get(getForKey("obtains", i0));
                addLnTag(obtainSb, "    public static [className] [methodName]([params]) {", obtain1.get(0), obtain1.get(1), obtain1.get(2));
                addLnTag(obtainSb, "        [configs] configs = new [configs]();", obtain1.get(3), obtain1.get(4));
                addLnTag(obtainSb, "        return obtain([useParams]);", obtain1.get(5));
                addLnTag(obtainSb, "    }");
            }
            addLnTag(obtainSb, "");
            addLnTag(obtainSb, "    public static [name] obtain([params]) {", obtain0.get(0), obtain0.get(1));
            addLnTag(obtainSb, "        return root([sDCardToolFullName].getSDCard()", obtain0.get(2));
            for (int i0 = 0; i0 < count(obtainCounts, getForKey("addObtainRoot")); i0++) {
                List<String> obtain1 = obtain.get(getForKey("addObtainRoot", i0));
                addLnTag(obtainSb, "                + addPrexSeparator([path])", obtain1.get(0));
            }
            addLnTag(obtainSb, "        );");
            addLnTag(obtainSb, "    }");
        }
        for (int i0 = 0; i0 < count(initDirsCounts, getForKey("initDirs")); i0++) {
            List<String> initDirs0 = initDirs.get(getForKey("initDirs", i0));
            addLnTag(initDirsSb, "        this.[filedName] = [filedType].root(", initDirs0.get(0), initDirs0.get(1));
            addLnTag(initDirsSb, "                this.root");
            addLnTag(initDirsSb, "                        + addPrexSeparator(\"[dirName]\")", initDirs0.get(2));
            addLnTag(initDirsSb, "        );");
        }
        for (int i0 = 0; i0 < count(initFilesCounts, getForKey("initFiles")); i0++) {
            List<String> initFiles0 = initFiles.get(getForKey("initFiles", i0));
            addLnTag(initFilesSb, "        this.[filedName] = new [filedType](", initFiles0.get(0), initFiles0.get(1));
            addLnTag(initFilesSb, "                this.root");
            addLnTag(initFilesSb, "                        + addPrexSeparator(\"[fileName]\")", initFiles0.get(2));
            addLnTag(initFilesSb, "                , \"[fileType]\"", initFiles0.get(3));
            if (isIf(initFilesIfs, getIfKey("initFilesParam", i0))) {
                List<String> initFiles1 = initFiles.get(getIfKey("initFilesParam", i0));
                addLnTag(initFilesSb, "                , [othersParam]", initFiles1.get(0));
            }
            addLnTag(initFilesSb, "        );");
        }
        for (int i0 = 0; i0 < count(dirsMethodCounts, getForKey("dirsMethod")); i0++) {
            List<String> dirsMethod0 = dirsMethod.get(getForKey("dirsMethod", i0));
            addLnTag(dirsMethodSb, "    public [fieldType] [methodName](String [value]) {", dirsMethod0.get(0), dirsMethod0.get(1), dirsMethod0.get(2));
            addLnTag(dirsMethodSb, "        return new [fieldType](", dirsMethod0.get(3));
            addLnTag(dirsMethodSb, "                this.root");
            addLnTag(dirsMethodSb, "                        + addPrexSeparator([value])", dirsMethod0.get(4));
            addLnTag(dirsMethodSb, "        );");
            addLnTag(dirsMethodSb, "    }");
        }
        for (int i0 = 0; i0 < count(dirListCounts, getForKey("dirList")); i0++) {
            List<String> dirList0 = dirList.get(getForKey("dirList", i0));
            addLnTag(dirListSb, "    public [tListTsFullName]<[fieldType]> [fieldName]List(", dirList0.get(0), dirList0.get(1), dirList0.get(2));
            for (int i1 = 0; i1 < count(dirListCounts, getForKey("dirListParam", i0)); i1++) {
                List<String> dirList1 = dirList.get(getForKey("dirListParam", i0, i1));
                addLnTag(dirListSb, "            [type] [name][divider]", dirList1.get(0), dirList1.get(1), dirList1.get(2));
            }
            addLnTag(dirListSb, "    ) {");
            addLnTag(dirListSb, "        [filterFullName] filter = new [filterFullName]();", dirList0.get(3), dirList0.get(4));
            for (int i1 = 0; i1 < count(dirListCounts, getForKey("dirListFilter", i0)); i1++) {
                List<String> dirList1 = dirList.get(getForKey("dirListFilter", i0, i1));
                addLnTag(dirListSb, "        filter.[type] = [type];", dirList1.get(0), dirList1.get(1));
            }
            addLnTag(dirListSb, "        return [tsFullName].ts(new java.io.File(root()).listFiles()).convert((index, file) -> {", dirList0.get(5));
            addLnTag(dirListSb, "            if (filter.check(file)) {");
            addLnTag(dirListSb, "                return [fieldName](file.getName());", dirList0.get(6));
            addLnTag(dirListSb, "            }");
            addLnTag(dirListSb, "            return null;");
            addLnTag(dirListSb, "        });");
            addLnTag(dirListSb, "    }");
        }
        for (int i0 = 0; i0 < count(filesMethodCounts, getForKey("filesMethod")); i0++) {
            List<String> filesMethod0 = filesMethod.get(getForKey("filesMethod", i0));
            addLnTag(filesMethodSb, "    public [fieldType] [filedName](String [value]) {", filesMethod0.get(0), filesMethod0.get(1), filesMethod0.get(2));
            addLnTag(filesMethodSb, "        return new [fieldType](", filesMethod0.get(3));
            addLnTag(filesMethodSb, "                this.root");
            addLnTag(filesMethodSb, "                        + addPrexSeparator([value] + \".txt\")", filesMethod0.get(4));
            addLnTag(filesMethodSb, "                , \"[fileType]\"", filesMethod0.get(5));
            if (isIf(filesMethodIfs, getIfKey("filesMethodParam", i0))) {
                List<String> filesMethod1 = filesMethod.get(getIfKey("filesMethodParam", i0));
                addLnTag(filesMethodSb, "                , [others]", filesMethod1.get(0));
            }
            addLnTag(filesMethodSb, "        );");
            addLnTag(filesMethodSb, "    }");
        }
        if (isIf(listMethodIfs, getIfKey("listMethod"))) {
            List<String> listMethod0 = listMethod.get(getIfKey("listMethod"));
            addLnTag(listMethodSb, "[listMethod]", listMethod0.get(0));
        }
        for (int i0 = 0; i0 < count(fileListCounts, getForKey("fileList")); i0++) {
            List<String> fileList0 = fileList.get(getForKey("fileList", i0));
            addLnTag(fileListSb, "    public [tListTsFullName]<[fieldType]> [fieldName]_list(", fileList0.get(0), fileList0.get(1), fileList0.get(2));
            for (int i1 = 0; i1 < count(fileListCounts, getForKey("fileListParam", i0)); i1++) {
                List<String> fileList1 = fileList.get(getForKey("fileListParam", i0, i1));
                addLnTag(fileListSb, "            [type] [name][divider]", fileList1.get(0), fileList1.get(1), fileList1.get(2));
            }
            addLnTag(fileListSb, "    ) {");
            addLnTag(fileListSb, "        [filterFullName] filter = new [filterFullName]();", fileList0.get(3), fileList0.get(4));
            for (int i1 = 0; i1 < count(fileListCounts, getForKey("filterParam", i0)); i1++) {
                List<String> fileList1 = fileList.get(getForKey("filterParam", i0, i1));
                addLnTag(fileListSb, "        filter.[name] = [name];", fileList1.get(0), fileList1.get(1));
            }
            addLnTag(fileListSb, "        return [tsFullName].ts(new java.io.File(root()).listFiles()).convert((index, file) -> {", fileList0.get(5));
            addLnTag(fileListSb, "            if (filter.check(file)) {");
            addLnTag(fileListSb, "                return [fieldName](file.getName());", fileList0.get(6));
            addLnTag(fileListSb, "            }");
            addLnTag(fileListSb, "            return null;");
            addLnTag(fileListSb, "        });");
            addLnTag(fileListSb, "    }");
        }

    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("public class [[name]] extends [[basePath]] {");
        lines.add("");
        lines.add("[[fileds]]");
        lines.add("");
        lines.add("[[fileFileds]]");
        lines.add("");
        lines.add("[[obtain]]");
        lines.add("");
        lines.add("    public static [[name]] root(String root) {");
        lines.add("        return new [[name]](root);");
        lines.add("    }");
        lines.add("");
        lines.add("    public [[name]](String root) {");
        lines.add("        super(root);");
        lines.add("[[initDirs]]");
        lines.add("[[initFiles]]");
        lines.add("    }");
        lines.add("");
        lines.add("[[dirsMethod]]");
        lines.add("");
        lines.add("[[dirList]]");
        lines.add("");
        lines.add("[[filesMethod]]");
        lines.add("");
        lines.add("[[listMethod]]");
        lines.add("[[fileList]]");
        lines.add("}");

        return lines;
    }
}
