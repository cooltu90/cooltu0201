package com.codingtu.cooltu.lib4a;

import android.app.Application;

import androidx.annotation.NonNull;

import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4j.config.LibApp;
import com.codingtu.cooltu.lib4j.config.LibConfigs;
import com.lzy.okgo.OkGo;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public abstract class CoreApp extends Application implements Thread.UncaughtExceptionHandler {

    //保存自己的实例
    public static CoreApp APP;

    @Override
    public void onCreate() {
        super.onCreate();
        APP = this;
        LibApp.APP = new LibApp() {
            @Override
            protected LibConfigs createConfigs() {
                return CoreApp.this.createConfigs();
            }
        };
        Thread.setDefaultUncaughtExceptionHandler(this);

        // 配置OkGo
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(CoreConfigs.configs().getOkGoReadTimeout(), TimeUnit.MILLISECONDS)
                .writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);

        // 全局配置OkGo
        OkGo.getInstance().init(this).setOkHttpClient(builder.build());
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        Logs.e(e);
    }

    public abstract CoreConfigs createConfigs();
}
