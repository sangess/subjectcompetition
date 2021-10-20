package com.sang.subjectcompetition.service.impl;

import com.sang.subjectcompetition.entity.College;
import com.sang.subjectcompetition.entity.College_Comp;
import com.sang.subjectcompetition.entity.Comp;
import com.sang.subjectcompetition.entity.resultInfo.CompResult;
import com.sang.subjectcompetition.respository.College_CompRepository;
import com.sang.subjectcompetition.service.College_CompService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class College_CompServiceImpl implements College_CompService {

    @Autowired
    private College_CompRepository college_compRepository;

    //基本操作--------------------------------------------------------------

    /**
     * 根据院系和竞赛返回列表
     * @param collegeId
     * @param compId
     * @return
     */
    @Override
    public College_Comp getCollegeComp(Integer collegeId, Integer compId) {
        College_Comp college_comp= college_compRepository.findCollege_CompByCollege_IdAndComp_Id(collegeId, compId);
        return college_comp;
    }

    /**
     * 新建学院和比赛的联系
     * @param college_comp
     * @return
     */
    @Override
    public College_Comp save(College_Comp college_comp) {
        College_Comp save = college_compRepository.save(college_comp);
        return save;
    }

    /**
     * 根据主键返回实体
     * @param collegeCompId
     * @return
     */
    @Override
    public College_Comp getCollegeComp(Integer collegeCompId) {
        College_Comp college_comp = college_compRepository.findById(collegeCompId).orElse(null);
        return college_comp;
    }

    /**
     * 根据学院id返回本学院的竞赛联系列表
     * @param collegeId
     * @return
     */
    @Override
    public List<College_Comp> getCollegeComps(Integer collegeId) {
        List<College_Comp> collegeCompList = college_compRepository.findCollege_CompsByCollege_Id(collegeId);
        return collegeCompList;
    }

    /**
     * 删除学院和比赛的申请，驳回，参与的联系
     * @param College_Comp_Id
     */
    @Override
    public void deleteCollege_CompById(Integer College_Comp_Id) {
        college_compRepository.deleteById(College_Comp_Id);
    }

    /**
     * 得到未处理申请的比赛申请  1/4
     * @return
     */
    @Override
    public List<College_Comp> getUndoApplyComp() {
        List<College_Comp> collegeCompList = new ArrayList<>();
        collegeCompList.addAll(college_compRepository.findCollege_CompsByApplyOrJoin(1));
        collegeCompList.addAll(college_compRepository.findCollege_CompsByApplyOrJoin(4));
        return collegeCompList;
    }

    /**
     * 得到已经处理的比赛申请 2--3
     * @return
     */
    @Override
    public List<College_Comp> getDoneApplyComp() {
        List<College_Comp> collegeCompList = new ArrayList<>();
        collegeCompList.addAll(college_compRepository.findCollege_CompsByApplyOrJoin(2));
        collegeCompList.addAll(college_compRepository.findCollege_CompsByApplyOrJoin(3));
        return collegeCompList;
    }

    /**
     * 得到未处理的比赛添加申请 0
     * @return
     */
    @Override
    public List<College_Comp> getUndoAddCompList() {
        List<College_Comp> collegeCompList = new ArrayList<>();
        if(college_compRepository.findCollege_CompsByApplyOrJoin(0)!=null)
        collegeCompList.addAll(college_compRepository.findCollege_CompsByApplyOrJoin(0));
        return collegeCompList;
    }

    /**
     * 得到未处理的比赛添加申请 0
     * @return
     */
    @Override
    public List<College_Comp> getDoneAddCompList() {
        List<College_Comp> collegeCompList = new ArrayList<>();
        if(college_compRepository.findCollege_CompsByApplyOrJoin(5)!=null)
            collegeCompList.addAll(college_compRepository.findCollege_CompsByApplyOrJoin(5));
        if(college_compRepository.findCollege_CompsByApplyOrJoin(6)!=null)
            collegeCompList.addAll(college_compRepository.findCollege_CompsByApplyOrJoin(6));
        return collegeCompList;
    }

    /**
     * 得到未处理申请的比赛申请  1/4
     * @return
     */
    @Override
    public List<College_Comp> getUndoApplyComp(Integer collegeId) {
        List<College_Comp> collegeCompList = new ArrayList<>();
        collegeCompList.addAll(college_compRepository.findCollege_CompsByApplyOrJoin(collegeId,1));
        collegeCompList.addAll(college_compRepository.findCollege_CompsByApplyOrJoin(collegeId,4));
        return collegeCompList;
    }

    /**
     * 得到已经处理的比赛申请 2--3
     * @return
     */
    @Override
    public List<College_Comp> getDoneApplyComp(Integer collegeId) {
        List<College_Comp> collegeCompList = new ArrayList<>();
        collegeCompList.addAll(college_compRepository.findCollege_CompsByApplyOrJoin(collegeId,2));
        collegeCompList.addAll(college_compRepository.findCollege_CompsByApplyOrJoin(collegeId,3));
        return collegeCompList;
    }

    @Override
    public List<College_Comp> findAllByDate(Date startDate, Date endDate) {
        List<College_Comp> compList=new ArrayList<>();
        Specification<College_Comp> specification=new Specification<College_Comp>() {
            @Override
            public Predicate toPredicate(Root<College_Comp> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(!StringUtils.isEmpty(startDate)){
                    predicates.add(cb.greaterThanOrEqualTo(root.get("applyDate").as(Date.class),startDate));
                }
                if(!StringUtils.isEmpty(endDate)){
                    predicates.add(cb.lessThan(root.get("applyDate").as(Date.class),endDate));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        compList = college_compRepository.findAll(specification);
        return compList;
    }




}
