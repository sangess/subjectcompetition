package com.sang.subjectcompetition.service;

import com.sang.subjectcompetition.entity.College;
import com.sang.subjectcompetition.entity.Comp;
import com.sang.subjectcompetition.entity.resultInfo.CompResult;
import org.springframework.data.jpa.domain.Specification;

import javax.swing.plaf.basic.ComboPopup;
import java.util.List;

public interface CompService {
    Comp save(Comp comp);

    Comp findCompById(Integer id);

    CompResult deleteCompById(Integer id);

    void deleteById(Integer compId);

    //业务逻辑层往下----------------------------------------------------

    /**
     * 查询所有的竞赛
     * @return
     */
    List<Comp> getAllComps();

    /**
     * 根据学院Id返回竞赛列表
     * @param collegeId
     * @return
     */
    List<Comp> getCompsByCollegeId(Integer collegeId);

    //管理员的操作------------------------------------------------------
    /**
     * 管理员发布竞赛
     * @param comp
     * @return
     */
    CompResult createComp(Comp comp );

    /**
     * 管理员取消竞赛
     * @param id
     * @return
     */
    CompResult revokeComp(Integer id);

    /**
     * 管理员同意比赛申请
     * @param collegeCompId
     * @return
     */
    CompResult applyComp(Integer collegeCompId);

    /**
     * 管理员拒绝比赛申请
     * @param collegeCompId
     * @return
     */
    CompResult refuseComp(Integer collegeCompId);

    /**
     * 根据多条件模糊查询竞赛信息
     * @return
     */
    List<Comp> getCompsBySelf(String compName,String organizer,Integer level,String subjectType,String place,Integer compState);


}
