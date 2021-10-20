package com.sang.subjectcompetition.controller.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sang.subjectcompetition.entity.*;
import com.sang.subjectcompetition.entity.resultInfo.ProResult;
import com.sang.subjectcompetition.service.CompService;
import com.sang.subjectcompetition.service.ProjectService;
import com.sang.subjectcompetition.service.StudentService;
import com.sang.subjectcompetition.service.Student_ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/wx")
public class WxCompController {

    @Autowired
    private CompService compService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private Student_ProjectService student_projectService;

    @GetMapping("/allComps")
    public JSONArray getAllcomps(){
        List<Comp> allComps = compService.getAllComps();
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(allComps));
        return  array;
    }

    @GetMapping("/myComps")
    public JSONArray getMycomps(@RequestParam Integer studentId,
                                @RequestParam String skey){

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        JSONArray array=new JSONArray();
        List<Project> projects = projectService.getStudentProjects(studentId);
        for (Project project : projects) {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("projectId",project.getId());
            jsonObject.put("teacherName",project.getCreateByTeacher().getNickname());
            jsonObject.put("teacherId",project.getCreateByTeacher().getId());
            jsonObject.put("projectName",project.getProjectName());
            jsonObject.put("compState",project.getComp().getCompState());
            jsonObject.put("compLevel",project.getComp().getLevel());
            Date occurrenceTime = project.getComp().getOccurrenceTime();
            jsonObject.put("compTime",dateFormat.format(occurrenceTime));
            jsonObject.put("compName",project.getComp().getCompName());
            jsonObject.put("compId",project.getComp().getId());
            jsonObject.put("compOrganizer",project.getComp().getOrganizer());
            array.add(jsonObject);
        }
        return array;
    }

    @GetMapping("/collegeComps")
    public JSONArray getcollegeComps(@RequestParam Integer studentId,
                                @RequestParam String skey){

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        JSONArray array=new JSONArray();
        List<Project> projects = projectService.getProjectByCollegeWithStudent(studentId);
        projects.sort(Comparator.naturalOrder());
        for (Project project : projects) {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("projectId",project.getId());
            jsonObject.put("teacherName",project.getCreateByTeacher().getNickname());
            jsonObject.put("teacherId",project.getCreateByTeacher().getId());
            jsonObject.put("projectName",project.getProjectName());
            jsonObject.put("compState",project.getComp().getCompState());
            jsonObject.put("compLevel",project.getComp().getLevel());
            Date occurrenceTime = project.getComp().getOccurrenceTime();
            jsonObject.put("compTime",dateFormat.format(occurrenceTime));
            jsonObject.put("compName",project.getComp().getCompName());
            jsonObject.put("compId",project.getComp().getId());
            jsonObject.put("compOrganizer",project.getComp().getOrganizer());
            array.add(jsonObject);
        }


        return array;
    }
    @GetMapping("/myApplyList")
    public JSONArray getmyApplyList(@RequestParam Integer studentId,
                                @RequestParam String skey){

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        JSONArray array=new JSONArray();
        List<Student_Project> projects = student_projectService.getStudentAllProject(studentId);
        for (Student_Project student_Project : projects) {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("projectId",student_Project.getProject().getId());
            jsonObject.put("teacherName",student_Project.getProject().getCreateByTeacher().getNickname());
            jsonObject.put("teacherId",student_Project.getProject().getCreateByTeacher().getId());
            jsonObject.put("projectName",student_Project.getProject().getProjectName());
            jsonObject.put("compState",student_Project.getProject().getComp().getCompState());
            jsonObject.put("applyState",student_Project.getApplyOrJoin());
            jsonObject.put("applyDate",dateFormat.format(student_Project.getApplyDate()));
            jsonObject.put("updateDate",dateFormat.format(student_Project.getUpdateApplyDate()));
            jsonObject.put("compLevel",student_Project.getProject().getComp().getLevel());
            Date occurrenceTime = student_Project.getProject().getComp().getOccurrenceTime();
            jsonObject.put("compTime",dateFormat.format(occurrenceTime));
            jsonObject.put("compName",student_Project.getProject().getComp().getCompName());
            jsonObject.put("compId",student_Project.getProject().getComp().getId());
            jsonObject.put("compOrganizer",student_Project.getProject().getComp().getOrganizer());
            array.add(jsonObject);
        }


        return array;
    }
    @GetMapping("/getProjectInfo")
    public JSONObject getProjectInfo(@RequestParam Integer projectId){

        Project project = projectService.getProjectById(projectId);
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("projectId",project.getId());
        jsonObject.put("teacherName",project.getCreateByTeacher().getNickname());
        jsonObject.put("teacherId",project.getCreateByTeacher().getId());
        jsonObject.put("projectName",project.getProjectName());
        jsonObject.put("compState",project.getComp().getCompState());
        jsonObject.put("compLevel",project.getComp().getLevel());
        Date occurrenceTime = project.getComp().getOccurrenceTime();
        jsonObject.put("compTime",dateFormat.format(occurrenceTime));
        jsonObject.put("compName",project.getComp().getCompName());
        jsonObject.put("compId",project.getComp().getId());
        jsonObject.put("compOrganizer",project.getComp().getOrganizer());
        List<Student> students = studentService.getTheStudents(projectId);
        JSONArray studentsList=new JSONArray();
        for (Student student : students) {
            JSONObject jsonObject1=new JSONObject();
            jsonObject1.put("personName",student.getNickname());
            jsonObject1.put("personUsername",student.getUsername());
            studentsList.add(jsonObject1);
        }
        jsonObject.put("studentList",studentsList);
        List<Teacher> teachers = studentService.getTheTeachers(projectId);
        JSONArray teacherList=new JSONArray();
        for (Teacher teacher : teachers) {
            JSONObject jsonObject1=new JSONObject();
            jsonObject1.put("personName",teacher.getNickname());
            jsonObject1.put("personUsername",teacher.getUsername());
            teacherList.add(jsonObject1);
        }
        jsonObject.put("teacherList",teacherList);
        return jsonObject;
    }

    @GetMapping("/joinProject")
    public ProResult joinProject(@RequestParam Integer studentId,
                                 @RequestParam Integer projectId){
        return studentService.applyJoinProject(studentId,projectId);
    }

    @GetMapping("/exitProject")
    public ProResult exitProject(@RequestParam Integer studentId,
                                 @RequestParam Integer projectId){
        return studentService.exitProject(studentId,projectId);
    }
    @GetMapping("/invokeApply")
    public ProResult invokeApply(@RequestParam Integer studentId,
                                 @RequestParam Integer projectId){
        Integer studentProjectId=student_projectService.getStudentProjectById(studentId,projectId).getId();
        return studentService.invokeApply(studentProjectId);
    }

}
