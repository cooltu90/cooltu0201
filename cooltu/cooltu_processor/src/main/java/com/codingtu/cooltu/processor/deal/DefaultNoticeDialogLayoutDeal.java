package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.processor.annotation.ui.DefaultNoticeDialogLayout;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.tools.IdTools;

import javax.lang.model.element.TypeElement;

public class DefaultNoticeDialogLayoutDeal extends TypeBaseDeal {
    @Override
    protected void dealTypeElement(TypeElement te) {
        Constant.DEFAULT_NOTICE_DIALOG_LAYOUT =
                IdTools.elementToId(
                                te,
                                DefaultNoticeDialogLayout.class,
                                te.getAnnotation(DefaultNoticeDialogLayout.class).value())
                        .toString();

    }
}
