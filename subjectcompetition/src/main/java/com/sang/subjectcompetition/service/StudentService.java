package com.sang.subjectcompetition.service;

import com.sang.subjectcompetition.entity.College;
import com.sang.subjectcompetition.entity.Student;
import com.sang.subjectcompetition.entity.Teacher;
import com.sang.subjectcompetition.entity.resultInfo.ProResult;
import com.sang.subjectcompetition.entity.resultInfo.UserInfo;

import java.util.List;

public interface StudentService {

    Student save(Student student);

    Student getStudentById(Integer id);

    void deleteStudentById(Integer id);

    //业务逻辑-------------------------------------------------------

    /**
     * 更新学生信息
     * @param student
     * @return
     */
    UserInfo updateStudent(Student student);

    /**
     * 获得本院系所有学生
     * @param collegeId
     * @return
     */
    List<Student> getAllStudents(Integer collegeId);

    //-----------------------------------------------面向老师------------------------------------------

    /**
     * 学生申请加入项目（未判断比赛的状态）
     * @param studentId
     * @param projectId
     * @return
     */
    //ProResult applyJoinProject(Integer studentId,Integer projectId);

    /**
     * 撤销学生申请
     * @param studentProjectId
     * @return
     */
    ProResult invokeApply(Integer studentProjectId);

    /**
     * 退出项目组（未判断比赛的状态）
     * @param studentId
     * @param projectId
     * @return
     */
    ProResult exitProject(Integer studentId,Integer projectId);

    /**
     * 查看同项目的所有学生
     * @param projectId
     * @return
     */
    List<Student> getTheStudents(Integer projectId);

    /**
     * 查看指导老师集合
     * @param projectId
     * @return
     */
    List<Teacher> getTheTeachers(Integer projectId);

    /**
     * 学生申请加入项目
     * @param studentId
     * @param projectId
     * @return
     */
    ProResult applyJoinProject(Integer studentId, Integer projectId);

    /**
     * 学生重新申请加入项目
     * @param student_projectId
     * @return
     */
    ProResult reApplyJoinProject(Integer student_projectId);

    /**
     * 是否参加了该比赛
     * @param studentId
     * @param projectId
     * @return
     */
    Boolean ifJoinTheSameComp(Integer studentId,Integer projectId);

    Student findStudentByOppenId(String oppenId);

    Student findStudentByUsernameAndPassword(String username, String password);

    public Boolean ifApplyTheSameComp(Integer studentId,Integer projectId);
}
