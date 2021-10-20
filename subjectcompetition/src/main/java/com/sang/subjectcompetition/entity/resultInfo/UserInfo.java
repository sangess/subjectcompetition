package com.sang.subjectcompetition.entity.resultInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//用户信息结果
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo<T> {
    private int code;
    private String message;
    private T data;
}
