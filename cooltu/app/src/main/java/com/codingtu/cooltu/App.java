package com.codingtu.cooltu;

import com.codingtu.cooltu.constant.AdapterType;
import com.codingtu.cooltu.constant.Module;
import com.codingtu.cooltu.lib4a.CoreApp;
import com.codingtu.cooltu.lib4a.CoreConfigs;
import com.codingtu.cooltu.lib4a.ui.fragment.CoreFragment;
import com.codingtu.cooltu.processor.annotation.ModuleInfo;
import com.codingtu.cooltu.processor.annotation.create.CreateAct;
import com.codingtu.cooltu.processor.annotation.create.CreateAdapter;
import com.codingtu.cooltu.processor.annotation.ui.DefaultCode;
import com.codingtu.cooltu.processor.annotation.ui.DefaultDialogLayout;
import com.codingtu.cooltu.processor.annotation.ui.DefaultEditDialogLayout;
import com.codingtu.cooltu.processor.annotation.ui.DefaultMenuDialogLayout;
import com.codingtu.cooltu.processor.annotation.ui.DefaultNoticeDialogLayout;
import com.codingtu.cooltu.processor.annotation.ui.DefaultToastDialogLayout;
import com.codingtu.cooltu.ui.base.BaseActivity;

@ModuleInfo(
        module = Module.APP,
        baseAct = BaseActivity.class,
        baseFragment = CoreFragment.class,
        rPkg = "com.codingtu.cooltu"
)
@DefaultToastDialogLayout(R.layout.dialog_toast)
@DefaultNoticeDialogLayout(R.layout.dialog_notice)
@DefaultEditDialogLayout(R.layout.dialog_edit)
@DefaultDialogLayout(R.layout.dialog)
@DefaultMenuDialogLayout(layout = R.layout.dialog_menu, item = R.layout.dialog_menu_item)
@DefaultCode("CODE_TEST")
@CreateAct(
        name = "from_base",
        packages = "com.codingtu.cooltu.ui",
        baseClass = BaseActivity.class,
        layoutTemp = R.layout.layout_temp
)
@CreateAdapter(
        name = "horse",
        packages = "com.codingtu.cooltu.ui.adapter",
        type = AdapterType.DEFAULT_MORE_LIST,
        layoutTemp = R.layout.layout_temp
)
//@CreateFragment(
//        name = "base_step",
//        packages = "com.codingtu.cooltu.ui",
//        baseClass = CoreFragment.class,
//        layoutTemp = R.layout.layout_temp
//)
public class App extends CoreApp {
    @Override
    public CoreConfigs createConfigs() {
        return new Configs();
    }
}


