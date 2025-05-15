package com.codingtu.cooltu.tools;

import com.codingtu.cooltu.bean.User;
import com.codingtu.cooltu.lib4a.tools.MeTool;

public class Me {

    public static User me() {
        return MeTool.me(User.class);
    }

    public static boolean isLogin() {
        return MeTool.isLogin(User.class);
    }

    public static void setMe(User user) {
        MeTool.setMe(user);
    }
}
