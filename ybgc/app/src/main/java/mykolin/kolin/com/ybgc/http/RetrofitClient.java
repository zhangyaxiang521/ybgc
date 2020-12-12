package mykolin.kolin.com.ybgc.http;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import mykolin.kolin.com.ybgc.service.Apiservice;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Administrator on 2020/6/20.
 */

public class RetrofitClient {
    private static RetrofitClient mInstance;
    private static Retrofit retrofit;


    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Apiservice.TITLE)
                .client(HttpManager.getInstance())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    public static RetrofitClient getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitClient.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitClient();
                }
            }
        }
        return mInstance;
    }

    private <T> T create(Class<T> clz) {
        return retrofit.create(clz);
    }

    Apiservice api() {
        return RetrofitClient.getInstance().create(Apiservice.class);
    }

    /**
     * 单上传文件的封装
//     *  @param url                完整的接口地址
     * @param file               需要上传的文件
     * @param fileUploadObserver 上传回调
     */
    public void upLoadFile(String mobileSessionId1,String recordType1,String tzkzDetailId1,String recordTime1,String remark1,String fileCode1,String paint1,   File file, FileUploadObserver<ResponseBody> fileUploadObserver) {
        ProgressRequestBody uploadFileRequestBody = new ProgressRequestBody(file, fileUploadObserver);
        MultipartBody.Part part = MultipartBody.Part.createFormData("uploadFile", file.getName(), uploadFileRequestBody);
        MultipartBody.Part mobileSessionId =MultipartBody.Part.createFormData("mobileSessionId", mobileSessionId1);
        MultipartBody.Part recordType =MultipartBody.Part.createFormData("recordType", recordType1);
        MultipartBody.Part tzkzDetailId =MultipartBody.Part.createFormData("tzkzDetailId", tzkzDetailId1);
        MultipartBody.Part recordTime =MultipartBody.Part.createFormData("recordTime", recordTime1);
        MultipartBody.Part remark =MultipartBody.Part.createFormData("remark", remark1);
        MultipartBody.Part fileCode =MultipartBody.Part.createFormData("fileCode", fileCode1);
        MultipartBody.Part paint =MultipartBody.Part.createFormData("paint", paint1);
        create(UpLoadFileApi.class)
                .uploadFile(mobileSessionId,recordType,tzkzDetailId,recordTime,remark,fileCode,paint,part)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(fileUploadObserver);
    }
    //上传文件的interface
    interface UpLoadFileApi {
        @Multipart
        @POST("ybgc/gcVideoAddData")
        Observable<ResponseBody> uploadFile(@Part MultipartBody.Part mobileSessionId, @Part MultipartBody.Part recordType,@Part MultipartBody.Part tzkzDetailId,@Part MultipartBody.Part recordTime,@Part MultipartBody.Part remark,@Part MultipartBody.Part fileCode,@Part MultipartBody.Part paint, @Part MultipartBody.Part file);
    }
    public void upLoadFileimage(String mobileSessionId1,String recordType1,String tzkzDetailId1,String recordTime1,String remark1,String fileCode1,String paint1,   File file, FileUploadObserver<ResponseBody> fileUploadObserver) {
        ProgressRequestBody uploadFileRequestBody = new ProgressRequestBody(file, fileUploadObserver);
        MultipartBody.Part part = MultipartBody.Part.createFormData("uploadFile", file.getName(), uploadFileRequestBody);
        MultipartBody.Part mobileSessionId =MultipartBody.Part.createFormData("mobileSessionId", mobileSessionId1);
        MultipartBody.Part recordType =MultipartBody.Part.createFormData("recordType", recordType1);
        MultipartBody.Part tzkzDetailId =MultipartBody.Part.createFormData("tzkzDetailId", tzkzDetailId1);
        MultipartBody.Part recordTime =MultipartBody.Part.createFormData("recordTime", recordTime1);
        MultipartBody.Part remark =MultipartBody.Part.createFormData("remark", remark1);
        MultipartBody.Part fileCode =MultipartBody.Part.createFormData("fileCode", fileCode1);
        MultipartBody.Part paint =MultipartBody.Part.createFormData("paint", paint1);
        create(UpLoadFileImageApi.class)
                .uploadFile(mobileSessionId,recordType,tzkzDetailId,recordTime,remark,fileCode,paint,part)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(fileUploadObserver);
    }
    interface UpLoadFileImageApi {
        @Multipart
        @POST("ybgc/gcImgAddData")
        Observable<ResponseBody> uploadFile(@Part MultipartBody.Part mobileSessionId, @Part MultipartBody.Part recordType,@Part MultipartBody.Part tzkzDetailId,@Part MultipartBody.Part recordTime,@Part MultipartBody.Part remark,@Part MultipartBody.Part fileCode,@Part MultipartBody.Part paint, @Part MultipartBody.Part file);
    }
}
