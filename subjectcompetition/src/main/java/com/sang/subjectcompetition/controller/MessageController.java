package com.sang.subjectcompetition.controller;

import com.sang.subjectcompetition.entity.*;
import com.sang.subjectcompetition.entity.resultInfo.MessageResult;
import com.sang.subjectcompetition.respository.AdminRepository;
import com.sang.subjectcompetition.respository.CollegeRepository;
import com.sang.subjectcompetition.respository.StudentRepository;
import com.sang.subjectcompetition.respository.TeacherRepository;
import com.sang.subjectcompetition.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/msg")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("/getUnReadListPopup/{receiver}")
    public List<Message> getUnReadListPopup(@PathVariable String receiver ){
        return messageService.findUnReadMessage(receiver);
    }

    @GetMapping("/getUnReadList/{receive}")
    public List<Map<String ,Object>> getUnReadList(@PathVariable String receive){
        List<Map<String ,Object>> result=new ArrayList<>();
        List<Message> unReadMessage = messageService.findUnReadMessage(receive);
        for (Message message : unReadMessage) {
            Map<String ,Object> map=new HashMap<>();
            map.put("id",message.getId());
            map.put("date",message.getMsgDate());
            map.put("title",message.getTitle());
            map.put("content",message.getContent());
            if(message.getSenderRole()==0){
                Student student = studentRepository.findStudentByTargetId(message.getSender());
                map.put("sendUsername",student.getUsername());
                map.put("sendName",student.getNickname());
            }else if(message.getSenderRole()==1){
                Teacher teacher = teacherRepository.findTeacherByTargetId(message.getSender());
                map.put("sendUsername",teacher.getUsername());
                map.put("sendName",teacher.getNickname());
            }else if(message.getSenderRole()==2){
                College college = collegeRepository.findCollegeByTargetId(message.getSender());
                map.put("sendUsername",college.getUsername());
                map.put("sendName",college.getNickname());
            }else{
                Admin admin = adminRepository.findAdminByTargetId(message.getSender());
                map.put("sendUsername",admin.getUsername());
                map.put("sendName",admin.getNickname());
            }
            result.add(map);
        }
        return result;
    }

    @GetMapping("/getReadList/{receive}")
    public List<Map<String ,Object>> getReadList(@PathVariable String receive){
        List<Map<String ,Object>> result=new ArrayList<>();
        List<Message> readMessage = messageService.findReadMessage(receive);
        for (Message message : readMessage) {
            Map<String ,Object> map=new HashMap<>();
            map.put("id",message.getId());
            map.put("date",message.getMsgDate());
            map.put("title",message.getTitle());
            map.put("content",message.getContent());
            if(message.getSenderRole()==0){
                Student student = studentRepository.findStudentByTargetId(message.getSender());
                map.put("sendUsername",student.getUsername());
                map.put("sendName",student.getNickname());
            }else if(message.getSenderRole()==1){
                Teacher teacher = teacherRepository.findTeacherByTargetId(message.getSender());
                map.put("sendUsername",teacher.getUsername());
                map.put("sendName",teacher.getNickname());
            }else if(message.getSenderRole()==2){
                College college = collegeRepository.findCollegeByTargetId(message.getSender());
                map.put("sendUsername",college.getUsername());
                map.put("sendName",college.getNickname());
            }else{
                Admin admin = adminRepository.findAdminByTargetId(message.getSender());
                map.put("sendUsername",admin.getUsername());
                map.put("sendName",admin.getNickname());
            }
            result.add(map);
        }
        return result;
    }

    @GetMapping("/markMessage/{messageId}")
    public MessageResult markMessage(@PathVariable Integer messageId){
        return messageService.markRead(messageId);
    }

    @GetMapping("/deleteMessage/{messageId}")
    public MessageResult deleteMessage(@PathVariable Integer messageId){
        return messageService.deleteMessage(messageId);
    }

}
