package com.jam.vlc.media;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.jam.vlc.R;

/**
 * Created by Administrator on 2017/2/20.
 */

public class ViewVideoPlay extends FrameLayout implements SurfaceHolder.Callback, View.OnClickListener {
    private SurfaceView mSurface;
    private SurfaceHolder mSurfaceHolder;
    private MediaPlayer mediaPlayer;
    private ImageButton rate, play;
    private ImageView zoomImg;
    private SeekBar sb_video;

    public ViewVideoPlay(Context context) {
        super(context);
        init();
    }

    public ViewVideoPlay(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ViewVideoPlay(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_video_play, this);

        //video view
        mSurface = (SurfaceView) findViewById(R.id.surfaceView);
        rate = (ImageButton) findViewById(R.id.rate);
        play = (ImageButton) findViewById(R.id.play);
        zoomImg = (ImageView) findViewById(R.id.zoomImg);
        rate.setOnClickListener(this);
        play.setOnClickListener(this);
        zoomImg.setOnClickListener(this);

        mediaPlayer = new MediaPlayer();

        mSurfaceHolder = mSurface.getHolder();
        mSurfaceHolder.addCallback(this);
        mSurfaceHolder.setKeepScreenOn(true);

    }

    public void playByPath(String path) {
        try {
            mediaPlayer.setDataSource(getContext(), Uri.parse(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mediaPlayer.prepareAsync();
    }

    public void play() {
        mediaPlayer.start();
    }

    public void setRate(float f) {
        mediaPlayer.setRate(f);
    }

    public void stop() {
        mediaPlayer.stop();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public void release() {
        mediaPlayer.release();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (mediaPlayer != null) {
            mediaPlayer.setSurfaceView(mSurface);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mediaPlayer != null) {
            mediaPlayer.detachViews();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rate:
                setRate(1.5f);
                break;
            case R.id.play:
                if (mediaPlayer.isPlaying()) {
                    pause();
                } else {
                    play();
                }
                break;
            case R.id.zoomImg:
                break;
        }
    }
}
