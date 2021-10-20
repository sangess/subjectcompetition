package com.sang.subjectcompetition.controller;

import com.sang.subjectcompetition.entity.Comp;
import com.sang.subjectcompetition.entity.Project;
import com.sang.subjectcompetition.entity.Teacher_Project;
import com.sang.subjectcompetition.entity.resultInfo.CompResult;
import com.sang.subjectcompetition.service.CompService;
import com.sang.subjectcompetition.service.ProjectService;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/comp")
public class CompController {

    @Autowired
    private CompService compService;

    @Autowired
    private ProjectService projectService;

    /**
     * 返回全部的竞赛信息
     * @return
     */
    @PostMapping("/getAllComps")
    public List<Comp> selectAllComp(){
        List<Comp> allComps = compService.getAllComps();
        allComps.sort(Comparator.naturalOrder());
        return allComps;
    }

    /**
     * 发布竞赛/更新
     * @param comp
     * @return
     */
    @PostMapping("/addComp")
    public String addComp(Comp comp){
        CompResult compResult = compService.createComp(comp);
        return compResult.getMessage();
    }

    /**
     * 多条件模糊查询
     * @return
     */
    @PostMapping("/moreSearch")
    public List<Comp> moreSearch(Comp comp){
        String compName=comp.getCompName();
        String organizer=comp.getOrganizer();
        Integer level=comp.getLevel();
        String subjectType=comp.getSubjectType();
        String place=comp.getPlace();
        Integer compState=comp.getCompState();
        return compService.getCompsBySelf(compName,organizer,level,subjectType,place,compState);
    }

    /**
     * 根据Id返回竞赛的项目
     */
    @GetMapping("/getProjectLists/{compId}")
    public List<Project> getProjectLists(@PathVariable Integer compId){
        List<Project> projectsBycompId = projectService.getProjectsBycompId(compId);
        return projectService.getProjectsBycompId(compId);
    }

}
