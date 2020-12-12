package mykolin.kolin.com.ybgc.ui;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yuyh.library.imgsel.ImageLoader;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mykolin.kolin.com.ybgc.R;
import mykolin.kolin.com.ybgc.adapter.YBGCXQAdapter;
import mykolin.kolin.com.ybgc.bean.YBGCXQBean;
import mykolin.kolin.com.ybgc.config.X_SystemBarUI;
import mykolin.kolin.com.ybgc.http.FileUploadObserver;
import mykolin.kolin.com.ybgc.http.RetrofitClient;
import mykolin.kolin.com.ybgc.service.Apiservice;
import mykolin.kolin.com.ybgc.utils.SpUtils;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity{


    @BindView(R.id.zhgl_tianjia)
    ImageView zhglTianjia;
    @BindView(R.id.searchview)
    SearchView searchview;
    @BindView(R.id.duanmian_recyclerView)
    RecyclerView duanmianRecyclerView;
    @BindView(R.id.duanmian_refresh)
    SmartRefreshLayout duanmianRefresh;
    private Unbinder unbinder;
    private YBGCXQAdapter adapter;
    private String tzke_id, organId, mobileSessionId;
    private List<YBGCXQBean.ReListBean> reList = new ArrayList<>();
    private int page = 1;
    private String gcmc = "";
    private String tzkzDetailId;
    private ProgressDialog dialog = null;
    private BroadcastReceiver mb;
    //需要的权限数组 读/写/相机
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    private ProgressDialog dialog1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        X_SystemBarUI.initSystemBar(this, R.color.colorAccent1);
        setContentView(R.layout.activity_main);
        requestPermissions();
        unbinder = ButterKnife.bind(this);
        Intent intent = getIntent();
        tzke_id = intent.getStringExtra("tzke_id");
        organId = intent.getStringExtra("organId");
        ininView();
        initHttpContent();
        mb =new  BroadcastReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.i("接收广播","成功");
                ShiPingPath = intent.getStringExtra("name");
                zhglTianjia.setImageDrawable(getResources().getDrawable(R.mipmap.sc));
                zhglTianjia.setEnabled(true);
                base64Imgage = null;
            }
        };
        IntentFilter mif = new IntentFilter("CCB");
        registerReceiver(mb, mif);
    }

    /**
     * 【申请手机相机和内存读取权限】
     **/
    public void requestPermissions() {
        String[] permissions = new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
        };
        ActivityCompat.requestPermissions(MainActivity.this, permissions, 100);
    }

    private void initHttpContent() {

        mobileSessionId = SpUtils.getStringSp(MainActivity.this, "mobileSessionId");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Apiservice.TITLE)
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        retrofit.create(Apiservice.class)
                .getYBGCXQ(mobileSessionId, organId, tzke_id, gcmc)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<YBGCXQBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(YBGCXQBean ybgcxqBean) {
                        reList = ybgcxqBean.getReList();
                        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
                        manager.setOrientation(LinearLayoutManager.VERTICAL);
                        duanmianRecyclerView.setLayoutManager(manager);
                        adapter = new YBGCXQAdapter(R.layout.ybgcxq_content, reList);
                        Log.i("info", reList + "有内容没mainacitity");
                        duanmianRecyclerView.setAdapter(adapter);
                        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                YBGCXQBean.ReListBean reListBean = (YBGCXQBean.ReListBean) adapter.getData().get(position);
                                int id = reListBean.getTzkzDetailId();
                                tzkzDetailId = String.valueOf(id);
                                showBottomDialog();
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("info", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i("info", "请求成功MainAvititycontent");
                    }
                });

    }

    private static final String FILE_PATH = "/sdcard/sysvideocamera.mp4";
    private static final String TAG = "main";

    private void showBottomDialog() {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(this, R.layout.choose_content, null);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
        //拍照
        dialog.findViewById(R.id.tv_take_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (verifyPermissions(MainActivity.this, PERMISSIONS_STORAGE[2]) == 0) {
                    ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS_STORAGE, 3);
                } else {
                    toCamera();  //打开相机
                }
                dialog.dismiss();
            }
        });
        //录像
        dialog.findViewById(R.id.tv_take_luxiang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,VideoActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }


    //跳转相机
    private Uri mUri;

    private void toCamera() {


        ImageLoader loader = new ImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        };
        ImgSelConfig config = new ImgSelConfig.Builder(this, loader)
                .multiSelect(true)
                .rememberSelected(false)
                //.btnBgColor(Color.GRAY)
                .btnTextColor(Color.WHITE)
                .statusBarColor(this.getResources().getColor(R.color.indigo))
                .backResId(R.drawable.ic_back)
                .title("图片")
                .titleColor(Color.WHITE)
                .titleBgColor(Color.parseColor("#3F51B5"))
                .cropSize(1, 1, 200, 200)
                .needCrop(false)
                .needCamera(true)
                .maxNum(1)
                .build();
        ImgSelActivity.startActivity(this, config, 102);
    }

    //图片转base64
    public static String imageToBase64(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        InputStream is = null;
        byte[] data = null;
        String result = null;
        try {
            is = new FileInputStream(path);
            //创建一个字符流大小的数组。
            data = new byte[is.available()];
            //写入数组
            is.read(data);
            //用默认的编码格式进行编码
            result = Base64.encodeToString(data, Base64.NO_CLOSE);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return result;
    }

    private void ininView() {
        searchview.setIconifiedByDefault(true);
        searchview.setSubmitButtonEnabled(true);
        searchview.setQueryHint("标段查询");
        ImageView search_button = (ImageView) searchview.findViewById(android.support.v7.appcompat.R.id.search_button);
        ImageView search_button1 = (ImageView) searchview.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        ImageView search_button2 = (ImageView) searchview.findViewById(android.support.v7.appcompat.R.id.search_go_btn);
        search_button1.setColorFilter(getResources().getColor(R.color.royalblue));
        search_button.setColorFilter(getResources().getColor(R.color.royalblue));
        search_button2.setColorFilter(getResources().getColor(R.color.royalblue));
        SearchView.SearchAutoComplete textView = (SearchView.SearchAutoComplete) searchview.findViewById(R.id.search_src_text);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setHintTextColor(getResources().getColor(R.color.transparent2));
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                gcmc = query;
                reList.clear();
                initHttpContent();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i("info", newText + "搜索框内容");
                gcmc = newText;
                reList.clear();
                initHttpContent();
                return false;
            }
        });
        duanmianRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                reList.clear();
                page = 1;
                initHttpContent();
                adapter.notifyDataSetChanged();
                duanmianRefresh.finishRefresh(true);
            }
        });
    }

    private String base64Imgage = null;
    //视频地址
    private  String ShiPingPath;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("info",String.valueOf(resultCode)+"resultCode");
        if (resultCode == RESULT_OK && data != null) {
            Bitmap bm = null;
            switch (requestCode) {
                case 102: //相机返回的数据（相机的返回码）
                    Log.i("info", data.toString());
                    List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
                    for (String path : pathList) {
                        Log.i("info", path);
                        base64Imgage = imageToBase64(path);
                    }
                    zhglTianjia.setImageDrawable(getResources().getDrawable(R.mipmap.sc));
                    zhglTianjia.setEnabled(true);
                    ShiPingPath = null;
                    break;
//                case 0:
//                    Log.i(TAG, "拍摄完成，resultCode=" + data.getDataString());
//                    ShiPingPath = null;
//                    ShiPingPath = null;
//                    zhglTianjia.setImageDrawable(getResources().getDrawable(R.mipmap.sc1));
//                    zhglTianjia.setEnabled(false);
//                    break;
//                    //返回的视频地址
            }
        }
    }
    private void initHttpUpimage() {
        if (base64Imgage == null || base64Imgage == "") {
            return;
        }
        CharSequence titile = "请稍等片刻...";
        CharSequence str = "正在上传中...";
        dialog = ProgressDialog.show(MainActivity.this,titile,str);
        Date t = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String paint = "$('.sample2').img2blob({watermark: \" + tzkzDetailGcbh + '~' + tzkzDetailGcmc + \",fontStyle: 'Microsoft YaHei,Arial',fontSize: '30', fontColor: 'red', fontX: 20, fontY: 40 });";
        HashMap<String, String> map = new HashMap();
        map.put("mobileSessionId", mobileSessionId);
        map.put("recordType", "0");
        map.put("tzkzDetailId", tzkzDetailId);
        map.put("recordTime", df.format(t));
        map.put("remark", "");
        map.put("fileCode", base64Imgage);
        map.put("paint", paint);
        JSONObject jsonObject = new JSONObject(map);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Apiservice.TITLE)
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        retrofit.create(Apiservice.class)
                .postupImage(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object object) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("info", e.getMessage());
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {
                        Log.i("info", "上传成功");
                        base64Imgage=null;
                        zhglTianjia.setImageDrawable(getResources().getDrawable(R.mipmap.sc1));
                        zhglTianjia.setEnabled(false);
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        unregisterReceiver(mb);
    }
    @OnClick(R.id.zhgl_tianjia)
    public void onViewClicked() {
        if (base64Imgage!=null||ShiPingPath!=null){
            zhglTianjia.setEnabled(true);
            zhglTianjia.setImageDrawable(getResources().getDrawable(R.mipmap.sc));
            if (base64Imgage!=null&&ShiPingPath==null){
                initHttpUpimage();
            }
            if (base64Imgage==null&&ShiPingPath!=null){
                upload2();
            }
        }else {
            zhglTianjia.setImageDrawable(getResources().getDrawable(R.mipmap.sc1));
            zhglTianjia.setEnabled(false);
        }

    }
    public void upload2() {
        dialog1 = new ProgressDialog(MainActivity.this);
        dialog1.setMax(100);
        dialog1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog1.setMessage("上传文件中");
        File file = new File(ShiPingPath);
        dialog1.show();
        Date t = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        RetrofitClient
                .getInstance()
                .upLoadFile(mobileSessionId,"1",tzkzDetailId,df.format(t).toString(),"","","",file , new FileUploadObserver<ResponseBody>() {
                    @Override
                    public void onUpLoadSuccess(ResponseBody responseBody) {
                        Toast.makeText(MainActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                        ShiPingPath = null;
                        zhglTianjia.setImageDrawable(getResources().getDrawable(R.mipmap.sc1));
                        zhglTianjia.setEnabled(false);
                        try {
                            Log.d("上传进度",responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        dialog1.dismiss();
                    }

                    @Override
                    public void onUpLoadFail(Throwable e) {
                        Toast.makeText(MainActivity.this, "上传失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
                        dialog1.dismiss();
                    }
                    @Override
                    public void onProgress(int progress) {
                        dialog1.setProgress(progress);
                    }
                });
    }


}
