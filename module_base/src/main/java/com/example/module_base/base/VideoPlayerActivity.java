package com.example.module_base.base;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_base.R;
import com.example.module_base.util.GlideUtil;

import org.yczbj.ycvideoplayerlib.controller.VideoPlayerController;
import org.yczbj.ycvideoplayerlib.manager.VideoPlayerManager;
import org.yczbj.ycvideoplayerlib.player.VideoPlayer;


public class VideoPlayerActivity extends BaseActivity {


    VideoPlayer videoPlayer;
    TextView tvTitle;
    TextView tvInformation;

    @Override
    public int initActivityLayout() {
        return R.layout.activity_video_player;
    }

    private static final String TAG = "VideoPlayerActivity";

    @Override
    public void initView() {
        videoPlayer = findViewById(R.id.video_player);
        tvTitle = findViewById(R.id.tv_title);
        tvInformation = findViewById(R.id.tv_information);
        String videoUrl = getIntent().getStringExtra("url");
        Log.i(TAG, "initView: " + videoUrl);
        videoPlayer.setUp(videoUrl, null);
        tvTitle.setText(getIntent().getStringExtra("title"));
        tvInformation.setText(getIntent().getStringExtra("kind")+" | "+getIntent().getStringExtra("time").substring(0,10));
        VideoPlayerController controller = new VideoPlayerController(this);
        controller.setTitle(getIntent().getStringExtra("title"));
        controller.setLength(getIntent().getStringExtra("duration"));
        ImageView imageView = controller.imageView();
        GlideUtil.BaseGlide(getIntent().getStringExtra("picture"), imageView);
        videoPlayer.setController(controller);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onStop() {
        super.onStop();
        //从前台切到后台，当视频正在播放或者正在缓冲时，调用该方法暂停视频
        VideoPlayerManager.instance().suspendVideoPlayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁页面，释放，内部的播放器被释放掉，同时如果在全屏、小窗口模式下都会退出
        VideoPlayerManager.instance().releaseVideoPlayer();
    }

    @Override
    public void onBackPressed() {
        //处理返回键逻辑；如果是全屏，则退出全屏；如果是小窗口，则退出小窗口
        if (VideoPlayerManager.instance().onBackPressed()) {
            return;
        } else {
            //销毁页面
            VideoPlayerManager.instance().releaseVideoPlayer();
        }
        super.onBackPressed();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //从后台切换到前台，当视频暂停时或者缓冲暂停时，调用该方法重新开启视频播放
        VideoPlayerManager.instance().resumeVideoPlayer();
    }
}
