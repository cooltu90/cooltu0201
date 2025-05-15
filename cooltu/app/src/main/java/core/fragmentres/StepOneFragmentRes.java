package core.fragmentres;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.User;
import com.codingtu.cooltu.constant.AdapterType;
import com.codingtu.cooltu.lib4a.constant.InputType;
import com.codingtu.cooltu.lib4a.view.dialogview.Dialog;
import com.codingtu.cooltu.lib4a.view.dialogview.EditDialog;
import com.codingtu.cooltu.processor.annotation.res.ColorRes;
import com.codingtu.cooltu.processor.annotation.res.ColorStr;
import com.codingtu.cooltu.processor.annotation.res.Dimen;
import com.codingtu.cooltu.processor.annotation.res.Dp;
import com.codingtu.cooltu.processor.annotation.res.ResForFragment;
import com.codingtu.cooltu.processor.annotation.ui.Adapter;
import com.codingtu.cooltu.processor.annotation.ui.dialog.DialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.EditDialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.NoticeDialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.ToastDialogUse;
import com.codingtu.cooltu.ui.StepOneFragment;
import com.codingtu.cooltu.ui.adapter.DogAdapter;

@NoticeDialogUse
@ToastDialogUse
@ResForFragment(StepOneFragment.class)
public class StepOneFragmentRes {

    @ColorStr("#ffffff")
    int textColor;

    @ColorRes(R.color.black)
    int tvColor;

    @Dp(12.5f)
    int dp;

    @Dimen(R.dimen.xxx)
    int dp1;

    @Adapter(type = AdapterType.DEFAULT_MORE_LIST, rvName = "rv1")
    DogAdapter dogAdapter;

    @EditDialogUse(
            title = "xxx",
            hint = "xxx",
            inputType = InputType.INT,
            objType = User.class,
            isUseTextWatcher = true,
            stopAnimation = true
    )
    EditDialog ed;

    @DialogUse(
            title = "xxx1",
            content = "xxx1",
            objType = User.class,
            leftBtText = "取消",
            rightBtText = "确定"
    )
    Dialog dialog;

    @DialogUse(
            title = "xxx",
            content = "32343234",
            leftBtText = "取消",
            rightBtText = "确定"
    )
    Dialog dialog1;
}
