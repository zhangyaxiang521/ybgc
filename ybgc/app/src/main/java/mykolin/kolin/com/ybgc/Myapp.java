package mykolin.kolin.com.ybgc;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.LinkedList;
import java.util.List;

import mykolin.kolin.com.ybgc.http.HttpManager;

/**
 * Created by Administrator on 2020/5/29.
 */

public class Myapp extends Application{
    private List<Activity> mList = new LinkedList<Activity>();
    private static Myapp mInstance;
    private HttpManager mHttpManager = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
        mHttpManager = new HttpManager();
    }
    //图片加载方法
    public void initGlide(String url, Context context, ImageView imageView) {
        Glide.with(context)
                .load(url)//加载图片
                .placeholder(R.drawable.icon_stub)//正在加载时的图片
                .error(R.drawable.icon_error)//加载错误是的图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)//设置缓冲
                .into(imageView);
    }





    public static Myapp getInstance(){
        return mInstance;
    }
    // add Activity
    public void addActivity(Activity activity) {
        mList.add(activity);
    }
    public void removeActivity(Activity activity){
        mList.remove(activity);
    }
    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }
}
