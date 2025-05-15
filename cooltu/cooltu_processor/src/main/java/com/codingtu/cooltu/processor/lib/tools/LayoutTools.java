package com.codingtu.cooltu.processor.lib.tools;

import com.codingtu.cooltu.lib4j.data.map.ListValueMap;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.file.read.FileReader;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;

import java.util.ArrayList;
import java.util.List;

public class LayoutTools {

    public static class ViewInfo {
        public ViewInfo parent;
        public List<ViewInfo> childs = new ArrayList<>();
        public String tag;
        public String id;
        public String fieldName;
    }

    public static List<ViewInfo> convert(String layoutName) {
        ViewInfo info = read(layoutName);

        ListValueMap<String, ViewInfo> viewInfoMap = new ListValueMap<>();

        dealViewInfo(viewInfoMap, info);
        dealViewinfo(viewInfoMap);

        return Es.convertList(Es.maps(viewInfoMap).toValueTs()).toList();
    }


    private static ViewInfo read(String layoutName) {
        return dealLines(read(layoutName, null, ""));
    }

    private static List<String> read(String layoutName, String parentId, String space) {
        List<String> lines = FileReader.from(CurrentPath.layout(layoutName)).readLine();
        List<String> newLines = new ArrayList<>();
        int count = CountTool.count(lines);
        if (count > 0) {
            boolean isInclude = false;
            String includeId = null;
            String includeLayout = null;
            String newSpace = "";
            for (int i = 0; i < count; i++) {
                String line = lines.get(i);
                if (line.contains("<include")) {
                    newSpace = line.substring(0, line.indexOf("<include"));
                    isInclude = true;
                }
                if (isInclude) {
                    if (line.contains("android:id")) {
                        includeId = StringTool.getSub(line, "@+id", "/", "\"");
                    }

                    if (line.contains("@layout")) {
                        includeLayout = StringTool.getSub(line, "@layout", "/", "\"");
                    }

                    if (line.contains("</include>") || line.contains("/>")) {
                        newLines.addAll(read(includeLayout, includeId, newSpace));
                        isInclude = false;
                    }
                } else {
                    if (!line.startsWith("<?xml") && StringTool.isNotBlank(line)) {
                        if (CountTool.isNull(newLines) && space.length() > 0) {
                            newLines.add(space + line);
                            newLines.add(space + "    android:id=\"@+id/" + parentId + "\"");
                        } else {
                            newLines.add(space + line);
                        }
                    }
                }
            }
        }
        return newLines;
    }

    private static ViewInfo dealLines(List<String> lines) {
        ViewInfo info = null;
        ViewInfo startInfo = null;
        int count = CountTool.count(lines);
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                String line = lines.get(i);
                boolean isTagLine = isTagLine(line);
                if (isTagLine) {
                    if (info == null) {
                        info = new ViewInfo();
                        startInfo = info;
                    } else {
                        ViewInfo viewInfo = new ViewInfo();
                        viewInfo.parent = info;
                        info.childs.add(viewInfo);
                        info = viewInfo;
                    }

                    int start = line.indexOf("<") + 1;
                    int end = line.indexOf(" ", start);
                    if (end < 0) {
                        end = line.length();
                    }
                    info.tag = line.substring(start, end);
                    if (!info.tag.contains(".")) {
                        if ("View".equals(info.tag)) {
                            info.tag = "android.view.View";
                        } else {
                            info.tag = "android.widget." + info.tag;
                        }
                    }

                }

                if (line.contains("android:id=\"@+id")) {
                    String id = StringTool.getSub(line, "android:id=", "@+id/", "\"");
                    info.id = id;
                    info.fieldName = info.id;
                } else if (line.contains("android:id=\"@id")) {
                    String id = StringTool.getSub(line, "android:id=", "@id/", "\"");
                    info.id = id;
                    info.fieldName = info.id;
                }

                if (isEndTagLine(line)) {
                    info = info.parent;
                }
            }
        }
        return startInfo;
    }

    private static boolean isEndTagLine(String line) {
        if (line.contains("/>") || line.contains("</")) {
            return true;
        }
        return false;
    }

    private static boolean isTagLine(String line) {
        String trimLine = line.trim();
        if (trimLine.startsWith("<")) {
            if (!trimLine.startsWith("</")) {
                return true;
            }
        }
        return false;
    }

    private static void dealViewInfo(ListValueMap<String, ViewInfo> viewInfoMap, LayoutTools.ViewInfo viewInfo) {
        if (viewInfo != null) {
            if (StringTool.isNotBlank(viewInfo.id)) {
                viewInfoMap.get(viewInfo.fieldName).add(viewInfo);
            }
            Es.es(viewInfo.childs).ls(new Es.EachEs<ViewInfo>() {
                @Override
                public boolean each(int position, ViewInfo viewInfo) {
                    dealViewInfo(viewInfoMap, viewInfo);
                    return false;
                }
            });
        }
    }

    private static void dealViewinfo(ListValueMap<String, ViewInfo> viewInfoMap) {
        boolean isFinish = true;
        List<String> keys = Es.es(viewInfoMap.keySet()).toList();
        for (String key : keys) {
            List<LayoutTools.ViewInfo> viewInfos = viewInfoMap.get(key);
            int count = CountTool.count(viewInfos);
            if (count > 1) {
                isFinish = false;
                viewInfoMap.put(key, null);
                for (int i = 0; i < count; i++) {
                    LayoutTools.ViewInfo viewInfo = viewInfos.get(i);
                    changeFieldName(viewInfo);
                    viewInfoMap.get(viewInfo.fieldName).add(viewInfo);
                }
            }
        }
        if (!isFinish) {
            dealViewinfo(viewInfoMap);
        }
    }


    private static void changeFieldName(LayoutTools.ViewInfo info) {
        info.fieldName = info.parent.fieldName + ConvertTool.toClassType(info.fieldName);
    }
}
