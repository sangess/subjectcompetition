package com.sang.subjectcompetition.service.impl;

import com.sang.subjectcompetition.entity.*;
import com.sang.subjectcompetition.entity.resultInfo.ProResult;
import com.sang.subjectcompetition.entity.resultInfo.UserInfo;
import com.sang.subjectcompetition.respository.Student_ProjectRepository;
import com.sang.subjectcompetition.respository.TeacherRepository;
import com.sang.subjectcompetition.respository.Teacher_ProjectRepository;
import com.sang.subjectcompetition.service.*;
import com.sang.subjectcompetition.service.util.UpdateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private Teacher_ProjectRepository teacher_projectRepository;
    @Autowired
    private Student_ProjectRepository student_projectRepository;

    @Autowired
    private Teacher_ProjectService teacher_projectService;

    @Autowired
    private CompService compService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private Student_ProjectService student_projectService;

    @Autowired
    private College_CompService college_compService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private MessageService messageService;

    //基本操作--------------------------------------------------------

    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher getTeacherById(Integer id) {
        return teacherRepository.findById(id).orElse(null);
    }

    //业务逻辑--------------------------------------------------------

    /**
     * 更新老师信息
     * @param teacher
     * @return
     */
    @Override
    public UserInfo updateTeacher(Teacher teacher) {
        UserInfo userInfo=new UserInfo();
        Teacher teacher_db=getTeacherById(teacher.getId());
        UpdateUtil.copyNullProperties(teacher,teacher_db);
        Teacher save = this.save(teacher_db);
        if (save!=null){
            userInfo.setCode(200);
            userInfo.setMessage("信息更改成功！");
            userInfo.setData(save);
            return userInfo;
        }else {
            userInfo.setCode(400);
            userInfo.setMessage("信息更改失败！");
            return userInfo;
        }
    }

    /**
     * 获得本院系所有老师
     * @param collegeId
     * @return
     */
    @Override
    public List<Teacher> getAllTeachersByCollegeId(Integer collegeId) {
        List<Teacher> teacherList = teacherRepository.getTeachersByCollege_Id(collegeId);
        return teacherList;
    }

    //----------------------------面向学院--------------------------

    /**
     * 老师向学院申请参加比赛，并创建自己的项目
     * @param teacherId
     * @param compId
     * @param project
     * @return
     */
    @Override
    public ProResult applyJoinComp(Integer teacherId, Integer compId,Project project) {
        Teacher teacher = getTeacherById(teacherId);
        College college = teacher.getCollege();
        ProResult proResult=new ProResult();
        Comp comp = compService.findCompById(compId);
/*        if(comp.getMaxCountProject()<=comp.getCountProject()){//判断是否已满名额限制
            proResult.setCode(400);
            proResult.setMessage("比赛队伍满额！");
            return proResult;
        }*/
        if (comp.getCompState()==0){
            proResult.setCode(401);
            proResult.setMessage("比赛正在规划中，无法申请！");
            return proResult;
        }else if(comp.getCompState()==2){
            proResult.setCode(401);
            proResult.setMessage("比赛已截止报名，无法申请！");
            return proResult;
        }else if(comp.getCompState()==3){
            proResult.setCode(401);
            proResult.setMessage("比赛正在进行中，无法申请！");
            return proResult;
        }else if(comp.getCompState()!=1){
            proResult.setCode(401);
            proResult.setMessage("比赛已结束，无法申请！");
            return proResult;
        }
        //判断申请人是否已经申请过此项目  0默认  1申请  2驳回  3同意  4重新申请
        if(project.getId()!=null){//如果是重新申请
            Integer projectId = project.getId();
            Teacher_Project teacher_project = teacher_projectService.findTeacher_ProjectByTeacherIdAndProjectIdAndIsCreator(teacherId, projectId, true);
            Integer applyOrJoinState = teacher_project.getApplyOrJoin();
            if (applyOrJoinState==1|applyOrJoinState==4){//已经申请过  重新点击申请按钮
                proResult.setMessage("您已申请过该比赛，请耐心等待");
                proResult.setCode(400);
                return proResult;
            }else if (applyOrJoinState==3){//已经同意了的项目
                proResult.setMessage("您的项目申请已经通过，请勿重复点击");
                proResult.setCode(400);
                return proResult;
            }else if (applyOrJoinState==0){
                proResult.setMessage("状态异常，请联系管理员");
                proResult.setCode(400);
                return proResult;
            }else{//-------------------------------2是驳回  代表重新申请此项目
                teacher_project.setApplyOrJoin(4);//重复申请
                teacher_project.setUpdateApplyDate(new Date());//重复申请
                Project project_db = projectService.getProjectById(projectId);
                UpdateUtil.copyNullProperties(project,project_db);
                proResult.setMessage("已发起重复申请");
                proResult.setCode(200);
                Message message=new Message();
                message.setReceiver(college.getTargetId());
                message.setSender(teacher.getTargetId());
                message.setSenderRole(1);
                message.setReceiverRole(2);
                message.setTitle("你有新的项目申请");
                message.setContent(teacher.getNickname()+"老师，用户名："+teacher.getUsername()
                        +"重新发出项目申请"+"  项目名:"+project.getProjectName()+"，所属比赛为："+project.getComp().getCompName()
                +"请尽快处理！");
                message.setType(0);
                message.setMsgDate(new Date());
                messageService.save(message);
                return proResult;
            }
        }
        Teacher_Project teacher_project =new Teacher_Project();
        project.setCreateByTeacher(getTeacherById(teacherId));//设置项目创建者的老师
        project.setApplyState(0);//未审核
        project.setComp(comp);
        project = projectService.save(project);
        teacher_project.setTeacher(getTeacherById(teacherId));
        teacher_project.setProject(project);
        teacher_project.setApplyDate(new Date());
        teacher_project.setUpdateApplyDate(new Date());
        teacher_project.setApplyOrJoin(1);//申请
        teacher_project.setIsCreator(true);//设置创建者标志
        Teacher_Project teacherProject = teacher_projectService.save(teacher_project);
        if (teacherProject!=null){
            proResult.setCode(200);
            proResult.setMessage("申请已发出！");
            Message message=new Message();
            message.setReceiver(college.getTargetId());
            message.setSender(teacher.getTargetId());
            message.setSenderRole(1);
            message.setReceiverRole(2);
            message.setTitle("你有新的项目申请");
            message.setContent(teacher.getNickname()+"老师，用户名："+teacher.getUsername()
                    +"发出项目申请"+"  项目名:"+project.getProjectName()+"，所属比赛为："+project.getComp().getCompName()
                    +"请尽快处理！");
            message.setType(0);
            message.setMsgDate(new Date());
            messageService.save(message);
            return proResult;
        }else{
            proResult.setCode(400);
            proResult.setMessage("申请发出失败！");
            return proResult;
        }
    }

    /**
     * 老师向学院重新申请参加比赛
     * @param teacherId
     * @param projectId
     * @return
     */
    @Override
    public ProResult reApplyJoinComp(Integer teacherId, Integer projectId) {
        ProResult proResult=new ProResult();
        Teacher_Project projectTeacher = teacher_projectService.getProjectTeacher(teacherId, projectId);
        Integer compState = projectTeacher.getProject().getComp().getCompState();
        if (compState==0){
            proResult.setCode(400);
            proResult.setMessage("比赛规划中，无法提交申请！");
            return proResult;
        }else if(compState==2){
            proResult.setCode(400);
            proResult.setMessage("比赛截止报名，无法提交申请！");
            return proResult;
        }else if(compState==3){
            proResult.setCode(400);
            proResult.setMessage("比赛进行中，无法提交申请！");
            return proResult;
        }else if(compState!=1){
            proResult.setCode(400);
            proResult.setMessage("比赛已结束，无法提交申请！");
            return proResult;
        }
        Integer state = projectTeacher.getApplyOrJoin();
        if(state==2){//判断是否是驳回
            projectTeacher.setApplyOrJoin(4);
            proResult.setCode(200);
            proResult.setMessage("重新申请成功！");
            return proResult;
        }else {
            proResult.setCode(400);
            proResult.setMessage("重新申请失败！状态异常");
            return proResult;
        }
    }

    /**
     * 老师撤销比赛申请
     * @param teacherId
     * @param projectId
     * @return
     */
    @Override
    public ProResult revokeJoinComp(Integer teacherId, Integer projectId) {
        ProResult proResult=new ProResult();
        Teacher teacher = getTeacherById(teacherId);
        College college = teacher.getCollege();
        Project project = projectService.getProjectById(projectId);
        Teacher_Project teacher_project=teacher_projectService.getProjectTeacher(teacherId,projectId);
        Integer state = teacher_project.getApplyOrJoin();
        if (state==3){//申请成功，只能退出比赛
            proResult.setCode(401);
            proResult.setMessage("已参与比赛，不可撤销申请");
            return proResult;
        }else if(state==0){
            proResult.setCode(400);
            proResult.setMessage("状态异常，权限不够！");
            return proResult;
        }else {
            teacher_projectService.deleteTeacherProject(teacherId, projectId);
            projectService.deleteProject(projectId);
            proResult.setCode(200);
            proResult.setMessage("撤销成功！");

            Message message=new Message();
            message.setReceiver(college.getTargetId());
            message.setSender(teacher.getTargetId());
            message.setSenderRole(1);
            message.setReceiverRole(2);
            message.setTitle("院系有项目申请撤销了");
            message.setContent(teacher.getNickname()+"老师，用户名："+teacher.getUsername()
                    +"撤销了项目申请"+"  项目名:"+project.getProjectName()+"，所属比赛为："+project.getComp().getCompName());
            message.setType(0);
            message.setMsgDate(new Date());
            messageService.save(message);

            return proResult;
        }
    }

    /**
     * 创建者老师退出比赛
     * @param teacherId
     * @param projectId
     * @return
     */
    @Override
    public ProResult exitComp(Integer teacherId, Integer projectId) {
        ProResult proResult=new ProResult();
        Teacher teacher = getTeacherById(teacherId);
        College college = teacher.getCollege();
        Project project = projectService.getProjectById(projectId);
        Teacher_Project projectTeacher = teacher_projectService.getProjectTeacher(teacherId, projectId);
        Comp comp = projectTeacher.getProject().getComp();
        Integer state =comp.getCompState();
        if (state==1){
            proResult.setCode(400);
            proResult.setMessage("退出比赛失败，比赛已经开始！");
            return proResult;
        }else if(state==2){
            proResult.setCode(400);
            proResult.setMessage("退出比赛失败，比赛已经结束！");
            return proResult;
        }
        try{
            //删除中间表  根据项目删除老师和项目之间的关系
            teacher_projectService.deleteTeacherProjectsByProject(projectId);
            projectService.deleteProject(projectId);
            comp.setCountProject(comp.getCountProject()-1);
            proResult.setCode(200);
            proResult.setMessage("退出比赛成功！");

            Message message=new Message();
            message.setReceiver(college.getTargetId());
            message.setSender(teacher.getTargetId());
            message.setSenderRole(1);
            message.setReceiverRole(2);
            message.setTitle("院系有项目退出比赛了");
            message.setContent(teacher.getNickname()+"老师，用户名："+teacher.getUsername()
                    +"取消了项目"+"  项目名:"+project.getProjectName()+"，所属比赛为："+project.getComp().getCompName());
            message.setType(0);
            message.setMsgDate(new Date());
            messageService.save(message);

            return proResult;
        }catch (Exception e){
            proResult.setCode(400);
            proResult.setMessage("异常！");
            return proResult;
        }
    }

    /**
     * 根据老师获得学院的竞赛信息
     * @param teacherId
     * @return
     */
    @Override
    public List<Comp> getCollegeCompListByTeacherId(Integer teacherId) {
        Integer collegeId = this.getTeacherById(teacherId).getCollege().getId();
        List<Comp> compList = compService.getCompsByCollegeId(collegeId);
        return compList;
    }

    /**
     * 返回全部比赛  包括院系申请状况
     * @param teacherId
     * @return
     */
    @Override
    public List<Map<String, Object>> getCollegeCompApplyStateListByTeacherId(Integer teacherId) {
        Integer collegeId=this.getTeacherById(teacherId).getCollege().getId();
        List<Map<String ,Object>> compList=new ArrayList<>();
        List<Comp> allComps = compService.getAllComps();
        List<College_Comp> collegeCompList=college_compService.getCollegeComps(collegeId);
        for (Comp allComp : allComps) {
            //遍历所有的比赛
            Map<String ,Object> singalComp=new HashMap<>();
            singalComp.put("id",allComp.getId());
            singalComp.put("compName",allComp.getCompName());
            singalComp.put("organizer",allComp.getOrganizer());
            singalComp.put("level",allComp.getLevel());
            singalComp.put("publishTime",allComp.getPublishTime());
            singalComp.put("occurrenceTime",allComp.getOccurrenceTime());
            singalComp.put("place",allComp.getPlace());
            singalComp.put("subjectType",allComp.getSubjectType());
            singalComp.put("compState",allComp.getCompState());
            //得到学院的比赛
            for (College_Comp college_comp : collegeCompList) {
                if (college_comp.getComp().getId()==allComp.getId()){
                    singalComp.put("applyState",college_comp.getApplyOrJoin());
                }
            }
            compList.add(singalComp);
        }
        Collections.sort(compList, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Date o1Time = (Date)o1.get("publishTime");
                Date o2Time = (Date)o2.get("publishTime");
                if(o1Time.compareTo(o2Time)<0)return 1;
                else if(o1Time.compareTo(o2Time)==0)
                    return (int)o1.get("id")-(int)o2.get("id");
                else return -1;
            }
        });
        return compList;
    }

    //-----------------------------面向老师--------------------------

    /**
     * 转交项目创建者给另一个老师
     * @param projectId
     * @param teacherId1
     * @param teacherId2
     * @return
     */
    @Override
    public UserInfo transmitProjectCreator(Integer projectId, Integer teacherId1, Integer teacherId2) {
        UserInfo userInfo=new UserInfo();
        try {
            Teacher_Project projectTeacher1 = teacher_projectService.getProjectTeacher(teacherId1, projectId);
            Teacher_Project projectTeacher2 = teacher_projectService.getProjectTeacher(teacherId2, projectId);
            projectTeacher2.setIsCreator(true);
            projectTeacher1.setIsCreator(false);
            projectTeacher2.setApplyOrJoin(projectTeacher1.getApplyOrJoin());
            projectTeacher1.setApplyOrJoin(0);
            Project projectById = projectService.getProjectById(projectId);
            projectById.setCreateByTeacher(getTeacherById(teacherId2));
            userInfo.setCode(200);
            userInfo.setMessage("成功！");
            return userInfo;
        }catch (Exception e){
            userInfo.setCode(400);
            userInfo.setMessage("异常！");
            return userInfo;
        }
    }

    /**
     * 老师申请加入项目组
     * @param teacherProjectId
     * @return
     */
    @Override
    public ProResult teacherApplyJoinProject(Integer teacherProjectId,Integer teacherId) {
        Teacher_Project projectTeacher = teacher_projectService.getProjectTeacher(teacherProjectId);
        Teacher teacher = getTeacherById(teacherId);
        Project project = projectTeacher.getProject();
        Teacher createByTeacher = project.getCreateByTeacher();
        ProResult proResult=new ProResult();
        if(project.getComp().getMaxCountTeacher()<=project.getCountTeacher()){//判断项目组老师是否已经满员
            proResult.setCode(400);
            proResult.setMessage("项目老师已满！");
            return proResult;
        }
        Teacher_Project projectTeacher1 = teacher_projectService.getProjectTeacher(teacherId, project.getId());
        if(projectTeacher1!=null){
            Integer applyOrJoin = projectTeacher.getApplyOrJoin();
            if(applyOrJoin==1|applyOrJoin==4){
                proResult.setCode(400);
                proResult.setMessage("您已经发起，请耐心等待审核！");
                return proResult;
            }else if(applyOrJoin==3){
                proResult.setCode(400);
                proResult.setMessage("您已经是项目成员！");
                return proResult;
            }else if(applyOrJoin==0) {
                proResult.setCode(400);
                proResult.setMessage("状态异常！");
                return proResult;
            }else {
                projectTeacher1.setUpdateApplyDate(new Date());
                projectTeacher1.setApplyOrJoin(4);
                proResult.setCode(200);
                proResult.setMessage("已经重新发送申请！");
                Message message=new Message();
                message.setReceiver(createByTeacher.getTargetId());
                message.setSender(teacher.getTargetId());
                message.setSenderRole(1);
                message.setReceiverRole(1);
                message.setTitle("你有新成员想加入");
                message.setContent(teacher.getNickname()+"老师，用户名："+teacher.getUsername()
                        +"重新申请加入"+"项目:"+project.getProjectName()+"，所属比赛为："+project.getComp().getCompName());
                message.setType(2);
                message.setMsgDate(new Date());
                messageService.save(message);
                return proResult;
            }
        }else {
            Teacher_Project teacher_project=new Teacher_Project();
            teacher_project.setProject(project);
            teacher_project.setApplyDate(new Date());
            teacher_project.setUpdateApplyDate(new Date());
            teacher_project.setTeacher(getTeacherById(teacherId));
            teacher_project.setApplyOrJoin(1);//设置申请状态
            Teacher_Project save = teacher_projectService.save(teacher_project);
            if(save!=null){
                proResult.setCode(200);
                proResult.setMessage("申请提交成功！");
                Message message=new Message();
                message.setReceiver(createByTeacher.getTargetId());
                message.setSender(teacher.getTargetId());
                message.setSenderRole(1);
                message.setReceiverRole(1);
                message.setTitle("你有新成员想加入");
                message.setContent(teacher.getNickname()+"老师，用户名："+teacher.getUsername()
                        +"申请加入"+"项目:"+project.getProjectName()+"所属比赛为："+project.getComp().getCompName());
                message.setType(2);
                message.setMsgDate(new Date());
                messageService.save(message);
                return proResult;
            }else{
                proResult.setCode(400);
                proResult.setMessage("申请提交失败！未知原因");
                return proResult;
            }
        }
    }

    /**
     * 老师退出项目组
     * @param teacherId
     * @param projectId
     * @return
     */
    @Override
    public ProResult exitProject(Integer teacherId, Integer projectId) {
        ProResult proResult=new ProResult();
        Teacher_Project projectTeacher = teacher_projectService.getProjectTeacher(teacherId, projectId);
        Integer state = projectTeacher.getProject().getComp().getCompState();//查询比赛状态，已开始或结束的比赛不可以退出
        if (state==0){
            proResult.setCode(400);
            proResult.setMessage("竞赛规划中，无法提交申请！");
            return proResult;
        }else if(state>2){
            proResult.setCode(400);
            proResult.setMessage("比赛已经进行中，无法提交申请！");
            return proResult;
        }
        if(projectTeacher.getIsCreator()==true) {
            proResult.setCode(404);
            proResult.setMessage("组织者老师不可以退出项目组，只能退出比赛！");
            return proResult;
        }
        try {
            teacher_projectService.deleteTeacherProject(teacherId, projectId);
            Project project = projectService.getProjectById(projectId);
            project.setCountTeacher(project.getCountTeacher()-1);//项目组老师的数目减一
            proResult.setCode(200);
            proResult.setMessage("退出项目组成功！");
            return proResult;
        }catch (Exception e){
            proResult.setCode(405);
            proResult.setMessage("异常错误！");
            return proResult;
        }
    }

    /**
     * 同意项目组老师加入项目的申请
     * @param teacherProjectId
     * @return
     */
    @Override
    public ProResult applyTeacherJoinProject(Integer teacherProjectId) {
        ProResult proResult=new ProResult();
        Teacher_Project teacherProject = teacher_projectService.getProjectTeacher(teacherProjectId);
        Teacher teacher = teacherProject.getTeacher();
        Project project = teacherProject.getProject();
        Teacher createByTeacher = project.getCreateByTeacher();
        Comp comp=project.getComp();
        Integer state = comp.getCompState();
        if(state==0){//比赛已经开始   不可以加入项目组
            proResult.setCode(400);
            proResult.setMessage("比赛正在规划，无法同意！");
            return proResult;
        }else if(state==2){
            proResult.setCode(400);
            proResult.setMessage("报名已经结束，无法同意！");
            return proResult;
        }else if(state==3){
            proResult.setCode(400);
            proResult.setMessage("比赛已经开始，无法同意！");
            return proResult;
        }else if(state!=1){
            proResult.setCode(400);
            proResult.setMessage("比赛已经结束，无法同意！");
            return proResult;
        }
        Integer countTeacher = project.getCountTeacher();
        if(project.getComp().getMaxCountTeacher()<=countTeacher){
            //项目组的老师已经满了  无法同意其进入项目组
            proResult.setCode(400);
            proResult.setMessage("项目组老师已经满了，无法同意成功！");
            return proResult;
        }
        Integer applyOrJoin = teacherProject.getApplyOrJoin();
        if(applyOrJoin!=1&applyOrJoin!=4){//如果不是申请状态，即申请和重新申请状态
            proResult.setCode(400);
            proResult.setMessage("状态异常！无法同意成功！");
            return proResult;
        }
        teacherProject.setUpdateApplyDate(new Date());
        teacherProject.setApplyOrJoin(3);//如果是申请状态  那就修改状态字典  使之同意的状态
        Teacher_Project save = teacher_projectService.save(teacherProject);
        if (save!=null){
            proResult.setCode(200);
            proResult.setMessage("同意成功！");
            Message message=new Message();
            message.setReceiver(teacher.getTargetId());
            message.setSender(createByTeacher.getTargetId());
            message.setSenderRole(1);
            message.setReceiverRole(1);
            message.setTitle("你的项目申请通过了");
            message.setContent("你申请加入的"+"项目:"+project.getProjectName()+"审核通过了，所属比赛为："+project.getComp().getCompName());
            message.setType(2);
            message.setMsgDate(new Date());
            messageService.save(message);
            return proResult;
        }else{
            proResult.setCode(400);
            proResult.setMessage("异常！");
            return proResult;
        }
    }

    /**
     * 老师重复申请加入项目
     * @param teacher_projectId
     * @return
     */
    @Override
    public ProResult teacherReApplyJoinProject(Integer teacher_projectId) {
        ProResult proResult=new ProResult();
        Teacher_Project projectTeacher = teacher_projectService.getProjectTeacher(teacher_projectId);
        Project project = projectTeacher.getProject();
        Teacher createByTeacher = project.getCreateByTeacher();
        Teacher teacher = projectTeacher.getTeacher();
        projectTeacher.setUpdateApplyDate(new Date());
        projectTeacher.setApplyOrJoin(4);
        proResult.setCode(200);
        proResult.setMessage("已重新发送申请！");
        Message message=new Message();
        message.setReceiver(createByTeacher.getTargetId());
        message.setSender(teacher.getTargetId());
        message.setSenderRole(1);
        message.setReceiverRole(1);
        message.setTitle("你有新成员想加入");
        message.setContent(teacher.getNickname()+"老师，用户名："+teacher.getUsername()
                +"申请加入"+"项目:"+project.getProjectName()+"，所属比赛为："+project.getComp().getCompName());
        message.setType(2);
        message.setMsgDate(new Date());
        messageService.save(message);
        return proResult;
    }

    /**
     * 拒绝老师加入项目组
     * @param teacherProjectId
     * @return
     */
    @Override
    public ProResult refuseTeacherJoinProject(Integer teacherProjectId) {
        ProResult proResult=new ProResult();
        Teacher_Project teacher_project=teacher_projectService.getProjectTeacher(teacherProjectId);
        teacher_project.setUpdateApplyDate(new Date());
        teacher_project.setApplyOrJoin(2);
        proResult.setCode(200);
        proResult.setMessage("审核驳回！");
        return proResult;
    }

    /**
     * 通过项目id获得同组的老师
     * @param projectId
     * @return
     */
    @Override
    public List<Teacher> getAllTeachersByProject(Integer projectId) {
        List<Teacher> teachers=new ArrayList<>();
        List<Teacher_Project> teacherProjects = teacher_projectService.getAllTeacherProjectsByProject(projectId);
        for (Teacher_Project teacherProject : teacherProjects) {
            if(teacherProject.getApplyOrJoin()==3){
                teachers.add(teacherProject.getTeacher());
            }
        }
        return teachers;
    }

    /**
     * 老师审核界面
     * @param teacherId
     * @return
     */
    @Override
    public List<Teacher_Project> getTeacherProjectsAboutMyself(Integer teacherId) {
        //得到的是非组长的老师与项目之间的关系
        List<Teacher_Project> teacherProjects = teacher_projectService.getTeacherProjectsByTeacherAndIsCreator(teacherId, true);
        Iterator<Teacher_Project> iterator=teacherProjects.iterator();
        while(iterator.hasNext()){
            Teacher_Project teacherProject=iterator.next();
            if(teacherProject.getApplyOrJoin()==3){//表示已经通过了
                iterator.remove();
            }
        }
        return teacherProjects;
    }

    /**
     * 根据teacherId返回学生的请求
     * @param teacherId
     * @return
     */
    @Override
    public List<Student_Project> getAllStudentProjectApply(Integer teacherId) {
        List<Project> projects=new ArrayList<>();
        List<Student_Project> studentProjects=new ArrayList<>();
        //得到id为teacherid的老师所有的组长级项目
        List<Teacher_Project> teacherProjects = teacher_projectService.getTeacherProjectsByTeacherAndIsCreator(teacherId, true);
        for (Teacher_Project teacherProject : teacherProjects) {
            //得到其所有的项目  并添加进去
            projects.add(teacherProject.getProject());
        }
        for (Project project : projects) {
            Integer projectId=project.getId();
            //根据项目id获得所有的项目与学生的集合
            List<Student_Project> allStudentByProject = student_projectService.getAllStudentByProject(projectId);
            for (Student_Project student_project : allStudentByProject) {
                if(student_project.getApplyOrJoin()!=3){//3表示同意，请求不可以含有这个
                    studentProjects.add(student_project);
                }
            }
        }
        return studentProjects;
    }

    /**
     * 同意学生加入项目
     * @param studentProjectId
     * @return
     */
    @Override
    public ProResult applyStudentJoin(Integer studentProjectId) {
        ProResult proResult=new ProResult();
        Student_Project studentProject = student_projectService.getStudentProjectById(studentProjectId);
        Project project = studentProject.getProject();
        Teacher teacher = project.getCreateByTeacher();
        Student student = studentProject.getStudent();
        Integer projectId=project.getId();
        Integer studentId=student.getId();
        Integer state = studentProject.getProject().getComp().getCompState();
        if(state==0){
            proResult.setCode(400);
            proResult.setMessage("比赛正在规划中，无法同意！");
            return proResult;
        }else if(state==2){
            proResult.setCode(400);
            proResult.setMessage("报名已结束，无法同意！");
            return proResult;
        }else if(state==3){
            proResult.setCode(400);
            proResult.setMessage("比赛已进行，无法加入！");
            return proResult;
        }else if(state!=1){
            proResult.setCode(400);
            proResult.setMessage("比赛已结束，无法加入！");
            return proResult;
        }
        if(project.getComp().getMaxCountStudent()<=project.getCountStudent()){
            proResult.setCode(400);
            proResult.setMessage("项目组人数已经过多");
            return proResult;
        }
        Boolean aBoolean = studentService.ifJoinTheSameComp(studentId, projectId);
        if(aBoolean){
            proResult.setCode(400);
            proResult.setMessage("该同学已加入或有这一比赛其他申请");
            return proResult;
        }
        Integer applyOrJoin = studentProject.getApplyOrJoin();
        if(applyOrJoin!=1&applyOrJoin!=4){//如果不是申请状态(申请和重新申请状态)
            proResult.setCode(400);
            proResult.setMessage("状态异常！无法同意成功！");
            return proResult;
        }
        project.setCountStudent(project.getCountStudent()+1);
        studentProject.setApplyOrJoin(3);
        studentProject.setUpdateApplyDate(new Date());
        Student_Project save = student_projectService.save(studentProject);
        if(save!=null){
            proResult.setCode(200);
            proResult.setMessage("同意成功！");
            Message message=new Message();
            message.setReceiver(student.getTargetId());
            message.setSender(teacher.getTargetId());
            message.setSenderRole(1);
            message.setReceiverRole(0);
            message.setTitle("你的比赛申请已同意");
            message.setContent(teacher.getNickname()+"老师，用户名："+teacher.getUsername()
                    +"同意了你的申请"+"你已加入项目:"+project.getProjectName()+"，所属比赛为："+project.getComp().getCompName());
            message.setType(2);
            message.setMsgDate(new Date());
            messageService.save(message);
            return proResult;
        }else {
            proResult.setCode(400);
            proResult.setMessage("异常！");
            return proResult;
        }

    }

    /**
     * 拒绝学生加入项目组
     * @param studentProjectId
     * @return
     */
    @Override
    public ProResult refuseStudentJoin(Integer studentProjectId) {
        ProResult proResult=new ProResult();
        Student_Project studentProject = student_projectService.getStudentProjectById(studentProjectId);
        Student student = studentProject.getStudent();
        Project project = studentProject.getProject();
        Teacher teacher = project.getCreateByTeacher();
        Integer applyOrJoin = studentProject.getApplyOrJoin();
        if(applyOrJoin!=1&applyOrJoin!=4){
            proResult.setCode(400);
            proResult.setMessage("状态异常！");
            return proResult;
        }
        studentProject.setApplyOrJoin(2);
        studentProject.setUpdateApplyDate(new Date());
        proResult.setCode(200);
        proResult.setMessage("申请驳回！");

        Message message=new Message();
        message.setReceiver(student.getTargetId());
        message.setSender(teacher.getTargetId());
        message.setSenderRole(1);
        message.setReceiverRole(0);
        message.setTitle("你的比赛申请已被驳回");
        message.setContent(teacher.getNickname()+"老师，用户名："+teacher.getUsername()
                +"驳回了你的申请"+" 项目:"+project.getProjectName()+"，所属比赛为："+project.getComp().getCompName());
        message.setType(1);
        message.setMsgDate(new Date());
        messageService.save(message);
        return proResult;
    }

    /**
     * 查看项目组的学生集合
     * @param projectId
     * @return
     */
    @Override
    public List<Student> getAllStudentJoinProject(Integer projectId) {
        List<Student> students=new ArrayList<>();
        List<Student_Project> studentProjects = student_projectService.getAllStudentByProject(projectId);
        for (Student_Project studentProject : studentProjects) {
            if(studentProject.getApplyOrJoin()==3){
                students.add(studentProject.getStudent());
            }
        }
        return students;
    }

    /**
     * 将项目的学生踢出
     * @param studentId
     * @param projectId
     * @return
     */
    @Override
    public ProResult fouceStudentExit(Integer studentId, Integer projectId) {
        ProResult proResult=new ProResult();
        Student_Project studentProject = student_projectService.getStudentProjectById(studentId, projectId);
        Project project = studentProject.getProject();
        Student student = studentProject.getStudent();
        Teacher teacher = project.getCreateByTeacher();
        try{
            student_projectService.deleteById(studentProject.getId());
            proResult.setCode(200);
            proResult.setMessage("成功操作！");
            Message message=new Message();
            message.setReceiver(student.getTargetId());
            message.setSender(teacher.getTargetId());
            message.setSenderRole(1);
            message.setReceiverRole(0);
            message.setTitle("你已被移出项目团队");
            message.setContent(teacher.getNickname()+"老师，用户名："+teacher.getUsername()
                    +"将你移出"+" 项目:"+project.getProjectName()+"，所属比赛为："+project.getComp().getCompName());
            message.setType(1);
            message.setMsgDate(new Date());
            messageService.save(message);
            return proResult;
        }catch (Exception e){
            proResult.setCode(400);
            proResult.setMessage("操作失败！");
            return proResult;
        }
    }

    /**
     * 解散项目组
     * @param projectId
     * @return
     */
    @Override
    public ProResult invokeProject(Integer projectId) {
        Project project = projectService.getProjectById(projectId);
        Teacher createByTeacher = project.getCreateByTeacher();
        List<Student_Project> students = project.getStudents();
        List<Teacher_Project> teachers = project.getTeachers();
        ProResult proResult=new ProResult();
        try{
            teacher_projectRepository.deleteTeacher_ProjectsByProjectId(projectId);
            student_projectRepository.deleteStudent_ProjectsByProjectId(projectId);
            projectService.deleteProject(projectId);
            proResult.setCode(200);
            proResult.setMessage("已解散！");
            for (Student_Project student_project : students) {
                Message message=new Message();
                message.setReceiver(student_project.getStudent().getTargetId());
                message.setSender(createByTeacher.getTargetId());
                message.setSenderRole(1);
                message.setReceiverRole(0);
                message.setTitle("项目团队已解散");
                message.setContent(createByTeacher.getNickname()+"老师，用户名："+createByTeacher.getUsername()
                        +"解散了"+" 项目:"+project.getProjectName()+"，所属比赛为："+project.getComp().getCompName());
                message.setType(0);
                message.setMsgDate(new Date());
                messageService.save(message);
            }
            for (Teacher_Project teacher_project : teachers) {
                Message message=new Message();
                message.setReceiver(teacher_project.getTeacher().getTargetId());
                message.setSender(createByTeacher.getTargetId());
                message.setSenderRole(1);
                message.setReceiverRole(1);
                message.setTitle("项目团队已解散");
                message.setContent(createByTeacher.getNickname()+"老师，用户名："+createByTeacher.getUsername()
                        +"解散了"+" 项目:"+project.getProjectName()+"，所属比赛为："+project.getComp().getCompName());
                message.setType(0);
                message.setMsgDate(new Date());
                messageService.save(message);
            }
            return proResult;
        }catch (Exception e){
            proResult.setCode(400);
            proResult.setMessage("异常！！");
            return proResult;
        }
    }

    /**
     * 移出老师
     * @param teacherId
     * @return
     */
    @Override
    public ProResult exitTeacher(Integer teacherId,Integer projectId) {
        Teacher teacher = getTeacherById(teacherId);
        Project project = projectService.getProjectById(projectId);
        Teacher createByTeacher = project.getCreateByTeacher();
        ProResult proResult=new ProResult();
        try {
            teacher_projectService.deleteTeacherProject(teacherId, projectId);
            proResult.setCode(200);
            proResult.setMessage("移出成功！");
            Message message=new Message();
            message.setReceiver(teacher.getTargetId());
            message.setSender(createByTeacher.getTargetId());
            message.setSenderRole(1);
            message.setReceiverRole(1);
            message.setTitle("你已被移出项目团队");
            message.setContent(createByTeacher.getNickname()+"老师，用户名："+createByTeacher.getUsername()
                    +"将你移出"+" 项目:"+project.getProjectName()+"，所属比赛为："+project.getComp().getCompName());
            message.setType(1);
            message.setMsgDate(new Date());
            messageService.save(message);
            return proResult;
        }catch (Exception e){
            proResult.setCode(400);
            proResult.setMessage("移出失败！");
            return proResult;
        }
    }

    /**
     * 移出学生
     * @param studentId
     * @return
     */
    @Override
    public ProResult exitStudent(Integer studentId ,Integer projectId) {
        ProResult proResult=new ProResult();
        Student_Project studentProject = student_projectService.getStudentProjectById(studentId, projectId);
        Project project = studentProject.getProject();
        Student student = studentProject.getStudent();
        Teacher teacher = project.getCreateByTeacher();
        try{
            student_projectService.deleteById(studentProject.getId());
            proResult.setCode(200);
            proResult.setMessage("成功操作！");
            Message message=new Message();
            message.setReceiver(student.getTargetId());
            message.setSender(teacher.getTargetId());
            message.setSenderRole(1);
            message.setReceiverRole(0);
            message.setTitle("你已被移出项目团队");
            message.setContent(teacher.getNickname()+"老师，用户名："+teacher.getUsername()
                    +"将你移出"+" 项目:"+project.getProjectName()+"，所属比赛为："+project.getComp().getCompName());
            message.setType(1);
            message.setMsgDate(new Date());
            messageService.save(message);
            return proResult;
        }catch (Exception e){
            proResult.setCode(400);
            proResult.setMessage("操作失败！");
            return proResult;
        }
    }


}
