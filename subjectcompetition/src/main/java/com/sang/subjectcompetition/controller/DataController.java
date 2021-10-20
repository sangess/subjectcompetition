package com.sang.subjectcompetition.controller;

import com.sang.subjectcompetition.entity.College;
import com.sang.subjectcompetition.entity.College_Comp;
import com.sang.subjectcompetition.entity.Student_Project;
import com.sang.subjectcompetition.entity.Teacher_Project;
import com.sang.subjectcompetition.respository.CollegeRepository;
import com.sang.subjectcompetition.respository.College_CompRepository;
import com.sang.subjectcompetition.respository.Student_ProjectRepository;
import com.sang.subjectcompetition.respository.Teacher_ProjectRepository;
import com.sang.subjectcompetition.service.CollegeService;
import com.sang.subjectcompetition.service.College_CompService;
import com.sang.subjectcompetition.service.Student_ProjectService;
import com.sang.subjectcompetition.service.Teacher_ProjectService;
import com.sang.subjectcompetition.service.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private College_CompRepository college_compRepository;

    @Autowired
    private College_CompService college_compService;

    @Autowired
    private Teacher_ProjectService teacher_projectService;

    @Autowired
    private Student_ProjectService student_projectService;

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private Student_ProjectRepository student_projectRepository;

    @Autowired
    private Teacher_ProjectRepository teacher_projectRepository;

    //----------------------------------admin端--------------------------------------------

    @GetMapping("/getAllCollegeJoinCompInfo")
    public Map<String ,Object> getAllCollegeInfo(){
        Map<String ,Object> infoMap=new HashMap<>();
        List<College> colleges = collegeRepository.findAll();
        List<College_Comp> collegeComps = college_compRepository.findAll();
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

    @GetMapping("/getAllStudentJoinProjectInfo")
    public Map<String ,Object> getAllStudentJoinProjectInfo(){
        Map<String ,Object> infoMap=new HashMap<>();
        List<College> colleges = collegeRepository.findAll();
        List<Student_Project> all = student_projectRepository.findAll();
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
            sum+=count;
            maps.add(map);
        }
        infoMap.put("collegeInfo",maps);
        infoMap.put("sum",sum);
        return infoMap;
    }

    @GetMapping("/getAllCollegeApplyProjectInfo")
    public Map<String ,Object> getAllCollegeApplyProjectInfo(){
        Map<String ,Object> infoMap=new HashMap<>();
        List<College> colleges = collegeRepository.findAll();
        List<Teacher_Project> all = teacher_projectRepository.findAll();
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

    @GetMapping("/getAllCollegeJoinCompInfoByDate")
    public Map<String,Object> getAllCollegeJoinCompInfoByDate(){
        Map<String,Object> infoMapDate=new HashMap<>();
        List<Map<String , Object>> eachData=new ArrayList<>();//所有学院的数据

        Date endDate=new Date();//当前时间
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH,c.getActualMinimum(Calendar.DAY_OF_MONTH));//本月第一天
        Date startDate=c.getTime();//本月第一天
        List<College> colleges = collegeRepository.findAll();
        List<String> showDate=new ArrayList<>();
        List<String> collegeName=new ArrayList<>();
        for (College college : colleges) {
            Map<String , Object> eachDataForCollege=new HashMap<>();//每个学院的数据
            List<Integer> eachMoth=new ArrayList<>();//单个院系往前12月的数据
            endDate=new Date();
            Calendar calendar=Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH,c.getActualMinimum(Calendar.DAY_OF_MONTH));//本月第一天
            startDate=calendar.getTime();
            for (int i =0;i<12;i++){
                List<College_Comp> collegeComps = college_compService.findAllByDate(startDate,endDate);
                int count =0;
                for (College_Comp collegeComp : collegeComps) {
                    if(collegeComp.getApplyOrJoin()!=2&&college.getId()==collegeComp.getCollege().getId())
                        count++;
                }
                calendar.add(Calendar.MONTH,-1);
                endDate=startDate;
                startDate=calendar.getTime();
                eachMoth.add(count);
            }
            Collections.reverse(eachMoth);
            collegeName.add(college.getNickname());
            eachDataForCollege.put("name",college.getNickname());
            eachDataForCollege.put("type","line");
            eachDataForCollege.put("smooth",false);
            eachDataForCollege.put("data",eachMoth);
            eachData.add(eachDataForCollege);
        }
        Calendar calendar=Calendar.getInstance();
        for (int i =0;i<12;i++){

            if (calendar.get(Calendar.YEAR)<c.get(Calendar.YEAR)){
                showDate.add("去年"+(calendar.get(Calendar.MONTH)+1)+"月");
            }else{
                showDate.add((calendar.get(Calendar.MONTH)+1)+"月");
            }
            calendar.add(Calendar.MONTH,-1);
        }
        Collections.reverse(showDate);
        Collections.reverse(collegeName);
        Collections.reverse(eachData);
        infoMapDate.put("date",showDate);
        infoMapDate.put("collegeInfo",eachData);
        infoMapDate.put("collegeNames",collegeName);
        return infoMapDate;
    }

    @GetMapping("/getAllStudentJoinProjectByDate")
    public Map<String,Object> getAllStudentJoinProjectByDate(){
        Map<String,Object> infoMapDate=new HashMap<>();
        List<Map<String , Object>> eachData=new ArrayList<>();//所有学院的数据
        Date endDate=new Date();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH,c.getActualMinimum(Calendar.DAY_OF_MONTH));//本月第一天
        Date startDate=c.getTime();
        List<College> colleges = collegeRepository.findAll();
        List<String> showDate=new ArrayList<>();
        List<String> collegeName=new ArrayList<>();
        for (College college : colleges) {
            Map<String , Object> eachDataForCollege=new HashMap<>();//每个学院的数据
            List<Integer> eachMoth=new ArrayList<>();//单个院系往前12月的数据
            endDate=new Date();
            Calendar calendar=Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH,c.getActualMinimum(Calendar.DAY_OF_MONTH));//本月第一天
            startDate=calendar.getTime();
            for (int i =0;i<12;i++){
                List<Student_Project> student_projects = student_projectService.findAllByDate(startDate,endDate);
                int count =0;
                for (Student_Project student_project : student_projects) {
                    if(student_project.getApplyOrJoin()!=2&&college.getId()==student_project.getStudent().getCollege().getId())
                        count++;
                }
                calendar.add(Calendar.MONTH,-1);
                endDate=startDate;
                startDate=calendar.getTime();
                eachMoth.add(count);
            }
            Collections.reverse(eachMoth);
            collegeName.add(college.getNickname());
            eachDataForCollege.put("name",college.getNickname());
            eachDataForCollege.put("type","line");
            eachDataForCollege.put("smooth",false);
            eachDataForCollege.put("data",eachMoth);
            eachData.add(eachDataForCollege);
        }
        Calendar calendar=Calendar.getInstance();
        for (int i =0;i<12;i++){

            if (calendar.get(Calendar.YEAR)<c.get(Calendar.YEAR)){
                showDate.add("去年"+(calendar.get(Calendar.MONTH)+1)+"月");
            }else{
                showDate.add((calendar.get(Calendar.MONTH)+1)+"月");
            }
            calendar.add(Calendar.MONTH,-1);
        }
        Collections.reverse(showDate);
        Collections.reverse(collegeName);
        Collections.reverse(eachData);
        infoMapDate.put("date",showDate);
        infoMapDate.put("collegeInfo",eachData);
        infoMapDate.put("collegeNames",collegeName);
        return infoMapDate;
    }

    @GetMapping("/getAllCollegeApplyProjectByDate")
    public Map<String,Object> getAllCollegeApplyProjectByDate(){
        Map<String,Object> infoMapDate=new HashMap<>();
        List<Map<String , Object>> eachData=new ArrayList<>();//所有学院的数据
        Date endDate=new Date();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH,c.getActualMinimum(Calendar.DAY_OF_MONTH));//本月第一天
        Date startDate=c.getTime();
        List<College> colleges = collegeRepository.findAll();
        List<String> showDate=new ArrayList<>();
        List<String> collegeName=new ArrayList<>();
        for (College college : colleges) {
            Map<String , Object> eachDataForCollege=new HashMap<>();//每个学院的数据
            List<Integer> eachMoth=new ArrayList<>();//单个院系往前12月的数据
            endDate=new Date();
            Calendar calendar=Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH,c.getActualMinimum(Calendar.DAY_OF_MONTH));//本月第一天
            startDate=calendar.getTime();
            for (int i =0;i<12;i++){
                List<Teacher_Project> teacher_projects = teacher_projectService.findAllByDate(startDate,endDate);
                int count =0;
                for (Teacher_Project teacher_project : teacher_projects) {
                    if(teacher_project.getApplyOrJoin()!=2&&teacher_project.getIsCreator()==true&&college.getId()==teacher_project.getProject().getCreateByTeacher().getCollege().getId())
                        count++;
                }
                calendar.add(Calendar.MONTH,-1);
                endDate=startDate;
                startDate=calendar.getTime();
                eachMoth.add(count);
            }
            Collections.reverse(eachMoth);
            collegeName.add(college.getNickname());
            eachDataForCollege.put("name",college.getNickname());
            eachDataForCollege.put("type","line");
            eachDataForCollege.put("smooth",false);
            eachDataForCollege.put("data",eachMoth);
            eachData.add(eachDataForCollege);
        }
        Calendar calendar=Calendar.getInstance();
        for (int i =0;i<12;i++){

            if (calendar.get(Calendar.YEAR)<c.get(Calendar.YEAR)){
                showDate.add("去年"+(calendar.get(Calendar.MONTH)+1)+"月");
            }else{
                showDate.add((calendar.get(Calendar.MONTH)+1)+"月");
            }
            calendar.add(Calendar.MONTH,-1);
        }
        Collections.reverse(showDate);
        Collections.reverse(collegeName);
        Collections.reverse(eachData);
        infoMapDate.put("date",showDate);
        infoMapDate.put("collegeInfo",eachData);
        infoMapDate.put("collegeNames",collegeName);
        return infoMapDate;
    }

    @GetMapping("/getStudentJoinCompInfo")
    public Map<String,Integer> getStudentJoinCompInfo(){
        Map<String,Integer> map=new HashMap<>();
        Date today = DateUtils.getStartTimeOfDay(new Date());
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        Date yesterday = calendar.getTime();
        calendar.add(Calendar.DATE,-6);
        Date weekDay = calendar.getTime();

        List<Student_Project> studentProjectWeek = student_projectService.findAllByDate(weekDay, today);
        int week=0;
        for (Student_Project student_project : studentProjectWeek) {
            if(student_project.getApplyOrJoin()!=2&&student_project.getProject().getComp().getCompState()<4)
                week++;
        }

        List<Student_Project> studentProjects = student_projectService.findAllByDate(yesterday, today);
        int count=0;
        for (Student_Project studentProject : studentProjects) {
            if(studentProject.getApplyOrJoin()!=2&&studentProject.getProject().getComp().getCompState()<4)
                count++;
        }

        List<Student_Project> all = student_projectRepository.findAll();
        int nowCount =0;
        for (Student_Project student_project : all) {
            if(student_project.getApplyOrJoin()!=2&&student_project.getProject().getComp().getCompState()<4)
                nowCount++;
        }
        map.put("nowCount",nowCount);
        map.put("yesterday",count);
        map.put("week",week);
        return map;
    }

    @GetMapping("/getCollegesJoinCompInfo")
    public Map<String ,Integer> getCollegesJoinCompInfo(){
        Map<String,Integer> map=new HashMap<>();
        Date today = DateUtils.getStartTimeOfDay(new Date());
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        Date yesterday = calendar.getTime();
        calendar.add(Calendar.DATE,-6);
        Date weekDay = calendar.getTime();

        List<College_Comp> college_compWeek = college_compService.findAllByDate(weekDay, today);
        int week=0;
        for (College_Comp college_comp : college_compWeek) {
            if(college_comp.getApplyOrJoin()!=2&&college_comp.getComp().getCompState()<4)
                week++;
        }

        List<College_Comp> college_comps = college_compService.findAllByDate(yesterday, today);
        int count=0;
        for (College_Comp college_comp : college_comps) {
            if(college_comp.getApplyOrJoin()!=2&&college_comp.getComp().getCompState()<4)
                count++;
        }

        List<College_Comp> all = college_compRepository.findAll();
        int nowCount =0;
        for (College_Comp college_comp  : all) {
            if(college_comp.getApplyOrJoin()!=2&&college_comp.getComp().getCompState()<4)
                nowCount++;
        }
        map.put("nowCount",nowCount);
        map.put("yesterday",count);
        map.put("week",week);
        return map;
    }

    @GetMapping("/getCollegeApplyCompInfo")
    public Map<String,Integer> getCollegeApplyCompInfo(){
        Map<String,Integer> map=new HashMap<>();
        Date today = DateUtils.getStartTimeOfDay(new Date());
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        Date yesterday = calendar.getTime();
        calendar.add(Calendar.DATE,-6);
        Date weekDay = calendar.getTime();

        List<Teacher_Project> Teacher_ProjectWeek = teacher_projectService.findAllByDate(weekDay, today);
        int week=0;
        for (Teacher_Project teacher_project : Teacher_ProjectWeek) {
            if(teacher_project.getApplyOrJoin()!=2&&teacher_project.getIsCreator()==true&&teacher_project.getProject().getComp().getCompState()<4)
                week++;
        }

        List<Teacher_Project> Teacher_Projects = teacher_projectService.findAllByDate(yesterday, today);
        int count=0;
        for (Teacher_Project teacher_project : Teacher_Projects) {
            if(teacher_project.getApplyOrJoin()!=2&&teacher_project.getIsCreator()==true&&teacher_project.getProject().getComp().getCompState()<4)
                count++;
        }

        List<Teacher_Project> all = teacher_projectRepository.findAll();
        int nowCount =0;
        for (Teacher_Project teacher_project  : all) {
            if(teacher_project.getApplyOrJoin()!=2&&teacher_project.getIsCreator()==true&&teacher_project.getProject().getComp().getCompState()<4)
                nowCount++;
        }
        map.put("nowCount",nowCount);
        map.put("yesterday",count);
        map.put("week",week);
        return map;
    }

    //---------------------------------college端-------------------------------------

    @GetMapping("/getStudentJoinCompInfoInCollege/{collegeId}")
    public Map<String,Integer> getStudentJoinCompInfoInCollege(@PathVariable Integer collegeId){
        Map<String,Integer> map=new HashMap<>();
        Date today = DateUtils.getStartTimeOfDay(new Date());
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        Date yesterday = calendar.getTime();
        calendar.add(Calendar.DATE,-6);
        Date weekDay = calendar.getTime();

        List<Student_Project> studentProjectWeek = student_projectService.findAllByDate(weekDay, today);
        int week=0;
        for (Student_Project student_project : studentProjectWeek) {
            if(student_project.getApplyOrJoin()!=2&&student_project.getProject().getComp().getCompState()<4&&collegeId==student_project.getStudent().getCollege().getId())
                week++;
        }

        List<Student_Project> studentProjects = student_projectService.findAllByDate(yesterday, today);
        int count=0;
        for (Student_Project studentProject : studentProjects) {
            if(studentProject.getApplyOrJoin()!=2&&studentProject.getProject().getComp().getCompState()<4&&collegeId==studentProject.getStudent().getCollege().getId())
                count++;
        }

        List<Student_Project> all = student_projectRepository.findAll();
        int nowCount =0;
        for (Student_Project student_project : all) {
            if(student_project.getApplyOrJoin()!=2&&student_project.getProject().getComp().getCompState()<4&&collegeId==student_project.getStudent().getCollege().getId())
                nowCount++;
        }
        map.put("nowCount",nowCount);
        map.put("yesterday",count);
        map.put("week",week);
        return map;
    }

    @GetMapping("/getCollegesJoinCompInfoInCollege/{collegeId}")
    public Map<String ,Integer> getCollegesJoinCompInfoInCollege(@PathVariable Integer collegeId){
        Map<String,Integer> map=new HashMap<>();
        Date today = DateUtils.getStartTimeOfDay(new Date());
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        Date yesterday = calendar.getTime();
        calendar.add(Calendar.DATE,-6);
        Date weekDay = calendar.getTime();

        List<College_Comp> college_compWeek = college_compService.findAllByDate(weekDay, today);
        int week=0;
        for (College_Comp college_comp : college_compWeek) {
            if(college_comp.getApplyOrJoin()!=2&&college_comp.getComp().getCompState()<4&&collegeId==college_comp.getCollege().getId())
                week++;
        }

        List<College_Comp> college_comps = college_compService.findAllByDate(yesterday, today);
        int count=0;
        for (College_Comp college_comp : college_comps) {
            if(college_comp.getApplyOrJoin()!=2&&college_comp.getComp().getCompState()<4&&collegeId==college_comp.getCollege().getId())
                count++;
        }

        List<College_Comp> all = college_compRepository.findAll();
        int nowCount =0;
        for (College_Comp college_comp  : all) {
            if(college_comp.getApplyOrJoin()!=2&&college_comp.getComp().getCompState()<4&&collegeId==college_comp.getCollege().getId())
                nowCount++;
        }
        map.put("nowCount",nowCount);
        map.put("yesterday",count);
        map.put("week",week);
        return map;
    }

    @GetMapping("/getCollegeApplyCompInfoInCollege/{collegeId}")
    public Map<String,Integer> getCollegeApplyCompInfoInCollege(@PathVariable Integer collegeId){
        Map<String,Integer> map=new HashMap<>();
        Date today = DateUtils.getStartTimeOfDay(new Date());
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        Date yesterday = calendar.getTime();
        calendar.add(Calendar.DATE,-6);
        Date weekDay = calendar.getTime();

        List<Teacher_Project> Teacher_ProjectWeek = teacher_projectService.findAllByDate(weekDay, today);
        int week=0;
        for (Teacher_Project teacher_project : Teacher_ProjectWeek) {
            if(teacher_project.getApplyOrJoin()!=2&&teacher_project.getIsCreator()==true&&teacher_project.getProject().getComp().getCompState()<4&&collegeId==teacher_project.getProject().getCreateByTeacher().getCollege().getId())
                week++;
        }

        List<Teacher_Project> Teacher_Projects = teacher_projectService.findAllByDate(yesterday, today);
        int count=0;
        for (Teacher_Project teacher_project : Teacher_Projects) {
            if(teacher_project.getApplyOrJoin()!=2&&teacher_project.getIsCreator()==true&&teacher_project.getProject().getComp().getCompState()<4&&collegeId==teacher_project.getProject().getCreateByTeacher().getCollege().getId())
                count++;
        }

        List<Teacher_Project> all = teacher_projectRepository.findAll();
        int nowCount =0;
        for (Teacher_Project teacher_project  : all) {
            if(teacher_project.getApplyOrJoin()!=2&&teacher_project.getIsCreator()==true&&teacher_project.getProject().getComp().getCompState()<4&&collegeId==teacher_project.getProject().getCreateByTeacher().getCollege().getId())
                nowCount++;
        }
        map.put("nowCount",nowCount);
        map.put("yesterday",count);
        map.put("week",week);
        return map;
    }


}

