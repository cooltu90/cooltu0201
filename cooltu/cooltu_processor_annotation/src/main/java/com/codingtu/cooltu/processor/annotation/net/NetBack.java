package com.codingtu.cooltu.processor.annotation.net;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface NetBack {
    Class mock() default Void.class;
}
