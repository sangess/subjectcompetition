package com.sang.subjectcompetition.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public interface DateService {
    Map<String,Object> getAllCollegeJoinCompInfoByDate(int year);

    Map<String,Object> getAllStudentJoinProjectByDate(int year);

    Map<String,Object> getAllCollegeApplyProjectByDate(int year);

    Map<String,Object> getAllStudentJoinProjectInfo(int year);

    Map<String,Object> getAllCollegeApplyProjectInfo(int year);

    Map<String,Object> getAllCollegeJoinCompInfo(int year);

    JSONObject getAllCollegeApplyProjectMoreYear(Integer yearCount);

    JSONObject getAllCollegeJoinCompMoreYear(Integer yearCount);

    JSONObject getAllStudentJoinProjectMoreYear(Integer yearCount);

    Map<String,Object> getAllCollegeGetAwardsByDate(int year,Integer type,Integer leve );

    Map<String,Object> getAllCollegeGetAwardsInfo(int year,Integer type,Integer leve );

    JSONObject getAllCollegeGetAwardsMoreYear(Integer yearCount, List<Integer> typeArry,List<Integer> levelArry);

}
