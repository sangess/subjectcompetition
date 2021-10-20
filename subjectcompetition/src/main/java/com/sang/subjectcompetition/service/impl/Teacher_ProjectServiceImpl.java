package com.sang.subjectcompetition.service.impl;

import com.sang.subjectcompetition.entity.Project;
import com.sang.subjectcompetition.entity.Student_Project;
import com.sang.subjectcompetition.entity.Teacher_Project;
import com.sang.subjectcompetition.respository.Student_ProjectRepository;
import com.sang.subjectcompetition.respository.Teacher_ProjectRepository;
import com.sang.subjectcompetition.service.Teacher_ProjectService;
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
public class Teacher_ProjectServiceImpl implements Teacher_ProjectService {

    @Autowired
    private Teacher_ProjectRepository teacher_projectRepository;

    @Autowired
    private Student_ProjectRepository student_projectRepository;

    @Override
    public Teacher_Project save(Teacher_Project teacher_project) {
        Teacher_Project save = teacher_projectRepository.save(teacher_project);
        return save;
    }

    /**
     * 根据项目和老师获得具体的项目
     * @param teacherProjectId
     * @return
     */
    @Override
    public Teacher_Project getProjectTeacher(Integer teacherProjectId) {
        Teacher_Project teacher_project = teacher_projectRepository.findById(teacherProjectId).orElse(null);
        return teacher_project;
    }

    /**
     * 根据项目和老师获得具体的项目
     * @param teacherId
     * @param projectId
     * @return
     */
    @Override
    public Teacher_Project getProjectTeacher(Integer teacherId, Integer projectId) {
        Teacher_Project teacherAndProject = teacher_projectRepository.getTeacher_ProjectByTeacherIdAndProjectId(teacherId, projectId);
        return teacherAndProject;
    }

    /**
     * 根据创建老师获得项目联系
     * @param teacherId
     * @param isCreator
     * @return
     */
    @Override
    public List<Teacher_Project> getTeacherProjectsByTeacherAndIsCreator(Integer teacherId,boolean isCreator) {
        return teacher_projectRepository.getTeacher_ProjectByTeacherIdAndIsCreator(teacherId,isCreator);
    }

    /**
     * 根据指定的老师和项目删除项目关系
     * @param teacherId
     * @param projectId
     */
    @Override
    public void deleteTeacherProject(Integer teacherId, Integer projectId) {
        teacher_projectRepository.deleteTeacher_ProjectByTeacherIdAndProjectId(teacherId,projectId);
    }

    /**
     * 根据项目删除老师和项目之间的关系
     * @param projectId
     */
    @Override
    public void deleteTeacherProjectsByProject(Integer projectId) {
        teacher_projectRepository.deleteTeacher_ProjectsByProject(projectId);
    }

    /**
     * 根据项目id查看项目与老师的集合
     * @param projectId
     * @return
     */
    @Override
    public List<Teacher_Project> getAllTeacherProjectsByProject(Integer projectId) {
        List<Teacher_Project> teacher_projectByProject = teacher_projectRepository.getTeacher_ProjectByProjectId(projectId);
        return teacher_projectByProject;
    }

    /**
     * 根据老师项目和创建者返回关系 队长
     * @param teacherId
     * @param projectId
     * @param isCreator
     * @return
     */
    @Override
    public Teacher_Project findTeacher_ProjectByTeacherIdAndProjectIdAndIsCreator(Integer teacherId, Integer projectId, Boolean isCreator) {
        return teacher_projectRepository.findTeacher_ProjectByTeacherIdAndProjectIdAndIsCreator(teacherId,projectId,isCreator);
    }

    /**
     * 得到未处理的学院与教师之间的关系
     * @param teacherId
     * @return
     */
    @Override
    public List<Teacher_Project> getUndoCollgeApplyList(Integer teacherId) {
        List<Teacher_Project> undoCollgeApplyList=new ArrayList<>();
        List<Teacher_Project> teacher_projects = this.getTeacherProjectsByTeacherAndIsCreator(teacherId, true);
        for (Teacher_Project teacher_project : teacher_projects) {
            Integer applyOrJoin = teacher_project.getApplyOrJoin();
            if (applyOrJoin==1|applyOrJoin==4){
                undoCollgeApplyList.add(teacher_project);
            }
        }
        return undoCollgeApplyList;
    }

    /**
     * 得到处理过的学院与教师之间的关系
     * @param teacherId
     * @return
     */
    @Override
    public List<Teacher_Project> getDoneCollgeApplyList(Integer teacherId) {
        List<Teacher_Project> doneCollgeApplyList=new ArrayList<>();
        List<Teacher_Project> teacher_projects = this.getTeacherProjectsByTeacherAndIsCreator(teacherId, true);
        for (Teacher_Project teacher_project : teacher_projects) {
            Integer applyOrJoin = teacher_project.getApplyOrJoin();
            if (applyOrJoin==2|applyOrJoin==3){
                doneCollgeApplyList.add(teacher_project);
            }
        }
        return doneCollgeApplyList;
    }

    /**
     * 根据老师的id获取老师所没有处理的学生项目申请
     */
    @Override
    public List<Student_Project> getUndoApplyProjectStudentList(Integer teacherId) {
        List<Project> projects=new ArrayList<>();
        List<Student_Project> result=new ArrayList<>();
        List<Teacher_Project> teacher_projects = getTeacherProjectsByTeacherAndIsCreator(teacherId, true);
        for (Teacher_Project teacher_project : teacher_projects) {
            projects.add(teacher_project.getProject());//得到老师所管理的所有项目
        }
        for (Project project : projects) {
            List<Student_Project> apply = student_projectRepository.findStudent_ProjectByProjectsIdAndApplyJoinState(project.getId(), 1);
            if (!apply.isEmpty()) result.addAll(apply);
            List<Student_Project> reApply = student_projectRepository.findStudent_ProjectByProjectsIdAndApplyJoinState(project.getId(), 4);
            if (!reApply.isEmpty()) result.addAll(reApply);
        }
        return result;
    }

    /**
     * 根据老师的id获取老师所处理过的学生项目申请
     */
    @Override
    public List<Student_Project> getDoneApplyProjectStudentList(Integer teacherId) {
        List<Project> projects=new ArrayList<>();
        List<Student_Project> result=new ArrayList<>();
        List<Teacher_Project> teacher_projects = getTeacherProjectsByTeacherAndIsCreator(teacherId, true);
        for (Teacher_Project teacher_project : teacher_projects) {
            projects.add(teacher_project.getProject());//得到老师所管理的所有项目
        }
        for (Project project : projects) {
            List<Student_Project> refuse = student_projectRepository.findStudent_ProjectByProjectsIdAndApplyJoinState(project.getId(), 2);
            if (!refuse.isEmpty()) result.addAll(refuse);
            List<Student_Project> trueApply = student_projectRepository.findStudent_ProjectByProjectsIdAndApplyJoinState(project.getId(), 3);
            if (!trueApply.isEmpty()) result.addAll(trueApply);
        }
        return result;
    }

    /**
     * 根据老师的id获取老师未处理的老师项目申请
     */
    @Override
    public List<Teacher_Project> getUndoApplyProjectTeacherList(Integer teacherId) {
        List<Teacher_Project> result=new ArrayList<>();
        //---------------------------带队老师身份--------------------------
        List<Teacher_Project> creator = getTeacherProjectsByTeacherAndIsCreator(teacherId, true);
        List<Teacher_Project> isNoCreator = teacher_projectRepository.getTeacher_ProjectsByIsCreator(false);
        List<Project> projects=new ArrayList<>();
        for (Teacher_Project teacher_project : creator) {
            projects.add(teacher_project.getProject());
        }
        for (Project project : projects) {
            for (Teacher_Project teacher_project : isNoCreator) {
                if(project.getId()==teacher_project.getProject().getId()){
                    if(teacher_project.getApplyOrJoin()==1|teacher_project.getApplyOrJoin()==4){
                        result.add(teacher_project);
                    }
                }
            }
        }
        //---------------------------非带队老师身份--------------------------
        List<Teacher_Project> noCreator = getTeacherProjectsByTeacherAndIsCreator(teacherId, false);
        for (Teacher_Project teacher_project : noCreator) {
            if(teacher_project.getApplyOrJoin()==1||teacher_project.getApplyOrJoin()==4){
                result.add(teacher_project);
            }
        }
        return result;
    }

    /**
     * 根据老师的id获取老师处理过的老师项目申请
     */
    @Override
    public List<Teacher_Project> getDoneApplyProjectTeacherList(Integer teacherId) {
        List<Teacher_Project> result=new ArrayList<>();
        //---------------------------带队老师身份---------------------------
        //拿到自身所管理带队的关系
        List<Teacher_Project> creator = getTeacherProjectsByTeacherAndIsCreator(teacherId, true);
        //拿到所有不是带队的关系
        List<Teacher_Project> isNoCreator = teacher_projectRepository.getTeacher_ProjectsByIsCreator(false);
        List<Project> projects=new ArrayList<>();
        for (Teacher_Project teacher_project : creator) {
            //得到自身所带队的项目
            projects.add(teacher_project.getProject());
        }
        for (Project project : projects) {
            for (Teacher_Project teacher_project : isNoCreator) {
                if(project.getId()==teacher_project.getProject().getId()){
                    if(teacher_project.getApplyOrJoin()==2|teacher_project.getApplyOrJoin()==3){
                        result.add(teacher_project);
                    }
                }
            }
        }
        //---------------------------------非带队者身份---------------------------------
        List<Teacher_Project> noCreator = getTeacherProjectsByTeacherAndIsCreator(teacherId, false);
        for (Teacher_Project teacher_project : noCreator) {
            if(teacher_project.getApplyOrJoin()==2||teacher_project.getApplyOrJoin()==3){
                result.add(teacher_project);
            }
        }
        return result;
    }

    @Override
    public List<Teacher_Project> findAllByDate(Date startDate, Date endDate) {
        Specification<Teacher_Project> specification=new Specification<Teacher_Project>() {
            @Override
            public Predicate toPredicate(Root<Teacher_Project> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
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
        return teacher_projectRepository.findAll(specification);
    }


}
