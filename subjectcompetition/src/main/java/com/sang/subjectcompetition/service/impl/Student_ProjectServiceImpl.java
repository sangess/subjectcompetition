package com.sang.subjectcompetition.service.impl;

import com.sang.subjectcompetition.entity.College_Comp;
import com.sang.subjectcompetition.entity.Student_Project;
import com.sang.subjectcompetition.respository.Student_ProjectRepository;
import com.sang.subjectcompetition.service.Student_ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class Student_ProjectServiceImpl implements Student_ProjectService {

    @Autowired
    private Student_ProjectRepository student_projectRepository;

    /**
     * 保存或者新建学生和项目之间的关系
     * @param student_project
     * @return
     */
    @Override
    public Student_Project save(Student_Project student_project) {

        return student_projectRepository.save(student_project);
    }

    /**
     * 根据id返回学生和项目之间的关系
     * @param id
     * @return
     */
    @Override
    public Student_Project getStudentProjectById(Integer id) {
        return student_projectRepository.findById(id).orElse(null);
    }

    /**
     * 根据学生id和项目id返回关系
     * @param studentId
     * @param projectId
     * @return
     */
    @Override
    public Student_Project getStudentProjectById(Integer studentId, Integer projectId) {
        return student_projectRepository.findStudent_ProjectByStudentIdAndAndProjectId(studentId,projectId);
    }

    /**
     * 根据关系的Id删除
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        student_projectRepository.deleteById(id);
    }

    /**
     * 根据项目id查看集合
     * @param projectId
     * @return
     */
    @Override
    public List<Student_Project> getAllStudentByProject(Integer projectId) {
        List<Student_Project> student_projectsByProject = student_projectRepository.findStudent_ProjectsByProjectId(projectId);
        return student_projectsByProject;
    }

    /**
     * 根据学生的id返回学生的未处理的请求
     * @param studentId
     * @return
     */
    @Override
    public List<Student_Project> getUndoApplyProjectList(Integer studentId) {
        List<Student_Project> undoApplyProjectList =new ArrayList<>();
        undoApplyProjectList.addAll(student_projectRepository.getApplyStudentProjectList(studentId,1));
        undoApplyProjectList.addAll(student_projectRepository.getApplyStudentProjectList(studentId,4));
        return undoApplyProjectList;
    }

    /**
     * 根据学生的id返回学生的处理过的请求
     * @param studentId
     * @return
     */
    @Override
    public List<Student_Project> getDoneApplyProjectList(Integer studentId) {
        List<Student_Project> undoApplyProjectList =new ArrayList<>();
        undoApplyProjectList.addAll(student_projectRepository.getApplyStudentProjectList(studentId,2));
        undoApplyProjectList.addAll(student_projectRepository.getApplyStudentProjectList(studentId,3));
        return undoApplyProjectList;
    }

    /**
     * 得到学生和项目所有的关系
     * @param studentId
     * @return
     */
    @Override
    public List<Student_Project> getStudentAllProject(Integer studentId) {
        return student_projectRepository.findStudent_ProjectsByStudentId(studentId);
    }

    @Override
    public List<Student_Project> findAllByDate(Date startDate, Date endDate) {
        Specification<Student_Project> specification=new Specification<Student_Project>() {
            @Override
            public Predicate toPredicate(Root<Student_Project> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
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
        return student_projectRepository.findAll(specification);
    }
}
