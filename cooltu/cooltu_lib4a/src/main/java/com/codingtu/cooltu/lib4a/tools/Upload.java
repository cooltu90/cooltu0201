package com.codingtu.cooltu.lib4a.tools;

import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;
import com.codingtu.cooltu.lib4j.function.OnError;
import com.codingtu.cooltu.lib4j.function.OnFinish;
import com.codingtu.cooltu.lib4j.function.OnProgress;
import com.codingtu.cooltu.lib4j.function.OnStart;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okgo.request.base.ProgressRequestBody;

import java.io.File;

import okhttp3.Request;
import okhttp3.RequestBody;
import okio.BufferedSink;

public class Upload implements OnDestroy {

    /**************************************************
     *
     *
     *
     **************************************************/

    private PostRequest<String> post;

    private File file;
    private String fileKey;

    private OnFinish<String> onFinish;
    private OnError onError;
    private OnProgress onProgress;
    private OnStart onStart;
    private boolean sync;

    @Override
    public void destroy() {
        onFinish = null;
        onError = null;
        onProgress = null;
        onStart = null;
        file = null;
        post = null;
    }


    Upload(String url) {
        if (StringTool.isBlank(url)) {
            onError(new RuntimeException("链接地址不能未空"));
            return;
        }

        post = OkGo.<String>post(url);
        post.tag(this).isMultipart(true);
    }

    public static Upload url(String url) {
        return new Upload(url);
    }

    public Upload header(String key, String value) {
        post.headers(key, value);
        return this;
    }

    public Upload file(String key, File file) {
        this.fileKey = key;
        this.file = file;
        return this;
    }

    public Upload file(String key, String filePath) {
        return file(key, new File(filePath));
    }

    public Upload param(String key, String value) {
        post.params(key, value);
        return this;
    }

    public Upload error(OnError onError) {
        this.onError = onError;
        return this;
    }

    public Upload finish(OnFinish<String> onFinish) {
        this.onFinish = onFinish;
        return this;
    }

    public Upload progress(OnProgress onProgress) {
        this.onProgress = onProgress;
        return this;
    }

    public Upload start(OnStart onStart) {
        this.onStart = onStart;
        return this;
    }

    public Upload sync() {
        this.sync = true;
        return this;
    }

    public void upload() {
        if (post == null) {
            return;
        }

        if (!file.exists()) {
            onError(new RuntimeException("上传文件不存在"));
            return;
        }

        if (onStart != null) {
            onStart.onStart();
        }

        post.params(fileKey, file);
        if (sync) {
            String responseResult = null;
            try {
                post.setCallback(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                    }

                    @Override
                    public void uploadProgress(Progress progress) {
                        super.uploadProgress(progress);
                        if (onProgress != null) {
                            onProgress.onProgress(progress.totalSize, progress.currentSize);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Logs.i("post error");
                        Upload.this.onError(response.getException());
                    }
                });
                okhttp3.Response response = post.execute();
                responseResult = response.body().string();
                try {
                    response.close();
                } catch (Exception e) {
                }
            } catch (Exception e) {
                Logs.i("response error");
                onError(e);
            } finally {

            }
            if (onFinish != null) {
                onFinish.onFinish(responseResult);
            }
            destroy();
            return;
        }

        post.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if (onFinish != null) {
                    onFinish.onFinish(response.body());
                }
                destroy();
            }

            @Override
            public void uploadProgress(Progress progress) {
                super.uploadProgress(progress);
                if (onProgress != null) {
                    onProgress.onProgress(progress.totalSize, progress.currentSize);
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Upload.this.onError(response.getException());
            }
        });
    }


    private void onError(Throwable throwable) {
        if (onError != null) {
            onError.onError(throwable);
        }
        destroy();
    }

}
