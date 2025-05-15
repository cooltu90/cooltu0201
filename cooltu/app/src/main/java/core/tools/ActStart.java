package core.tools;

import android.app.Activity;
import android.content.Intent;

public class ActStart {
    public static final void fromBaseActivity(Activity act) {
        Intent intent = new Intent(act, com.codingtu.cooltu.ui.FromBaseActivity.class);
        intent.putExtra(Pass.FROM_ACT, act.getClass().getCanonicalName());
        com.codingtu.cooltu.lib4a.tools.ActTool.startActivityForResult(act, intent, Code4Request.FROM_BASE_ACTIVITY);
    }
    public static final void subThreadActivity(Activity act) {
        Intent intent = new Intent(act, com.codingtu.cooltu.ui.SubThreadActivity.class);
        intent.putExtra(Pass.FROM_ACT, act.getClass().getCanonicalName());
        com.codingtu.cooltu.lib4a.tools.ActTool.startActivityForResult(act, intent, Code4Request.SUB_THREAD_ACTIVITY);
    }
    public static final void fromActivity(Activity act) {
        Intent intent = new Intent(act, com.codingtu.cooltu.ui.FromActivity.class);
        intent.putExtra(Pass.FROM_ACT, act.getClass().getCanonicalName());
        com.codingtu.cooltu.lib4a.tools.ActTool.startActivityForResult(act, intent, Code4Request.FROM_ACTIVITY);
    }
    public static final void stepTwoActivity(Activity act) {
        Intent intent = new Intent(act, com.codingtu.cooltu.ui.StepTwoActivity.class);
        intent.putExtra(Pass.FROM_ACT, act.getClass().getCanonicalName());
        com.codingtu.cooltu.lib4a.tools.ActTool.startActivityForResult(act, intent, Code4Request.STEP_TWO_ACTIVITY);
    }
    public static final void stepOneActivity(Activity act) {
        Intent intent = new Intent(act, com.codingtu.cooltu.ui.StepOneActivity.class);
        intent.putExtra(Pass.FROM_ACT, act.getClass().getCanonicalName());
        com.codingtu.cooltu.lib4a.tools.ActTool.startActivityForResult(act, intent, Code4Request.STEP_ONE_ACTIVITY);
    }
    public static final void welcomeActivity(Activity act) {
        Intent intent = new Intent(act, com.codingtu.cooltu.ui.WelcomeActivity.class);
        intent.putExtra(Pass.FROM_ACT, act.getClass().getCanonicalName());
        com.codingtu.cooltu.lib4a.tools.ActTool.startActivityForResult(act, intent, Code4Request.WELCOME_ACTIVITY);
    }
    public static final void welcomeActivity(Activity act, java.lang.String name, int age, boolean isTest) {
        Intent intent = new Intent(act, com.codingtu.cooltu.ui.WelcomeActivity.class);
        intent.putExtra(Pass.FROM_ACT, act.getClass().getCanonicalName());
        intent.putExtra(Pass.NAME, name);
        intent.putExtra(Pass.AGE, age);
        intent.putExtra(Pass.IS_TEST, isTest);
        com.codingtu.cooltu.lib4a.tools.ActTool.startActivityForResult(act, intent, Code4Request.WELCOME_ACTIVITY);
    }
    public static final void welcomeActivity(Activity act, java.lang.String name, java.lang.String xx) {
        Intent intent = new Intent(act, com.codingtu.cooltu.ui.WelcomeActivity.class);
        intent.putExtra(Pass.FROM_ACT, act.getClass().getCanonicalName());
        intent.putExtra(Pass.NAME, name);
        intent.putExtra(Pass.XX, xx);
        com.codingtu.cooltu.lib4a.tools.ActTool.startActivityForResult(act, intent, Code4Request.WELCOME_ACTIVITY);
    }

}
