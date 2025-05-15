package com.codingtu.cooltu.processor.annotation.ui.dialog;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface MenuDialogUse {
    //标题
    String title() default "操作";

    MenuDialogItem[] items();

    //传递的值类型
    Class objType() default Void.class;
}
