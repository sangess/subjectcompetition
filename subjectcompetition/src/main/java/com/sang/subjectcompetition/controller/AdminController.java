package com.sang.subjectcompetition.controller;

import com.sang.subjectcompetition.entity.*;
import com.sang.subjectcompetition.entity.resultInfo.CompResult;
import com.sang.subjectcompetition.entity.resultInfo.UserInfo;
import com.sang.subjectcompetition.service.AdminService;
import com.sang.subjectcompetition.service.College_CompService;
import com.sang.subjectcompetition.service.CompService;
import com.sang.subjectcompetition.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private College_CompService college_compService;

    @Autowired
    private CompService compService;

    @Autowired
    private ProjectService projectService;

    @GetMapping("/getUserAdminById")
    public Admin getUserAdminById(@RequestParam int id){
        Admin admin = adminService.getAdminById(id);
        return admin;
    }

    /**
     * 保存或者更新管理员账户
     * @param admin
     * @return
     */
    @PostMapping("/updateAdmin")
    public UserInfo updateAdmin(Admin admin){
        System.out.println(admin.getId());
        return adminService.updateAdmin(admin);
    }

    /**
     * 创建学院账号
     * @param college
     * @return
     */
    @PostMapping("/createCollege")
    public UserInfo createCollege(College college){
        return adminService.createCollege(college);
    }

    /**
     * 得到未处理的比赛申请集合
     * @return
     */
    @PostMapping("/getUndoApplyCompList")
    public List<College_Comp> getUndoApplyCompList(){
        return college_compService.getUndoApplyComp();
    }

    /**
     * 得到已处理的比赛申请集合
     * @return
     */
    @PostMapping("/getDoneApplyCompList")
    public List<College_Comp> getDoneApplyCompList(){
        return college_compService.getDoneApplyComp();
    }

    /**
     * 同意比赛申请
     * @param collegeCompId
     * @return
     */
    @GetMapping("applyComp/{collegeCompId}")
    public CompResult applyComp(@PathVariable Integer collegeCompId){
        return compService.applyComp(collegeCompId);
    }

    /**
     * 拒绝比赛申请
     * @param collegeCompId
     * @return
     */
    @GetMapping("refuseComp/{collegeCompId}")
    public CompResult refuseComp(@PathVariable Integer collegeCompId){
        return compService.refuseComp(collegeCompId);
    }

    /**
     * 得到所有的竞赛项目
     * @return
     */
    @GetMapping("/getAllProjects")
    public List<Project> getAllProjects(){
        List<Project> result=new ArrayList<>();
        List<Project> allProjects = projectService.getAllProjects();
        for (Project allProject : allProjects) {
            if (allProject.getApplyState()==1){
                result.add(allProject);
            }
        }
        return result;
    }

    /**
     * 得到未处理的添加竞赛的申请列表
     * @return
     */
    @GetMapping("/getUndoAddCompList")
    public List<College_Comp> getUndoAddCompList(){
        return college_compService.getUndoAddCompList();
    }

    @GetMapping("/getDoneAddCompList")
    public List<College_Comp> getDoneAddCompList(){
        return college_compService.getDoneAddCompList();
    }

    @GetMapping("/checkComp/{compId}")
    public List<Comp> checkComp(@PathVariable Integer compId){
        List<Comp> compList=new ArrayList<>();
        compList.add(compService.findCompById(compId));
        return compList;
    }

    @GetMapping("/applyAddCommp/{comp_collegeId}")
    public CompResult applyAddCommp(@PathVariable Integer comp_collegeId){
        CompResult compResult=new CompResult();
        College_Comp collegeComp = college_compService.getCollegeComp(comp_collegeId);
        Comp comp = compService.findCompById(collegeComp.getComp().getId());
        comp.setVisiable(true);
        comp.setPublishTime(new Date());
        compService.save(comp);
        collegeComp.setUpdateApplyDate(new Date());
        collegeComp.setApplyOrJoin(5);
        College_Comp save = college_compService.save(collegeComp);
        if(save!=null){
            compResult.setCode(200);
            compResult.setMessage("操作成功！");
        }else {
            compResult.setCode(400);
            compResult.setMessage("操作失败！");
        }
        return compResult;
    }

    @GetMapping("/refuseAddComp/{comp_collegeId}")
    public CompResult refuseAddComp(@PathVariable Integer comp_collegeId){
        CompResult compResult=new CompResult();
        College_Comp collegeComp = college_compService.getCollegeComp(comp_collegeId);
        collegeComp.setApplyOrJoin(6);
        College_Comp save = college_compService.save(collegeComp);
        if(save!=null){
            compResult.setCode(200);
            compResult.setMessage("操作成功！");
        }else {
            compResult.setCode(400);
            compResult.setMessage("操作失败！");
        }
        return compResult;
    }
}
