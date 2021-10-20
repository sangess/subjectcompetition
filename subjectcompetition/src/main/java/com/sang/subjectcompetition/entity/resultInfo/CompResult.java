package com.sang.subjectcompetition.entity.resultInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//申请比赛返回的结果类型
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompResult<T> {
    private int code;
    private String message;
    private T data;
}
