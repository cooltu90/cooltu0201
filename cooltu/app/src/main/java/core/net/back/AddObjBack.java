package core.net.back;

import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public class AddObjBack extends com.codingtu.cooltu.lib4a.net.netback.NetBack {
    public java.util.List<com.codingtu.cooltu.bean.User> users;

    @Override
    public void accept(String code, Result<ResponseBody> result, com.codingtu.cooltu.lib4a.net.bean.CoreSendParams params, List objs) {
        super.accept(code, result, params, objs);
        if (com.codingtu.cooltu.lib4j.tools.StringTool.isNotBlank(json)) {
            users = com.codingtu.cooltu.lib4j.json.JsonTool.toBeanList(com.codingtu.cooltu.bean.User.class, json);
        }


    }

}
