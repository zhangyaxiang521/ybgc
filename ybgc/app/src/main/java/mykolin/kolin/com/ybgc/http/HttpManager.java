package mykolin.kolin.com.ybgc.http;


import java.util.concurrent.TimeUnit;

import mykolin.kolin.com.ybgc.BuildConfig;
import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2020/5/31.
 */

public class HttpManager {
    private static OkHttpClient okHttpClient;

    public static OkHttpClient getInstance() {
        if (okHttpClient == null) {
            synchronized (HttpManager.class) {
                if (okHttpClient == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    if (BuildConfig.DEBUG) {
                        //拦截okHttp的日志，如果开启了会导致上传回调被调用两次
//                        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//                        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//                        builder.addInterceptor(interceptor);
                    }
                    //超时时间
                    builder.connectTimeout(15, TimeUnit.SECONDS);//15S连接超时
                    builder.readTimeout(20, TimeUnit.SECONDS);//20s读取超时
                    builder.writeTimeout(20, TimeUnit.SECONDS);//20s写入超时
                    //错误重连
                    builder.retryOnConnectionFailure(true);
                    okHttpClient = builder.build();
                }
            }
        }
        return okHttpClient;
    }
}
