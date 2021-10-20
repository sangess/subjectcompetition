package com.sang.subjectcompetition.config;

import com.sang.subjectcompetition.entity.Comp;
import com.sang.subjectcompetition.entity.Project;
import com.sang.subjectcompetition.entity.Student;
import com.sang.subjectcompetition.respository.StudentRepository;
import com.sang.subjectcompetition.service.CompService;
import com.sang.subjectcompetition.service.ProjectService;
import com.sang.subjectcompetition.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/*定时任务*/
@Component
public class TimeTask {

    @Autowired
    private CompService compService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ProjectService projectService;

    /**
     * 由规划变为可申请状态
     *   0->1
     */
    @Scheduled(cron = "59,0 59,0 23,0  * * ?")
    public void changeCompStateToCanApply(){
        Date nowDate=new Date();
        List<Comp> allComps = compService.getAllComps();
        for (Comp comp : allComps) {
            //判断今天时间是否小于竞赛可以申请的时间
            if(comp.getCanApplyTime().compareTo(nowDate)>=0&&comp.getNotApplyTime().compareTo(nowDate)>0&&comp.getOccurrenceTime().compareTo(nowDate)>0){
                Comp comp_db = compService.findCompById(comp.getId());
                comp_db.setCompState(1);
                compService.save(comp_db);
            }
        }
    }

    /**
     * 由可申请状态变为备赛状态
     *   1->2
     */
    @Scheduled(cron = "59,0 59,0 23,0  * * ?")
    public void changeCompStateToNotApply(){
        Date nowDate=new Date();
        List<Comp> allComps = compService.getAllComps();
        for (Comp comp : allComps) {
            if(comp.getNotApplyTime().compareTo(nowDate)<=0&&comp.getOccurrenceTime().compareTo(nowDate)>0){
                Comp comp_db = compService.findCompById(comp.getId());
                comp_db.setCompState(2);
                compService.save(comp_db);
            }
        }
    }

    /**
     * 由可申请状态变为备赛状态
     *   1->2
     */
    @Scheduled(cron = "59,0 59,0 23,0 * * ?")
    public void changeCompStateToOccurrence(){
        Date nowDate=new Date();
        List<Comp> allComps = compService.getAllComps();
        for (Comp comp : allComps) {
            if(comp.getOccurrenceTime().compareTo(nowDate)<=0){
                Comp comp_db = compService.findCompById(comp.getId());
                comp_db.setCompState(3);
                compService.save(comp_db);
            }
        }
    }

    @Scheduled(cron = "59 0 0  * * *")
    public void changeUserInfo(){
        Date nowDate=new Date();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(nowDate);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        Date date = calendar.getTime();
        List<Student> all = studentRepository.findAll();
        for (Student student : all) {
            List<Project> projects = projectService.getStudentProjects(student.getId());
            for (Project project : projects) {
                if(project.getComp().getNotApplyTime().compareTo(date)==0){
                    Student student_db = studentService.getStudentById(student.getId());
                    student_db.setScore(student_db.getScore()+1);
                    student_db.setCompCount(student_db.getCompCount()+1);
                    studentService.save(student_db);
                }
            }
        }

    }
}
