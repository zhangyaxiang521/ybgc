package mykolin.kolin.com.ybgc.ui;

import android.content.Intent;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import mykolin.kolin.com.ybgc.R;

public class VideoActivity extends AppCompatActivity {

    @BindView(R.id.sv_view)
    SurfaceView svView;
    @BindView(R.id.btn_VideoStart)
    Button btnVideoStart;
    @BindView(R.id.btn_VideoStop)
    Button btnVideoStop;
    @BindView(R.id.djs_time)
    TextView djsTime;
    private Unbinder unbinder;
    private boolean isRecording;
    private MediaRecorder mediaRecorder;
    private File file;
    private Camera camera;
    private int recLen = 10;//跳过倒计时提示5秒
    Timer timer;  //定义一个计时器
    private String mp4Path;
    private Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        unbinder = ButterKnife.bind(this);
        btnVideoStop.setEnabled(false);
    }

    @OnClick({R.id.btn_VideoStart, R.id.btn_VideoStop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_VideoStart:
                start();
                break;
            case R.id.btn_VideoStop:
                String text = (String) btnVideoStop.getText();
                if(text.equals("停止")){
                    stop();
                }
                if (text.equals("确定")){
                    //android版本为8以上的，静态声明广播注册需要设置包名
                    Intent in = new Intent("CCB");
                    in.setPackage("mykolin.kolin.com.ybgc");
                    in.putExtra("name",mp4Path);
                    sendBroadcast(in);
                    Log.i("发送广播","成功");
                    VideoActivity.this.finish();
                }
                break;
        }
    }


    protected void start() {
        btnVideoStop.equals("停止");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        //获取当前时间并作为时间戳
        String timeStamp = simpleDateFormat.format(new Date());
        try {
            file = new File("/sdcard/" + timeStamp + ".mp4");
            if (file.exists()) {
                // 如果文件存在，删除它，演示代码保证设备上只有一个录音文件
                file.delete();
            }
            mediaRecorder = new MediaRecorder();
            camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
            if (camera != null) {
                camera.setDisplayOrientation(90);//摄像图旋转90度
                camera.unlock();
                mediaRecorder.setCamera(camera);
            }
            mediaRecorder.reset();
            // 设置音频录入源
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            // 设置视频图像的录入源
            mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            //拍摄角度
            mediaRecorder.setOrientationHint(90);
            // 设置录入媒体的输出格式
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            //设置比特率（比特率越高质量越高同样也越大）
//            mediaRecorder.setVideoEncodingBitRate(3 * 1024 * 1024);
            // 设置音频的编码格式
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
            // 设置视频的编码格式
            mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
            // 设置视频的采样率，每秒4帧
//            mediaRecorder.setVideoFrameRate(4);

            // 设置录制视频文件的输出路径
            mediaRecorder.setOutputFile(file.getAbsolutePath());
            // 设置捕获视频图像的预览界面
            mediaRecorder.setPreviewDisplay(svView.getHolder().getSurface());

            mediaRecorder.setOnErrorListener(new MediaRecorder.OnErrorListener() {

                @Override
                public void onError(MediaRecorder mr, int what, int extra) {
                    // 发生错误，停止录制
                    mediaRecorder.stop();
                    mediaRecorder.release();
                    mediaRecorder = null;
                    isRecording = false;
                    btnVideoStart.setEnabled(true);
                    btnVideoStop.setEnabled(false);
                    btnVideoStop.setText("停止");
                    Toast.makeText(VideoActivity.this, "录制出错", Toast.LENGTH_LONG).show();
                }
            });

            // 准备、开始
            mediaRecorder.prepare();
            mediaRecorder.start();

            btnVideoStart.setEnabled(false);
            btnVideoStop.setEnabled(true);
            isRecording = true;
            Toast.makeText(VideoActivity.this, "开始录像", Toast.LENGTH_LONG).show();
            btnVideoStop.setText("停止");
            timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(runnable = new Runnable() {
                        @Override
                        public void run() {
                            recLen--;
                            djsTime.setText(String.valueOf(recLen));
                            if (recLen <= 0) {
                                recLen=10;
                                timer.cancel();
                                stop();
                            }
                        }
                    });
                }
            };
            timer.schedule(task, recLen, 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void stop() {
        if (isRecording) {
            // 如果正在录制，停止并释放资源
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            isRecording = false;
            btnVideoStart.setEnabled(true);
            btnVideoStop.setText("确定");
            Toast.makeText(VideoActivity.this, "停止录像，并保存文件", Toast.LENGTH_LONG).show();
            Log.i("info", file.getAbsolutePath() + "视频路径");
            mp4Path = file.getAbsolutePath();
        }
        if (camera != null) {
            camera.release();
            camera = null;
        }
        timer.cancel();
         recLen = 10;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (timer!=null){
            timer.purge();
        }
        EventBus.getDefault().unregister(this);
    }
}
