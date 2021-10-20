package com.sang.subjectcompetition.controller;

import com.sang.subjectcompetition.entity.*;
import com.sang.subjectcompetition.entity.resultInfo.CompResult;
import com.sang.subjectcompetition.entity.resultInfo.ProResult;
import com.sang.subjectcompetition.entity.resultInfo.UserInfo;
import com.sang.subjectcompetition.service.*;
import com.sang.subjectcompetition.service.util.UploadFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private Student_ProjectService student_projectService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UploadFileUtils uploadFileUtils;

    @Autowired
    private WorkFileService workFileService;

    @Autowired
    private WorkService workService;

    private static String OS = System.getProperty("os.name").toLowerCase();

    @Value("${file-upload.image.winUrl}")
    private String winUrl;

    @Value("${file-upload.image.linuxUrl}")
    private String linuxUrl;

    /**
     * 根据老师id得到院内的比赛项目
     */
    @GetMapping("/getProjectByCollegeWithStudent/{studentId}")
    public List<Project> getProjectByCollegeWithStudent(@PathVariable Integer studentId){

        List<Project> projectByCollegeWithStudent = projectService.getProjectByCollegeWithStudent(studentId);
        projectByCollegeWithStudent.sort(Comparator.naturalOrder());
        return projectByCollegeWithStudent;
    }


    /**
     * 学生申请加入项目
     * @param projectId
     * @param studentId
     * @return
     */
    @GetMapping("/applyJoinProject/{projectId}/{studentId}")
    public ProResult applyJoinProject(@PathVariable Integer projectId,@PathVariable Integer studentId){
        return studentService.applyJoinProject(studentId,projectId);
    }

    /**
     * 学生重新申请加入项目
     * @param student_projectd
     * @return
     */
    @GetMapping("/reApplyJoinProject/{student_projectd}")
    public ProResult reApplyJoinProject(@PathVariable Integer student_projectd){
        Integer studentId=student_projectService.getStudentProjectById(student_projectd).getStudent().getId();
        Integer projectId=student_projectService.getStudentProjectById(student_projectd).getProject().getId();
        return studentService.applyJoinProject(studentId,projectId);
    }

    /**
     * 根据学生的id返回学生的未处理的请求
     * @param studentId
     * @return
     */
    @GetMapping("/getUndoApplyProjectList/{studentId}")
    public List<Student_Project> getUndoApplyProjectList(@PathVariable Integer studentId){
        List<Student_Project> undoApplyProjectList = student_projectService.getUndoApplyProjectList(studentId);
        undoApplyProjectList.sort(Comparator.naturalOrder());
        return undoApplyProjectList;
    }

    /**
     * 根据学生的id返回学生的处理过的请求
     * @param studentId
     * @return
     */
    @GetMapping("/getDoneApplyProjectList/{studentId}")
    public List<Student_Project> getDoneApplyProjectList(@PathVariable Integer studentId){
        List<Student_Project> doneApplyProjectList = student_projectService.getDoneApplyProjectList(studentId);
        doneApplyProjectList.sort(Comparator.naturalOrder());
        return doneApplyProjectList;
    }

    /**
     * 得到学生信息
     * @param studentId
     * @return
     */
    @GetMapping("/getStudentInfoById/{studentId}")
    public Student getStudentInfoById(@PathVariable Integer studentId){
        return studentService.getStudentById(studentId);
    }

    /**
     * 更新学生信息
     * @param student
     * @return
     */
    @PostMapping("/updateStudent")
    public UserInfo updateStudent(Student student){
        Integer studentId=student.getId();
        return studentService.updateStudent(student);
    }

    @GetMapping("/exitProject/{studentId}/{projectId}")
    public ProResult exitProject(@PathVariable Integer studentId, @PathVariable Integer projectId){
        return studentService.exitProject(studentId,projectId);
    }

    @PostMapping("/workUpload")
    public ProResult workUpload(@RequestParam(value = "uploadFile") MultipartFile[] uploadFile, Work work,Integer projectId){
        ProResult proResult=new ProResult();
        if(uploadFile==null){
            proResult.setCode(400);
            proResult.setMessage("请添加作品附件！");
            return proResult;
        }
        Project project = projectService.getProjectById(projectId);
        if(project.getWork()!=null){
            work.setId(project.getWork().getId());
        }else {
            work.setApplyDate(new Date());
        }
        String infoPath="/"+project.getCreateByTeacher().getCollege().getNickname()+"/"+project.getComp().getCompName()+"/"+project.getProjectName()+"/";
        List<WorkFile> workFiles = uploadFileUtils.uploadFile(uploadFile, infoPath);
        try {
            work.setIfMark(false);
            work.setProject(project);
            work = workService.save(work);
            for (WorkFile workFile : workFiles) {
                WorkFile workFileById = workFileService.findWorkFileById(workFile.getId());
                workFileById.setWork(work);
                workFileService.save(workFile);
            }
            proResult.setCode(200);
            proResult.setMessage("提交成功！");
        }catch (Exception e){
            proResult.setCode(400);
            proResult.setMessage("提交失败！");
        }
        return proResult;
    }

    @PostMapping("/editWork")
    public ProResult editWork(@RequestParam(value = "workFile") MultipartFile workFile,Integer workFileId) throws FileNotFoundException {
        ProResult proResult=new ProResult();
        String rootBean;
        if(OS.startsWith("win")){
            rootBean=winUrl;
        }else {
            rootBean=linuxUrl;
        }
        WorkFile workFileById = workFileService.findWorkFileById(workFileId);
        Project project = workFileById.getWork().getProject();
        String fileUrl = workFileById.getFileUrl();
        File fileDelete=new File(rootBean+fileUrl);
        fileDelete.delete();
        try {
            String infoPath = "/" + project.getCreateByTeacher().getCollege().getNickname() + "/" + project.getComp().getCompName() + "/" + project.getProjectName() + "/";
            String workPath = uploadFileUtils.uploadWorkFile(workFile, infoPath);
            workFileById.setFileName(workFile.getOriginalFilename());
            workFileById.setFileUrl(workPath);
            workFileService.save(workFileById);
            proResult.setCode(200);
            proResult.setMessage("修改成功！");
            return proResult;
        }catch (Exception e){
            proResult.setCode(400);
            proResult.setMessage("修改失败！");
            return proResult;
        }
    }

    @GetMapping("/deleteWorkFile/{workFileId}")
    public ProResult deleteWorkFile(@PathVariable Integer workFileId){
        ProResult proResult=new ProResult();
        String rootBean;
        if(OS.startsWith("win")){
            rootBean=winUrl;
        }else {
            rootBean=linuxUrl;
        }
        WorkFile workFileById = workFileService.findWorkFileById(workFileId);
        Work work = workFileById.getWork();
        String fileUrl = workFileById.getFileUrl();
        File file=new File(rootBean+fileUrl);
        try {
            file.delete();
            workFileService.delete(workFileId);
            proResult.setCode(200);
            proResult.setMessage("删除成功！");
            return proResult;
        }catch (Exception e){
            proResult.setCode(400);
            proResult.setMessage("删除失败！");
            return proResult;
        }



    }

    /**
     * 没有提交做作品的
     * @param studentId
     * @return
     */
    @GetMapping("/getUnHandOutWorks/{studentId}")
    public List<Project> getUnHandOutWorks(@PathVariable Integer studentId){
        List<Project> projectList=new ArrayList<>();
        List<Project> projectByCollegeWithStudent = projectService.getStudentProjects(studentId);
        for (Project project : projectByCollegeWithStudent) {
            Integer compState = project.getComp().getCompState();
            if(compState<3&&project.getWork()==null){
                projectList.add(project);
            }
        }
        return projectList;
    }

    /**
     * 提交过作品的
     * @param studentId
     * @return
     */
    @GetMapping("/getHandOutWorks/{studentId}")
    public List<Work> getHandOutWorks(@PathVariable Integer studentId){
        List<Work> workList=new ArrayList<>();
        List<Project> projectByCollegeWithStudent = projectService.getStudentProjects(studentId);
        for (Project project : projectByCollegeWithStudent) {
            Integer compState = project.getComp().getCompState();
            if(project.getWork()!=null){
                if(project.getWork().getIfMark()==false)
                    workList.add(project.getWork());
            }
        }
        return workList;
    }

    /**
     * 提交过作品的
     * @param studentId
     * @return
     */
    @GetMapping("/getWorksMark/{studentId}")
    public List<Work> getWorksMark(@PathVariable Integer studentId){
        List<Work> workList=new ArrayList<>();
        List<Project> projectByCollegeWithStudent = projectService.getStudentProjects(studentId);
        for (Project project : projectByCollegeWithStudent) {
            Integer compState = project.getComp().getCompState();
            if(project.getWork()!=null){
                if(project.getWork().getIfMark()==true)
                    workList.add(project.getWork());
            }
        }
        return workList;
    }
}
