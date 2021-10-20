package com.sang.subjectcompetition.controller.wechat;

import com.alibaba.fastjson.JSONObject;
import com.sang.subjectcompetition.entity.Student;
import com.sang.subjectcompetition.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx")
public class WxSelfController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/mySelf")
    public Student getUserInfo(@RequestParam Integer studentId){
        return studentService.getStudentById(studentId);
    }
}
