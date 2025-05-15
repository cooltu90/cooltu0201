package com.codingtu.cooltu.processor.lib.tools;

import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.processor.builder.core.UiBaseBuilder;
import com.codingtu.cooltu.processor.builder.impl.ActBaseBuilder;
import com.codingtu.cooltu.processor.builder.impl.FragmentBaseBuilder;
import com.codingtu.cooltu.processor.deal.ActBaseDeal;
import com.codingtu.cooltu.processor.deal.FragmentBaseDeal;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;

import java.util.ArrayList;
import java.util.List;

public class BaseTools {


    /**************************************************
     *
     * 获取全部父类（包括自己）
     *
     **************************************************/

    public static BaseTools.GetParent<UiBaseBuilder> getActBaseParentGetter() {
        return new BaseTools.GetParent<UiBaseBuilder>() {
            @Override
            public UiBaseBuilder getParent(UiBaseBuilder uiBaseBuilder) {
                ActBaseBuilder builder = CurrentPath.actBaseBuilder(uiBaseBuilder.baseClass);
                if (builder == null) {
                    return null;
                }
                return builder.getUiBaseBuilder();
            }
        };
    }

    public static BaseTools.GetParent<UiBaseBuilder> getFragBaseParentGetter() {
        return new BaseTools.GetParent<UiBaseBuilder>() {
            @Override
            public UiBaseBuilder getParent(UiBaseBuilder uiBaseBuilder) {
                FragmentBaseBuilder builder = CurrentPath.fragBaseBuilder(uiBaseBuilder.baseClass);
                if (builder == null) {
                    return null;
                }
                return builder.getUiBaseBuilder();
            }
        };
    }

    public static <E> List<E> getThisWithParents(E e, GetParent<E> getParent) {
        ArrayList<E> es = new ArrayList<>();
        getThisWithParents(e, getParent, new Es.EachEs<E>() {
            @Override
            public boolean each(int position, E e) {
                es.add(e);
                return false;
            }
        });
        return es;
    }

    public static <E> void getThisWithParents(E e, GetParent<E> getParent, Es.EachEs<E> eachEs) {
        getThisWithParents(e, new int[]{0}, getParent, eachEs);
    }

    private static <E> void getThisWithParents(E e, int[] indexs, GetParent<E> getParent, Es.EachEs<E> eachEs) {
        if (e != null) {
            eachEs.each(indexs[0]++, e);
            E parent = getParent.getParent(e);
            if (parent != null) {
                getThisWithParents(parent, indexs, getParent, eachEs);
            }
        }
    }

    public static interface GetParent<E> {
        E getParent(E e);
    }

    /**************************************************
     *
     * 获取全部子类（包括自己）
     *
     **************************************************/
    public static BaseTools.GetThis<UiBaseBuilder> getActBaseChildGetter() {
        return new BaseTools.GetThis<UiBaseBuilder>() {
            @Override
            public UiBaseBuilder getThis(String thisClass) {
                return CurrentPath.actBaseBuilder(thisClass).getUiBaseBuilder();
            }

            @Override
            public List<String> getChilds(String thisClass) {
                return ActBaseDeal.map.get(thisClass);
            }
        };
    }

    public static BaseTools.GetThis<UiBaseBuilder> getFragBaseChildGetter() {
        return new BaseTools.GetThis<UiBaseBuilder>() {
            @Override
            public UiBaseBuilder getThis(String thisClass) {
                return CurrentPath.fragBaseBuilder(thisClass).getUiBaseBuilder();
            }

            @Override
            public List<String> getChilds(String thisClass) {
                return FragmentBaseDeal.map.get(thisClass);
            }
        };
    }


    public static <E> List<E> getThisWithChilds(String thisClass, GetThis<E> getThis) {
        ArrayList<E> list = new ArrayList<>();
        getThisWithChilds(thisClass, new Es.EachEs<E>() {
            @Override
            public boolean each(int position, E e) {
                list.add(e);
                return false;
            }
        }, getThis);
        return list;
    }


    public static <E> void getThisWithChilds(String thisClass, Es.EachEs<E> eachEs, GetThis<E> getThis) {
        getThisWithChilds(thisClass, new int[]{0}, eachEs, getThis);
    }


    private static <E> void getThisWithChilds(String thisClass, int[] indexs, Es.EachEs<E> eachEs, GetThis<E> getThis) {
        E builder = getThis.getThis(thisClass);
        if (builder != null) {
            eachEs.each(indexs[0]++, builder);
            List<String> childs = getThis.getChilds(thisClass);
            int count = CountTool.count(childs);
            if (count > 0) {
                for (int i = 0; i < count; i++) {
                    String child = childs.get(i);
                    getThisWithChilds(child, indexs, eachEs, getThis);
                }
            }
        }
    }

    public static interface GetThis<T> {
        public T getThis(String thisClass);

        public List<String> getChilds(String thisClass);
    }

}
