package com.sang.subjectcompetition.service;

import com.sang.subjectcompetition.entity.Project;
import com.sang.subjectcompetition.entity.Student_Project;
import com.sang.subjectcompetition.entity.Teacher_Project;

import java.util.Date;
import java.util.List;

public interface Teacher_ProjectService {

    /**
     * 保存或者更新
     * @param teacher_project
     * @return
     */
    Teacher_Project save(Teacher_Project teacher_project);

    /**
     * 根据项目和老师获得具体的项目
     * @param teacherProjectId
     * @return
     */
    Teacher_Project getProjectTeacher(Integer teacherProjectId);

    /**
     * 根据项目和老师获得具体的项目
     * @param projectId
     * @param teacherId
     * @return
     */
    Teacher_Project getProjectTeacher(Integer teacherId,Integer projectId);

    /**
     * 根据创建老师获得项目联系
     * @param teacherId
     * @param isCreator
     * @return
     */
    List<Teacher_Project> getTeacherProjectsByTeacherAndIsCreator(Integer teacherId,boolean isCreator);

    /**
     * 根据老师和项目删除指定的项目申请
     * @param teacherId
     * @param projectId
     */
    void deleteTeacherProject(Integer teacherId,Integer projectId);

    /**
     * 根据项目删除老师和项目之间的关系
     * @param projectId
     */
    void deleteTeacherProjectsByProject(Integer projectId);

    /**
     * 根据项目id查看同组的老师关系集合
     * @param projectId
     * @return
     */
    List<Teacher_Project> getAllTeacherProjectsByProject(Integer projectId);

    /**
     * 根据老师项目和创建者返回关系
     * @param teacherId
     * @param projectId
     * @param isCreator
     * @return
     */
    Teacher_Project findTeacher_ProjectByTeacherIdAndProjectIdAndIsCreator(Integer teacherId,Integer projectId,Boolean isCreator);

    /**
     * 得到未处理的学院与教师之间的关系
     * @param teacherId
     * @return
     */
    List<Teacher_Project> getUndoCollgeApplyList(Integer teacherId);

    /**
     * 得到处理过的学院与教师之间的关系
     * @param teacherId
     * @return
     */
    List<Teacher_Project> getDoneCollgeApplyList(Integer teacherId);

    /**
     * 根据老师的id获取老师所没有处理的学生项目申请
     * @param teacherId
     * @return
     */
    List<Student_Project> getUndoApplyProjectStudentList(Integer teacherId);

    /**
     * 根据老师的id获取老师所处理过的学生项目申请
     * @param teacherId
     * @return
     */
    List<Student_Project> getDoneApplyProjectStudentList(Integer teacherId);

    /**
     * 根据老师获得老师与老师之间的未处理的申请
     * @param teacherId
     * @return
     */
    List<Teacher_Project> getUndoApplyProjectTeacherList(Integer teacherId);

    /**
     * 根据老师获得老师与老师之间的未处理的申请
     * @param teacherId
     * @return
     */
    List<Teacher_Project> getDoneApplyProjectTeacherList(Integer teacherId);

    List<Teacher_Project> findAllByDate(Date startDate,Date endDate);
}
