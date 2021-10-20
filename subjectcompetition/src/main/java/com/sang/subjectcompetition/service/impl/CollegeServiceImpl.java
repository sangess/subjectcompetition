package com.sang.subjectcompetition.service.impl;

import com.sang.subjectcompetition.entity.*;
import com.sang.subjectcompetition.entity.resultInfo.CompResult;
import com.sang.subjectcompetition.entity.resultInfo.ProResult;
import com.sang.subjectcompetition.entity.resultInfo.UserInfo;
import com.sang.subjectcompetition.respository.CollegeRepository;
import com.sang.subjectcompetition.service.*;
import com.sang.subjectcompetition.service.util.UUIDUtil;
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
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private CompService compService;

    @Autowired
    private College_CompService college_compService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private Teacher_ProjectService teacher_projectService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MessageService messageService;

    //基本操作-------------------------------------------------------
    
    @Override
    public UserInfo save(College college) {
        UserInfo userInfo=new UserInfo();
        College college_save = collegeRepository.save(college);
        if(college.getTargetId()==null)college_save.setTargetId(UUID.randomUUID().toString());
        if (college_save!=null){
            userInfo.setCode(200);
            userInfo.setMessage("注册成功！");
            userInfo.setData(college_save);
        }else {
            userInfo.setCode(400);
            userInfo.setMessage("注册失败！");
        }
        return userInfo;
    }

    @Override
    public College getCollegeById(Integer id) {
        College college = collegeRepository.findById(id).orElse(null);
        return college;
    }

    @Override
    public List<College> findAll() {
        return collegeRepository.findAll();
    }

    //业务逻辑---------------------------------------------------------

    /**
     * 更改学院信息
     * @param college
     * @return
     */
    @Override
    public UserInfo updateCollege(College college ) {

        College college_db=collegeRepository.findById(college.getId()).orElse(null);
        if(college_db!=null){
            UpdateUtil.copyNullProperties(college,college_db);
        }
        UserInfo userInfo=new UserInfo();
        UserInfo save = this.save(college_db);
        if (save.getData()!=null){
            userInfo.setCode(200);
            userInfo.setMessage("信息更改成功！");
            userInfo.setData(save.getData());
            return userInfo;
        }else {
            userInfo.setCode(400);
            userInfo.setMessage("信息更改失败！");
            return userInfo;
        }
    }

    //--------------------面向学校管理员-------------------------
    /**
     * 学院申请比赛
     * @param collegeId
     * @param compId
     * @return
     */
    @Override
    public CompResult applyComp(Integer collegeId, Integer compId) {
        CompResult compResult=new CompResult();
        Comp comp = compService.findCompById(compId);
        College college=this.getCollegeById(collegeId);
        if (comp.getCompState()==0){
            compResult.setCode(408);
            compResult.setMessage("比赛正在规划中，无法参与！");
            return compResult;
        }else if(comp.getCompState()==2){
            compResult.setCode(408);
            compResult.setMessage("报名已结束，无法参与！");
            return compResult;
        }else if(comp.getCompState()==3){
            compResult.setCode(408);
            compResult.setMessage("比赛正在进行中，无法参与！");
            return compResult;
        }else if (comp.getCompState()!=1){
            compResult.setCode(408);
            compResult.setMessage("比赛已结束，无法参与！");
            return compResult;
        }
        //拿到关系   是否有已经申请
        College_Comp collegeComp1 = college_compService.getCollegeComp(collegeId, compId);
        if(collegeComp1!=null){
            if(collegeComp1.getApplyOrJoin()==1||collegeComp1.getApplyOrJoin()==4){
                compResult.setCode(400);
                compResult.setMessage("已经申请该比赛，静待通知！");
                return compResult;
            }else if(collegeComp1.getApplyOrJoin()==3){
                compResult.setCode(400);
                compResult.setMessage("您申请的比赛审核已经通过！");
                return compResult;
            }else{
                collegeComp1.setUpdateApplyDate(new Date());
                collegeComp1.setApplyOrJoin(4);//1表示重新申请
                college_compService.save(collegeComp1);
                compResult.setCode(200);
                compResult.setMessage("已重新发出申请");
                Message message=new Message();
                message.setReceiver("3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad");
                message.setSender(college.getTargetId());
                message.setSenderRole(2);
                message.setReceiverRole(3);
                message.setTitle("您有新的比赛申请");
                message.setContent(college.getNickname()+"正在申请比赛："+comp.getCompName()+",请尽快处理！");
                message.setType(0);
                message.setMsgDate(new Date());
                messageService.save(message);
                return compResult;
            }
        }else {//没有二者的关系   建立联系
            College_Comp collegeComp=new College_Comp();
            collegeComp.setCollege(college);
            collegeComp.setComp(comp);
            College_Comp college_comp = college_compService.save(collegeComp);
            college_comp.setApplyDate(new Date());
            college_comp.setApplyOrJoin(1);//1表示申请
            college_compService.save(college_comp);
            if(college_comp!=null){
                compResult.setCode(201);
                compResult.setMessage("申请已发送！");
                Message message=new Message();
                message.setReceiver("3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad");
                message.setSender(college.getTargetId());
                message.setSenderRole(2);
                message.setReceiverRole(3);
                message.setTitle("您有新的比赛申请");
                message.setContent(college.getNickname()+"正在申请比赛："+comp.getCompName()+",请尽快处理！");
                message.setType(0);
                message.setMsgDate(new Date());
                messageService.save(message);
                return compResult;
            }else {
                compResult.setCode(400);
                compResult.setMessage("申请发送失败！");
                return compResult;
            }
        }
    }

    /**
     * 学院申请创建竞赛
     * @param comp
     * @return
     */
    @Override
    public CompResult applyCreateComp(Comp comp,Integer collegeId) {
        CompResult compResult=new CompResult();
        comp.setVisiable(false);
        Comp comp_db = compService.save(comp);
        College_Comp college_comp=new College_Comp();
        college_comp.setApplyDate(new Date());
        college_comp.setUpdateApplyDate(new Date());
        college_comp.setCollege(getCollegeById(collegeId));
        college_comp.setComp(comp_db);
        college_comp.setApplyOrJoin(0);
        College_Comp collegeComp = college_compService.save(college_comp);
        if(collegeComp!=null){
            compResult.setMessage("申请已发送！");
            compResult.setCode(200);
        }else {
            compResult.setMessage("异常！");
            compResult.setCode(400);
        }
        return compResult;
    }

    /**
     * 学院撤销比赛申请
     * @param collegeId
     * @param compId
     * @return
     */
    @Override
    public CompResult revokeCompApply(Integer collegeId, Integer compId) {
        CompResult compResult=new CompResult();
        College_Comp collegeComp = college_compService.getCollegeComp(collegeId, compId);
        Integer state = collegeComp.getComp().getCompState();
        if (state==1){
            compResult.setCode(408);
            compResult.setMessage("比赛在进行中，无法申请！");
            return compResult;
        }else if (state==2){
            compResult.setCode(408);
            compResult.setMessage("比赛已结束，无法申请！");
            return compResult;
        }
        if (collegeComp.getApplyOrJoin()==3){//已经参与
            compResult.setCode(402);
            compResult.setMessage("申请撤销失败，已经参与，请取消参赛！");
            return compResult;
        }else if (collegeComp.getApplyOrJoin()==0){
            compResult.setCode(400);
            compResult.setMessage("无申请记录！");
            return compResult;
        }else {
            college_compService.deleteCollege_CompById(collegeComp.getId());
            compResult.setCode(201);
            compResult.setMessage("申请撤销成功！");
            return compResult;
        }
    }

    /**
     * 学院退出比赛
     * @param collegeId
     * @param compId
     * @return
     */
    @Override
    public CompResult exitComp(Integer collegeId, Integer compId) {
        CompResult compResult=new CompResult();
        College_Comp collegeComp = college_compService.getCollegeComp(collegeId, compId);
        Integer state = collegeComp.getComp().getCompState();
        if (state==1){
            compResult.setCode(408);
            compResult.setMessage("比赛在进行中，无法退出！");
            return compResult;
        }else if (state==2){
            compResult.setCode(408);
            compResult.setMessage("比赛已结束，退出无效！");
            return compResult;
        }
        try {
            college_compService.deleteCollege_CompById(collegeComp.getId());
            compResult.setCode(200);
            compResult.setMessage("退出成功！");
            return compResult;
        }catch (Exception e){
            compResult.setCode(400);
            compResult.setMessage("系统异常！");
            return compResult;
        }
    }

    /**
     * 根据比赛申请状态获得院系内的比赛  state为  1申请  2驳回  3参与
     * @param collegeId
     * @param state
     * @return
     */
    @Override
    public List<Comp> getCompListByState(Integer collegeId, Integer state) {
        List<Comp> comps=new ArrayList<>();
        List<College_Comp> collegeComps=college_compService.getCollegeComps(collegeId);//得到院系所有的列表
        for (College_Comp collegeComp : collegeComps) {
            if(collegeComp.getApplyOrJoin()==state){
                comps.add(collegeComp.getComp());
            }
        }
        return null;
    }

    //----------------------------面向教师---------------------------------

    /**
     * 得到院系内未处理的老师项目请求
     * @param collegeId
     * @return
     */
    @Override
    public List<Teacher_Project> getUndoApplyTeacherList(Integer collegeId) {
        List<Teacher_Project> result=new ArrayList<>();
        List<Teacher> teachers = getAllTeachersByCollegeId(collegeId);
        for (Teacher teacher : teachers) {
            List<Teacher_Project> teacher_projects = teacher_projectService.getTeacherProjectsByTeacherAndIsCreator(teacher.getId(), true);
            for (Teacher_Project teacher_project : teacher_projects) {
                Integer applyOrJoin = teacher_project.getApplyOrJoin();
                if (applyOrJoin==1|applyOrJoin==4){//得到申请的请求关系
                    result.add(teacher_project);
                }
            }
        }
        return result;
    }

    /**
     * 得到院系内处理过的请求
     * @param collegeId
     * @return
     */
    @Override
    public List<Teacher_Project> getDoneApplyTeacherList(Integer collegeId) {
        List<Teacher_Project> result=new ArrayList<>();
        List<Teacher> teachers = getAllTeachersByCollegeId(collegeId);
        for (Teacher teacher : teachers) {
            List<Teacher_Project> teacher_projects = teacher_projectService.getTeacherProjectsByTeacherAndIsCreator(teacher.getId(), true);
            for (Teacher_Project teacher_project : teacher_projects) {
                Integer applyOrJoin = teacher_project.getApplyOrJoin();
                if (applyOrJoin==3|applyOrJoin==2){//得到申请的请求关系
                    result.add(teacher_project);
                }
            }
        }
        return result;
    }

    @Override
    public CompResult reapplyAddComp(Integer compCollegeId) {
        CompResult compResult=new CompResult();
        College_Comp collegeComp = college_compService.getCollegeComp(compCollegeId);
        collegeComp.setApplyOrJoin(0);
        collegeComp.setUpdateApplyDate(new Date());
        College_Comp save = college_compService.save(collegeComp);
        if(save!=null){
            compResult.setCode(200);
            compResult.setMessage("重新提交 ！");
        }else {
            compResult.setCode(400);
            compResult.setMessage("提交失败 ！");
        }
        return compResult;
    }

    /**
     * 根据院系获得老师列表
     * @param collegeId
     * @return
     */
    @Override
    public List<Teacher> getAllTeachersByCollegeId(Integer collegeId) {
        List<Teacher> teachers = teacherService.getAllTeachersByCollegeId(collegeId);
        return teachers;
    }

    /**
     * 增加院系的老师
     * @param teacher
     * @param collegeId
     * @return
     */
    @Override
    public UserInfo createTeacher(Teacher teacher, Integer collegeId) {
        UserInfo userInfo=new UserInfo();
        teacher.setTargetId(UUID.randomUUID().toString());
        teacher.setUsername(UUIDUtil.getUUIDInOrderId().toString());
        College college = this.getCollegeById(collegeId);
        teacher.setCollege(college);
        Teacher save = teacherService.save(teacher);
        if(save!=null){
            userInfo.setCode(200);
            userInfo.setMessage("创建成功！");
            userInfo.setData(save);
            Message message=new Message();
            message.setReceiver(teacher.getTargetId());
            message.setSender(college.getTargetId());
            message.setSenderRole(2);
            message.setReceiverRole(1);
            message.setTitle("欢迎新成员！");
            message.setContent(college.getNickname()+"已为您创建新账号："+teacher.getUsername()+",您可以申请项目了哟");
            message.setType(2);
            message.setMsgDate(new Date());
            messageService.save(message);
            return userInfo;
        }else {
            userInfo.setCode(400);
            userInfo.setMessage("创建失败！");
            return userInfo;
        }
    }

    /**
     * 同意老师请求参加比赛
     * @param teacherProjcetId
     * @return
     */
    @Override
    public ProResult applyTeacherProject(Integer teacherProjcetId) {
        ProResult proResult=new ProResult();
        Teacher_Project projectTeacher = teacher_projectService.getProjectTeacher(teacherProjcetId);
        Teacher teacher=projectTeacher.getTeacher();
        College college = teacher.getCollege();
        Integer compState = projectTeacher.getProject().getComp().getCompState();
        if (compState==0){
            proResult.setCode(400);
            proResult.setMessage("比赛正在规划中，无法进行操作！");
            return proResult;
        }else if(compState==2){
            proResult.setCode(408);
            proResult.setMessage("报名已结束，无法同意！");
            return proResult;
        }else if(compState==3){
            proResult.setCode(408);
            proResult.setMessage("比赛正在进行中，无法参与！");
            return proResult;
        }else if (compState!=1){
            proResult.setCode(408);
            proResult.setMessage("比赛已结束，无法参与！");
            return proResult;
        }
/*        Integer maxCountProject = projectTeacher.getProject().getComp().getMaxCountProject();
        Integer countProject = projectTeacher.getProject().getComp().getCountProject();*/
/*        if(maxCountProject<=countProject){
            proResult.setCode(400);
            proResult.setMessage("比赛项目数已经满，无法同意！");
            return proResult;
        }*/
        Integer state = projectTeacher.getApplyOrJoin();
        if(state==1|state==4){
            projectTeacher.setApplyOrJoin(3);
            projectTeacher.setUpdateApplyDate(new Date());
            Project project = projectTeacher.getProject();
            projectService.getProjectById(project.getId()).setApplyState(1);
            projectService.getProjectById(project.getId()).setCountTeacher(projectService.getProjectById(project.getId()).getCountTeacher()+1);
            projectTeacher.getProject().getComp().setCountProject(projectTeacher.getProject().getComp().getCountProject()+1);
            proResult.setCode(200);
            proResult.setMessage("同意成功！");
            Message message=new Message();
            message.setReceiver(teacher.getTargetId());
            message.setSender(college.getTargetId());
            message.setSenderRole(2);
            message.setReceiverRole(1);
            message.setTitle("您的项目申请通过了！");
            message.setContent(college.getNickname()+"已同意您申请的项目："+project.getProjectName());
            message.setType(2);
            message.setMsgDate(new Date());
            messageService.save(message);
            return proResult;
        }else {
            proResult.setCode(400);
            proResult.setMessage("同意失败！");
            return proResult;
        }
    }

    /**
     * 拒绝老师参加比赛
     * @param teacherProjcetId
     * @return
     */
    @Override
    public ProResult refuseTeacherProject(Integer teacherProjcetId) {
        ProResult proResult=new ProResult();
        Teacher_Project projectTeacher = teacher_projectService.getProjectTeacher(teacherProjcetId);
        Project project = projectTeacher.getProject();
        Teacher teacher = projectTeacher.getTeacher();
        College college = teacher.getCollege();
        Integer state=projectTeacher.getApplyOrJoin();
        if(state!=1&state!=4){
            proResult.setCode(400);
            proResult.setMessage("状态异常！");
            return proResult;
        }else{
            projectTeacher.setApplyOrJoin(2);//驳回是2
            projectTeacher.setUpdateApplyDate(new Date());
            proResult.setCode(200);
            proResult.setMessage("审核驳回！");
            Message message=new Message();
            message.setReceiver(teacher.getTargetId());
            message.setSender(college.getTargetId());
            message.setSenderRole(2);
            message.setReceiverRole(1);
            message.setTitle("您的项目申请被驳回了！");
            message.setContent(college.getNickname()+"驳回了您申请的项目："+project.getProjectName());
            message.setType(1);
            message.setMsgDate(new Date());
            messageService.save(message);
            return proResult;
        }
    }

    /**
     * 根据状态得到院系内项目信息   审核界面专用
     * @param collegeId
     * @param state
     * @return
     */
    @Override
    public List<Teacher_Project> getTeacherProjcetList(Integer collegeId, Integer state) {
        List<Teacher_Project> teacherProjects=new ArrayList<>();
        //得到院系内所有老师
        List<Teacher> teacherList = getAllTeachersByCollegeId(collegeId);
        //遍历所有的老师
        for (Teacher teacher : teacherList) {
            //根据老师是否是创建者拿到老师和项目之间的关系集合
            List<Teacher_Project> teacherProjectsByTeacherAndIsCreator = teacher_projectService.getTeacherProjectsByTeacherAndIsCreator(teacher.getId(),true);
            for (Teacher_Project teacher_project : teacherProjectsByTeacherAndIsCreator) {
                if (teacher_project.getApplyOrJoin()==state){
                    teacherProjects.add(teacher_project);
                }
            }
        }
        return teacherProjects;
    }

    //------------------------------------面向学生----------------------------------

    /**
     * 添加学生账号
     * @param student
     * @param collegeId
     * @return
     */
    @Override
    public UserInfo createStudent(Student student, Integer collegeId) {
        UserInfo userInfo=new UserInfo();
        student.setTargetId(UUIDUtil.getUUID());
        student.setUsername(UUIDUtil.getUUIDInOrderId().toString());
        College college = this.getCollegeById(collegeId);
        student.setCollege(college);
        Student save = studentService.save(student);
        if(save!=null){
            userInfo.setCode(200);
            userInfo.setMessage("创建成功！");
            userInfo.setData(save);
            Message message=new Message();
            message.setReceiver(student.getTargetId());
            message.setSender(college.getTargetId());
            message.setSenderRole(2);
            message.setReceiverRole(0);
            message.setTitle("欢迎新成员");
            message.setContent(college.getNickname()+"为你创建的新的账号："+student.getUsername());
            message.setType(2);
            message.setMsgDate(new Date());
            messageService.save(message);
            return userInfo;
        }else {
            userInfo.setCode(400);
            userInfo.setMessage("创建失败！");
            return userInfo;
        }
    }

}
