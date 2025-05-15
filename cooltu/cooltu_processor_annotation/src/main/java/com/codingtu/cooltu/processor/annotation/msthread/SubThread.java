package com.codingtu.cooltu.processor.annotation.msthread;

import com.codingtu.cooltu.processor.annotation.net.Default;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface SubThread {
    boolean isStart() default false;

    int value() default 0;

    boolean isDelay() default false;

    long defaultDelayMillis() default -1;

}
