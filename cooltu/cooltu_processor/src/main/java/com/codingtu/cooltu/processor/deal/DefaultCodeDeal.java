package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.processor.annotation.ui.DefaultCode;
import com.codingtu.cooltu.processor.builder.impl.Code4RequestBuilder;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;

import javax.lang.model.element.TypeElement;

public class DefaultCodeDeal extends TypeBaseDeal {
    @Override
    protected void dealTypeElement(TypeElement te) {
        DefaultCode defaultCode = te.getAnnotation(DefaultCode.class);
        String[] codes = defaultCode.value();
        Es.es(codes).ls(new Es.EachEs<String>() {
            @Override
            public boolean each(int position, String s) {
                Code4RequestBuilder.BUILDER.add(s);
                return false;
            }
        });
    }
}
