package com.sang.subjectcompetition.service;

import com.sang.subjectcompetition.entity.Project;
import com.sang.subjectcompetition.entity.Student;
import com.sang.subjectcompetition.entity.Teacher;

import java.util.List;
import java.util.TreeSet;

public interface ProjectService {

    /**
     * 根据id获得项目
     */
    Project getProjectById(Integer projectId);

    /**
     * 保存/更新项目
     */
    Project save(Project project);

    /**
     * 删除指定的项目
     * @param projectId
     */
    void deleteProject(Integer projectId);

    /**
     * 根据竞赛和创建老师返回项目对象
     * @param compId
     * @param teacherId
     * @return
     */
    Project findProjectByCompIdAndCreateByTeacherId(Integer compId,Integer teacherId);

    /**
     * 根据院系返回院系内的所有项目
     */
    List<Project> getProjectByCollege(Integer collegeId);

    /**
     * 老师得到本院系的
     * @param teacherId
     */
    List<Project> getProjectByCollegeWithTeacher(Integer teacherId);

    /**
     * 学生得到本院系的
     * @param studentId
     */
    List<Project> getProjectByCollegeWithStudent(Integer studentId);

    /**
     * 根据竞赛id返回该竞赛所有的项目
     */
    List<Project> getProjectsBycompId(Integer compId);

    /**
     * 得到所有的项目
     * @return
     */
    List<Project> getAllProjects();

    /**
     * 返回项目参与的学生
     * @param projectId
     * @return
     */
    List<Student> getProjectJoinStudent(Integer projectId);

    /**
     * 返回项目参与的老师
     * @param projectId
     * @return
     */
    List<Teacher> getProjectJoinTeacher(Integer projectId);

    /**
     * 根据学院id  进行多条件查询获得集合
     * @param collegeId
     * @return
     */
    List<Project> getMoreSearchProjectByCollege(Integer collegeId,Project project);

    /**
     * 对所有的项目进行多条件查询获得集合
     * @return
     */
    List<Project> getMoreSearchAllProject(Project project);

    /**
     * 得到学生的所有的项目集合
     * @param studentId
     * @return
     */
    List<Project> getStudentProjects(Integer studentId);

    /**
     * 得到教师领队的项目
     * @param teacherId
     * @return
     */
    List<Project> getTeacherProjects(Integer teacherId);

    /**
     * 得到老师参与的项目
     * @param teacherId
     * @return
     */
    List<Project> getTeacherJoinProjects(Integer teacherId);
}
