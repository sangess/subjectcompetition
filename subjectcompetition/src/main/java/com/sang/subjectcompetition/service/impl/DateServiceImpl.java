package com.sang.subjectcompetition.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sang.subjectcompetition.entity.*;
import com.sang.subjectcompetition.entity.enums.Level;
import com.sang.subjectcompetition.entity.enums.Type;
import com.sang.subjectcompetition.respository.CollegeRepository;
import com.sang.subjectcompetition.respository.Student_ProjectRepository;
import com.sang.subjectcompetition.service.*;
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
    private AwardsService awardsService;

    /**
     * 根据时间获取所有学院参加
     *
     * @param year
     * @return
     */
    @Override
    public Map<String, Object> getAllCollegeJoinCompInfoByDate(int year) {
        Map<String, Object> infoMapDate = new HashMap<>();
        List<Map<String, Object>> eachData = new ArrayList<>();//所有学院的数据
        List<String> showDate = new ArrayList<>();//日期
        List<String> collegeName = new ArrayList<>();//学院名称
        List<College> colleges = collegeRepository.findAll();
        for (College college : colleges) {
            Map<String, Object> eachDataForCollege = new HashMap<>();//每个学院的数据
            List<Integer> eachMoth = new ArrayList<>();//单个院系往前12月的数据
            Date[] dateArray = DateUtils.getDateArray(year);
            for (int i = 0; i < dateArray.length - 1; i++) {
                int count = 0;
                List<College_Comp> collegeComps = college_compService.findAllByDate(dateArray[i], dateArray[i + 1]);
                if (collegeComps != null) {
                    for (College_Comp collegeComp : collegeComps) {
                        if (collegeComp.getApplyOrJoin() != 2 && college.getId() == collegeComp.getCollege().getId())
                            count++;
                    }
                }
                eachMoth.add(count);
            }
            collegeName.add(college.getNickname());
            eachDataForCollege.put("name", college.getNickname());
            eachDataForCollege.put("type", "line");
            eachDataForCollege.put("smooth", false);
            eachDataForCollege.put("data", eachMoth);
            eachData.add(eachDataForCollege);

        }
        String title = "各院系";
        if (year == 0) {
            title = title + "过去一年";
        } else {
            title = title + year + "年";
        }
        title = title + "参加比赛情况";
        Date[] dateArray = DateUtils.getDateArray(year);
        for (int i = 0; i < dateArray.length - 1; i++) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(dateArray[i]);
            String date = "";
            if (year == 0) {//过去12个月
                if (DateUtils.getNowYear() > instance.get(Calendar.YEAR)) {
                    date = "去年";
                }

            }
            date = date + (instance.get(Calendar.MONTH) + 1) + "月";
            showDate.add(date);
        }

        Collections.reverse(collegeName);
        Collections.reverse(eachData);
        infoMapDate.put("text", title);
        infoMapDate.put("date", showDate);
        infoMapDate.put("collegeInfo", eachData);
        infoMapDate.put("collegeNames", collegeName);
        return infoMapDate;
    }

    /**
     * 根据时间学生参加项目竞赛
     *
     * @param year
     * @return
     */
    @Override
    public Map<String, Object> getAllStudentJoinProjectByDate(int year) {
        Map<String, Object> infoMapDate = new HashMap<>();
        List<Map<String, Object>> eachData = new ArrayList<>();//所有学院的数据
        List<College> colleges = collegeRepository.findAll();
        List<String> showDate = new ArrayList<>();
        List<String> collegeName = new ArrayList<>();
        for (College college : colleges) {
            Map<String, Object> eachDataForCollege = new HashMap<>();//每个学院的数据
            List<Integer> eachMoth = new ArrayList<>();//单个院系往前12月的数据
            Date[] dateArray = DateUtils.getDateArray(year);
            for (int i = 0; i < dateArray.length - 1; i++) {
                List<Student_Project> student_projects = student_projectService.findAllByDate(dateArray[i], dateArray[i + 1]);
                int count = 0;
                for (Student_Project student_project : student_projects) {
                    if (student_project.getApplyOrJoin() != 2 && college.getId() == student_project.getStudent().getCollege().getId())
                        count++;
                }
                eachMoth.add(count);
            }
            collegeName.add(college.getNickname());
            eachDataForCollege.put("name", college.getNickname());
            eachDataForCollege.put("type", "line");
            eachDataForCollege.put("smooth", false);
            eachDataForCollege.put("data", eachMoth);
            eachData.add(eachDataForCollege);
        }
        String title = "各院系";
        if (year == 0) {
            title = title + "过去一年";
        } else {
            title = title + year + "年";
        }
        title = title + "学生参加比赛情况";
        Date[] dateArray = DateUtils.getDateArray(year);
        for (int i = 0; i < dateArray.length - 1; i++) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(dateArray[i]);
            String date = "";
            if (year == 0) {//过去12个月
                if (DateUtils.getNowYear() > instance.get(Calendar.YEAR)) {
                    date = "去年";
                }
            }
            date = date + (instance.get(Calendar.MONTH) + 1) + "月";
            showDate.add(date);
        }
        Collections.reverse(collegeName);
        Collections.reverse(eachData);
        infoMapDate.put("text", title);
        infoMapDate.put("date", showDate);
        infoMapDate.put("collegeInfo", eachData);
        infoMapDate.put("collegeNames", collegeName);
        return infoMapDate;
    }

    /**
     * 根据时间查询学院申请参加比赛信息
     *
     * @param year
     * @return
     */
    @Override
    public Map<String, Object> getAllCollegeApplyProjectByDate(int year) {
        Map<String, Object> infoMapDate = new HashMap<>();
        List<Map<String, Object>> eachData = new ArrayList<>();//所有学院的数据
        List<College> colleges = collegeRepository.findAll();
        List<String> showDate = new ArrayList<>();
        List<String> collegeName = new ArrayList<>();
        for (College college : colleges) {
            Map<String, Object> eachDataForCollege = new HashMap<>();//每个学院的数据
            List<Integer> eachMoth = new ArrayList<>();//单个院系往前12月的数据
            Date[] dateArray = DateUtils.getDateArray(year);
            for (int i = 0; i < dateArray.length - 1; i++) {
                List<Teacher_Project> teacher_projects = teacher_projectService.findAllByDate(dateArray[i], dateArray[i + 1]);
                int count = 0;
                for (Teacher_Project teacher_project : teacher_projects) {
                    if (teacher_project.getApplyOrJoin() != 2 && teacher_project.getIsCreator() == true && college.getId() == teacher_project.getProject().getCreateByTeacher().getCollege().getId())
                        count++;
                }
                eachMoth.add(count);
            }
            collegeName.add(college.getNickname());
            eachDataForCollege.put("name", college.getNickname());
            eachDataForCollege.put("type", "line");
            eachDataForCollege.put("smooth", false);
            eachDataForCollege.put("data", eachMoth);
            eachData.add(eachDataForCollege);
        }
        Date[] dateArray = DateUtils.getDateArray(year);
        for (int i = 0; i < dateArray.length - 1; i++) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(dateArray[i]);
            String date = "";
            if (year == 0) {//过去12个月
                if (DateUtils.getNowYear() > instance.get(Calendar.YEAR)) {
                    date = "去年";
                }
            }
            date = date + (instance.get(Calendar.MONTH) + 1) + "月";
            showDate.add(date);
        }
        String title = "各院系";
        if (year == 0) {
            title = title + "过去一年";
        } else {
            title = title + year + "年";
        }
        title = title + "成立项目情况";
        Collections.reverse(collegeName);
        Collections.reverse(eachData);
        infoMapDate.put("text", title);
        infoMapDate.put("date", showDate);
        infoMapDate.put("collegeInfo", eachData);
        infoMapDate.put("collegeNames", collegeName);
        return infoMapDate;
    }

    @Override
    public Map<String, Object> getAllStudentJoinProjectInfo(int year) {
        Map<String, Object> infoMap = new HashMap<>();
        List<College> colleges = collegeRepository.findAll();
        Date[] dates = DateUtils.getFirstAndLastDay(year);
        List<Student_Project> all = student_projectService.findAllByDate(dates[0], dates[1]);
        List<Map<String, Object>> maps = new ArrayList<>();
        Integer sum = 0;
        for (College college : colleges) {
            int count = 0;
            Map<String, Object> map = new HashMap<>();
            for (Student_Project student_project : all) {
                if (student_project.getStudent().getCollege().getId() == college.getId() && student_project.getApplyOrJoin() != 2)
                    count++;
            }
            map.put("name", college.getNickname());
            map.put("value", count);
            maps.add(map);
            sum += count;
        }
        infoMap.put("collegeInfo", maps);
        infoMap.put("sum", sum);
        return infoMap;
    }

    @Override
    public Map<String, Object> getAllCollegeApplyProjectInfo(int year) {
        Map<String, Object> infoMap = new HashMap<>();
        List<College> colleges = collegeRepository.findAll();
        Date[] dates = DateUtils.getFirstAndLastDay(year);
        List<Teacher_Project> all = teacher_projectService.findAllByDate(dates[0], dates[1]);
        List<Map<String, Object>> maps = new ArrayList<>();
        Integer sum = 0;
        for (College college : colleges) {
            int count = 0;
            Map<String, Object> map = new HashMap<>();
            for (Teacher_Project teacher_project : all) {
                if (teacher_project.getIsCreator() == true && teacher_project.getProject().getCreateByTeacher().getCollege().getId() == college.getId() && teacher_project.getApplyOrJoin() != 2)
                    count++;
            }
            map.put("name", college.getNickname());
            map.put("value", count);
            sum += count;
            maps.add(map);
        }
        infoMap.put("collegeInfo", maps);
        infoMap.put("sum", sum);
        return infoMap;
    }

    @Override
    public Map<String, Object> getAllCollegeJoinCompInfo(int year) {
        Map<String, Object> infoMap = new HashMap<>();
        List<College> colleges = collegeRepository.findAll();
        Date[] dates = DateUtils.getFirstAndLastDay(year);
        List<College_Comp> collegeComps = college_compService.findAllByDate(dates[0], dates[1]);
        List<Map<String, Object>> maps = new ArrayList<>();
        Integer sum = 0;
        for (College college : colleges) {
            Map<String, Object> map = new HashMap<>();
            int count = 0;
            for (College_Comp collegeComp : collegeComps) {
                if (collegeComp.getCollege().getId() == college.getId() && collegeComp.getApplyOrJoin() != 2)
                    count++;
            }
            map.put("name", college.getNickname());
            map.put("value", count);
            sum += count;
            maps.add(map);
        }
        infoMap.put("collegeInfo", maps);
        infoMap.put("sum", sum);
        return infoMap;
    }

    /**
     * 动态展示近几年的数据 各院系参加比赛
     *
     * @return
     */
    @Override
    public JSONObject getAllCollegeApplyProjectMoreYear(Integer yearCount) {

        JSONObject result = new JSONObject();
        List<String> yearList = new ArrayList<>();
        List<String> collegeList = new ArrayList<>();
        List<College> colleges = collegeRepository.findAll();
        JSONArray data = new JSONArray();
        Date[] recentYear = DateUtils.getRecentYear(yearCount);


        for (int i = 0; i < recentYear.length - 1; i++) {

            JSONObject each = new JSONObject();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(recentYear[i]);
            int year = calendar.get(Calendar.YEAR);

            JSONObject title = new JSONObject();
            title.put("text", year + "年各院系成立项目情况");
            JSONArray series = new JSONArray();
            JSONObject eachData = new JSONObject();
            JSONArray eachArry = new JSONArray();
            for (College college : colleges) {
                List<Teacher_Project> teacher_projects = teacher_projectService.findAllByDate(recentYear[i], recentYear[i + 1]);
                int count = 0;
                for (Teacher_Project teacher_project : teacher_projects) {
                    if (teacher_project.getApplyOrJoin() != 2 && teacher_project.getIsCreator() == true && college.getId() == teacher_project.getProject().getCreateByTeacher().getCollege().getId())
                        count++;
                }
                JSONObject eachValue = new JSONObject();
                eachValue.put("name", college.getNickname());
                eachValue.put("value", count);
                eachArry.add(eachValue);
            }
            eachData.put("data", eachArry);
            series.add(eachData);
            series.add(eachData);
            each.put("title", title);
            each.put("series", series);
            each.put("year", year + "年");
            data.add(each);
            yearList.add(String.valueOf(year));

        }
        for (College college : colleges) {
            collegeList.add(college.getNickname());
        }

        result.put("collegeList", collegeList);
        result.put("yearList", yearList);
        result.put("data", data);
        return result;

    }

    /**
     * 各院系成立项目情况
     *
     * @param yearCount
     * @return
     */
    @Override
    public JSONObject getAllCollegeJoinCompMoreYear(Integer yearCount) {

        JSONObject result = new JSONObject();
        List<String> yearList = new ArrayList<>();
        List<String> collegeList = new ArrayList<>();
        List<College> colleges = collegeRepository.findAll();
        JSONArray data = new JSONArray();
        Date[] recentYear = DateUtils.getRecentYear(yearCount);


        for (int i = 0; i < recentYear.length - 1; i++) {

            JSONObject each = new JSONObject();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(recentYear[i]);
            int year = calendar.get(Calendar.YEAR);

            JSONObject title = new JSONObject();
            title.put("text", year + "年各院系参加比赛情况");
            JSONArray series = new JSONArray();
            JSONObject eachData = new JSONObject();
            JSONArray eachArry = new JSONArray();
            for (College college : colleges) {
                int count = 0;
                List<College_Comp> collegeComps = college_compService.findAllByDate(recentYear[i], recentYear[i + 1]);
                if (collegeComps != null) {
                    for (College_Comp collegeComp : collegeComps) {
                        if (collegeComp.getApplyOrJoin() != 2 && college.getId() == collegeComp.getCollege().getId())
                            count++;
                    }
                }
                JSONObject eachValue = new JSONObject();
                eachValue.put("name", college.getNickname());
                eachValue.put("value", count);
                eachArry.add(eachValue);
            }
            eachData.put("data", eachArry);
            series.add(eachData);
            series.add(eachData);
            each.put("title", title);
            each.put("series", series);
            each.put("year", year + "年");
            data.add(each);
            yearList.add(String.valueOf(year));

        }
        for (College college : colleges) {
            collegeList.add(college.getNickname());
        }

        result.put("collegeList", collegeList);
        result.put("yearList", yearList);
        result.put("data", data);
        return result;

    }

    /**
     * 动态展示近几年的数据 学生参加竞赛
     *
     * @return
     */
    @Override
    public JSONObject getAllStudentJoinProjectMoreYear(Integer yearCount) {

        JSONObject result = new JSONObject();
        List<String> yearList = new ArrayList<>();
        List<String> collegeList = new ArrayList<>();
        List<College> colleges = collegeRepository.findAll();
        JSONArray data = new JSONArray();
        Date[] recentYear = DateUtils.getRecentYear(yearCount);


        for (int i = 0; i < recentYear.length - 1; i++) {

            JSONObject each = new JSONObject();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(recentYear[i]);
            int year = calendar.get(Calendar.YEAR);

            JSONObject title = new JSONObject();
            title.put("text", year + "年各院系学生参加竞赛获奖情况");
            JSONArray series = new JSONArray();
            JSONObject eachData = new JSONObject();
            JSONArray eachArry = new JSONArray();
            for (College college : colleges) {
                int count = 0;
                List<Student_Project> student_projects = student_projectService.findAllByDate(recentYear[i], recentYear[i + 1]);
                if (student_projects != null) {
                    for (Student_Project student_project : student_projects) {
                        if (student_project.getApplyOrJoin() != 2 && college.getId() == student_project.getStudent().getCollege().getId())
                            count++;
                    }
                }
                JSONObject eachValue = new JSONObject();
                eachValue.put("name", college.getNickname());
                eachValue.put("value", count);
                eachArry.add(eachValue);
            }
            eachData.put("data", eachArry);
            series.add(eachData);
            series.add(eachData);
            each.put("title", title);
            each.put("series", series);
            each.put("year", year + "年");
            data.add(each);
            yearList.add(String.valueOf(year));
        }
        for (College college : colleges) {
            collegeList.add(college.getNickname());
        }

        result.put("collegeList", collegeList);
        result.put("yearList", yearList);
        result.put("data", data);
        return result;
    }

    /**
     * 得到各院系得奖情况
     *
     * @param year
     * @param type
     * @param level
     * @return
     */
    @Override
    public Map<String, Object> getAllCollegeGetAwardsByDate(int year, Integer type, Integer level) {
        Map<String, Object> infoMapDate = new HashMap<>();
        List<Map<String, Object>> eachData = new ArrayList<>();//所有学院的数据
        List<College> colleges = collegeRepository.findAll();
        List<String> showDate = new ArrayList<>();
        List<String> collegeName = new ArrayList<>();
        for (College college : colleges) {
            Map<String, Object> eachDataForCollege = new HashMap<>();//每个学院的数据
            List<Integer> eachMoth = new ArrayList<>();//单个院系往前12月的数据
            Date[] dateArray = DateUtils.getDateArray(year);
            for (int i = 0; i < dateArray.length - 1; i++) {
                int count = 0;
                List<Awards> awardsList = awardsService.findAll(dateArray[i], dateArray[i + 1], type, level);
                if (awardsList != null) {
                    for (Awards awards : awardsList) {
                        Project project = awards.getProject();
                        if (project.getCreateByTeacher().getCollege().getId() == college.getId() && awards.getState() == 2) {
                            count++;
                        }
                    }
                }
                eachMoth.add(count);
            }
            collegeName.add(college.getNickname());
            eachDataForCollege.put("name", college.getNickname());
            eachDataForCollege.put("type", "line");
            eachDataForCollege.put("smooth", false);
            eachDataForCollege.put("data", eachMoth);
            eachData.add(eachDataForCollege);
        }
        Date[] dateArray = DateUtils.getDateArray(year);
        for (int i = 0; i < dateArray.length - 1; i++) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(dateArray[i]);
            String date = "";
            if (year == 0) {//过去12个月
                if (DateUtils.getNowYear() > instance.get(Calendar.YEAR)) {
                    date = "去年";
                }
            }
            date = date + (instance.get(Calendar.MONTH) + 1) + "月";
            showDate.add(date);
        }
        String title = "各院系";
        if (year == 0) {
            title = title + "过去一年";
        } else {
            title = title + year + "年";
        }
        title = title + Type.getTypeNameByType(type) + Level.getTypeNameByType(level) + "奖项情况";
        Collections.reverse(collegeName);
        Collections.reverse(eachData);
        infoMapDate.put("text", title);
        infoMapDate.put("date", showDate);
        infoMapDate.put("collegeInfo", eachData);
        infoMapDate.put("collegeNames", collegeName);
        return infoMapDate;
    }

    /**
     * 各院系奖项情况
     *
     * @param year
     * @param type
     * @param level
     * @return
     */
    @Override
    public Map<String, Object> getAllCollegeGetAwardsInfo(int year, Integer type, Integer level) {
        Map<String, Object> infoMap = new HashMap<>();
        List<College> colleges = collegeRepository.findAll();
        Date[] dates = DateUtils.getFirstAndLastDay(year);
        List<Awards> awardsList = awardsService.findAll(dates[0], dates[1], type, level);
        List<Map<String, Object>> maps = new ArrayList<>();
        Integer sum = 0;
        for (College college : colleges) {
            int count = 0;
            Map<String, Object> map = new HashMap<>();
            for (Awards awards : awardsList) {
                Project project = awards.getProject();
                if (project.getCreateByTeacher().getCollege().getId() == college.getId() && awards.getState() == 2) {
                    count++;
                }
            }
            map.put("name", college.getNickname());
            map.put("value", count);
            sum += count;
            maps.add(map);
        }
        String title = "各院系";
        if (year == 0) {
            title = title + "过去一年";
        } else {
            title = title + year + "年";
        }
        title = title + Type.getTypeNameByType(type) + Level.getTypeNameByType(level) + "奖项情况";
        infoMap.put("title", title);
        infoMap.put("type", Type.getTypeNameByType(type) + Level.getTypeNameByType(level) + "奖项情况");
        infoMap.put("collegeInfo", maps);
        infoMap.put("sum", sum);
        return infoMap;
    }

    /**
     * 各院系近几年各奖项的的情况
     *
     * @param yearCount
     * @param typeArry
     * @param levelArry
     * @return
     */
    @Override
    public JSONObject getAllCollegeGetAwardsMoreYear(Integer yearCount, List<Integer> typeArry, List<Integer> levelArry) {

        JSONObject result = new JSONObject();
        List<String> yearList = new ArrayList<>();
        List<String> collegeList = new ArrayList<>();
        List<College> colleges = collegeRepository.findAll();
        JSONArray data = new JSONArray();
        Date[] recentYear = DateUtils.getRecentYear(yearCount);
        JSONArray awardsType= new JSONArray();

        for (int i = 0; i < recentYear.length - 1; i++) {

            JSONObject each = new JSONObject();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(recentYear[i]);
            int year = calendar.get(Calendar.YEAR);

            JSONObject title = new JSONObject();
            title.put("text", year + "年各院系学生参加竞赛情况");
            JSONArray series = new JSONArray();



            List<Integer> allTypeArry = Arrays.asList(1, 2, 3, 4);
            List<Integer> allLevelArry = Arrays.asList(1, 2, 3, 4);

            if (typeArry.size() == 0 && levelArry.size() == 0) {
                typeArry = allTypeArry;
                levelArry = allLevelArry;
            } else if (typeArry.size() == 0) {
                typeArry = allTypeArry;
            } else if (levelArry.size() == 0) {
                levelArry = allLevelArry;
            }
            JSONObject typeObject = new JSONObject();
            JSONArray typeObjectArry = new JSONArray();
            for (Integer type : typeArry) {
                for (Integer level : levelArry) {
                    JSONArray eachArry = new JSONArray();
                    JSONObject eachData = new JSONObject();
                    JSONObject typeEach = new JSONObject();
                    int sum = 0;
                    for (College college : colleges) {
                        int count = 0;
                        List<Awards> awardsList = awardsService.findAll(recentYear[i], recentYear[i + 1], type, level);
                        for (Awards awards : awardsList) {
                            Project project = awards.getProject();
                            if (project.getCreateByTeacher().getCollege().getId() == college.getId() && awards.getState() == 2) {
                                count++;
                            }
                        }
                        JSONObject eachValue = new JSONObject();
                        eachValue.put("name", college.getNickname());
                        eachValue.put("value", count);
                        eachArry.add(eachValue);
                        sum  =sum + count;
                    }
                    eachData.put("data", eachArry);
                    series.add(eachData);
                    //这里学院的获奖个情况信息进行归总****重要
                    typeEach.put("name",Type.getTypeNameByType(type)+Level.getTypeNameByType(level));
                    typeEach.put("value",sum);
                    typeObjectArry.add(typeEach);
                }
            }
            typeObject.put("data",typeObjectArry);
            series.add(typeObject);
            each.put("title", title);
            each.put("series", series);
            each.put("year", year + "年");
            data.add(each);
            yearList.add(String.valueOf(year));
        }
        for (College college : colleges) {
            collegeList.add(college.getNickname());
        }
        JSONArray jsonArray = new JSONArray();

        for (Integer type : typeArry) {
            for (Integer level : levelArry) {
                awardsType.add(Type.getTypeNameByType(type)+Level.getTypeNameByType(level));
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name",Type.getTypeNameByType(type)+Level.getTypeNameByType(level));
                jsonObject.put("type","bar");
                jsonArray.add(jsonObject);
            }
        }

        result.put("collegeList", collegeList);
        result.put("awardsType", awardsType);
        result.put("seriesType", jsonArray);
        result.put("yearList", yearList);
        result.put("data", data);
        return result;
    }

}
