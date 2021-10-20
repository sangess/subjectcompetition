package com.sang.subjectcompetition.controller;

import com.sang.subjectcompetition.service.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
