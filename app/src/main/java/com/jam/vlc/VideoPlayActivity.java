package com.jam.vlc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.jam.vlc.media.ViewVideoPlay;


/**
 * Created by Administrator on 2017/2/20.
 */

public class VideoPlayActivity extends Activity implements View.OnClickListener {
    private ViewVideoPlay mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        initView();
    }

    private void initView() {
        mVideoView = (ViewVideoPlay) findViewById(R.id.videoView);
        findViewById(R.id.goNext).setOnClickListener(this);
        // 获得屏幕宽度
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int sHeight = dm.heightPixels;// 屏幕高度

        mVideoView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, sHeight / 3));
        mVideoView.playByPath("http://video.zhihuishu.com/testzhs/createcourse/COURSE/201512/a484caed0c72425d8535089f7566b030_500.mp4");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goNext:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    protected void onPause() {
        mVideoView.pause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        mVideoView.stop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mVideoView.release();
        super.onDestroy();
    }
}
