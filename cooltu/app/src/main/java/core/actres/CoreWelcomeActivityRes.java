package core.actres;

import android.widget.RelativeLayout;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.processor.annotation.ui.InBase;
import com.codingtu.cooltu.processor.annotation.res.ResFor;
import com.codingtu.cooltu.processor.annotation.ui.NoStart;
import com.codingtu.cooltu.ui.base.CoreWelcomeActivity;

@NoStart
@ResFor(CoreWelcomeActivity.class)
public class CoreWelcomeActivityRes {

    @InBase(Constant.SIGN_PROTECTED)
    RelativeLayout tv3;

    @InBase(Constant.SIGN_PROTECTED)
    boolean isTest;

}
