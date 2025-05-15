package core.net.back;

import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public class GetObjBack extends com.codingtu.cooltu.lib4a.net.netback.NetBack {
    public com.codingtu.cooltu.bean.User user;

    @Override
    public void accept(String code, Result<ResponseBody> result, com.codingtu.cooltu.lib4a.net.bean.CoreSendParams params, List objs) {
        super.accept(code, result, params, objs);
        if (com.codingtu.cooltu.lib4j.tools.StringTool.isNotBlank(json)) {
            user = com.codingtu.cooltu.lib4j.json.JsonTool.toBean(com.codingtu.cooltu.bean.User.class, json);
        }


    }

}
