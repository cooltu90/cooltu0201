package com.codingtu.cooltu.processor.annotation.bean;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ConvertItem {
    String value();
}
