package core.net.back;

import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public class AddObj2Back extends com.codingtu.cooltu.lib4a.net.netback.NetBack {

    @Override
    public void accept(String code, Result<ResponseBody> result, com.codingtu.cooltu.lib4a.net.bean.CoreSendParams params, List objs) {
        super.accept(code, result, params, objs);


    }

}
