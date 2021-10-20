package com.sang.subjectcompetition.service.impl;

import com.sang.subjectcompetition.entity.College;
import com.sang.subjectcompetition.entity.College_Comp;
import com.sang.subjectcompetition.entity.Student_Project;
import com.sang.subjectcompetition.entity.Teacher_Project;
import com.sang.subjectcompetition.respository.CollegeRepository;
import com.sang.subjectcompetition.respository.Student_ProjectRepository;
import com.sang.subjectcompetition.service.College_CompService;
import com.sang.subjectcompetition.service.DateService;
import com.sang.subjectcompetition.service.Student_ProjectService;
import com.sang.subjectcompetition.service.Teacher_ProjectService;
import com.sang.subjectcompetition.service.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DateServiceImpl implements DateService {
    
    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private College_CompService college_compService;

    @Autowired
    private Student_ProjectService student_projectService;

    @Autowired
    private Teacher_ProjectService teacher_projectService;

    @Autowired
    private Student_ProjectRepository student_projectRepository;

    /**
     * 根据时间获取所有学院参加
     * @param year
     * @return
     */
    @Override
    public Map<String, Object> getAllCollegeJoinCompInfoByDate(int year) {
        Map<String,Object> infoMapDate=new HashMap<>();
        List<Map<String , Object>> eachData=new ArrayList<>();//所有学院的数据
        List<String> showDate=new ArrayList<>();//日期
        List<String> collegeName=new ArrayList<>();//学院名称
        List<College> colleges = collegeRepository.findAll();
        for (College college : colleges) {
            Map<String , Object> eachDataForCollege=new HashMap<>();//每个学院的数据
            List<Integer> eachMoth=new ArrayList<>();//单个院系往前12月的数据
            Date[] dateArray = DateUtils.getDateArray(year);
            for(int i = 0 ; i < dateArray.length-1 ; i++){
                int count =0;
                List<College_Comp> collegeComps = college_compService.findAllByDate(dateArray[i],dateArray[i+1]);
                if(collegeComps!=null){
                    for (College_Comp collegeComp : collegeComps) {
                        if(collegeComp.getApplyOrJoin()!=2&&college.getId()==collegeComp.getCollege().getId())
                            count++;
                    }
                }
                eachMoth.add(count);
            }
            collegeName.add(college.getNickname());
            eachDataForCollege.put("name",college.getNickname());
            eachDataForCollege.put("type","line");
            eachDataForCollege.put("smooth",false);
            eachDataForCollege.put("data",eachMoth);
            eachData.add(eachDataForCollege);

        }
        Date[] dateArray = DateUtils.getDateArray(year);
        for (int i = 0 ; i < dateArray.length - 1 ; i++){
            Calendar instance = Calendar.getInstance();
            instance.setTime(dateArray[i]);
            String date = "";
            if(year==0 ){//过去12个月
                if(DateUtils.getNowYear()>instance.get(Calendar.YEAR)){
                    date = "去年";
                }
            }
            date = date + (instance.get(Calendar.MONTH)+1)+"月";
            showDate.add(date);
        }

        Collections.reverse(collegeName);
        Collections.reverse(eachData);
        infoMapDate.put("date",showDate);
        infoMapDate.put("collegeInfo",eachData);
        infoMapDate.put("collegeNames",collegeName);
        return infoMapDate;
    }

    /**
     * 根据时间学生参加项目竞赛
     * @param year
     * @return
     */
    @Override
    public Map<String, Object> getAllStudentJoinProjectByDate(int year) {
        Map<String,Object> infoMapDate=new HashMap<>();
        List<Map<String , Object>> eachData=new ArrayList<>();//所有学院的数据
        List<College> colleges = collegeRepository.findAll();
        List<String> showDate=new ArrayList<>();
        List<String> collegeName=new ArrayList<>();
        for (College college : colleges) {
            Map<String , Object> eachDataForCollege=new HashMap<>();//每个学院的数据
            List<Integer> eachMoth=new ArrayList<>();//单个院系往前12月的数据
            Date[] dateArray = DateUtils.getDateArray(year);
            for(int i = 0 ; i < dateArray.length-1 ; i++){
                List<Student_Project> student_projects = student_projectService.findAllByDate(dateArray[i],dateArray[i+1]);
                int count =0;
                for (Student_Project student_project : student_projects) {
                    if(student_project.getApplyOrJoin()!=2&&college.getId()==student_project.getStudent().getCollege().getId())
                        count++;
                }
                eachMoth.add(count);
            }
            collegeName.add(college.getNickname());
            eachDataForCollege.put("name",college.getNickname());
            eachDataForCollege.put("type","line");
            eachDataForCollege.put("smooth",false);
            eachDataForCollege.put("data",eachMoth);
            eachData.add(eachDataForCollege);
        }
        Date[] dateArray = DateUtils.getDateArray(year);
        for (int i = 0 ; i < dateArray.length - 1 ; i++){
            Calendar instance = Calendar.getInstance();
            instance.setTime(dateArray[i]);
            String date = "";
            if(year==0 ){//过去12个月
                if(DateUtils.getNowYear()>instance.get(Calendar.YEAR)){
                    date = "去年";
                }
            }
            date = date + (instance.get(Calendar.MONTH)+1)+"月";
            showDate.add(date);
        }
        Collections.reverse(collegeName);
        Collections.reverse(eachData);
        infoMapDate.put("date",showDate);
        infoMapDate.put("collegeInfo",eachData);
        infoMapDate.put("collegeNames",collegeName);
        return infoMapDate;
    }

    /**
     * 根据时间查询学院申请参加比赛信息
     * @param year
     * @return
     */
    @Override
    public Map<String, Object> getAllCollegeApplyProjectByDate(int year) {
        Map<String,Object> infoMapDate=new HashMap<>();
        List<Map<String , Object>> eachData=new ArrayList<>();//所有学院的数据
        List<College> colleges = collegeRepository.findAll();
        List<String> showDate=new ArrayList<>();
        List<String> collegeName=new ArrayList<>();
        for (College college : colleges) {
            Map<String , Object> eachDataForCollege=new HashMap<>();//每个学院的数据
            List<Integer> eachMoth=new ArrayList<>();//单个院系往前12月的数据
            Date[] dateArray = DateUtils.getDateArray(year);
            for(int i = 0 ; i < dateArray.length-1 ; i++){
                List<Teacher_Project> teacher_projects = teacher_projectService.findAllByDate(dateArray[i],dateArray[i+1]);
                int count =0;
                for (Teacher_Project teacher_project : teacher_projects) {
                    if(teacher_project.getApplyOrJoin()!=2&&teacher_project.getIsCreator()==true&&college.getId()==teacher_project.getProject().getCreateByTeacher().getCollege().getId())
                        count++;
                }
                eachMoth.add(count);
            }
            collegeName.add(college.getNickname());
            eachDataForCollege.put("name",college.getNickname());
            eachDataForCollege.put("type","line");
            eachDataForCollege.put("smooth",false);
            eachDataForCollege.put("data",eachMoth);
            eachData.add(eachDataForCollege);
        }
        Date[] dateArray = DateUtils.getDateArray(year);
        for (int i = 0 ; i < dateArray.length - 1 ; i++){
            Calendar instance = Calendar.getInstance();
            instance.setTime(dateArray[i]);
            String date = "";
            if(year==0 ){//过去12个月
                if(DateUtils.getNowYear()>instance.get(Calendar.YEAR)){
                    date = "去年";
                }
            }
            date = date + (instance.get(Calendar.MONTH)+1)+"月";
            showDate.add(date);
        }

        Collections.reverse(collegeName);
        Collections.reverse(eachData);
        infoMapDate.put("date",showDate);
        infoMapDate.put("collegeInfo",eachData);
        infoMapDate.put("collegeNames",collegeName);
        return infoMapDate;
    }

    @Override
    public Map<String, Object> getAllStudentJoinProjectInfo(int year) {
        Map<String ,Object> infoMap=new HashMap<>();
        List<College> colleges = collegeRepository.findAll();
        Date[] dates = DateUtils.getFirstAndLastDay(year);
        List<Student_Project> all = student_projectService.findAllByDate(dates[0],dates[1]);
        List<Map<String ,Object>> maps=new ArrayList<>();
        Integer sum=0;
        for (College college : colleges) {
            int count=0;
            Map<String ,Object> map=new HashMap<>();
            for (Student_Project student_project : all) {
                if(student_project.getStudent().getCollege().getId()==college.getId()&&student_project.getApplyOrJoin()!=2)
                    count++;
            }
            map.put("name",college.getNickname());
            map.put("value",count);
            maps.add(map);
            sum+=count;
        }
        infoMap.put("collegeInfo",maps);
        infoMap.put("sum",sum);
        return infoMap;
    }

    @Override
    public Map<String, Object> getAllCollegeApplyProjectInfo(int year) {
        Map<String ,Object> infoMap=new HashMap<>();
        List<College> colleges = collegeRepository.findAll();
        Date[] dates = DateUtils.getFirstAndLastDay(year);
        List<Teacher_Project> all = teacher_projectService.findAllByDate(dates[0],dates[1]);
        List<Map<String ,Object>> maps=new ArrayList<>();
        Integer sum=0;
        for (College college : colleges) {
            int count=0;
            Map<String ,Object> map=new HashMap<>();
            for (Teacher_Project teacher_project : all) {
                if (teacher_project.getIsCreator()==true&&teacher_project.getProject().getCreateByTeacher().getCollege().getId()==college.getId()&&teacher_project.getApplyOrJoin()!=2)
                    count++;
            }
            map.put("name",college.getNickname());
            map.put("value",count);
            sum+=count;
            maps.add(map);
        }
        infoMap.put("collegeInfo",maps);
        infoMap.put("sum",sum);
        return infoMap;
    }

    @Override
    public Map<String, Object> getAllCollegeJoinCompInfo(int year) {
        Map<String ,Object> infoMap=new HashMap<>();
        List<College> colleges = collegeRepository.findAll();
        Date[] dates = DateUtils.getFirstAndLastDay(year);
        List<College_Comp> collegeComps = college_compService.findAllByDate(dates[0],dates[1]);
        List<Map<String ,Object>> maps=new ArrayList<>();
        Integer sum=0;
        for (College college : colleges) {
            Map<String ,Object> map=new HashMap<>();
            int count=0;
            for (College_Comp collegeComp : collegeComps) {
                if(collegeComp.getCollege().getId()==college.getId()&&collegeComp.getApplyOrJoin()!=2)
                    count++;
            }
            map.put("name",college.getNickname());
            map.put("value",count);
            sum+=count;
            maps.add(map);
        }
        infoMap.put("collegeInfo",maps);
        infoMap.put("sum",sum);
        return infoMap;
    }
}
