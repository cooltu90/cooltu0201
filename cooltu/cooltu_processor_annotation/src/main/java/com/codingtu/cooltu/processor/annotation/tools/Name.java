package com.codingtu.cooltu.processor.annotation.tools;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Name {
   String value();
}