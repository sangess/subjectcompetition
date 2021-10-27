package com.sang.subjectcompetition;

import com.sang.subjectcompetition.controller.DataController;
import com.sang.subjectcompetition.entity.*;
import com.sang.subjectcompetition.entity.resultInfo.UserInfo;
import com.sang.subjectcompetition.respository.ProjectRepository;
import com.sang.subjectcompetition.respository.TeacherRepository;
import com.sang.subjectcompetition.respository.Teacher_ProjectRepository;
import com.sang.subjectcompetition.service.*;
import com.sang.subjectcompetition.service.util.ColorUtils;
import com.sang.subjectcompetition.service.util.DateUtils;
import com.sang.subjectcompetition.service.util.UploadFileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SubjectcompetitionApplicationTests {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private CompService compService;

    @Autowired
    private College_CompService college_compService;

    @Autowired
    private Teacher_ProjectRepository teacher_projectRepository;

    @Resource
    private TeacherService teacherService;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private AwardsService awardsService;

    @Test
    void test(){
        Date[] recentYear = DateUtils.getDateArray(0);
        for (int i=0;i<recentYear.length-1 ; i++) {
            List<Awards> awardsList = awardsService.findAll(recentYear[i], recentYear[i + 1], 4, 4);
            if(awardsList!=null){
                System.out.println(awardsList);
            }
        }
    }
}
