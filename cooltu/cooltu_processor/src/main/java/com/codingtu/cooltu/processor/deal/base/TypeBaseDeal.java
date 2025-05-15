package com.codingtu.cooltu.processor.deal.base;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

public abstract class TypeBaseDeal extends BaseDeal {
    @Override
    public void dealElement(Element element) {
        dealTypeElement((TypeElement) element);
    }

    protected abstract void dealTypeElement(TypeElement te);
}
