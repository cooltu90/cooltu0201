package core.net.api;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public interface TestApiService {

    @retrofit2.http.GET("/getObj/{ids}")
    Flowable<Result<ResponseBody>> getObj(
            @retrofit2.http.Header("token") java.lang.String token,
            @retrofit2.http.Path("ids") java.lang.String id,
            @retrofit2.http.Query(value = "order[desc]", encoded = true) java.lang.String order
    );

    @retrofit2.http.POST("/addObj")
    Flowable<Result<ResponseBody>> addObj(
            @retrofit2.http.Body okhttp3.RequestBody body
    );

    @retrofit2.http.POST("/addObj")
    Flowable<Result<ResponseBody>> addObj1(
            @retrofit2.http.Body okhttp3.RequestBody body
    );

    @retrofit2.http.POST("/addObj")
    Flowable<Result<ResponseBody>> addObj2(
            @retrofit2.http.Query("name") java.lang.String name,
            @retrofit2.http.Query("age") int age,
            @retrofit2.http.Query("parent") java.lang.String parent
    );

    @retrofit2.http.GET("/addObj")
    Flowable<Result<ResponseBody>> addObj3(
            @retrofit2.http.Query("name") java.lang.String name
    );

    @retrofit2.http.PUT("/addObj")
    Flowable<Result<ResponseBody>> addObj4(
            @retrofit2.http.Body okhttp3.RequestBody body
    );


}
