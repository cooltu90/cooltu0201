package com.codingtu.cooltu.lib4a.view.combine;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4j.destory.Destroys;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;
import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.es.impl.StringEs;
import com.codingtu.cooltu.lib4j.tools.CountTool;

import java.util.ArrayList;
import java.util.List;

/**************************************************
 *
 * onDestory
 *
 **************************************************/
public class RadioGroup implements OnDestroy, View.OnClickListener {
    private boolean hasNull;
    private int selected = -1;
    private BaseEs<View> bts;
    private List<OnSelectChange> onSelectChanges;
    private OnSetItem onSetItem;
    private StringEs items;
    private OnClick onClick;

    public RadioGroup() {

    }

    public static interface OnClick {
        public boolean onClick(int index, View view);
    }

    public static interface OnSelectChange {
        public void onChange(int selected);
    }

    public static interface OnSetItem {

        public void setSelected(View view);

        public void setSelectno(View view);

    }

    public static RadioGroup obtain() {
        return new RadioGroup();
    }

    public RadioGroup destroys(Destroys destroys) {
        if (destroys != null) destroys.add(this);
        return this;
    }


    public RadioGroup setHasNull(boolean hasNull) {
        this.hasNull = hasNull;
        return this;
    }

    public RadioGroup setBts(View... bts) {
        this.bts = Es.es(bts);
        this.bts.ls(new Es.EachEs<View>() {
            @Override
            public boolean each(int position, View view) {
                view.setTag(R.id.tag_0, position);
                view.setOnClickListener(RadioGroup.this);
                return false;
            }
        });
        return this;
    }

    public RadioGroup setBts(ViewGroup vp) {
        if (vp.getChildCount() > 0) {
            this.bts = Es.es(new Es.EachGetter<View>() {
                @Override
                public int count() {
                    return vp.getChildCount();
                }

                @Override
                public View get(int i) {
                    View childAt = vp.getChildAt(i);
                    childAt.setTag(R.id.tag_0, i);
                    childAt.setOnClickListener(RadioGroup.this);
                    return childAt;
                }
            });
        }
        return this;
    }

    public RadioGroup initItems() {
        items = new StringEs();
        this.bts.ls(new Es.EachEs<View>() {
            @Override
            public boolean each(int position, View view) {
                if (view instanceof TextView) {
                    items.add(((TextView) view).getText().toString());
                }
                return false;
            }
        });
        return this;
    }

    public RadioGroup addOnSelectChange(OnSelectChange onSelectChange) {
        if (onSelectChanges == null) {
            onSelectChanges = new ArrayList<>();
        }
        onSelectChanges.add(onSelectChange);
        return this;
    }


    public RadioGroup setOnSetItem(OnSetItem onSetItem) {
        this.onSetItem = onSetItem;
        return this;
    }

    public RadioGroup setOnClick(OnClick onClick) {
        this.onClick = onClick;
        return this;
    }


    @Override
    public void onClick(View v) {
        int index = (int) v.getTag(R.id.tag_0);
        if (onClick != null) {
            if (!onClick.onClick(index, v)) {
                return;
            }
        }
        setSelected(index);
    }


    public RadioGroup setSelected(int index) {
        if (index == selected) {
            if (hasNull) {
                setSelectedReal(-1);
            }
        } else {
            setSelectedReal(index);
        }
        return this;
    }

    private void setSelectedReal(int index) {
        this.selected = index;
        change();
        for (int i = 0; i < CountTool.count(onSelectChanges); i++) {
            OnSelectChange onSelectChange = onSelectChanges.get(i);
            if (onSelectChange != null) onSelectChange.onChange(this.selected);
        }
    }

    private void change() {
        if (bts != null) bts.ls(new Es.EachEs<View>() {
            @Override
            public boolean each(int i, View view) {
                if (selected == i) {
                    if (onSetItem != null) {
                        onSetItem.setSelected(view);
                    }
                } else {
                    if (onSetItem != null) {
                        onSetItem.setSelectno(view);
                    }
                }
                return false;
            }
        });
    }


    public int getSelected() {
        return selected;
    }


    @Override
    public void destroy() {
        if (bts != null) {
            bts.ls(new Es.EachEs<View>() {
                @Override
                public boolean each(int position, View view) {
                    view.setOnClickListener(null);
                    return false;
                }
            }).clear();
        }
        bts = null;
        if (onSelectChanges != null) {
            onSelectChanges.clear();
            onSelectChanges = null;
        }
        onSetItem = null;
    }

    public BaseEs<View> getBts() {
        return bts;
    }

    /**************************************************
     *
     *
     *
     **************************************************/


    public RadioGroup setItems(String... items) {
        this.items = Es.strs(items);
        return this;
    }

    public RadioGroup addItems(String item) {
        if (this.items == null) {
            this.items = Es.strs(item);
        } else {
            this.items.add(item);
        }
        return this;
    }

    public String getCurrentItem() {
        return this.items.getByIndex(getSelected());
    }

    public int getIndex(String item) {
        return this.items.index(item);
    }


    public String getItem(int selected) {
        return this.items.getByIndex(selected);
    }

    public int count() {
        if (this.bts != null) return this.bts.count();
        return 0;
    }

    public boolean isLastSelected() {
        return getSelected() == count() - 1;
    }
}
