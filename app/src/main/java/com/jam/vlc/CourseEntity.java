package com.jam.vlc;

import java.io.Serializable;

/**
 * @author lcx
 * @describe 新版课程实体类
 */
public class CourseEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    public double totalScore;
    /**
     * 课开始时间 2013-10-11 11:51:12
     */
    public String startTime;
    /**
     * 学校ID
     */
    public int schoolId;
    /**
     * 学校名字
     */
    public String schoolName;
    /**
     * 班级ID
     */
    public int classId;
    /**
     * 班级名字
     */
    public String className;

    /**
     * 课程ID
     */
    public String courseId;
    /**
     * 课程结束时间
     */
    public String endTime;
    /**
     * 课程图片
     */
    public String courseImg;
    /**
     * 招生ID
     */
    public String recruitId;
    /**
     * 课程名字
     */
    public String courseName;
    /**
     * 成绩发放规则---1 智慧树平台发放 2 学校自行发放 3 平台发放自定义成绩规则
     */
    public int scoreRole;
    /**
     * 学习的人数
     */
    public int studentCount;
    /**
     * 考试开始时间
     */
    public String examStartTime;
    /**
     * 考试结束时间
     */
    public String examEndTime;
    /**
     * 是否是学分课---0非学分；1学分
     */
    public int hasCredit;
    /**
     * 补考规则：0允许且仅1次，1禁止补考
     */
    public int retakeStatus;

    /**
     * 该值由课程开始和课程结束数据计算出来：课程状态1.未开始2.进行中3.已完成
     */
    public int courseState;
    /**
     * 是否设置了学习目标信息
     */
    public boolean isSetTaskInfo;
    /**
     * 是否设置了分数信息
     */
    public boolean isSetScoreInfo;
    /**
     * 私有云类型：0非私有云，1私有云课程
     */

    public int turnType;
    /**
     * 进阶式课程表ID，提交视频进度用
     */
    public Integer linkCourseId;
    /**
     * 是否跨章学习。0否，1是
     */
    public Integer isJumpChapter;
    /**
     * 1进阶课程 2微课程
     */
    public Integer courseType;



}
