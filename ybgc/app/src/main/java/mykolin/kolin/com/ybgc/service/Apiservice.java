package mykolin.kolin.com.ybgc.service;


import io.reactivex.Observable;
import mykolin.kolin.com.ybgc.bean.BDBean;
import mykolin.kolin.com.ybgc.bean.YBGCBean;
import mykolin.kolin.com.ybgc.bean.YBGCXQBean;
import mykolin.kolin.com.ybgc.bean.ZHGLBean;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2020/5/31.
 */

public interface Apiservice {
    //高杰内网    http://192.168.1.249:8080/yhgsxm
    //炎黄   http://117.34.104.21:10015/yhgsxm
    //海龙内网    http://192.168.1.239:8080/yhgsxm2020
    //谷哥内网    http://192.168.43.110:8080/hwgsxm
    //谷哥内网 1   http://192.168.1.188:8080/hwgsxm
    public  String TITLE = "http://192.168.1.249:8080/yhgsxm/api/v1.0/";
    public  String LOGINCONTENT =TITLE + "login";
    /**
     * 获取知识体系数据
     * @param requestBody
     */

    @Headers({"Content-Type:application/json;","Accept:application/json"})
    @POST("login")
    Observable<ZHGLBean> postLogin(@Body RequestBody requestBody);

    @GET("sys/getCurrentStaffOrgans?")
    Observable<BDBean> getBDcontent(@Query("mobileSessionId") String mobileSessionId);


    @GET("ybgc/treeEndNodesQuery?")
    Observable<YBGCBean> getYBGC(@Query("mobileSessionId") String mobileSessionId,@Query("organId") String organId);

    @GET("ybgc/tzkzDetailListQuery?")
    Observable<YBGCXQBean> getYBGCXQ(@Query("mobileSessionId") String mobileSessionId, @Query("organId") String organId,@Query("tzkzId") String tzkzId,@Query("gcmc") String gcmc);

    //图片上传
    @POST("ybgc/gcImgAddData")
    Observable<Object> postupImage(@Body RequestBody requestBody);
    //视频上传
    @Multipart
    @POST("ybgc/gcVideoAddData")
    Observable<ResponseBody> uploadFile(@Url String url, @Part MultipartBody.Part file);


}
