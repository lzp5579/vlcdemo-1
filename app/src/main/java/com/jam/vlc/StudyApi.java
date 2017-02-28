package com.jam.vlc;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/2/28.
 */

public interface StudyApi {
    @POST("appstudent/student/tutorial/getStudyingCourses")
    Call<ResponseBody> getCourseInfo(@Query("userId") String userId);
}
