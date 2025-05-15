package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.processor.annotation.ui.DefaultMenuDialogLayout;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.tools.IdTools;

import javax.lang.model.element.TypeElement;

public class DefaultMenuDialogLayoutDeal extends TypeBaseDeal {
    @Override
    protected void dealTypeElement(TypeElement te) {
        DefaultMenuDialogLayout annotation = te.getAnnotation(DefaultMenuDialogLayout.class);
        Constant.DEFAULT_MENU_DIALOG_LAYOUT =
                IdTools.elementToId(
                                te,
                                DefaultMenuDialogLayout.class,
                                annotation.layout())
                        .toString();
        Constant.DEFAULT_MENU_DIALOG_ITEM_LAYOUT =
                IdTools.elementToId(
                                te,
                                DefaultMenuDialogLayout.class,
                                annotation.item())
                        .toString();
    }
}
