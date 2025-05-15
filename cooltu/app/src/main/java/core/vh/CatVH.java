package core.vh;

import android.view.ViewGroup;

public class CatVH extends com.codingtu.cooltu.lib4a.ui.adapter.viewholder.CoreAdapterVH {
    public android.widget.TextView tv;



    public CatVH(ViewGroup parent) {
        super(com.codingtu.cooltu.R.layout.item_cat, parent);
        tv = itemView.findViewById(com.codingtu.cooltu.R.id.tv);

    }
}
