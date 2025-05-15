package com.codingtu.cooltu.processor.annotation.msthread;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface MsThread {
    Class base() default Void.class;
}
