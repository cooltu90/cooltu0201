package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.processor.annotation.ui.DefaultToastDialogLayout;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.tools.IdTools;

import javax.lang.model.element.TypeElement;

public class DefaultToastDialogLayoutDeal extends TypeBaseDeal {
    @Override
    protected void dealTypeElement(TypeElement te) {
        Constant.DEFAULT_TOAST_DIALOG_LAYOUT =
                IdTools.elementToId(
                                te,
                                DefaultToastDialogLayout.class,
                                te.getAnnotation(DefaultToastDialogLayout.class).value())
                        .toString();
    }
}
