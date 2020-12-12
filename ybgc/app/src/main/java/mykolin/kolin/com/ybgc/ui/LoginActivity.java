package mykolin.kolin.com.ybgc.ui;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mykolin.kolin.com.ybgc.R;
import mykolin.kolin.com.ybgc.bean.ZHGLBean;
import mykolin.kolin.com.ybgc.config.X_SystemBarUI;
import mykolin.kolin.com.ybgc.service.Apiservice;
import mykolin.kolin.com.ybgc.utils.NetworkConnect;
import mykolin.kolin.com.ybgc.utils.SpUtils;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edittext_name)
    EditText edittextName;
    @BindView(R.id.edittext_pass)
    EditText edittextPass;
    @BindView(R.id.switch1)
    SwitchCompat switch1;
    @BindView(R.id.sure_id)
    Button sureId;
    @BindView(R.id.login_tishi)
    TextView loginTishi;
    @BindView(R.id.main_login)
    RelativeLayout mainLogin;
    private int btnY = 0;
    private String name, pass;
    private boolean progressShow;
    private ProgressDialog pd;
    public static final String TAG = "LoginActivity";
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        X_SystemBarUI.initSystemBar(this, R.color.transparent);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        controlKeyboardLayout(mainLogin, sureId);
        InputStream is ;
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.ARGB_8888;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        opt.inSampleSize = 2;
        is= getResources().openRawResource(R.drawable.bg_login);
        Bitmap bm = BitmapFactory.decodeStream(is, null, opt);
        BitmapDrawable bd = new BitmapDrawable(getResources(), bm);
        mainLogin.setBackgroundDrawable(bd);
        initView();
        initEvent();
    }

    private void initView() {
        String name =  SpUtils.getStringSp(LoginActivity.this,"yonghuname");
        String pass =  SpUtils.getStringSp(LoginActivity.this,"mima");
        Log.i("info",name+"777777");
        if (!TextUtils.isEmpty(name)){
            edittextName.setText(name);
        }
        if (!TextUtils.isEmpty(pass)){
            edittextPass.setText(pass);
        }
    }

    private void initEvent() {
       switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if (isChecked) {
                   SpUtils.putStringSp(LoginActivity.this, "yonghuname", edittextName.getText().toString());
                   SpUtils.putStringSp(LoginActivity.this, "mima", edittextPass.getText().toString());
               } else {
                   SpUtils.removeSpKey(LoginActivity.this, "yonghuname");
                   SpUtils.removeSpKey(LoginActivity.this, "mima");
               }
           }
       });
    }

    @OnClick(R.id.sure_id)
    public void onViewClicked() {
        new NetworkConnect().isNetworkAvailable(this);
        progressShow = true;
        pd = new ProgressDialog(this);
        pd.setCanceledOnTouchOutside(false);
        pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                progressShow = false;
            }
        });
        pd.setMessage(getString(R.string.login));
        pd.show();
        initData();
    }

    private void initData(){
        name = edittextName.getText().toString();
        pass = edittextPass.getText().toString();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "用户名为空", Toast.LENGTH_LONG).show();
            pd.dismiss();
        } else if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "密码为空", Toast.LENGTH_LONG).show();
            pd.dismiss();
        }else  {
            String path = Apiservice.LOGINCONTENT;
            Log.i("info",path);
            HashMap<String, String> map = new HashMap();
            map.put("staffAcct", name);
            map.put("staffPwd", pass);
            map.put("mobileKey", "");
            JSONObject jsonObject = new JSONObject(map);
            initHttp(jsonObject);
        }
    }

    private void initHttp(JSONObject map) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Apiservice.TITLE)
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
                 RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),map.toString());
                 retrofit.create(Apiservice.class)
                .postLogin(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZHGLBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ZHGLBean zhglBean) {
                        SpUtils.putStringSp(LoginActivity.this, "mobileSessionId", zhglBean.getMobileSessionId());
                        SpUtils.putStringSp(LoginActivity.this, "zhiwei", zhglBean.getStaff().getStaffName());
                        SpUtils.putStringSp(LoginActivity.this, "name", zhglBean.getStaff().getStaffDuty());
                        SpUtils.putStringSp(LoginActivity.this, "yonghuname", zhglBean.getStaff().getStaffAcct());
                        SpUtils.putStringSp(LoginActivity.this, "mima", zhglBean.getStaff().getStaffPwd());
                        SpUtils.putStringSp(LoginActivity.this, "dianhua", zhglBean.getStaff().getStaffTel());
                        SpUtils.putStringSp(LoginActivity.this, "staffId", String.valueOf(zhglBean.getStaff().getStaffId()));
                        SpUtils.putStringSp(LoginActivity.this, "roleId", String.valueOf(zhglBean.getStaff().getRoleId()));
                        Intent intent = new Intent(LoginActivity.this, YBGCActivity.class);
                        startActivity(intent);
                        LoginActivity.this.finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        pd.dismiss();
                        Log.i("info",e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        pd.dismiss();
                        Log.i("info","请求成功");
                    }
                });

    }

    /**
     * @param root         最外层布局，需要调整的布局
     * @param scrollToView 被键盘遮挡的scrollToView，滚动root,使scrollToView在root可视区域的底部
     */
    private void controlKeyboardLayout(final View root, final View scrollToView) {
        // 注册一个回调函数，当在一个视图树中全局布局发生改变或者视图树中的某个视图的可视状态发生改变时调用这个回调函数。
        root.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Rect rect = new Rect();
                        // 获取root在窗体的可视区域
                        root.getWindowVisibleDisplayFrame(rect);
                        // 当前视图最外层的高度减去现在所看到的视图的最底部的y坐标
                        int rootInvisibleHeight = root.getRootView()
                                .getHeight() - rect.bottom;
                        Log.i("tag", "最外层的高度" + root.getRootView().getHeight());
                        Log.i("tag", "bottom的高度" + rect.bottom);
                        // 若rootInvisibleHeight高度大于100，则说明当前视图上移了，说明软键盘弹出了
                        if (rootInvisibleHeight > 100) {
                            //软键盘弹出来的时候
                            int[] location = new int[2];
                            // 获取scrollToView在窗体的坐标
                            scrollToView.getLocationInWindow(location);

                            //btnY的初始值为0，一旦赋过一次值就不再变化
                            if (btnY == 0) {
                                btnY = location[1];
                            }

                            // 计算root滚动高度，使scrollToView在可见区域的底部
                            int srollHeight = (btnY + scrollToView
                                    .getHeight()) - rect.bottom;

                            root.scrollTo(0, srollHeight);
                        } else {
                            // 软键盘没有弹出来的时候
                            root.scrollTo(0, 0);
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
    }
}
