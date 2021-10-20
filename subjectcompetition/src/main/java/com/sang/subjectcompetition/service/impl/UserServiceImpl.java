package com.sang.subjectcompetition.service.impl;

import com.sang.subjectcompetition.entity.*;
import com.sang.subjectcompetition.entity.resultInfo.LoginResult;
import com.sang.subjectcompetition.entity.resultInfo.UserInfo;
import com.sang.subjectcompetition.respository.AdminRepository;
import com.sang.subjectcompetition.respository.CollegeRepository;
import com.sang.subjectcompetition.respository.StudentRepository;
import com.sang.subjectcompetition.respository.TeacherRepository;
import com.sang.subjectcompetition.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public LoginResult loginUser(@RequestBody User user) {
        LoginResult loginResult=new LoginResult();
        String token=user.getToken();
        String username=user.getUsername();
        String password=user.getPassword();
        if ("admin".equalsIgnoreCase(token)){
            Admin admin = adminRepository.findAdminByUsernameAndAndPassword(username, password);
            if (admin!=null){
                loginResult.setCode(2003);
                loginResult.setMessage("管理员"+admin.getNickname()+"登录成功！");
                loginResult.setUser(new Admin(admin.getId(),username,admin.getNickname(),admin.getTargetId(),"admin"));
                return loginResult;
            }else {
                loginResult.setCode(2001);
                loginResult.setMessage("账号或密码错误！");
                return loginResult;
            }
        }else if ("college".equalsIgnoreCase(token)){
            College college = collegeRepository.findCollegeByUsernameAndPassword(username, password);
            if (college!=null){
                loginResult.setCode(2003);
                loginResult.setMessage(college.getNickname()+"学院 登录成功！");
                loginResult.setUser(new College(college.getId(),username,college.getNickname(),college.getTargetId(),"college"));
                return loginResult;
            }else {
                loginResult.setCode(2001);
                loginResult.setMessage("账号或密码错误！");
                return loginResult;
            }
        }else if ("teacher".equalsIgnoreCase(token)){
            Teacher teacher = teacherRepository.findTeacherByUsernameAndPassword(username, password);
            if (teacher!=null){
                loginResult.setCode(2003);
                loginResult.setMessage(teacher.getNickname()+"老师 登录成功！");
                loginResult.setUser(new Teacher(teacher.getId(),username,teacher.getNickname(),teacher.getTargetId(),"teacher"));
                return loginResult;
            }else {
                loginResult.setCode(2001);
                loginResult.setMessage("账号或密码错误！");
                return loginResult;
            }
        }else{
            Student student = studentRepository.findStudentByUsernameAndPassword(username, password);
            if (student!=null){
                loginResult.setCode(2003);
                loginResult.setMessage(student.getNickname()+"同学 登录成功！");
                loginResult.setUser(new Student(student.getId(),username,student.getNickname(),student.getTargetId(),"student"));
                return loginResult;
            }else {
                loginResult.setCode(2001);
                loginResult.setMessage("账号或密码错误！");
                return loginResult;
            }
        }
    }
}
