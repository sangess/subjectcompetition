package com.sang.subjectcompetition.controller;

import com.sang.subjectcompetition.entity.*;
import com.sang.subjectcompetition.entity.resultInfo.CompResult;
import com.sang.subjectcompetition.entity.resultInfo.ProResult;
import com.sang.subjectcompetition.entity.resultInfo.UserInfo;
import com.sang.subjectcompetition.respository.College_CompRepository;
import com.sang.subjectcompetition.service.CollegeService;
import com.sang.subjectcompetition.service.College_CompService;
import com.sang.subjectcompetition.service.ProjectService;
import com.sang.subjectcompetition.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/college")
public class CollegeController {
    @Autowired
    private CollegeService collegeService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private College_CompService college_compService;

    @Autowired
    private College_CompRepository college_compRepository;

    @Autowired
    private WorkService workService;

    /**
     * 学院申请比赛
     * @param compId
     * @param collegeId
     * @return
     */
    @GetMapping("/applyComp/{compId}/{collegeId}")
    public CompResult applyComp(@PathVariable Integer collegeId,
                                @PathVariable Integer compId){
        CompResult compResult = collegeService.applyComp(collegeId, compId);
        return compResult;
    }

    /**
     * 学院对比赛未处理的是  管理员未处理的
     * @param collegeId
     * @return
     */
    @PostMapping("/getUndoCompApply/{collegeId}")
    public List<College_Comp> getUndoCompApply(@PathVariable Integer collegeId){
        List<College_Comp> undoApplyComp = college_compService.getUndoApplyComp(collegeId);
        undoApplyComp.sort(Comparator.naturalOrder());
        return undoApplyComp;
    }

    /**
     * 学院对比赛处理过的是  管理员处理过的
     * @param collegeId
     * @return
     */
    @PostMapping("/getDoneCompApply/{collegeId}")
    public List<College_Comp> getDoneCompApply(@PathVariable Integer collegeId){
        List<College_Comp> doneApplyComp = college_compService.getDoneApplyComp(collegeId);
        doneApplyComp.sort(Comparator.naturalOrder());
        return doneApplyComp;
    }

    /**
     * 重新申请比赛
     * @param collegeCompId
     * @return
     */
    @GetMapping("/reApplyComp/{collegeCompId}")
    public CompResult reApplyComp(@PathVariable Integer collegeCompId){
        Integer collegeId=college_compService.getCollegeComp(collegeCompId).getCollege().getId();
        Integer compId=college_compService.getCollegeComp(collegeCompId).getComp().getId();
        return collegeService.applyComp(collegeId, compId);
    }

    /**
     * 得到学院的信息
     * @param collegeId
     * @return
     */
    @PostMapping("/getUserCollegeById/{collegeId}")
    public College getUserCollegeById(@PathVariable Integer collegeId){
        return collegeService.getCollegeById(collegeId);
    }

    /**
     * 修改学院的信息
     * @param college
     * @return
     */
    @PostMapping("/updateCollege")
    public UserInfo updateCollege(College college){
        return collegeService.updateCollege(college);
    }

    /**
     * 添加教师账号
     * @param teacher
     * @param collegeId
     * @return
     */
    @PostMapping("/createTeacher")
    public UserInfo createTeacher(Teacher teacher,Integer collegeId){
        return collegeService.createTeacher(teacher, collegeId);
    }

    /**
     * 添加学生账号
     * @param student
     * @param collegeId
     * @return
     */
    @PostMapping("/createStudent")
    public UserInfo createStudent(Student student, Integer collegeId){
        return collegeService.createStudent(student, collegeId);
    }

    /**
     * 得到学院与教师未处理的请求
     */
    @GetMapping("/getUndoApplyTeacherList/{collegeId}")
    public List<Teacher_Project> getUndoApplyTeacherList(@PathVariable Integer collegeId){
        List<Teacher_Project> undoApplyTeacherList = collegeService.getUndoApplyTeacherList(collegeId);
        undoApplyTeacherList.sort(Comparator.naturalOrder());
        return undoApplyTeacherList;
    }

    /**
     * 得到学院与教师处理过的请求
     */
    @GetMapping("/getDoneApplyTeacherList/{collegeId}")
    public List<Teacher_Project> getDoneApplyTeacherList(@PathVariable Integer collegeId){
        List<Teacher_Project> doneApplyTeacherList = collegeService.getDoneApplyTeacherList(collegeId);
        doneApplyTeacherList.sort(Comparator.naturalOrder());
        return doneApplyTeacherList;
    }

    /**
     * 同意教师申请比赛项目
     */
    @GetMapping("/applyTeacherProject/{teacher_projectId}")
    public ProResult applyTeacherProject(@PathVariable Integer teacher_projectId){
        return collegeService.applyTeacherProject(teacher_projectId);
    }

    /**
     * 拒绝教师申请比赛项目
     */
    @GetMapping("/refuseTeacherProject/{teacher_projectId}")
    public ProResult refuseTeacherProject(@PathVariable Integer teacher_projectId){
        return collegeService.refuseTeacherProject(teacher_projectId);
    }

    /**
     * 根据院系得到院内的比赛项目
     */
    @GetMapping("/getProjectByCollege/{collegeId}")
    public List<Project> getProjectByCollege(@PathVariable Integer collegeId){
        List<Project> project = projectService.getProjectByCollege(collegeId);
        project.sort(Comparator.naturalOrder());
        return project;
    }

    @PostMapping("/applyCreateComp")
    public CompResult applyCreateComp(Comp comp,Integer collegeId){
        return collegeService.applyCreateComp(comp,collegeId);
    }

    @GetMapping("/getDoneAddList/{collegeId}")
    public List<College_Comp> getDoneAddList(@PathVariable Integer collegeId){
        List<College_Comp> collegeCompList = new ArrayList<>();
        if(college_compRepository.findCollege_CompsByApplyOrJoin(collegeId,5)!=null)
            collegeCompList.addAll(college_compRepository.findCollege_CompsByApplyOrJoin(collegeId,5));
        if(college_compRepository.findCollege_CompsByApplyOrJoin(collegeId,6)!=null)
            collegeCompList.addAll(college_compRepository.findCollege_CompsByApplyOrJoin(collegeId,6));
        return collegeCompList;
    }

    @GetMapping("/getUndoAddList/{collegeId}")
    public List<College_Comp> getUndoAddList(@PathVariable Integer collegeId){
        List<College_Comp> collegeCompList = new ArrayList<>();
        if(college_compRepository.findCollege_CompsByApplyOrJoin(collegeId,0)!=null)
            collegeCompList.addAll(college_compRepository.findCollege_CompsByApplyOrJoin(collegeId,0));
        return collegeCompList;
    }

    @GetMapping("/reApplyAddCommp/{comp_collegeId}")
    public CompResult reApplyAddCommp(@PathVariable Integer comp_collegeId){
        return collegeService.reapplyAddComp(comp_collegeId);
    }

    /**
     * 得到院系内 没有评分的作品
     * @param collegeId
     * @return
     */
    @GetMapping("/getUnMarkProjects/{collegeId}")
    public List<Work> getUnMarkProjects(@PathVariable Integer collegeId){
        List<Work> workList=new ArrayList<>();
        List<Project> projects = projectService.getProjectByCollege(collegeId);
        for (Project project : projects) {
            if(project.getWork()!=null){
                if(project.getWork().getIfMark()==false)
                    workList.add(project.getWork());
            }
        }
        return workList;
    }

    /**
     * 得到院系内 评过分的项目
     * @param collegeId
     * @return
     */
    @GetMapping("/getMarkWorkProjects/{collegeId}")
    public List<Map<String,Object>> getMarkWorkProjects(@PathVariable Integer collegeId){
        List<Map<String,Object>> compList=new ArrayList<>();
        List<College_Comp> college_comps = college_compRepository.findCollege_CompsByApplyOrJoin(collegeId, 3);
        for (College_Comp college_comp : college_comps) {
            Map<String,Object> objectMap=new HashMap<>();
            Comp comp = college_comp.getComp();
            objectMap.put("compId",comp.getId());
            objectMap.put("compName",comp.getCompName());
            objectMap.put("occurrenceTime",comp.getOccurrenceTime());
            objectMap.put("notApplyTime",comp.getNotApplyTime());
            List<Project> projects = comp.getProjects();
            List<Work> result=new ArrayList<>();
            for (Project project : projects) {
                if(project.getWork()!=null){
                    if(project.getWork().getIfMark()==true){
                        result.add(project.getWork());
                    }
                }
            }

            Collections.sort(result, new Comparator<Work>() {
                @Override
                public int compare(Work o1, Work o2) {
                    if(o1.getScore()-o2.getScore()<0)return 1;
                    else if(o1.getScore()-o2.getScore()>0)return -1;
                    else return o1.getId()-o2.getId();
                }
            });
            objectMap.put("projectList",result);
            if(result.size()!=0)compList.add(objectMap);
        }
        return compList;
    }

    @GetMapping("/markWork/{score}/{workId}")
    public ProResult markWork(@PathVariable Integer score,@PathVariable Integer workId){
        ProResult proResult=new ProResult();
        Work work = workService.findWorkById(workId);
        work.setIfMark(true);
        work.setScore(score);
        Work save = workService.save(work);
        if (save.getId()==workId){
            proResult.setCode(200);
            proResult.setMessage("打分成功！");
            return proResult;
        }else{
            proResult.setCode(400);
            proResult.setMessage("打分失败！");
            return proResult;
        }
    }
}
