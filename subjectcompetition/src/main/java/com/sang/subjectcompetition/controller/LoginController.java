package com.sang.subjectcompetition.controller;

import com.sang.subjectcompetition.entity.Admin;
import com.sang.subjectcompetition.entity.User;
import com.sang.subjectcompetition.entity.resultInfo.LoginResult;
import com.sang.subjectcompetition.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginResult login(@RequestBody User user){/*, HttpSession session*/
        LoginResult loginResult = userService.loginUser(user);
        /*        if(user.getToken().equalsIgnoreCase("admin")){
            Admin admin = (Admin) loginResult.getUser();
            session.setAttribute("user",admin);
        }*/
        return loginResult;
    }
}
