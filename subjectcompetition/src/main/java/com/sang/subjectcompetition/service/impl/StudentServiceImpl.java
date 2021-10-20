package com.sang.subjectcompetition.service.impl;

import com.sang.subjectcompetition.entity.*;
import com.sang.subjectcompetition.entity.resultInfo.ProResult;
import com.sang.subjectcompetition.entity.resultInfo.UserInfo;
import com.sang.subjectcompetition.respository.StudentRepository;
import com.sang.subjectcompetition.service.*;
import com.sang.subjectcompetition.service.util.UpdateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private Student_ProjectService student_projectService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private MessageService messageService;

    //基本操作------------------------------------------------------------------------

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteStudentById(Integer id) {
        studentRepository.deleteById(id);
    }

    //业务逻辑--------------------------------------------------------------------------

    /**
     * 更新学生信息
     * @param student
     * @return
     */
    @Override
    public UserInfo updateStudent(Student student) {
        UserInfo userInfo=new UserInfo();
        Student student_db = getStudentById(student.getId());
        UpdateUtil.copyNullProperties(student,student_db);
        Student save = this.save(student_db);
        if (save!=null){
            userInfo.setCode(200);
            userInfo.setData(save);
            userInfo.setMessage("信息更改成功！");
            return userInfo;
        }else {
            userInfo.setCode(400);
            userInfo.setMessage("信息更改失败！");
            return userInfo;
        }
    }

    /**
     * 得到本院系全部学生信息
     * @param collegeId
     * @return
     */
    @Override
    public List<Student> getAllStudents(Integer collegeId) {
        List<Student> studentList = studentRepository.getStudentsByCollege_Id(collegeId);
        return studentList;
    }

    /**
     * 学生申请加入项目
     * @param studentId
     * @param projectId
     * @return
     */
    @Override
    public ProResult applyJoinProject(Integer studentId, Integer projectId) {
        Student student = getStudentById(studentId);
        Project project = projectService.getProjectById(projectId);
        Teacher createByTeacher = project.getCreateByTeacher();
        ProResult proResult=new ProResult();
        Integer state = project.getComp().getCompState();
        if(state==0){
            proResult.setCode(400);
            proResult.setMessage("比赛正在规划中");
            return proResult;
        }else if(state==2){
            proResult.setCode(400);
            proResult.setMessage("比赛已经截止报名");
            return proResult;
        }else if(state==3){
            proResult.setCode(400);
            proResult.setMessage("比赛正在进行中");
            return proResult;
        }else if(state!=1){
            proResult.setCode(400);
            proResult.setMessage("比赛已经结束！");
            return proResult;
        }
        if (project.getComp().getMaxCountStudent()<=project.getCountStudent()){
            proResult.setCode(400);
            proResult.setMessage("人员已满！");
            return proResult;
        }
        if(ifApplyTheSameComp(studentId,projectId)){
            proResult.setCode(400);
            proResult.setMessage("您已申请该比赛");
            return proResult;
        }
        //-----------如果已经申请过了参加该项目  走下面的if
        Student_Project studentProjectById = student_projectService.getStudentProjectById(studentId, projectId);
        if(studentProjectById!=null){//有申请记录
            if (studentProjectById.getApplyOrJoin()==1|studentProjectById.getApplyOrJoin()==4){
                proResult.setCode(400);
                proResult.setMessage("您已申请");
                return proResult;
            }else if(studentProjectById.getApplyOrJoin()==3){
                proResult.setCode(400);
                proResult.setMessage("该比赛您已参加");
                return proResult;
            }else {//驳回的情况
                studentProjectById.setUpdateApplyDate(new Date());
                studentProjectById.setApplyOrJoin(4);
                proResult.setCode(200);
                proResult.setMessage("重新申请已发送！");
                Message message=new Message();
                message.setReceiver(createByTeacher.getTargetId());
                message.setSender(student.getTargetId());
                message.setSenderRole(0);
                message.setReceiverRole(1);
                message.setTitle("你有新的成员想加入");
                message.setContent(student.getNickname()+"同学，用户名："+student.getUsername()
                        +"申请加入"+" 项目:"+project.getProjectName()+"所属比赛为："+project.getComp().getCompName());
                message.setType(0);
                message.setMsgDate(new Date());
                messageService.save(message);
                return proResult;
            }
        }else{//第一次申请加入项目
            Student_Project student_project=new Student_Project();
            student_project.setStudent(student);
            student_project.setApplyDate(new Date());
            student_project.setUpdateApplyDate(new Date());
            student_project.setProject(project);
            student_project.setApplyOrJoin(1);
            Student_Project save = student_projectService.save(student_project);
            if(save!=null){
                proResult.setCode(200);
                proResult.setMessage("申请已发出！");
                Message message=new Message();
                message.setReceiver(createByTeacher.getTargetId());
                message.setSender(student.getTargetId());
                message.setSenderRole(0);
                message.setReceiverRole(1);
                message.setTitle("你有新的成员想加入");
                message.setContent(student.getNickname()+"同学，用户名："+student.getUsername()
                        +"申请加入"+" 项目:"+project.getProjectName()+"所属比赛为："+project.getComp().getCompName());
                message.setType(0);
                message.setMsgDate(new Date());
                messageService.save(message);
                return proResult;
            }else {
                proResult.setCode(400);
                proResult.setMessage("申请失败！");
                return proResult;
            }
        }
    }

    /**
     * 重新申请学生加入项目
     * @param student_projectId
     * @return
     */
    @Override
    public ProResult reApplyJoinProject(Integer student_projectId) {
        ProResult proResult=new ProResult();
        Student_Project studentProjectById = student_projectService.getStudentProjectById(student_projectId);
        Integer compState = studentProjectById.getProject().getComp().getCompState();
        if(compState>0){
            proResult.setCode(400);
            proResult.setMessage("比赛已经开始，无法申请！");
            return proResult;
        }
        if(studentProjectById.getApplyOrJoin()!=2){
            proResult.setCode(400);
            proResult.setMessage("状态异常");
            return proResult;
        }else{
            studentProjectById.setUpdateApplyDate(new Date());
            studentProjectById.setApplyOrJoin(4);
            proResult.setCode(200);
            proResult.setMessage("重新加入项目的申请发送成功！");
            return proResult;
        }
    }

    /**
     * 学生撤销申请
     * @param studentProjectId
     * @return
     */
    @Override
    public ProResult invokeApply(Integer studentProjectId) {
        ProResult proResult=new ProResult();
        Student_Project studentProject = student_projectService.getStudentProjectById(studentProjectId);
        Student student = studentProject.getStudent();
        Project project = studentProject.getProject();
        Teacher createByTeacher = project.getCreateByTeacher();
        Integer compState = studentProject.getProject().getComp().getCompState();
        if(compState==2){
            proResult.setCode(400);
            proResult.setMessage("报名结束，无法撤销！");
            return proResult;
        }else if(compState==3|compState==4){
            proResult.setCode(400);
            proResult.setMessage("比赛开始，无法撤销！");
            return proResult;
        }else {
            Integer state = studentProject.getApplyOrJoin();
            if(state==3){
                proResult.setCode(400);
                proResult.setMessage("您已加入！");
                return proResult;
            }else {
                student_projectService.deleteById(studentProjectId);
                proResult.setCode(200);
                proResult.setMessage("撤销成功！");
                return proResult;
            }
        }
    }

    /**
     * 学生退出项目组
     * @param studentId
     * @param projectId
     * @return
     */
    @Override
    public ProResult exitProject(Integer studentId, Integer projectId) {
        ProResult proResult=new ProResult();
        Student student = getStudentById(studentId);
        Student_Project student_project=student_projectService.getStudentProjectById(studentId,projectId);
        Project project = projectService.getProjectById(projectId);
        Teacher createByTeacher = project.getCreateByTeacher();
        Integer compState = project.getComp().getCompState();
        if(compState!=1){
            proResult.setCode(400);
            proResult.setMessage("报名已截止，无法退出");
            return proResult;
        }
        try{
            student_projectService.deleteById(student_project.getId());
            project.setCountStudent(project.getCountStudent()-1);
            proResult.setCode(200);
            proResult.setMessage("退出成功！");
            Message message=new Message();
            message.setReceiver(createByTeacher.getTargetId());
            message.setSender(student.getTargetId());
            message.setSenderRole(0);
            message.setReceiverRole(1);
            message.setTitle("你有成员退出项目团队");
            message.setContent(student.getNickname()+"同学，用户名："+student.getUsername()
                    +"退出"+" 项目:"+project.getProjectName()+"所属比赛为："+project.getComp().getCompName());
            message.setType(0);
            message.setMsgDate(new Date());
            messageService.save(message);
            return proResult;
        }catch (Exception e){
            proResult.setCode(400);
            proResult.setMessage("退出失败！");
            return proResult;
        }
    }

    /**
     * 查看同组的所有学生
     * @param projectId
     * @return
     */
    @Override
    public List<Student> getTheStudents(Integer projectId) {
        List<Student> studentList=new ArrayList<>();
        List<Student_Project> allStudentByProject = student_projectService.getAllStudentByProject(projectId);
        for (Student_Project studentProject : allStudentByProject) {
            if(studentProject.getApplyOrJoin()==3) {
                Student student = studentProject.getStudent();
                studentList.add(student);
            }
        }
        return studentList;
    }

    /**
     * 根据项目id查看指导老师集合
     * @param projectId
     * @return
     */
    @Override
    public List<Teacher> getTheTeachers(Integer projectId) {
        return teacherService.getAllTeachersByProject(projectId);
    }

    //判断是否申请过这个比赛
    @Override
    public Boolean ifJoinTheSameComp(Integer studentId,Integer projectId){
        int flag=0;
        //得到这个比赛的所有项目
        List<Project> projects=projectService.getProjectById(projectId).getComp().getProjects();
        //得到学生所有的比赛项目
        List<Student_Project> student_projects=student_projectService.getStudentAllProject(studentId);
        for (Student_Project student_project : student_projects) {
            for (Project project : projects) {
                if (project.getId()==student_project.getProject().getId()){
                    if (student_project.getApplyOrJoin()!=2) {
                        if(flag==1)return true;
                        flag=1;//表示存在
                    }
                }
            }
        }
        return false;
    }

    //判断是否申请过这个比赛
    @Override
    public Boolean ifApplyTheSameComp(Integer studentId,Integer projectId){
        int flag=0;
        //得到这个比赛的所有项目
        List<Project> projects=projectService.getProjectById(projectId).getComp().getProjects();
        //得到学生所有的比赛项目
        List<Student_Project> student_projects=student_projectService.getStudentAllProject(studentId);
        for (Student_Project student_project : student_projects) {
            for (Project project : projects) {
                if (project.getComp().getId()==student_project.getProject().getComp().getId()){
                    if (student_project.getApplyOrJoin()!=2) {
                        if(flag==1)return true;
                        flag=1;//表示存在
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Student findStudentByOppenId(String oppenId) {
        return studentRepository.findStudentByOpenId(oppenId);
    }

    @Override
    public Student findStudentByUsernameAndPassword(String username,String password){
        return studentRepository.findStudentByUsernameAndPassword(username,password);
    }
}
