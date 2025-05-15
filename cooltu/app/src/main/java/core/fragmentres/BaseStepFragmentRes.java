package core.fragmentres;

import com.codingtu.cooltu.processor.annotation.res.ResForFragment;
import com.codingtu.cooltu.processor.annotation.ui.Adapter;
import com.codingtu.cooltu.ui.BaseStepFragment;
import com.codingtu.cooltu.ui.adapter.CatAdapter;

@ResForFragment(BaseStepFragment.class)
public class BaseStepFragmentRes {

    @Adapter
    CatAdapter catAdapter;

}
