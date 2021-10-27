package com.sang.subjectcompetition.service.impl;

import com.sang.subjectcompetition.entity.Awards;
import com.sang.subjectcompetition.entity.Project;
import com.sang.subjectcompetition.entity.Student;
import com.sang.subjectcompetition.entity.resultInfo.ProResult;
import com.sang.subjectcompetition.respository.AwardsRepository;
import com.sang.subjectcompetition.service.AwardsService;
import com.sang.subjectcompetition.service.ProjectService;
import com.sang.subjectcompetition.service.StudentService;
import com.sang.subjectcompetition.service.util.ScoreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AwardsServiceImpl implements AwardsService {

    @Autowired
    private AwardsRepository awardsRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private StudentService studentService;

    @Override
    public Awards getAwardsById(Integer awardsId) {
        return awardsRepository.findById(awardsId).orElse(null);
    }

    @Override
    public ProResult save(Awards awards) {
        ProResult proResult=new ProResult();
        if(awards.getId()!=null){
            awards.setUpdateApplyDate(new Date());
            Awards save = awardsRepository.save(awards);
            if (save!=null){
                proResult.setCode(200);
                proResult.setMessage("操作成功！");
                return proResult;
            }else {
                proResult.setCode(400);
                proResult.setMessage("操作失败！");
                return proResult;
            }
        }
        awards.setState(1);
        awards.setApplyDate(new Date());
        awards.setUpdateApplyDate(new Date());


        Awards save = awardsRepository.save(awards);
        if (save!=null){
            proResult.setCode(200);
            proResult.setMessage("提交成功！");
            return proResult;
        }else {
            proResult.setCode(400);
            proResult.setMessage("提交失败！");
            return proResult;
        }
    }

    @Override
    public ProResult applyAwards(Integer awardsId) {
        ProResult proResult=new ProResult();
        Awards awards = getAwardsById(awardsId);
        if(awards!=null){
            awards.setState(2);
            List<Student> students = projectService.getProjectJoinStudent(awards.getProject().getId());
            for (Student student : students) {
                Student student_db = studentService.getStudentById(student.getId());
                int score=student_db.getScore()+ScoreUtil.getScore(awards.getType(),awards.getLevel());
                student_db.setScore(score);
                System.out.println("----------"+ScoreUtil.getScore(awards.getType(), awards.getLevel()));
                studentService.save(student_db);
            }
            return save(awards);
        }else {
            proResult.setMessage("异常");
            proResult.setCode(400);
            return proResult;
        }
    }

    @Override
    public ProResult invokeAwards(Integer awardsId) {
        ProResult proResult=new ProResult();
        Awards awards = getAwardsById(awardsId);
        if(awards!=null){
            awards.setState(3);
            return save(awards);
        }
        proResult.setMessage("异常");
        proResult.setCode(400);
        return proResult;
    }

    @Override
    public ProResult deleteApply(Integer awardsId) {
        ProResult proResult=new ProResult();
        try {
            awardsRepository.deleteById(awardsId);
            proResult.setCode(200);
            proResult.setMessage("操作成功！");
            return proResult;
        }catch (Exception e){
            proResult.setCode(400);
            proResult.setMessage("删除失败！");
            return proResult;
        }
    }

    @Override
    public List<Awards> findAll() {
        return awardsRepository.findAll();
    }

    @Override
    public List<Awards> findAll(Date beginDate, Date endDate, Integer type, Integer level) {
        Specification<Awards> specification = new Specification<Awards>() {
            @Override
            public Predicate toPredicate(Root<Awards> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(!StringUtils.isEmpty(beginDate)){
                    predicates.add(cb.greaterThanOrEqualTo(root.get("awardsTime").as(Date.class),beginDate));
                }
                if(!StringUtils.isEmpty(endDate)){
                    predicates.add(cb.lessThan(root.get("awardsTime").as(Date.class),endDate));
                }
                if(type!=null){
                    predicates.add(cb.equal(root.get("type").as(Integer.class),type));
                }
                if(level!=null){
                    predicates.add(cb.equal(root.get("level").as(Integer.class),level));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return awardsRepository.findAll(specification);
    }
}
