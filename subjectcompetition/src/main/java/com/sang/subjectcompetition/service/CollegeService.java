package com.sang.subjectcompetition.service;

import com.sang.subjectcompetition.entity.*;
import com.sang.subjectcompetition.entity.resultInfo.CompResult;
import com.sang.subjectcompetition.entity.resultInfo.ProResult;
import com.sang.subjectcompetition.entity.resultInfo.UserInfo;

import java.util.List;

public interface CollegeService {

    UserInfo save(College college);

    College getCollegeById(Integer id);

    List<College> findAll();

    //业务逻辑--------------------------------------------------------

    /**
     * 更改学院信息
     * @param college
     * @return
     */
    UserInfo updateCollege(College college);

    //-------------面向学校管理员-----------------

    /**
     * 学院申请比赛
     * @param collegeId
     * @param compId
     * @return
     */
    CompResult applyComp(Integer collegeId, Integer compId);

    /**
     * 学院申请创建竞赛
     * @param comp
     * @return
     */
    CompResult applyCreateComp(Comp comp,Integer collegeId);

    /**
     * 学院撤销比赛申请
     * @param collegeId
     * @param compId
     * @return
     */
    CompResult revokeCompApply(Integer collegeId,Integer compId);

    /**
     * 学院退出比赛
     * @param collegeId
     * @param compId
     * @return
     */
    CompResult exitComp(Integer collegeId,Integer compId);

    /**
     * 根据状态得到院系内比赛的信息
     * @param collegeId
     * @param state
     * @return
     */
    List<Comp> getCompListByState(Integer collegeId, Integer state);

    //----------------------------面向教师---------------------------------getUndoApplyTeacherList

    /**
     * 得到院系内未处理的老师项目请求
     * @param collegeId
     * @return
     */
    List<Teacher_Project> getUndoApplyTeacherList(Integer collegeId);

    /**
     * 得到院系内处理过的老师项目请求
     * @param collegeId
     * @return
     */
    List<Teacher_Project> getDoneApplyTeacherList(Integer collegeId);

    CompResult reapplyAddComp(Integer compCollegeId);

    /**
     * 根据院系获得所有的老师
     * @param collegeId
     * @return
     */
    List<Teacher> getAllTeachersByCollegeId(Integer collegeId);

    /**
     * 增加院系的老师账号
     * @param teacher
     * @param collegeId
     * @return
     */
    UserInfo createTeacher(Teacher teacher ,Integer collegeId);

    /**
     * 同意老师参加比赛
     * @param teacherProjcetId
     * @return
     */
    ProResult applyTeacherProject(Integer teacherProjcetId);

    /**
     * 拒绝老师参加比赛
     * @param teacherProjcetId
     * @return
     */
    ProResult refuseTeacherProject(Integer teacherProjcetId);

    /**
     * 根据状态得到院系内项目信息
     * @param collegeId
     * @param state
     * @return
     */
    List<Teacher_Project> getTeacherProjcetList(Integer collegeId , Integer state);

    //--------------------------------------面向学生-----------------------------------------
    UserInfo createStudent(Student student,Integer collegeId);
}
