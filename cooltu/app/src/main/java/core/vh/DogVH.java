package core.vh;

import android.view.ViewGroup;

public class DogVH extends com.codingtu.cooltu.lib4a.ui.adapter.viewholder.CoreAdapterVH {
    public android.widget.TextView tv;
    public android.widget.TextView tv1;



    public DogVH(ViewGroup parent) {
        super(com.codingtu.cooltu.R.layout.item_dog, parent);
        tv = itemView.findViewById(com.codingtu.cooltu.R.id.tv);
        tv1 = itemView.findViewById(com.codingtu.cooltu.R.id.tv1);

    }
}
