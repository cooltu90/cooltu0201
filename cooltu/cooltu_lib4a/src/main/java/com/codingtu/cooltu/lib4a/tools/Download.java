package com.codingtu.cooltu.lib4a.tools;

import com.codingtu.cooltu.lib4a.net.interceptor.HeaderInterceptor;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;
import com.codingtu.cooltu.lib4j.function.OnError;
import com.codingtu.cooltu.lib4j.function.OnFinish;
import com.codingtu.cooltu.lib4j.function.OnProgress;
import com.codingtu.cooltu.lib4j.function.OnStart;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

public class Download implements OnDestroy {

    private String url;
    private File file;
    private File dir;
    private String fileName;
    private String tag;
    private Long timeout;
    private HeaderInterceptor headerInterceptor;

    private OnFinish<File> onFinish;
    private OnError onError;
    private OnProgress onProgress;
    private OnStart onStart;
    private boolean sync;
    private long totalLen;
    private Integer cacheSize;
    private long downloadedLen;
    private long lastTime;


    Download() {
    }

    public static Download url(String url) {
        Download download = new Download();
        download.url = url;
        download.tag = "download";
        return download;
    }

    @Override
    public void destroy() {
        onFinish = null;
        onError = null;
        onProgress = null;
        onStart = null;
        headerInterceptor = null;
    }

    public Download fileDir(String dir) {
        this.dir = new File(dir);
        return this;
    }

    public Download fileDir(File dir) {
        this.dir = dir;
        return this;
    }

    public Download fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public Download file(String path) {
        return file(new File(path));
    }

    public Download file(File file) {
        this.file = file;
        return this;
    }

    public Download tag(String tag) {
        this.tag = tag;
        return this;
    }

    public Download timeout(Long timeout) {
        this.timeout = timeout;
        return this;
    }

    public Download headerInterceptor(HeaderInterceptor headerInterceptor) {
        this.headerInterceptor = headerInterceptor;
        return this;
    }

    public Download cacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
        return this;
    }

    public Download error(OnError onError) {
        this.onError = onError;
        return this;
    }

    public Download finish(OnFinish<File> onFinish) {
        this.onFinish = onFinish;
        return this;
    }

    public Download progress(OnProgress onProgress) {
        this.onProgress = onProgress;
        return this;
    }

    public Download start(OnStart onStart) {
        this.onStart = onStart;
        return this;
    }

    public Download sync() {
        this.sync = true;
        return this;
    }


    public void download() {
        if (StringTool.isBlank(url)) {
            onError(new RuntimeException("下载链接为空"));
            return;
        }

        if (file == null) {
            if (dir == null) {
                onError(new RuntimeException("未指定下载保存路径"));
                return;
            }
            if (StringTool.isBlank(fileName)) {
                fileName = url.substring(url.lastIndexOf("/") + 1);
            }
            file = new File(dir, fileName);
        } else {
            dir = file.getParentFile();
            fileName = file.getName();
        }

        if (onStart != null) {
            onStart.onStart();
        }

        OkGo okGo = OkGo.getInstance();
        if (timeout != null && headerInterceptor != null) {
            okGo.setOkHttpClient(
                    okGo.getOkHttpClient()
                            .newBuilder()
                            .connectTimeout(timeout, TimeUnit.MILLISECONDS)
                            .addInterceptor(headerInterceptor)
                            .build()
            );
        } else if (timeout != null) {
            okGo.setOkHttpClient(
                    okGo.getOkHttpClient()
                            .newBuilder()
                            .connectTimeout(timeout, TimeUnit.MILLISECONDS)
                            .build()
            );
        } else if (headerInterceptor != null) {
            okGo.setOkHttpClient(
                    okGo.getOkHttpClient()
                            .newBuilder()
                            .addInterceptor(headerInterceptor)
                            .build()
            );
        }

        SDCardTool.createFileDir(file);

        if (sync) {
            //同步
            InputStream input = null;
            OutputStream out = null;
            okhttp3.Response response = null;

            try {

                if (cacheSize == null) {
                    cacheSize = 1024 * 512;
                }

                response = okGo.<File>get(url)
                        .tag(tag)
                        .execute();
                totalLen = response.body().contentLength();
                input = response.body().byteStream();
                out = new FileOutputStream(file);

                byte[] buff = new byte[cacheSize];
                int len = 0;

                while ((len = input.read(buff)) != -1) {
                    out.write(buff, 0, len);
                    downloadedLen += len;

                    long nowTime = System.currentTimeMillis();
                    if (nowTime - lastTime > 100) {
                        if (downloadedLen < totalLen) {
                            if (onProgress != null) {
                                onProgress.onProgress(totalLen, downloadedLen);
                            }
                        }
                        lastTime = nowTime;
                    }

                }

                if (onProgress != null) {
                    onProgress.onProgress(totalLen, totalLen);
                }

            } catch (IOException e) {
                Download.this.onError(e);
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (response != null) {
                    try {
                        response.close();
                    } catch (Exception e) {
                    }
                }
            }

            if (onFinish != null) {
                onFinish.onFinish(file);
            }
            destroy();

            return;
        }

        FileCallback fileCallback = new FileCallback(dir.getAbsolutePath(), fileName) {
            @Override
            public void onSuccess(Response<File> response) {
                if (onFinish != null) {
                    onFinish.onFinish(response.body());
                }
                destroy();
            }

            @Override
            public void onError(Response<File> response) {
                super.onError(response);
                Download.this.onError(response.getException());
            }

            @Override
            public void downloadProgress(Progress progress) {
                super.downloadProgress(progress);
                if (onProgress != null) {
                    onProgress.onProgress(progress.totalSize, progress.currentSize);
                }
            }
        };
        okGo.<File>get(url)
                .tag(tag)
                .execute(fileCallback);
    }

    private void onError(Throwable throwable) {
        if (onError != null) {
            onError.onError(throwable);
        }
        destroy();
    }

}
