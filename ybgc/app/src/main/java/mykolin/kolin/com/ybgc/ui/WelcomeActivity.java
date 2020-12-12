package mykolin.kolin.com.ybgc.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import mykolin.kolin.com.ybgc.R;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener{
    private int recLen = 5;//跳过倒计时提示5秒
     private TextView tv;
     Timer timer = new Timer();  //定义一个计时器
     private Handler handler;
     private Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        initView();
        timer.schedule(task, 1000, 1000);//等待时间一秒，停顿时间一秒
        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
             @Override
             public void run() {
                                //从闪屏界面跳转到首界面
                               Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                               startActivity(intent);
                                finish();
                           }
         }, 5000);//延迟5S后发送handler信息
    }

    private void initView() {
       tv = findViewById(R.id.welcome_tv);
       tv.setOnClickListener(this);
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    recLen--;
                    tv.setText("跳过"+recLen);
                    if (recLen<0){
                        timer.cancel();
                        tv.setVisibility(View.GONE);
                    }
                }
            });
        }
    };

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.welcome_tv:
                Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                if (runnable!=null){
                    handler.removeCallbacks(runnable);
                }
                 break;
         }
    }
}
