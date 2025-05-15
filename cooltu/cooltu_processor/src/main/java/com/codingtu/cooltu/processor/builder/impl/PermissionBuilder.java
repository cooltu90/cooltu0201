package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.processor.annotation.ui.Permission;
import com.codingtu.cooltu.processor.builder.base.PermissionBuilderBase;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.ExecutableElement;

public class PermissionBuilder extends PermissionBuilderBase {

    public static final PermissionBuilder BUILDER = new PermissionBuilder();

    private List<Permission> backs = new ArrayList<>();
    private List<ExecutableElement> ees = new ArrayList<>();

    public PermissionBuilder() {
        super(CurrentPath.javaInfo(FullName.PERMISSIONS));
    }

    public void add(Permission permission, ExecutableElement ee) {
        this.backs.add(permission);
        this.ees.add(ee);
    }

    @Override
    protected boolean isBuild() {
        return true;
    }

    @Override
    protected void beforeBuild(List<String> lines) {
        super.beforeBuild(lines);
        //Logs.i(lines);
    }

    @Override
    protected void dealLines() {
        addTag(pkg, Pkg.CORE_TOOLS);
        Es.es(backs).ls(new Es.EachEs<Permission>() {
            @Override
            public boolean each(int methodIndex, Permission permissionBack) {
                ExecutableElement ee = ees.get(methodIndex);

                String methodName = ElementTools.simpleName(ee);
                String actName = CurrentPath.javaInfo(ElementTools.getParentType(ee)).name;
                String methodNameStatic = ConvertTool.toStaticType(methodName);
                String actStatic = ConvertTool.toStaticType(actName);

                field(methodIndex, methodNameStatic, actStatic, "" + methodIndex);

                method(methodIndex, methodName, actName, FullName.PERMISSION_TOOL, methodNameStatic, actStatic);

                Es.es(permissionBack.value()).ls(new Es.EachEs<String>() {
                    @Override
                    public boolean each(int permissionIndex, String permission) {
                        permission(methodIndex, permissionIndex, permission);
                        return false;
                    }
                });

                return false;
            }
        });
    }

}
/* model_temp_start
package [[pkg]];

import android.app.Activity;

public class Permissions {
                                                                                                    [<sub>][for][field]
    public static final int CODE_[methodName]_IN_[act] = [value];
                                                                                                    [<sub>][for][field]
                                                                                                    [<sub>][for][method]
    public static void [methodName]In[act](Activity act) {
        [permissionToolFullName].check(act, CODE_[methodNameStatic]_IN_[actStatic]
                                                                                                    [<sub>][for][permission]
                , "[value]"
                                                                                                    [<sub>][for][permission]
        );
    }
                                                                                                    [<sub>][for][method]
}
model_temp_end */