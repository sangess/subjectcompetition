package com.sang.subjectcompetition.entity.resultInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResult<T> {
    private int code;
    private String message;
    private T user;
}
