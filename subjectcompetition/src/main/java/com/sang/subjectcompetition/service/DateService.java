package com.sang.subjectcompetition.service;

import java.util.Map;

public interface DateService {
    Map<String,Object> getAllCollegeJoinCompInfoByDate(int year);

    Map<String,Object> getAllStudentJoinProjectByDate(int year);

    Map<String,Object> getAllCollegeApplyProjectByDate(int year);

    Map<String,Object> getAllStudentJoinProjectInfo(int year);

    Map<String,Object> getAllCollegeApplyProjectInfo(int year);

    Map<String,Object> getAllCollegeJoinCompInfo(int year);
}
