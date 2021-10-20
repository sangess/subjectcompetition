package com.sang.subjectcompetition.service;

import com.sang.subjectcompetition.entity.College;
import com.sang.subjectcompetition.entity.College_Comp;
import com.sang.subjectcompetition.entity.Comp;
import com.sang.subjectcompetition.entity.resultInfo.CompResult;

import java.util.Date;
import java.util.List;

public interface College_CompService {

    /**
     * 根据院系和竞赛返回关系列表
     * @param collegeId
     * @param compId
     * @return
     */
    College_Comp getCollegeComp(Integer collegeId,Integer compId);

    /**
     * 保存学院和竞赛的关系
     * @param college_comp
     * @return
     */
    College_Comp save(College_Comp college_comp);

    /**
     * 根据主键返回实体
     * @param collegeCompId
     * @return
     */
    College_Comp getCollegeComp(Integer collegeCompId);

    /**
     * 根据学院Id返回竞赛列表
     * @param collegeId
     * @return
     */
    List<College_Comp> getCollegeComps(Integer collegeId);

    /**
     * 删除学院和竞赛的联系：申请，参与，驳回
     * @param College_Comp_Id
     */
    void deleteCollege_CompById(Integer College_Comp_Id);

    //业务逻辑-----------------------------------------------------------

    /**
     * 得到未处理的申请  1--4
     * @return
     */
    List<College_Comp> getUndoApplyComp();

    /**
     * 得到已经处理的申请  2--3
     * @return
     */
    List<College_Comp> getDoneApplyComp();

    /**
     * 得到未处理的申请  1--4
     * @return
     */
    List<College_Comp> getUndoApplyComp(Integer collegeId);

    /**
     * 得到已经处理的申请  2--3
     * @return
     */
    List<College_Comp> getDoneApplyComp(Integer collegeId);

    /**
     * 查询时间范围内的数据
     * @param startDate
     * @param endDate
     * @return
     */
    List<College_Comp> findAllByDate(Date startDate,Date endDate);

    /**
     * 得到未处理的比赛添加申请 0
     * @return
     */
    List<College_Comp> getUndoAddCompList();

    /**
     * 得到未处理的比赛添加申请 5-6
     * @return
     */
    List<College_Comp> getDoneAddCompList();

}
