package com.codingtu.cooltu.processor.bean;

import java.util.List;

import javax.lang.model.element.VariableElement;

public class NetInfo {

    public String methodName;
    public String apisBaseUrl;
    public String apisName;
    public String methodBaseUrl;
    public String methodValue;
    public List<? extends VariableElement> params;
    public boolean isJsonBody;
}
