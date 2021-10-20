package com.sang.subjectcompetition.entity.resultInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResult<T> {
    private int code;
    private String message;
    private T data;
}
