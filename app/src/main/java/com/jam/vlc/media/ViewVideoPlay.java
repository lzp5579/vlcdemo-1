package com.jam.vlc.media;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.jam.vlc.R;

import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/20.
 */

public class ViewVideoPlay extends FrameLayout implements SurfaceHolder.Callback, View.OnClickListener {
    private SurfaceView mSurface;
    private SurfaceHolder mSurfaceHolder;
    private ImageButton rate, play;
    private ImageView zoomImg;
    private SeekBar sb_video;


    private Media mCurrentMedia = null;
    private LibVLC mLibVLC;
    private MediaPlayer mMediaPlayer;

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


        mLibVLC = new LibVLC(); //FIXME, this is wrong
        mMediaPlayer = new MediaPlayer(mLibVLC);


        mMediaPlayer.setEventListener(new MediaPlayer.EventListener() {
            @Override
            public void onEvent(MediaPlayer.Event event) {
                switch (event.type) {
                    case MediaPlayer.Event.Opening:
                        Log.e("jam", "Opening------------------");
                        break;
                    case MediaPlayer.Event.Playing:
                        Log.e("jam", "Playing------------------");
                        break;
                    case MediaPlayer.Event.Paused:
                        Log.e("jam", "Paused------------------");
                        break;
                    case MediaPlayer.Event.Stopped:
                        Log.e("jam", "Stopped------------------");
                        break;
                    case MediaPlayer.Event.TimeChanged:
                        Log.e("jam", "TimeChanged------------------");
                        break;
                    case MediaPlayer.Event.SeekableChanged:
                        Log.e("jam", "SeekableChanged------------------");
                        break;
                    case MediaPlayer.Event.EncounteredError:
                        Log.e("jam", "EncounteredError------------------");
                        break;
                    case MediaPlayer.Event.PausableChanged:
                        Log.e("jam", "PausableChanged------------------");
                        break;
                    case MediaPlayer.Event.EndReached:
                        Log.e("jam", "EndReached------------------");
                        break;
                }
            }
        });

        mSurfaceHolder = mSurface.getHolder();
        mSurfaceHolder.addCallback(this);
        mSurfaceHolder.setKeepScreenOn(true);

    }

    public void playByPath(String path) {
        try {
            setDataSource(getContext(), Uri.parse(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        prepareAsync();
    }

    public void play() {
        mMediaPlayer.play();
    }

    public void setRate(float f) {
        mMediaPlayer.setRate(f);
    }

    public void stop() {
        mMediaPlayer.stop();
    }

    public void pause() {
        mMediaPlayer.pause();
    }

    public void release() {
        mMediaPlayer.release();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        setSurfaceView(mSurface);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        detachViews();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rate:
                setRate(1.5f);
                break;
            case R.id.play:
                if (mMediaPlayer.isPlaying()) {
                    pause();
                } else {
                    play();
                }
                break;
            case R.id.zoomImg:
                break;
        }
    }

    public void setDataSource(Context context, Uri uri)
            throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {
        setDataSource(context, uri, null);
    }

    // FIXME, this is INCORRECT, @headers are ignored
    public void setDataSource(Context context, Uri uri, Map<String, String> headers)
            throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {
        mCurrentMedia = new Media(mLibVLC, uri);
        mMediaPlayer.setMedia(mCurrentMedia);
    }

    public void setDataSource(String path)
            throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {
        mCurrentMedia = new Media(mLibVLC, path);
        mMediaPlayer.setMedia(mCurrentMedia);
    }

    public void setDataSource(FileDescriptor fd)
            throws IOException, IllegalArgumentException, IllegalStateException {
        mCurrentMedia = new Media(mLibVLC, fd);
        mMediaPlayer.setMedia(mCurrentMedia);
    }

    // FIXME, this is INCORRECT, @offset and @length are ignored
    public void setDataSource(FileDescriptor fd, long offset, long length)
            throws IOException, IllegalArgumentException, IllegalStateException {
        setDataSource(fd);
    }

    public void prepareAsync() {
        mCurrentMedia.addOption(":video-paused");
        mMediaPlayer.play();
    }

    public void setSurfaceView(SurfaceView surface) {
        mMediaPlayer.getVLCVout().setVideoView(surface);
        mMediaPlayer.getVLCVout().attachViews();
    }

    public void detachViews() {
        mMediaPlayer.getVLCVout().detachViews();
    }

    public void setVideoScalingMode(int mode) {
    }


    public void setWakeMode(Context context, int mode) {
    }

    public void setScreenOnWhilePlaying(boolean screenOn) {
    }

    public int getVideoWidth() {
        return -1;
    }

    public int getVideoHeight() {
        return -1;
    }

    public boolean isPlaying() {
        return mMediaPlayer.isPlaying();
    }

    public void seekTo(int msec) throws IllegalStateException {
    }

    // This is of course, less precise than VLC
    public int getCurrentPosition() {
        return (int) mMediaPlayer.getTime();
    }

    // This is of course, less precise than VLC
    public int getDuration() {
        return (int) mMediaPlayer.getLength();
    }


    public void reset() {
    }

    public boolean isLooping() {
        return false;
    }

    public void setVolume(float leftVolume, float rightVolume) {
        mMediaPlayer.setVolume((int) ((leftVolume + rightVolume) * 100 / 2));
    }

}
