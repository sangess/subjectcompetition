package com.sang.subjectcompetition.service;

import com.sang.subjectcompetition.entity.*;
import com.sang.subjectcompetition.entity.resultInfo.ProResult;
import com.sang.subjectcompetition.entity.resultInfo.UserInfo;

import java.util.List;
import java.util.Map;

public interface TeacherService {

    Teacher save(Teacher teacher);

    Teacher getTeacherById(Integer id);

    //业务逻辑--------------------------------------------------------

    /**
     * 更新老师信息
     * @param teacher
     * @return
     */
    UserInfo updateTeacher(Teacher teacher);

    /**
     * 获得本院系所有的老师
     * @param collegeId
     * @return
     */
    List<Teacher> getAllTeachersByCollegeId(Integer collegeId);

    //--------------------------面向学院----------------------------------

    /**
     * 老师向学院申请参加比赛
     * @param teacherId
     * @param project
     * @param compId
     * @return
     */
    ProResult applyJoinComp(Integer teacherId,Integer compId,Project project);

    /**
     * 老师重新向学院申请参加比赛
     * @param teacherId
     * @param projectId
     * @return
     */
    ProResult reApplyJoinComp(Integer teacherId,Integer projectId);

    /**
     * 老师撤销比赛申请
     * @param teacherId
     * @param projectId
     * @return
     */
    ProResult revokeJoinComp(Integer teacherId,Integer projectId);

    /**
     * 创建者老师退出比赛
     * @param teacherId
     * @param projectId
     * @return
     */
    ProResult exitComp(Integer teacherId,Integer projectId);

    /**
     * 根据老师获得学院的竞赛信息
     * @param teacherId
     * @return
     */
    List<Comp> getCollegeCompListByTeacherId(Integer teacherId);

    /**
     * 返回全部比赛包括本学院列表
     * @param teacherId
     * @return
     */
    List<Map<String , Object>> getCollegeCompApplyStateListByTeacherId(Integer teacherId);

    //---------------------------------面向老师---------------------------------

    /**
     * 老师申请加入项目
     * @param teacherProjectId
     * @return
     */
    ProResult teacherApplyJoinProject(Integer teacherProjectId,Integer teacherId);

    /**
     * 转交组长给另一个老师
     * @param projectId
     * @param teacherId1
     * @param teacherId2
     * @return
     */
    UserInfo transmitProjectCreator(Integer projectId,Integer teacherId1,Integer teacherId2);

    /**
     * 老师退出项目组
     * @param teacherId
     * @param projectId
     * @return
     */
    ProResult exitProject(Integer teacherId,Integer projectId);

    /**
     * 同意老师加入项目组的申请
     * @param teacherProjectId
     * @return
     */
    ProResult applyTeacherJoinProject(Integer teacherProjectId);

    /**
     * 老师重复申请加入项目
     * @param teacher_projectId
     * @return
     */
    ProResult teacherReApplyJoinProject(Integer teacher_projectId);

    /**
     * 拒绝老师加入项目组
     * @param teacherProjectId
     * @return
     */
    ProResult refuseTeacherJoinProject(Integer teacherProjectId);

    /**
     * 通过项目id查看同组的老师
     * @param projectId
     * @return
     */
    List<Teacher> getAllTeachersByProject(Integer projectId);

    /**
     * 老师审核老师界面
     * @return
     */
    List<Teacher_Project> getTeacherProjectsAboutMyself(Integer teacherId);

    //----------------------------------面向学生-----------------------------------

    /**
     * 根据老师来获得所有的审核
     * @param teacherId
     * @return
     */
    List<Student_Project> getAllStudentProjectApply(Integer teacherId);

    /**
     * 同意学生加入项目
     * @param studentProjectId
     * @return
     */
    ProResult applyStudentJoin(Integer studentProjectId);

    /**
     * 拒绝同学加入项目
     * @param studentProjectId
     * @return
     */
    ProResult refuseStudentJoin(Integer studentProjectId);

    /**
     * 查看所有参加id为项目id的学生集合
     * @param projectId
     * @return
     */
    List<Student> getAllStudentJoinProject(Integer projectId);

    /**
     * 将学生踢出项目组
     * @param studentId
     * @param projectId
     * @return
     */
    ProResult fouceStudentExit(Integer studentId,Integer projectId);

    /**
     * 解散项目组
     * @param projectId
     * @return
     */
    ProResult invokeProject(Integer projectId);

    /**
     * 移出老师
     * @param teacherId
     * @return
     */
    ProResult exitTeacher(Integer teacherId,Integer projectId);

    /**
     * 移出学生
     * @param studentId
     * @return
     */
    ProResult exitStudent(Integer studentId ,Integer projectId);
}
