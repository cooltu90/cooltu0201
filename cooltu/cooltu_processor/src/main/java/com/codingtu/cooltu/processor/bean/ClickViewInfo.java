package com.codingtu.cooltu.processor.bean;

import com.codingtu.cooltu.processor.lib.param.Params;
import com.codingtu.cooltu.processor.lib.tools.IdTools;

import java.util.ArrayList;
import java.util.List;

public class ClickViewInfo {
    public List<Boolean> inAct=new ArrayList<>();
    public List<IdTools.Id> ids;
    public Params methodParams;
    public String method;
    public boolean isCheckLogin;
    public String checkClassName;
}
