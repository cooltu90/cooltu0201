package com.codingtu.cooltu.processor.lib.tools;

import com.codingtu.cooltu.lib4j.file.read.FileReader;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.processor.constant.Tags;

import java.io.File;
import java.util.List;

public class TempTools {

    public static List<String> getTempLines(File file) {
        List<String> lines = FileReader.from(file).readLine();
        int start = 0;
        int end = 0;
        for (int i = 0; i < CountTool.count(lines); i++) {
            String line = lines.get(i);
            if (Tags.TEMP_START.equals(line.trim())) {
                start = i + 1;
            } else if (Tags.TEMP_END.equals(line.trim())) {
                end = i;
            }
        }

        if (start == end) {
            return null;
        }

        return lines.subList(start, end);
    }

}
