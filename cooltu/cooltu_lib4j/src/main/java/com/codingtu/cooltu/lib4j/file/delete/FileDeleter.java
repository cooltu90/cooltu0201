package com.codingtu.cooltu.lib4j.file.delete;

import com.codingtu.cooltu.lib4j.file.FileTool;
import com.codingtu.cooltu.lib4j.file.copy.FileCopy;
import com.codingtu.cooltu.lib4j.function.OnFinish;
import com.codingtu.cooltu.lib4j.function.OnProgress;
import com.codingtu.cooltu.lib4j.function.OnStart;

import java.io.File;

public class FileDeleter {

    private File deleteFile;
    private OnProgress onProgress;
    private OnFinish onFinish;
    private OnStart onStart;
    private long totalLen;
    private long currentLen;

    public static FileDeleter file(String path) {
        return file(new File(path));
    }

    public static FileDeleter file(File file) {
        FileDeleter fileDeleter = new FileDeleter();
        fileDeleter.deleteFile = file;
        return fileDeleter;
    }

    public FileDeleter progress(OnProgress onProgress) {
        this.onProgress = onProgress;
        return this;
    }

    public FileDeleter finish(OnFinish onFinish) {
        this.onFinish = onFinish;
        return this;
    }

    public FileDeleter start(OnStart onStart) {
        this.onStart = onStart;
        return this;
    }

    public void delete() {
        totalLen = FileTool.obtainTotalLength(deleteFile, null);

        if (onStart != null) {
            onStart.onStart();
        }
        deleteReal(deleteFile);

        onProgress(totalLen);

        if (onFinish != null) {
            onFinish.onFinish(null);
        }
    }

    private void deleteReal(File file) {
        if (!file.exists()) {
            return;
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            int size = files == null ? 0 : files.length;
            for (int i = 0; i < size; i++) {
                deleteReal(files[i]);
            }
        } else {
            currentLen += file.length();
        }
        file.delete();
        onProgress(currentLen);
    }

    private void onProgress(long currentLen) {
        if (this.onProgress != null) {
            this.onProgress.onProgress(totalLen, currentLen);
        }
    }

}
