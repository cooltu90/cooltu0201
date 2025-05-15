package com.codingtu.cooltu.lib4j.tools;

import com.codingtu.cooltu.lib4j.data.bean.CoreBean;
import com.codingtu.cooltu.lib4j.json.JsonTool;

public class OsTool {

    public static <T> T[] objsToArrays(Object[] objs) {
        int count = CountTool.count(objs);
        if (count > 0) {
            T[] newArray = (T[]) java.lang.reflect.Array.newInstance
                    (objs[0].getClass(), count);
            for (int i = 0; i < count; i++) {
                newArray[i] = (T) objs[i];
            }
            return newArray;
        }
        return null;
    }

    public static <T extends CoreBean> T copy(T t) {
        return JsonTool.toBean((Class<T>) t.getClass(), t.toJson());
    }
}
