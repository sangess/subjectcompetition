package com.sang.subjectcompetition.controller;

import com.sang.subjectcompetition.entity.*;
import com.sang.subjectcompetition.entity.resultInfo.ProResult;
import com.sang.subjectcompetition.respository.AwardsRepository;
import com.sang.subjectcompetition.service.AwardsService;
import com.sang.subjectcompetition.service.CollegeService;
import com.sang.subjectcompetition.service.ProjectService;
import com.sang.subjectcompetition.service.Teacher_ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/awards")
public class AwardsController {

    @Autowired
    private AwardsService awardsService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private AwardsRepository awardsRepository;
    /**
     * 展示院系的未处理的奖项申请请求
     * @param collegeId
     * @return
     */
    @GetMapping("/getUndoApplyAwardsList/{collegeId}")
    public List<Awards> getUndoApplyAwardsList(@PathVariable Integer collegeId){
        List<Awards> awards=new ArrayList<>();
        List<Awards> awardsList = awardsRepository.findAll();
        for (Awards awardsItem : awardsList) {
            if (awardsItem.getProject().getCreateByTeacher().getCollege().getId()==collegeId&&awardsItem.getState()==1){
                awards.add(awardsItem);
            }
        }
        awards.sort(Comparator.naturalOrder());
        return awards;

    }

    /**
     * 展示院系的处理过的奖项申请请求
     * @param collegeId
     * @return
     */
    @GetMapping("/getDoneApplyAwardsList/{collegeId}")
    public List<Awards> getDoneApplyAwardsList(@PathVariable Integer collegeId){

        List<Awards> awards=new ArrayList<>();
        List<Awards> awardsList = awardsRepository.findAll();
        for (Awards awardsItem : awardsList) {
            if (awardsItem.getProject().getCreateByTeacher().getCollege().getId()==collegeId&&(awardsItem.getState()==2||awardsItem.getState()==3)){
                awards.add(awardsItem);
            }
        }
        awards.sort(Comparator.naturalOrder());
        return awards;
    }

    /**
     * 学院端同意奖项申请
     * @param awardsId
     * @return
     */
    @GetMapping("/applyAwards/{awardsId}")
    public ProResult applyAwards(@PathVariable Integer awardsId){
        return awardsService.applyAwards(awardsId);
    }

    /**
     * 学院端拒绝奖项申请
     * @param awardsId
     * @return
     */
    @GetMapping("/invokeAwards/{awardsId}")
    public ProResult invokeAwards(@PathVariable Integer awardsId){
        return awardsService.invokeAwards(awardsId);
    }

    /**
     * 展示所有的奖项
     * @return
     */
    @GetMapping("/showAwards")
    public List<Awards> showAwards(){
        List<Awards> awards = awardsService.findAll();
        awards.sort(Comparator.naturalOrder());
        return awards;
    }
}

