package com.codingtu.cooltu;

import com.codingtu.cooltu.lib4a.CoreConfigs;

public class Configs extends CoreConfigs {
    @Override
    public String getBaseUrl() {
        return null;
    }

    @Override
    public String getImageGetterFileProvider() {
        return "com.codingtu.cooltu.fileprovider";
    }

    @Override
    public boolean isLog() {
        return true;
    }

    @Override
    public boolean isLogHttpConnect() {
        return false;
    }

    @Override
    public boolean isLogJsonException() {
        return false;
    }

    @Override
    public String getDefaultLogTag() {
        return "TestApp";
    }
}
