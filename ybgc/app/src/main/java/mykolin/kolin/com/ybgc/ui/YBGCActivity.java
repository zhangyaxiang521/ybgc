package mykolin.kolin.com.ybgc.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mykolin.kolin.com.ybgc.R;
import mykolin.kolin.com.ybgc.adapter.YGBCAdapter;
import mykolin.kolin.com.ybgc.bean.BDBean;
import mykolin.kolin.com.ybgc.bean.YBGCBean;
import mykolin.kolin.com.ybgc.config.X_SystemBarUI;
import mykolin.kolin.com.ybgc.service.Apiservice;
import mykolin.kolin.com.ybgc.utils.NetworkConnect;
import mykolin.kolin.com.ybgc.utils.SpUtils;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class YBGCActivity extends BaseActivity {

    @BindView(R.id.zhgl_tianjia)
    ImageView zhglTianjia;
    @BindView(R.id.nice_spinner)
    NiceSpinner niceSpinner;
    @BindView(R.id.ybgc_recycler)
    RecyclerView ybgcRecycler;
    private Unbinder unbinder;
    private List<Integer> list1;
    private String mobileSessionId;
    private YGBCAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        X_SystemBarUI.initSystemBar(this, R.color.colorAccent1);
        setContentView(R.layout.activity_ybgc);
        new NetworkConnect().isNetworkAvailable(this);
        unbinder = ButterKnife.bind(this);
        initHttpBD();
        initView();
    }

        private void initView() {
        niceSpinner.setBackgroundResource(R.drawable.textview_round_border);
        niceSpinner.setTextColor(getResources().getColor(R.color.colorAccent1));
        niceSpinner.setTextSize(13);
        niceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int integer = list1.get(position);
                initHttpContent(integer);
            }
            @Override

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initHttpContent(int integer) {
        final String s = String.valueOf(integer);
        Log.i("info", s + "integer");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Apiservice.TITLE)
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        retrofit.create(Apiservice.class)
                .getYBGC(mobileSessionId, s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<YBGCBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(YBGCBean bdBean) {
                        List<YBGCBean.ReListBean> reList = bdBean.getReList();
                        LinearLayoutManager manager = new LinearLayoutManager(YBGCActivity.this);
                        manager.setOrientation(LinearLayoutManager.VERTICAL);
                        ybgcRecycler.setLayoutManager(manager);
                        adapter = new YGBCAdapter(R.layout.ybgc_content, reList);
                        Log.i("info", reList + "有内容没");
                        ybgcRecycler.setAdapter(adapter);
                        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                YBGCBean.ReListBean o = (YBGCBean.ReListBean) adapter.getData().get(position);
                                String tzke_id = String.valueOf(o.getTZKZ_ID());
                                Log.i("info", tzke_id + "点击监听");
                                Intent intent = new Intent(YBGCActivity.this, MainActivity.class);
                                intent.putExtra("tzke_id", tzke_id);
                                intent.putExtra("organId", s);
                                startActivity(intent);
                            }

                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("info", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i("info", "请求成功YBGCActivitycontent");
                    }
                });
    }

    private void initHttpBD() {
        mobileSessionId = SpUtils.getStringSp(YBGCActivity.this, "mobileSessionId");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Apiservice.TITLE)
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        retrofit.create(Apiservice.class)
                .getBDcontent(mobileSessionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BDBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BDBean bdBean) {
                        List<BDBean.ReListBean> reList = bdBean.getReList();
                        List<String> list = new ArrayList<>();
                        list1 = new ArrayList<>();
                        if (reList.size() > 0) {
                            for (int i = 0; i < reList.size(); i++) {
                                list.add(reList.get(i).getOrganName());
                                list1.add(reList.get(i).getOrganId());
                            }
                        }
                        niceSpinner.attachDataSource(list);
                        initHttpContent(list1.get(0));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("info", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i("info", "请求成功ybgcACITIVITY");
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
