package com.codingtu.cooltu.processor.annotation.create;

import com.codingtu.cooltu.constant.ScreenOrientationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
public @interface CreateAct {
    String name();

    String packages();

    Class baseClass();

    String screenOrientation() default ScreenOrientationType.PHONE;

    int layoutTemp();

}
