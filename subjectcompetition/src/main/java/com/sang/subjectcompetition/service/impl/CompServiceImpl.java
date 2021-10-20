package com.sang.subjectcompetition.service.impl;

import com.sang.subjectcompetition.entity.*;
import com.sang.subjectcompetition.entity.resultInfo.CompResult;
import com.sang.subjectcompetition.entity.resultInfo.UserInfo;
import com.sang.subjectcompetition.respository.CompRepository;
import com.sang.subjectcompetition.service.*;
import com.sang.subjectcompetition.service.util.UpdateUtil;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class CompServiceImpl implements CompService {
    @Autowired
    private CompRepository compRepository;

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private College_CompService college_compService;

    @Autowired
    private MessageService messageService;

    //基本操作----------------------------------------------------

    @Override
    public Comp save(Comp comp) {
        Comp comp_save = compRepository.save(comp);
        return comp_save;
    }

    @Override
    public Comp findCompById(Integer id) {
        return compRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Integer compId) {
        compRepository.deleteById(compId);
    }

    //业务逻辑层---------------------------------------------------------------

    /**
     * 查询所有的比赛
     * @return
     */
    @Override
    public List<Comp> getAllComps() {
        List<Comp> compList = compRepository.findAll();
        Iterator<Comp> iterator = compList.iterator();
        while(iterator.hasNext()){
            Comp comp = iterator.next();
            if(comp.getVisiable()==false){
                iterator.remove();
            }
        }
        return compList;
    }

    /**
     * 根据CompId竞赛的id删除竞赛和学院的联系
     * @param id
     * @return
     */
    @Override
    public CompResult deleteCompById(Integer id) {
        CompResult compResult=new CompResult();
        try {
            this.deleteById(id);
            compResult.setCode(204);
            compResult.setMessage("删除成功！");
        }catch (Exception e){
            compResult.setCode(404);
            compResult.setMessage("删除失败！");
        }finally {
            return compResult;
        }

    }

    /**
     * 根据学院id返回本学院的竞赛列表
     * @param collegeId
     * @return
     */
    @Override
    public List<Comp> getCompsByCollegeId(Integer collegeId) {
        List<Comp> compList = new ArrayList<>();
        List<College_Comp> collegeComps = college_compService.getCollegeComps(collegeId);
        for (College_Comp collegeComp : collegeComps) {
            Integer compId = collegeComp.getComp().getId();
            Comp comp=compRepository.findById(compId).orElse(null);
            compList.add(comp);
        }
        return compList;
    }

    //管理员操作------------------------------------------------------------------

    /**
     * 管理员发布竞赛
     * @param comp
     * @return
     */
    @Override
    @Transactional
    public CompResult createComp(Comp comp) {
        CompResult compResult=new CompResult();
        if (comp.getId()!=null) {
            Comp comp_db = findCompById(comp.getId());
            if (comp_db != null) {//更新操作
                UpdateUtil.copyNullProperties(comp, comp_db);
                comp_db.setVisiable(true);
                Comp save = save(comp_db);
                if (save != null) {
                    compResult.setCode(200);
                    compResult.setMessage("修改成功！");
                    return compResult;
                } else {
                    compResult.setCode(400);
                    compResult.setMessage("修改失败！");
                    return compResult;
                }
            }
        }
        Comp comp_save = this.save(comp);
        comp_save.setPublishTime(new Date());
        comp_save.setVisiable(true);
        if (comp_save!=null){
            compResult.setCode(200);
            compResult.setMessage("添加成功！");
            return compResult;
        }else {
            compResult.setCode(400);
            compResult.setMessage("发布失败！");
            return compResult;
        }
    }

    /**
     * 管理员撤销竞赛
     * @param id
     * @return
     */
    @Override
    public CompResult revokeComp(Integer id) {
        CompResult result=new CompResult();
        try {
            this.deleteById(id);
            result.setCode(101);
            result.setMessage("撤销成功！");
        }catch (Exception e){
            result.setCode(401);
            result.setMessage("撤销失败！");
        }
        return result;
    }

    /**
     * 管理员同意比赛申请
     * @param collegeCompId
     * @return
     */
    @Override
    public CompResult applyComp(Integer collegeCompId) {
        CompResult compResult=new CompResult();
        College_Comp collegeComp = college_compService.getCollegeComp(collegeCompId);
        College college = collegeComp.getCollege();
        Comp comp = collegeComp.getComp();
        Integer state = collegeComp.getApplyOrJoin();
        if(state!=1&state!=4){//不是申请状态
            compResult.setCode(400);
            compResult.setMessage("状态异常，无法同意！");
            return compResult;
        }else {
            collegeComp.setApplyOrJoin(3);//3表示同意
            collegeComp.setUpdateApplyDate(new Date());
            college_compService.save(collegeComp);
            compResult.setCode(200);
            Message message=new Message();
            message.setReceiver(college.getTargetId());
            message.setSender("3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad");
            message.setSenderRole(3);
            message.setReceiverRole(2);
            message.setTitle("您的比赛申请通过了！");
            message.setContent("管理员"+"已同意您申请的比赛："+comp.getCompName());
            message.setType(2);
            message.setMsgDate(new Date());
            messageService.save(message);
            compResult.setMessage("审核通过！");
            return compResult;
        }
    }

    /**
     * 管理员拒绝比赛申请
     * @param collegeCompId
     * @return
     */
    @Override
    public CompResult refuseComp(Integer collegeCompId) {
        CompResult compResult=new CompResult();
        College_Comp collegeComp = college_compService.getCollegeComp(collegeCompId);
        Integer state = collegeComp.getApplyOrJoin();
        if(state!=1&state!=4){//不是申请状态
            compResult.setCode(400);
            compResult.setMessage("状态异常，无法同意！");
            return compResult;
        }else {
            collegeComp.setApplyOrJoin(2);//2表示驳回
            collegeComp.setUpdateApplyDate(new Date());
            college_compService.save(collegeComp);
            compResult.setCode(200);
            compResult.setMessage("审核驳回！");
            return compResult;
        }
    }

    /**
     * 多条件模糊查询
     * @param compName
     * @param organizer
     * @param level
     * @param subjectType
     * @param place
     * @param compState
     * @return
     */
    @Override
    public List<Comp> getCompsBySelf(String compName,String organizer,Integer level,String subjectType,String place,Integer compState){
        return compRepository.getCompsBySelf(compName,organizer,level,subjectType,place,compState);
    }
}
