package com.codingtu.cooltu.processor.annotation.msthread;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface MainThread {
    boolean isDelay() default false;

    long defaultDelayMillis() default -1;
}
