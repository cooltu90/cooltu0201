package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.processor.annotation.ui.DefaultDialogLayout;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.tools.IdTools;

import javax.lang.model.element.TypeElement;

public class DefaultDialogLayoutDeal extends TypeBaseDeal {
    @Override
    protected void dealTypeElement(TypeElement te) {
        Constant.DEFAULT_DIALOG_LAYOUT =
                IdTools.elementToId(
                                te,
                                DefaultDialogLayout.class,
                                te.getAnnotation(DefaultDialogLayout.class).value())
                        .toString();
    }
}
