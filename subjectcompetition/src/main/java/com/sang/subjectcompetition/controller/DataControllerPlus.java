package com.sang.subjectcompetition.controller;

import com.alibaba.fastjson.JSONObject;
import com.sang.subjectcompetition.entity.resultInfo.ProResult;
import com.sang.subjectcompetition.entity.resultInfo.WxResult;
import com.sang.subjectcompetition.service.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("datas")
public class DataControllerPlus {

    @Autowired
    private DateService dateService;

    @GetMapping("getAllCollegeJoinCompInfoByDate/{year}")
    public Map<String , Object> getAllCollegeJoinCompInfoByDate(@PathVariable Integer year){
        return dateService.getAllCollegeJoinCompInfoByDate(year);
    }

    @GetMapping("getAllStudentJoinProjectByDate/{year}")
    public Map<String,Object> getAllStudentJoinProjectByDate(@PathVariable Integer year){
        return dateService.getAllStudentJoinProjectByDate(year);
    }

    @GetMapping("getAllCollegeApplyProjectByDate/{year}")
    public Map<String,Object> getAllCollegeApplyProjectByDate(@PathVariable Integer year){
        return dateService.getAllCollegeApplyProjectByDate(year);
    }

    @GetMapping("getAllStudentJoinProjectInfo/{year}")
    public Map<String,Object> getAllStudentJoinProjectInfo(@PathVariable Integer year){
        return dateService.getAllStudentJoinProjectInfo(year);
    }

    @GetMapping("getAllCollegeApplyProjectInfo/{year}")
    public Map<String,Object> getAllCollegeApplyProjectInfo(@PathVariable Integer year){
        return dateService.getAllCollegeApplyProjectInfo(year);
    }

    @GetMapping("getAllCollegeJoinCompInfo/{year}")
    public Map<String,Object> getAllCollegeJoinCompInfo(@PathVariable Integer year){
        return dateService.getAllCollegeJoinCompInfo(year);
    }

    @GetMapping("getAllCollegeApplyProjectMoreYear/{yearCount}")
    public JSONObject getAllCollegeApplyProjectMoreYear(@PathVariable Integer yearCount){
        return dateService.getAllCollegeApplyProjectMoreYear(yearCount);
    }

    @GetMapping("getAllCollegeJoinCompMoreYear/{yearCount}")
    public JSONObject getAllCollegeJoinCompMoreYear(@PathVariable Integer yearCount){
        return dateService.getAllCollegeJoinCompMoreYear(yearCount);
    }

    @GetMapping("getAllStudentJoinProjectMoreYear/{yearCount}")
    public JSONObject getAllStudentJoinProjectMoreYear(@PathVariable Integer yearCount){
        return dateService.getAllStudentJoinProjectMoreYear(yearCount);
    }

    @GetMapping("getAllCollegeGetAwardsByDate/{yearCount}/{type}/{level}")
    public Map<String,Object> getAllCollegeGetAwardsByDate(@PathVariable Integer yearCount,@PathVariable Integer type,@PathVariable Integer level){
        return dateService.getAllCollegeGetAwardsByDate(yearCount,type,level);
    }

    @GetMapping("getAllCollegeGetAwardsInfo/{yearCount}/{type}/{level}")
    public Map<String,Object> getAllCollegeGetAwardsInfo(@PathVariable Integer yearCount,@PathVariable Integer type,@PathVariable Integer level){
        return dateService.getAllCollegeGetAwardsInfo(yearCount,type,level);
    }

    @PostMapping("getAllCollegeAwardsMoreYear")
    public JSONObject getAllCollegeGetAwardsMoreYear(@RequestBody Map<String,Object> map){
        Integer year = (Integer)map.get("year");
        List<Integer> typeArry = (List<Integer>) map.get("typeArry");
        List<Integer> levelArry = (List<Integer>) map.get("levelArry");
        return dateService.getAllCollegeGetAwardsMoreYear(year,typeArry,levelArry);
    }

}
