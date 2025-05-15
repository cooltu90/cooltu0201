package core.actres;

import com.codingtu.cooltu.bean.FormData1;
import com.codingtu.cooltu.processor.annotation.form.FromBean;
import com.codingtu.cooltu.processor.annotation.res.ResFor;
import com.codingtu.cooltu.ui.FromActivity;

@ResFor(FromActivity.class)
public class FromActivityRes {

    @FromBean
    public FormData1 formData1;

}
