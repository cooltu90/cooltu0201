package core.actbase;

import android.view.View;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class WelcomeActivityBase extends com.codingtu.cooltu.ui.base.BaseWelcomeActivity implements View.OnClickListener, View.OnLongClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI{
    protected com.codingtu.cooltu.form.TestCallBack testCallBack;
    protected java.lang.String testName;
    protected int textColor;
    protected int tvColor;
    protected int dp;
    protected int dp1;
    protected java.lang.String fromAct;
    protected java.lang.String name;
    protected int age;

    public String baseClassName = "WelcomeActivityBase";

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        textColor = android.graphics.Color.parseColor("#ffffff");

        tvColor = com.codingtu.cooltu.lib4a.tools.ResourceTool.getColor(com.codingtu.cooltu.R.color.black);

        dp = com.codingtu.cooltu.lib4a.tools.MobileTool.dpToPx(12.5f);

        dp1 = com.codingtu.cooltu.lib4a.tools.ResourceTool.getDimen(com.codingtu.cooltu.R.dimen.xxx);

        fromAct = core.tools.Pass.fromAct(getIntent());
        name = core.tools.Pass.name(getIntent());
        age = core.tools.Pass.age(getIntent());
        isTest = core.tools.Pass.isTest(getIntent());


        String nowBaseClassName = getClass().getSimpleName() + "Base";
        if (nowBaseClassName.equals(baseClassName)) {
            onCreateComplete();
        }
    }

    @Override
    public void onCreateComplete() {
        super.onCreateComplete();

        showBt.setOnClickListener(this);




    }


    @Override
    public void onClick(View v) {
        super.onClick(v);

        try {
            switch (v.getId()) {
                case com.codingtu.cooltu.R.id.showBt:
                    showBtClick(
                    );
                    break;
                case com.codingtu.cooltu.R.id.reportTv:
                    reportTvClick(
                    );
                    break;

            }
        } catch (Exception e) {
            com.codingtu.cooltu.lib4a.log.Logs.e(e);
            if (!(e instanceof com.codingtu.cooltu.lib4a.exception.NotToastException)) {
                toast(e.getMessage());
            }
        }
    }

    protected void showBtClick() throws Exception {}
    protected void reportTvClick() throws Exception {}


    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {

        }
        return super.onLongClick(v);


    }


    @Override
    public void accept(String code, Result<ResponseBody> result, com.codingtu.cooltu.lib4a.net.bean.CoreSendParams params, List objs) {
        super.accept(code, result, params, objs);


        if ("addObj1Back".equals(code)) {
            new core.net.back.AddObj1Back() {
                @Override
                public void accept(String code, Result<ResponseBody> result, com.codingtu.cooltu.lib4a.net.bean.CoreSendParams params, List objs) {
                    super.accept(code, result, params, objs);
                    addObj1Back(json);
                }
            }.accept(code, result, params, objs);
        }

    }
    protected void addObj1Back(java.lang.String json) {}

    @Override
    public void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == android.app.Activity.RESULT_OK) {

        }
    }

    @Override
    public void back(int requestCode, String[] permissions, int[] grantResults) {
        super.back(requestCode, permissions, grantResults);

    }


    private com.codingtu.cooltu.lib4a.view.dialogview.ToastDialog toastDialog;

    protected com.codingtu.cooltu.lib4a.view.dialogview.ToastDialog getToastDialog() {
        if (toastDialog == null)
            toastDialog = new com.codingtu.cooltu.lib4a.view.dialogview.ToastDialog(getAct())
                    .destroys(this)
                    .setLayout(com.codingtu.cooltu.R.layout.dialog_toast)
                    .build();
        return toastDialog;
    }

    private com.codingtu.cooltu.lib4a.view.dialogview.NoticeDialog noticeDialog;

    protected com.codingtu.cooltu.lib4a.view.dialogview.NoticeDialog getNoticeDialog() {
        if (noticeDialog == null)
            noticeDialog = new com.codingtu.cooltu.lib4a.view.dialogview.NoticeDialog(getAct())
                    .destroys(this)
                    .setLayout(com.codingtu.cooltu.R.layout.dialog_notice)
                    .onClick(v -> {
                        noticeDialogYes(noticeDialog.obtainData());
                    })
                    .build();
        return noticeDialog;
    }

    public void noticeDialogYes(Object data) {
        noticeDialog.hidden();
    }

    protected com.codingtu.cooltu.lib4a.view.dialogview.EditDialog editDialog;

    protected void showEditDialog(String text) {
        if (editDialog == null)
            editDialog = new com.codingtu.cooltu.lib4a.view.dialogview.EditDialog(getAct())
                    .destroys(this)
                    .setTitle("提示")
                    .setHint("请输入文字")
                    .setInputType(1)
                    .setLayout(com.codingtu.cooltu.R.layout.dialog_edit)
                    .setYes(new com.codingtu.cooltu.lib4a.view.dialogview.EditDialog.Yes() {
                        @Override
                        public boolean yes(String text, Object obj) {
                            return editDialogYes(text);
                        }
                    })
                    .build();
        editDialog.setEditText(text);
        editDialog.setObject(null);
        editDialog.show();
    }


    protected boolean editDialogYes(String text) {
        return false;
    }

    protected com.codingtu.cooltu.lib4a.view.dialogview.Dialog dialog;
    protected void showDialog() {
        if (dialog == null) {
            dialog = new com.codingtu.cooltu.lib4a.view.dialogview.Dialog(getAct())
                    .destroys(this)
                    .setTitle("提示")
                    .setContent("胜多负少的")
                    .setLeftBtText("取消")
                    .setRighBtText("确定")
                    .setLayout(com.codingtu.cooltu.R.layout.dialog)
                    .setOnBtClick(new com.codingtu.cooltu.lib4a.view.dialogview.Dialog.OnBtClick() {
                        @Override
                        public void onLeftClick(Object obj) {
                            dialogLeft();
                        }

                        @Override
                        public void onRightClick(Object obj) {
                            dialogRight();
                        }
                    })
                    .build();
        }
        dialog.setObject(null);
        dialog.show();
    }
    protected void showDialog(String content) {
        if (dialog == null) {
            dialog = new com.codingtu.cooltu.lib4a.view.dialogview.Dialog(getAct())
                    .destroys(this)
                    .setTitle("提示")
                    .setContent(content)
                    .setLeftBtText("取消")
                    .setRighBtText("确定")
                    .setLayout(com.codingtu.cooltu.R.layout.dialog)
                    .setOnBtClick(new com.codingtu.cooltu.lib4a.view.dialogview.Dialog.OnBtClick() {
                        @Override
                        public void onLeftClick(Object obj) {
                            dialogLeft();
                        }

                        @Override
                        public void onRightClick(Object obj) {
                            dialogRight();
                        }
                    })
                    .build();
        } else {
            dialog.updateContent(content);
        }
        dialog.setObject(null);
        dialog.show();
    }
    protected void dialogLeft() { }
    protected void dialogRight() { }

    protected java.lang.String testName() {
        if (testName == null) {
            testName = new java.lang.String();
            testName(testName);
        }
        return testName;
    }

    protected void testName(java.lang.String testName) {}


    /**************************************************
     *
     *  menuDialog
     *
     **************************************************/
    protected com.codingtu.cooltu.lib4a.view.dialogview.MenuDialog menuDialog;

    protected void showMenuDialog() {
        if (menuDialog == null) {
            menuDialog = new com.codingtu.cooltu.lib4a.view.dialogview.MenuDialog(getAct())
                    .destroys(this)
                    .setLayout(com.codingtu.cooltu.R.layout.dialog_menu)
                    .setItemLayout(com.codingtu.cooltu.R.layout.dialog_menu_item)
                    .setItem(com.codingtu.cooltu.R.id.reportTv, "导出工单")
                    .setItem(com.codingtu.cooltu.R.id.deleteItemBt, "删除")
                    .setItem(com.codingtu.cooltu.R.id.deleteItemBt1, "删除1")
                    .setOnClickListener(this)
                    .setShowItem(new com.codingtu.cooltu.lib4a.view.dialogview.MenuDialog.ShowItem() {
                        @Override
                        public boolean showItem(int viewId, Object obj) {
                            switch (viewId) {
                                case com.codingtu.cooltu.R.id.reportTv:
                                    return showReportTv();
                                case com.codingtu.cooltu.R.id.deleteItemBt:
                                    return showDeleteItemBt();
                                case com.codingtu.cooltu.R.id.deleteItemBt1:
                                    return showDeleteItemBt1();
                            }
                            return false;
                        }
                    })
                    .setTitle("操作1")
                    .build();
        }
        menuDialog.setObj(null);
        menuDialog.show();
    }
    protected boolean showReportTv() {
        return true;
    }
    protected boolean showDeleteItemBt() {
        return true;
    }
    protected boolean showDeleteItemBt1() {
        return true;
    }
    public int getDocType() {
        return 1;
    }
    public java.lang.String getModule() {
        return "PP\"";
    }
    public java.lang.String getXXX() {
        return com.codingtu.cooltu.Constants.PKG_MODULE_APP;
    }
    protected com.codingtu.cooltu.form.TestCallBack testCallBack() {
        if (testCallBack == null) {
            testCallBack = testCallBackInit();
        }
        return testCallBack;
    }

    protected com.codingtu.cooltu.form.TestCallBack testCallBackInit() {
        return null;
    }


}

