package core.actres;

import com.codingtu.cooltu.processor.annotation.res.ResFor;
import com.codingtu.cooltu.processor.annotation.ui.NoStart;
import com.codingtu.cooltu.processor.annotation.ui.StartGroup;
import com.codingtu.cooltu.ui.base.BaseWelcomeActivity;

@NoStart
@ResFor(BaseWelcomeActivity.class)
public class BaseWelcomeActivityRes {

    @StartGroup({2})
    private String xx;

}
