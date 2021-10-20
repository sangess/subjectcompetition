package com.sang.subjectcompetition.service.impl;

import com.sang.subjectcompetition.entity.*;
import com.sang.subjectcompetition.respository.ProjectRepository;
import com.sang.subjectcompetition.respository.Student_ProjectRepository;
import com.sang.subjectcompetition.respository.Teacher_ProjectRepository;
import com.sang.subjectcompetition.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private Student_ProjectRepository student_projectRepository;

    @Autowired
    private Teacher_ProjectRepository teacher_projectRepository;

    /**
     * 根据id获取项目
     * @param projectId
     * @return
     */
    @Override
    public Project getProjectById(Integer projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        return project;
    }

    /**
     * 保存或者更新项目
     * @param project
     * @return
     */
    @Override
    public Project save(Project project) {
        Project project_save = projectRepository.save(project);
        return project_save;
    }

    /**
     * 根据指定的项目id删除指定的项目
     * @param projectId
     */
    @Override
    public void deleteProject(Integer projectId) {
        projectRepository.deleteById(projectId);
    }

    /**
     * 根据竞赛id和老师id返回竞赛项目
     * @param compId
     * @param teacherId
     * @return
     */
    @Override
    public Project findProjectByCompIdAndCreateByTeacherId(Integer compId, Integer teacherId) {
        return projectRepository.findProjectByCompIdAndCreateByTeacherId(compId,teacherId);
    }

    /**
     * 根据院系id返回院系内的所有的竞赛项目
     * @param collegeId
     * @return
     */
    @Override
    public List<Project> getProjectByCollege(Integer collegeId) {
        List<Project> projectList=new ArrayList<>();
        List<Teacher> teachers = collegeService.getAllTeachersByCollegeId(collegeId);
        for (Teacher teacher : teachers) {
            Integer teacherId=teacher.getId();
            List<Project> projects = getTeacherProjects(teacherId);
            if (projects.size()!=0){
                projectList.addAll(new ArrayList<>(projects));
            }
        }
        for (Project project : projectList) {
        }
        return projectList;
    }

    /**
     * 老师得到本院系的项目
     * @param teacherId
     * @return
     */
    @Override
    public List<Project> getProjectByCollegeWithTeacher(Integer teacherId) {
        Integer collegeId=teacherService.getTeacherById(teacherId).getCollege().getId();
        return getProjectByCollege(collegeId);
    }

    /**
     * 学生得到本院系的项目
     * @param studentId
     * @return
     */
    @Override
    public List<Project> getProjectByCollegeWithStudent(Integer studentId) {
        Integer collegeId = studentService.getStudentById(studentId).getCollege().getId();
        return getProjectByCollege(collegeId);
    }

    /**
     * 根据竞赛id返回该竞赛所有的项目
     * @param compId
     * @return
     */
    @Override
    public List<Project> getProjectsBycompId(Integer compId){
        return projectRepository.findProjectsByCompId(compId);
    }

    /**
     * 得到所有的竞赛项目
     * @return
     */
    @Override
    public List<Project> getAllProjects() {
        List<Project> projectList = projectRepository.findAll();
        return projectList;
    }

    /**
     * 根据项目获得参赛学生
     * @param projectId
     * @return
     */
    @Override
    public List<Student> getProjectJoinStudent(Integer projectId) {
        List personList=new ArrayList();
        //----------------------学生----------------------
        List<Student_Project> student_projects = student_projectRepository.findStudent_ProjectByProjectsIdAndApplyJoinState(projectId,3);
        if(!student_projects.isEmpty()){
            for (Student_Project student_project : student_projects) {
                personList.add(student_project.getStudent());
            }
        }
        return personList;
    }

    /**
     * 根据项目获得参赛老师
     * @param projectId
     * @return
     */
    @Override
    public List<Teacher> getProjectJoinTeacher(Integer projectId) {
        List<Teacher>  personList=new ArrayList();
        //----------------------老师----------------------
        //带队
        List<Teacher_Project> isCreator = teacher_projectRepository.getTeacher_ProjectsByProjectIdAndIsCreator(projectId, true);
        for (Teacher_Project teacher_project : isCreator) {
            if(teacher_project.getApplyOrJoin()==3) personList.add(teacher_project.getTeacher());
        }
        //非带队
        List<Teacher_Project> isNoCreator = teacher_projectRepository.getTeacher_ProjectsByProjectIdAndIsCreator(projectId, false);
        for (Teacher_Project teacher_project : isNoCreator) {
            if(teacher_project.getApplyOrJoin()==3) personList.add(teacher_project.getTeacher());
        }
        return personList;
    }

    /**
     * 跟据院系id所多条件获取多条件搜索
     * @param collegeId
     * @param project
     * @return
     */
    @Override
    public List<Project> getMoreSearchProjectByCollege(Integer collegeId, Project project) {
        List<Project> projectList=new ArrayList<>();
        List<Project> projects = projectRepository.findProjectsBySearch(project.getProjectName(), project.getComp().getId(), project.getApplyState());
        for (Project project1 : projects) {
            if (project1.getCreateByTeacher().getCollege().getId()==collegeId){
                projectList.add(project1);
            }
        }
        return projectList;
    }

    /**
     * 对所有的项目进行多条件查询获得集合
     * @return
     */
    @Override
    public List<Project> getMoreSearchAllProject(Project project) {
        List<Project> projectList=new ArrayList<>();
        List<Project> projects = projectRepository.findProjectsBySearch(project.getProjectName(), project.getComp().getId(), project.getApplyState());
        return projectList;
    }

    /**
     * 得到学生的项目集合
     * @param studentId
     * @return
     */
    @Override
    public List<Project> getStudentProjects(Integer studentId) {
        List<Project> projects=new ArrayList<>();
        List<Student_Project> studentProjectList = student_projectRepository.findStudent_ProjectsByStudentId(studentId);
        for (Student_Project student_project : studentProjectList) {
            if(student_project.getApplyOrJoin()==3){
                projects.add(student_project.getProject());
            }
        }
        return projects;
    }

    /**
     * 得到教师领队的项目
     * @param teacherId
     * @return
     */
    @Override
    public List<Project> getTeacherProjects(Integer teacherId) {
        List<Project> projects=new ArrayList<>();
        List<Teacher_Project> teacher_projects = teacher_projectRepository.getTeacher_ProjectsByTeacherId(teacherId);
        for (Teacher_Project teacher_project : teacher_projects) {
            if (teacher_project.getApplyOrJoin()==3&&teacher_project.getIsCreator()==true) {
                projects.add(teacher_project.getProject());
            }
        }
        return projects;
    }

    /**
     * 得到老师参与的项目
     * @param teacherId
     * @return
     */
    @Override
    public List<Project> getTeacherJoinProjects(Integer teacherId) {
        List<Project> projects=new ArrayList<>();
        List<Teacher_Project> teacher_projects = teacher_projectRepository.getTeacher_ProjectsByTeacherId(teacherId);
        for (Teacher_Project teacher_project : teacher_projects) {
            if (teacher_project.getApplyOrJoin()==3) {
                projects.add(teacher_project.getProject());
            }
        }
        return projects;
    }


}
