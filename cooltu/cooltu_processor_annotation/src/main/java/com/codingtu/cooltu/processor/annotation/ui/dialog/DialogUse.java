package com.codingtu.cooltu.processor.annotation.ui.dialog;

public @interface DialogUse {
    String title();

    String content();

    Class objType() default Void.class;

    String leftBtText() default "取消";

    String rightBtText() default "确定";
}
