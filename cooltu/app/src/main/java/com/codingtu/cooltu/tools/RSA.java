package com.codingtu.cooltu.tools;

import com.codingtu.cooltu.lib4a.cryption.BaseRSA;

public class RSA extends BaseRSA<RSA> {

    public static RSA obtain() {
        return new RSA();
    }

    @Override
    protected void cachePrivateKeyStr(String privateKey) {
        //缓存私钥到本地
    }

    @Override
    protected String obtainPrivateKeyStrFormLocal() {
        return null;
    }

}
