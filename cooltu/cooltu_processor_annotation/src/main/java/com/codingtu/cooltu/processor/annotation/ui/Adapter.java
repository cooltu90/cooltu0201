package com.codingtu.cooltu.processor.annotation.ui;

import com.codingtu.cooltu.constant.AdapterType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Adapter {
    AdapterType type() default AdapterType.DEFAULT_LIST;

    String rvName() default "rv";

    Class rvConfig() default Void.class;
}
