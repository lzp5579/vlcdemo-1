package com.jam.vlc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


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
        findViewById(R.id.video1).setOnClickListener(this);
        findViewById(R.id.video2).setOnClickListener(this);
        findViewById(R.id.video3).setOnClickListener(this);
        // 获得屏幕宽度
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int sHeight = dm.heightPixels;// 屏幕高度

//        mVideoView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, sHeight / 3));
//        mVideoView.playByPath("http://wshlive.zhihuishu.com/livepkgr/rb_104105_1_500/index.m3u8");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goNext:
//                Intent intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://appstudent.zhihuishu.com/")
                        .build();


                StudyApi repo = retrofit.create(StudyApi.class);

                //在这里无论是同步操作还是异步操作每一个call对象实例只能被执行一次。多次执行抛出异常
                Call<ResponseBody> call = repo.getCourseInfo("159526673");
                //在这里如果我们的request和respone都是一一对应的。我们通过Clone方法创建一个一模一样的实例，并且它的开销也是很小的。
                // Call<ResponseBody> cloneCall = call.clone();
                //cloneCall.execute();


                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        Log.e("jam", "111111111111111111111111");
                        try {
                            Gson gson = new Gson();
                            CourseResponse json = gson.fromJson(response.body().string(), new TypeToken<CourseResponse>() {
                            }.getType());
                            if (json != null && json.rt != null) {
                                for (CourseEntity courseEntity : json.rt) {
                                    Log.e("jam", courseEntity.courseName);
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("jam", "2222222222222222222222222222");
                    }
                });

                break;
            case R.id.video1:
                mVideoView.playByPath("rtmp://live.zhihuishu.com/livepkgr/rb_000000_1_500");
                break;
            case R.id.video2:
                mVideoView.playByPath("http://video.zhihuishu.com/zhs_yufa_150820/createcourse/COURSE/201611/c241aa0e25f44cf08c2e5ed96a53b598_500.mp4");
                break;
            case R.id.video3:
                mVideoView.playByPath("http://video.zhihuishu.com/testzhs/createcourse/COURSE/201603/ba028bc5a184465390ca57a58e62260a.mp4");
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
//        mVideoView.stop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mVideoView.release();
        super.onDestroy();
    }


    /**
     * 课程列表
     */
    public class CourseResponse {
        public long currentTime;
        public String status;
        public String msg;
        public List<CourseEntity> rt;
    }

}
