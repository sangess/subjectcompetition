package com.sang.subjectcompetition.service;

import com.sang.subjectcompetition.entity.User;
import com.sang.subjectcompetition.entity.resultInfo.LoginResult;
import org.springframework.stereotype.Service;


public interface UserService {
     LoginResult loginUser(User user);
}
