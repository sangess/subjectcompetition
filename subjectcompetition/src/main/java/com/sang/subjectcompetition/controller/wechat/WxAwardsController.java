package com.sang.subjectcompetition.controller.wechat;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sang.subjectcompetition.entity.*;
import com.sang.subjectcompetition.entity.resultInfo.ProResult;
import com.sang.subjectcompetition.respository.StudentRepository;
import com.sang.subjectcompetition.respository.Student_ProjectRepository;
import com.sang.subjectcompetition.service.AwardsService;
import com.sang.subjectcompetition.service.ProjectService;
import com.sang.subjectcompetition.service.StudentService;
import com.sang.subjectcompetition.service.util.UploadFileUtils;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/wx")
@ConfigurationProperties(prefix = "apis")
public class WxAwardsController {

    @Value("${apis.dev-url}")
    private String devUrl;

    @Value("${apis.url}")
    private String url;

    @Autowired
    private AwardsService awardsService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private Student_ProjectRepository student_projectRepository;

    @Autowired
    private UploadFileUtils uploadFileUtils;

    @PostMapping("/applyAwards")
    public ProResult applyAwards(Awards awards, Integer projectId, MultipartFile image) throws IOException {
        ProResult proResult=new ProResult();
        System.out.println(image==null?"空":"非空");
        Project project = projectService.getProjectById(projectId);
        awards.setProject(project);
        String awardsInfoPath="/"+project.getCreateByTeacher().getCollege().getNickname()+"/"+project.getComp().getCompName()+"/"+project.getProjectName()+"/";
        String path = uploadFileUtils.uploadFile(image, awardsInfoPath);
        if(path==null){
            proResult.setCode(400);
            proResult.setMessage("提交文件失败");
        }else{
            awards.setImageUrl(path);
            proResult=awardsService.save(awards);
        }
        return proResult;
    }

    @GetMapping("/studentProjects")
    public JSONArray studentProjects(@RequestParam Integer studentId){
        List<Student_Project> applyStudentProjectList = student_projectRepository.getApplyStudentProjectList(studentId, 3);
        JSONArray jsonArray=new JSONArray();
        for (Student_Project student_project : applyStudentProjectList) {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("compName",student_project.getProject().getComp().getCompName());
            jsonObject.put("compState",student_project.getProject().getComp().getCompState());
            jsonObject.put("id",student_project.getProject().getId());
            jsonObject.put("projectName",student_project.getProject().getProjectName());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @GetMapping("/getStudentProjects")
    public JSONArray getStudentProjects(@RequestParam Integer studentId){
        List<Student_Project> applyStudentProjectList = student_projectRepository.getApplyStudentProjectList(studentId, 3);
        JSONArray jsonArray=new JSONArray();
        for (Student_Project student_project : applyStudentProjectList) {
            if(student_project.getProject().getComp().getCompState()>=3&&student_project.getProject().getAwards()==null){
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("compName",student_project.getProject().getComp().getCompName());
                jsonObject.put("compState",student_project.getProject().getComp().getCompState());
                jsonObject.put("id",student_project.getProject().getId());
                jsonObject.put("projectName",student_project.getProject().getProjectName());
                jsonArray.add(jsonObject);
            }
        }
        return jsonArray;
    }

    @GetMapping("/getStudentAwardsInfo")
    public JSONObject getStudentAwardsInfo(@RequestParam Integer studentId){
        Student student = studentService.getStudentById(studentId);
        Integer score=student.getScore();
        Integer compCount=student.getCompCount();
        List<Student> all = studentRepository.findAll();
        all.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if(o1.getScore()<o2.getScore())return 1;
                else if(o1.getScore()==o2.getScore()){
                    if(o1.getCompCount()<o2.getCompCount())return 1;
                    else if(o1.getCompCount()==o2.getCompCount()){
                        return o1.getId()-o2.getId()<0?1:-1;
                    }else return -1;
                }else return -1;
            }
        });
        int list=all.indexOf(student)+1;
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("score",score);
        jsonObject.put("list",list);
        jsonObject.put("compCount",compCount);
        return jsonObject;
    }

    @GetMapping("/getStudentAwards")
    public JSONArray getStudentAwards(@RequestParam Integer studentId){
        JSONArray jsonArray=new JSONArray();
        List<Project> studentProjects = projectService.getStudentProjects(studentId);
        for (Project project : studentProjects) {
            if(project.getAwards()!=null) {
                if (project.getAwards().getState() == 2) {
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put("compName",project.getComp().getCompName());
                    jsonObject.put("level",project.getAwards().getLevel());
                    jsonObject.put("content",project.getAwards().getContent());
                    jsonObject.put("type",project.getAwards().getType());
                    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
                    jsonObject.put("awardsTime",dateFormat.format(project.getAwards().getApplyDate()));
                    jsonObject.put("imageUrl",devUrl+project.getAwards().getImageUrl());
                    JSONArray teachers=new JSONArray();
                    JSONArray students=new JSONArray();
                    List<Teacher> teacherList = projectService.getProjectJoinTeacher(project.getId());
                    List<Student> studentList = projectService.getProjectJoinStudent(project.getId());
                    for (Teacher teacher : teacherList) {
                        JSONObject object=new JSONObject();
                        object.put("teacherName",teacher.getNickname());
                        teachers.add(object);
                    }
                    for (Student student : studentList) {
                        JSONObject object=new JSONObject();
                        object.put("studentName",student.getNickname());
                        students.add(object);
                    }
                    jsonObject.put("teachers",teachers);
                    jsonObject.put("students",students);
                    jsonArray.add(jsonObject);
                }
            }
        }
        return jsonArray;
    }
}
