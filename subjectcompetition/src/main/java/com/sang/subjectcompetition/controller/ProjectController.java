package com.sang.subjectcompetition.controller;

import com.sang.subjectcompetition.entity.Project;
import com.sang.subjectcompetition.entity.resultInfo.ProResult;
import com.sang.subjectcompetition.service.ProjectService;
import com.sang.subjectcompetition.service.StudentService;
import com.sang.subjectcompetition.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    /**
     * 得到参加项目的老师
     * @param projectId
     * @return
     */
    @GetMapping("/getProjectJoinTeacher/{projectId}")
    public List getProjectJoinTeacher(@PathVariable Integer projectId){
        return projectService.getProjectJoinTeacher(projectId);
    }

    /**
     * 得到参加项目的学生
     * @param projectId
     * @return
     */
    @GetMapping("/getProjectJoinStudent/{projectId}")
    public List getProjectJoinStudent(@PathVariable Integer projectId){
        return projectService.getProjectJoinStudent(projectId);
    }

    /**
     * 根据学院id来得到模糊查询
     * @param project
     * @param collegeId
     * @return
     */
    @PostMapping("/getMoreSearchProjectByCollege")
    public List<Project> getMoreSearchProjectByCollege(Project project,Integer collegeId){
        List<Project> moreSearchProjectByCollege = projectService.getMoreSearchProjectByCollege(collegeId, project);
        moreSearchProjectByCollege.sort(Comparator.naturalOrder());
        return moreSearchProjectByCollege;
    }

    /**
     * 根据学院id来得到模糊查询
     * @param project
     * @param teacherId
     * @return
     */
    @PostMapping("/getMoreSearchProjectByTeacher")
    public List<Project> getMoreSearchProjectByTeacher(Project project,Integer teacherId){
        Integer collegeId=teacherService.getTeacherById(teacherId).getCollege().getId();

        List<Project> projects = projectService.getMoreSearchProjectByCollege(collegeId, project);
        projects.sort(Comparator.naturalOrder());
        return projects;
    }

    /**
     * 根据学院id来得到模糊查询
     * @param project
     * @param studentId
     * @return
     */
    @PostMapping("/getMoreSearchProjectByStudent")
    public List<Project> getMoreSearchProjectByStudent(Project project,Integer studentId){
        Integer collegeId=studentService.getStudentById(studentId).getCollege().getId();
        List<Project> moreSearchProjectByCollege = projectService.getMoreSearchProjectByCollege(collegeId, project);
        moreSearchProjectByCollege.sort(Comparator.naturalOrder());
        return moreSearchProjectByCollege;
    }

    /**
     * 根据学院id来得到模糊查询
     * @param project
     * @return
     */
    @PostMapping("/getMoreSearchAllProjects")
    public List<Project> getMoreSearchAllProjects(Project project){
        List<Project> moreSearchAllProject = projectService.getMoreSearchAllProject(project);
        moreSearchAllProject.sort(Comparator.naturalOrder());
        return moreSearchAllProject;
    }

    /**
     * 得到学生参与的项目集合
     * @param studentId
     * @return
     */
    @GetMapping("/getStudentProjects/{studentId}")
    public List<Project> getStudentProjects(@PathVariable Integer studentId){
        List<Project> studentProjects = projectService.getStudentProjects(studentId);
        studentProjects.sort(Comparator.naturalOrder());
        return studentProjects;
    }

    /**
     * 得到教师领队的项目
     * @param teacherId
     * @return
     */
    @GetMapping("/getTeacherJoinProjects/{teacherId}")
    public List<Project> getTeacherJoinProjects(@PathVariable Integer teacherId){
        List<Project> teacherJoinProjects = projectService.getTeacherJoinProjects(teacherId);
        teacherJoinProjects.sort(Comparator.naturalOrder());
        return teacherJoinProjects;
    }

    /**
     * 解散项目组
     * @param projectId
     * @return
     */
    @GetMapping("/invokeProject/{projectId}")
    public ProResult invokeProject(@PathVariable Integer projectId) {
        return teacherService.invokeProject(projectId);
    }

}
