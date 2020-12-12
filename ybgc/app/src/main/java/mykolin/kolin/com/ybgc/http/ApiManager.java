package mykolin.kolin.com.ybgc.http;

import mykolin.kolin.com.ybgc.service.Apiservice;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2020/6/4.
 */

public class ApiManager {
    private Apiservice mDailyApi;
    private static ApiManager sApiManager;
    //获取ApiManager的单例
    public static ApiManager getInstence() {
        if (sApiManager == null) {
            synchronized (ApiManager.class) {
                if (sApiManager == null) {
                    sApiManager = new ApiManager();
                }
            }
        }
        return sApiManager;
    }
    /**
     * 封装配置知乎API
     */
    public Apiservice getDailyService() {
        //不需要使用拦截器就不创建直接从if开始
        OkHttpClient client = new OkHttpClient.Builder()
//                //添加应用拦截器
//                .addInterceptor(new MyOkhttpInterceptor())
//                //添加网络拦截器
//                .addNetworkInterceptor(new MyOkhttpInterceptor())
                .build();
        if (mDailyApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Apiservice.TITLE)
                    //将client与retrofit关联
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            //到这一步创建完成
            mDailyApi = retrofit.create(Apiservice.class);
        }
        return mDailyApi;
    }
}
