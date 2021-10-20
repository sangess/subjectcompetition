package com.sang.subjectcompetition.controller;

import com.sang.subjectcompetition.entity.*;
import com.sang.subjectcompetition.entity.resultInfo.ProResult;
import com.sang.subjectcompetition.entity.resultInfo.UserInfo;
import com.sang.subjectcompetition.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private Teacher_ProjectService teacher_projectService;

    @Autowired
    private ProjectService projectService;

    /**
     * 得到老师的信息
     * @param teacherId
     * @return
     */
    @GetMapping("/getUserTeacherById/{teacherId}")
    public Teacher getUserTeacherById(@PathVariable Integer teacherId){
        return teacherService.getTeacherById(teacherId);
    }

    /**
     * 修改教师信息
     * @param teacher
     * @return
     */
    @PostMapping("/updateTeacher")
    public UserInfo updateTeacher(Teacher teacher){
        return teacherService.updateTeacher(teacher);
    }

    /**
     * 根据老师获得本学院的竞赛
     * @param teacherId
     * @return
     */
    @GetMapping("/getCollegeCompListByTeacherId/{teacherId}")
    public List<Map<String ,Object>> getCollegeCompListByTeacherId(@PathVariable Integer teacherId){
        return teacherService.getCollegeCompApplyStateListByTeacherId(teacherId);
    }

    /**
     * 教师申请创建项目
     * @param compId
     * @param teacherId
     * @param project
     * @return
     */
    @PostMapping("/applyJoinComp")
    public ProResult applyProject(@RequestParam Integer compId, @RequestParam Integer teacherId, Project project){
        return teacherService.applyJoinComp(teacherId,compId,project);
    }

    /**
     * 得到未处理的学院和教师之间的关系
     * @param teacherId
     * @return
     */
    @GetMapping("/getUndoCollgeApplyList/{teacherId}")
    public List<Teacher_Project> getUndoCollgeApplyList(@PathVariable Integer teacherId){
        List<Teacher_Project> undoCollgeApplyList = teacher_projectService.getUndoCollgeApplyList(teacherId);
        undoCollgeApplyList.sort(Comparator.naturalOrder());
        return undoCollgeApplyList;
    }

    /**
     * 得到处理过的学院和教师之间的关系
     * @param teacherId
     * @return
     */
    @GetMapping("/getDoneCollgeApplyList/{teacherId}")
    public List<Teacher_Project> getDoneCollgeApplyList(@PathVariable Integer teacherId){
        List<Teacher_Project> doneCollgeApplyList = teacher_projectService.getDoneCollgeApplyList(teacherId);
        doneCollgeApplyList.sort(Comparator.naturalOrder());
        return doneCollgeApplyList;
    }

    /**
     * 通过id获取项目
     * @param projectId
     * @return
     */
    @GetMapping("/getProjectById/{projectId}")
    public Project getProjectById(@PathVariable Integer projectId){
        return projectService.getProjectById(projectId);
    }

    /**
     * 根据老师获得没有处理的学生加入项目的申请
     */
    @GetMapping("/getUndoApplyProjectStudentList/{teacherId}")
    public List<Student_Project> getUndoApplyProjectStudentList(@PathVariable Integer teacherId){
        List<Student_Project> undoApplyProjectStudentList = teacher_projectService.getUndoApplyProjectStudentList(teacherId);
        undoApplyProjectStudentList.sort(Comparator.naturalOrder());
        return undoApplyProjectStudentList;
    }

    /**
     * 根据老师获得处理过的学生加入项目的申请
     */
    @GetMapping("/getDoneApplyProjectStudentList/{teacherId}")
    public List<Student_Project> getDoneApplyProjectStudentList(@PathVariable Integer teacherId){
        List<Student_Project> doneApplyProjectStudentList = teacher_projectService.getDoneApplyProjectStudentList(teacherId);
        doneApplyProjectStudentList.sort(Comparator.naturalOrder());
        return doneApplyProjectStudentList;
    }

    /**
     * 同意学生加入项目
     */
    @GetMapping("/applyStudentJoinProject/{student_projectId}")
    public ProResult applyStudentJoinProject(@PathVariable Integer student_projectId){
        return teacherService.applyStudentJoin(student_projectId);
    }
    /**
     * 同意学生加入项目
     */
    @GetMapping("/refuseStudentJoinProject/{student_projectId}")
    public ProResult refuseStudentJoinProject(@PathVariable Integer student_projectId){
        return teacherService.refuseStudentJoin(student_projectId);
    }

    /**
     * 根据老师id得到院内的比赛项目
     */
    @GetMapping("/getProjectByCollegeWithTeacher/{teacherId}")
    public List<Teacher_Project> getProjectByCollegeWithTeacher(@PathVariable Integer teacherId){
        List<Teacher_Project> teacher_projects=new ArrayList<>();
        College college=teacherService.getTeacherById(teacherId).getCollege();
        List<Teacher> teachers = college.getTeachers();
        for (Teacher teacher : teachers) {
            List<Teacher_Project> teacherProjectsByTeacherAndIsCreator = teacher_projectService.getTeacherProjectsByTeacherAndIsCreator(teacher.getId(), true);
            if (teacherProjectsByTeacherAndIsCreator.size()>=0){
                teacher_projects.addAll(teacherProjectsByTeacherAndIsCreator);
            }
        }

        List<Project> projectByCollegeWithTeacher = projectService.getProjectByCollegeWithTeacher(teacherId);
        teacher_projects.sort(Comparator.naturalOrder());
        return teacher_projects;
    }

    /**
     * 老师申请加入项目
     */
    @GetMapping("/teacherApplyJoinProject/{teacherprojectId}/{teacherId}")
    public ProResult teacherApplyJoinProject(@PathVariable Integer teacherprojectId,@PathVariable Integer teacherId){
        return teacherService.teacherApplyJoinProject(teacherprojectId,teacherId);
    }

    /**
     * 得到老师之间未处理的申请信息
     */
    @GetMapping("/getUndoApplyProjectTeacherList/{teacherId}")
    public List<Teacher_Project> getUndoApplyProjectTeacherList(@PathVariable Integer teacherId){
        List<Teacher_Project> undoApplyProjectTeacherList = teacher_projectService.getUndoApplyProjectTeacherList(teacherId);
        undoApplyProjectTeacherList.sort(Comparator.naturalOrder());
        return undoApplyProjectTeacherList;
    }

    /**
     * 得到老师之间处理过的申请信息
     */
    @GetMapping("/getDoneApplyProjectTeacherList/{teacherId}")
    public List<Teacher_Project> getDoneApplyProjectTeacherList(@PathVariable Integer teacherId){
        List<Teacher_Project> doneApplyProjectTeacherList = teacher_projectService.getDoneApplyProjectTeacherList(teacherId);
        doneApplyProjectTeacherList.sort(Comparator.naturalOrder());
        return doneApplyProjectTeacherList;
    }

    /**
     * 同意老师加入项目
     * @param teacher_projectId
     * @return
     */
    @GetMapping("/applyTeacherJoinProject/{teacher_projectId}")
    public ProResult applyTeacherJoinProject(@PathVariable Integer teacher_projectId){
        return teacherService.applyTeacherJoinProject(teacher_projectId);
    }

    /**
     * 拒绝老师加入项目
     * @param teacher_projectId
     * @return
     */
    @GetMapping("/refuseTeacherJoinProject/{teacher_projectId}")
    public ProResult refuseTeacherJoinProject(@PathVariable Integer teacher_projectId){
        return teacherService.refuseTeacherJoinProject(teacher_projectId);
    }

    /**
     * 老师重复申请加入项目
     * @param teacher_projectId
     * @return
     */
    @GetMapping("/teacherReApplyJoinProject/{teacher_projectId}")
    public ProResult teacherReApplyJoinProject(@PathVariable Integer teacher_projectId){
        return teacherService.teacherReApplyJoinProject(teacher_projectId);
    }

    /**
     * 移出老师
     * @param teacherId
     * @return
     */
    @GetMapping("/exitTeacher/{teacherId}/{projectId}")
    public ProResult exitTeacher(@PathVariable Integer teacherId,@PathVariable Integer projectId){
        return teacherService.exitTeacher(teacherId,projectId);
    }

    /**
     * 移出学生
     * @param studentId
     * @return
     */
    @GetMapping("/exitStudent/{studentId}/{projectId}")
    public ProResult exitStudent(@PathVariable Integer studentId,@PathVariable Integer projectId){
        return teacherService.exitStudent(studentId,projectId);
    }

    /**
     * 教师退出项目组
     * @param teacherId
     * @param projectId
     * @return
     */
    @GetMapping("/exitProject/{teacherId}/{projectId}")
    public ProResult exitProject(@PathVariable Integer teacherId,@PathVariable Integer projectId){
        return teacherService.exitProject(teacherId,projectId);
    }
}
