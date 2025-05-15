package com.codingtu.cooltu.processor.annotation.ui;

import com.codingtu.cooltu.constant.Constant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface InBase {
    String value() default Constant.SIGN_PRIVATE;
}
