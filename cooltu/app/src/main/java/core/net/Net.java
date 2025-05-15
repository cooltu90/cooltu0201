package core.net;

public class Net {
    private static final String GET_OBJ = "getObjBack";
    private static final String ADD_OBJ = "addObjBack";
    private static final String ADD_OBJ1 = "addObj1Back";
    private static final String ADD_OBJ2 = "addObj2Back";
    private static final String ADD_OBJ3 = "addObj3Back";
    private static final String ADD_OBJ4 = "addObj4Back";
    private static final String TEST_API_ADD_OBJ = "https://wwww.sddfsdfsd.com";
    private static final String TEST_API_ADD_OBJ1 = "https://wwww.sddfsdfsd.com";
    private static final String TEST_API_ADD_OBJ2 = "https://wwww.sddfsdfsd.com";

    public static com.codingtu.cooltu.lib4a.net.api.API getObj(java.lang.String id, java.lang.String order) {
        core.net.params.GetObjParams params = new core.net.params.GetObjParams();
        params.token = "stDXFdsdff";
        params.id = id;
        params.order = order;
        return com.codingtu.cooltu.lib4a.net.NetTool.api((retrofit, ps) -> {
            core.net.params.GetObjParams paramsGet = (core.net.params.GetObjParams) ps;

            return retrofit.create(core.net.api.TestApiService.class).getObj(
                    paramsGet.token,
                    paramsGet.id,
                    paramsGet.order
            );
        }, GET_OBJ, com.codingtu.cooltu.lib4a.CoreConfigs.configs().getBaseUrl(), params);
    }
    public static com.codingtu.cooltu.lib4a.net.api.API addObj(java.lang.String name, int age) {
        core.net.params.AddObjParams params = new core.net.params.AddObjParams();
        params.name = name;
        params.age = age;
        return com.codingtu.cooltu.lib4a.net.NetTool.api((retrofit, ps) -> {
            core.net.params.AddObjParams paramsGet = (core.net.params.AddObjParams) ps;
            com.codingtu.cooltu.lib4j.json.base.JO jo = com.codingtu.cooltu.lib4j.json.JsonTool.createJO();
            jo.put("name", paramsGet.name);
            jo.put("age", paramsGet.age);

            return retrofit.create(core.net.api.TestApiService.class).addObj(
                    com.codingtu.cooltu.lib4a.net.NetTool.toJsonBody(jo.toJson())
            );
        }, ADD_OBJ, TEST_API_ADD_OBJ, params);
    }
    public static com.codingtu.cooltu.lib4a.net.api.API addObj1(com.codingtu.cooltu.bean.User user) {
        core.net.params.AddObj1Params params = new core.net.params.AddObj1Params();
        params.user = user;
        return com.codingtu.cooltu.lib4a.net.NetTool.api((retrofit, ps) -> {
            core.net.params.AddObj1Params paramsGet = (core.net.params.AddObj1Params) ps;

            return retrofit.create(core.net.api.TestApiService.class).addObj1(
                    com.codingtu.cooltu.lib4a.net.NetTool.toJsonBody(com.codingtu.cooltu.lib4j.json.JsonTool.toJson(paramsGet.user))
            );
        }, ADD_OBJ1, TEST_API_ADD_OBJ1, params);
    }
    public static com.codingtu.cooltu.lib4a.net.api.API addObj2(java.lang.String name, int age, java.lang.String parent) {
        core.net.params.AddObj2Params params = new core.net.params.AddObj2Params();
        params.name = name;
        params.age = age;
        params.parent = parent;
        return com.codingtu.cooltu.lib4a.net.NetTool.api((retrofit, ps) -> {
            core.net.params.AddObj2Params paramsGet = (core.net.params.AddObj2Params) ps;

            return retrofit.create(core.net.api.TestApiService.class).addObj2(
                    paramsGet.name,
                    paramsGet.age,
                    paramsGet.parent
            );
        }, ADD_OBJ2, TEST_API_ADD_OBJ2, params);
    }
    public static com.codingtu.cooltu.lib4a.net.api.API addObj3(java.lang.String name) {
        core.net.params.AddObj3Params params = new core.net.params.AddObj3Params();
        params.name = name;
        return com.codingtu.cooltu.lib4a.net.NetTool.api((retrofit, ps) -> {
            core.net.params.AddObj3Params paramsGet = (core.net.params.AddObj3Params) ps;

            return retrofit.create(core.net.api.TestApiService.class).addObj3(
                    paramsGet.name
            );
        }, ADD_OBJ3, com.codingtu.cooltu.lib4a.CoreConfigs.configs().getBaseUrl(), params);
    }
    public static com.codingtu.cooltu.lib4a.net.api.API addObj4(java.lang.String id) {
        core.net.params.AddObj4Params params = new core.net.params.AddObj4Params();
        params.id = id;
        return com.codingtu.cooltu.lib4a.net.NetTool.api((retrofit, ps) -> {
            core.net.params.AddObj4Params paramsGet = (core.net.params.AddObj4Params) ps;
            com.codingtu.cooltu.lib4j.json.base.JO jo = com.codingtu.cooltu.lib4j.json.JsonTool.createJO();
            jo.put("id", paramsGet.id);

            return retrofit.create(core.net.api.TestApiService.class).addObj4(
                    com.codingtu.cooltu.lib4a.net.NetTool.toJsonBody(jo.toJson())
            );
        }, ADD_OBJ4, com.codingtu.cooltu.lib4a.CoreConfigs.configs().getBaseUrl(), params);
    }


}

