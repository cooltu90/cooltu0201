package core.actres;

import com.codingtu.cooltu.Constants;
import com.codingtu.cooltu.R;
import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.form.TestCallBack;
import com.codingtu.cooltu.lib4a.view.dialogview.Dialog;
import com.codingtu.cooltu.lib4a.view.dialogview.EditDialog;
import com.codingtu.cooltu.lib4a.view.dialogview.MenuDialog;
import com.codingtu.cooltu.processor.annotation.res.ColorRes;
import com.codingtu.cooltu.processor.annotation.res.ColorStr;
import com.codingtu.cooltu.processor.annotation.res.Dimen;
import com.codingtu.cooltu.processor.annotation.res.Dp;
import com.codingtu.cooltu.processor.annotation.res.ResFor;
import com.codingtu.cooltu.processor.annotation.ui.InBase;
import com.codingtu.cooltu.processor.annotation.ui.Init;
import com.codingtu.cooltu.processor.annotation.ui.InitAbstract;
import com.codingtu.cooltu.processor.annotation.ui.StartGroup;
import com.codingtu.cooltu.processor.annotation.ui.dialog.DialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.EditDialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.MenuDialogItem;
import com.codingtu.cooltu.processor.annotation.ui.dialog.MenuDialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.NoticeDialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.ToastDialogUse;
import com.codingtu.cooltu.processor.annotation.ui.fix.FixInt;
import com.codingtu.cooltu.processor.annotation.ui.fix.FixString;
import com.codingtu.cooltu.processor.annotation.ui.fix.FixValue;
import com.codingtu.cooltu.ui.WelcomeActivity;

@ToastDialogUse
@NoticeDialogUse
@ResFor(WelcomeActivity.class)
public class WelcomeActivityRes {

    @StartGroup({1, 2})
    String name;
    @StartGroup({1})
    int age;
    @StartGroup({1})
    boolean isTest;


    @ColorStr("#ffffff")
    int textColor;

    @ColorRes(R.color.black)
    int tvColor;

    @Dp(12.5f)
    int dp;

    @Dimen(R.dimen.xxx)
    int dp1;

    @DialogUse(
            title = "提示",
            content = "胜多负少的"
    )
    Dialog dialog;

    @EditDialogUse(
            title = "提示",
            hint = "请输入文字",
            stopAnimation = false
    )
    EditDialog editDialog;

    @MenuDialogUse(
            title = "操作1",
            items = {
                    @MenuDialogItem(id = R.id.reportTv, name = "导出工单"),
                    @MenuDialogItem(id = R.id.deleteItemBt, name = "删除"),
                    @MenuDialogItem(id = R.id.deleteItemBt1, name = "删除1"),
            }
            //, objType = String.class

    )
    MenuDialog menuDialog;

    @FixInt(Constants.TYPE_ONE)
    String getDocType;

    @FixString("PP\"")
    String getModule;

    @FixValue("com.codingtu.cooltu.Constants.PKG_MODULE_APP")
    String getXXX;

    @InBase(Constant.SIGN_PROTECTED)
    @InitAbstract
    TestCallBack testCallBack;

    @InBase(Constant.SIGN_PROTECTED)
    @Init
    String testName;

}
