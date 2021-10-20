package com.sang.subjectcompetition.service;

import com.sang.subjectcompetition.entity.Student_Project;

import java.util.Date;
import java.util.List;

public interface Student_ProjectService {

    Student_Project save(Student_Project student_project);

    /**
     * 根据id返回学生和项目的关系
     * @param id
     * @return
     */
    Student_Project getStudentProjectById(Integer id);

    /**
     * 根据学生id和项目id返回关系
     * @param studentId
     * @param projectId
     * @return
     */
    Student_Project getStudentProjectById(Integer studentId,Integer projectId);



    /**
     * 删除关系通过id
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据项目id查看关系集合
     * @param projectId
     * @return
     */
    List<Student_Project> getAllStudentByProject(Integer projectId);

    /**
     * 根据学生id返回学生未处理过的请求
     * @param studentId
     * @return
     */
    List<Student_Project> getUndoApplyProjectList(Integer studentId);

    /**
     * 根据学生id返回学生处理过的请求
     * @param studentId
     * @return
     */
    List<Student_Project> getDoneApplyProjectList(Integer studentId);

    /**
     * 得到学生所有和项目的关系
     * @param studentId
     * @return
     */
    List<Student_Project> getStudentAllProject(Integer studentId);

    List<Student_Project> findAllByDate(Date startDate,Date endDate);
}
