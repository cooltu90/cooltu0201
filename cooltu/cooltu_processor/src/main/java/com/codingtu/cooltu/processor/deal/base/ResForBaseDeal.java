package com.codingtu.cooltu.processor.deal.base;

import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.processor.annotation.res.ColorRes;
import com.codingtu.cooltu.processor.annotation.res.ColorStr;
import com.codingtu.cooltu.processor.annotation.res.Dimen;
import com.codingtu.cooltu.processor.annotation.res.Dp;
import com.codingtu.cooltu.processor.annotation.ui.Adapter;
import com.codingtu.cooltu.processor.annotation.ui.InBase;
import com.codingtu.cooltu.processor.annotation.ui.Init;
import com.codingtu.cooltu.processor.annotation.ui.InitAbstract;
import com.codingtu.cooltu.processor.annotation.ui.dialog.DialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.EditDialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.MenuDialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.NoticeDialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.ToastDialogUse;
import com.codingtu.cooltu.processor.annotation.ui.fix.FixInt;
import com.codingtu.cooltu.processor.annotation.ui.fix.FixString;
import com.codingtu.cooltu.processor.annotation.ui.fix.FixValue;
import com.codingtu.cooltu.processor.builder.core.UiBaseBuilder;
import com.codingtu.cooltu.processor.lib.tools.BaseTools;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.IdTools;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

public abstract class ResForBaseDeal extends TypeBaseDeal {
    protected String uiClass;

    @Override
    protected void dealTypeElement(TypeElement te) {
        uiClass = getUiClass(te);

        BaseTools.GetThis<UiBaseBuilder> uiBaseBuilderGetter = getUiBaseBuilderGetter();
        UiBaseBuilder uiBaseBuilder = uiBaseBuilderGetter.getThis(uiClass);

        ElementTools.getVariableElements(te).ls(new Es.EachEs<VariableElement>() {
            @Override
            public boolean each(int position, VariableElement ve) {
                dealField(uiClass, ve, ElementTools.getFieldKv(ve), uiBaseBuilderGetter, uiBaseBuilder);
                return false;
            }
        });
        uiBaseBuilder.isToastDialog = te.getAnnotation(ToastDialogUse.class) != null;
        uiBaseBuilder.isNoticeDialog = te.getAnnotation(NoticeDialogUse.class) != null;

    }

    protected void dealField(String fullName, VariableElement ve, KV<String, String> kv,
                             BaseTools.GetThis<UiBaseBuilder> uiBaseBuilderGetter,
                             UiBaseBuilder uiBaseBuilder) {

        InBase inBase = ve.getAnnotation(InBase.class);
        if (inBase != null) {
            uiBaseBuilder.addInBase(kv);
            uiBaseBuilder.addInBase1(ve);
            Init init = ve.getAnnotation(Init.class);
            if (init != null) {
                uiBaseBuilder.addInits(ve);
            }
            InitAbstract initAbstract = ve.getAnnotation(InitAbstract.class);
            if (initAbstract != null) {
                uiBaseBuilder.addInitAbstracts(ve);
            }
        }
        ColorStr ColorStr = ve.getAnnotation(ColorStr.class);
        if (ColorStr != null) {
            uiBaseBuilder.colorStrs.add(new KV<>(kv.v, ColorStr.value()));
        }

        ColorRes colorRes = ve.getAnnotation(ColorRes.class);
        if (colorRes != null) {
            IdTools.Id id = IdTools.elementToId(ve, ColorRes.class, colorRes.value());
            uiBaseBuilder.colorReses.add(new KV<>(kv.v, id));
        }

        Dp dp = ve.getAnnotation(Dp.class);
        if (dp != null) {
            uiBaseBuilder.dps.add(new KV<>(kv.v, dp.value()));
        }

        Dimen dimen = ve.getAnnotation(Dimen.class);
        if (dimen != null) {
            IdTools.Id id = IdTools.elementToId(ve, Dimen.class, dimen.value());
            uiBaseBuilder.dimens.add(new KV<>(kv.v, id));
        }

        Adapter adapter = ve.getAnnotation(Adapter.class);
        if (adapter != null) {
            uiBaseBuilder.adapters.add(ve);
        }

        EditDialogUse editDialogUse = ve.getAnnotation(EditDialogUse.class);
        if (editDialogUse != null) {
            uiBaseBuilder.editDialogUses.add(ve);
        }

        DialogUse dialogUse = ve.getAnnotation(DialogUse.class);
        if (dialogUse != null) {
            uiBaseBuilder.dialogUses.add(ve);
        }

        MenuDialogUse menuDialogUse = ve.getAnnotation(MenuDialogUse.class);
        if (menuDialogUse != null) {
            uiBaseBuilder.menuDialogUses.add(ve);
        }

        FixInt fixInt = ve.getAnnotation(FixInt.class);
        if (fixInt != null) {
            uiBaseBuilder.fixInts.add(ve);
        }

        FixString fixString = ve.getAnnotation(FixString.class);
        if (fixString != null) {
            uiBaseBuilder.fixStrings.add(ve);
        }

        FixValue fixValue = ve.getAnnotation(FixValue.class);
        if (fixValue != null) {
            uiBaseBuilder.fixValues.add(ve);
        }


    }

    protected abstract BaseTools.GetThis<UiBaseBuilder> getUiBaseBuilderGetter();

    protected abstract String getUiClass(TypeElement te);
}
