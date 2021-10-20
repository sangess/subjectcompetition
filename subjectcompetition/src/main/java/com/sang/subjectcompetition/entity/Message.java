package com.sang.subjectcompetition.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 是否已读
     */
    private  Boolean ifRead=false;

    /**
     * 类型  0 1常规   红色   2绿色
     */
    private Integer type=0;

    /**
     * 发送的角色 0表示接受的人是学生   1表示接受的人是教师  2表示接受的人是学院   3表示接受的人是管理员
     */
     //error以下
     // 0表示在学生表中找
     // 1表示在在教师表中找
     // 2表示在学院表中找
     // 3表示在管理员表中找
    private Integer  receiverRole;

    /**
     * 发送的角色 0表示发送的人是学生   1表示发送的人是教师  2表示发送的人是学院   3表示发送的人是管理员
     */
    private Integer  senderRole;

    /**
     * 接受者
     */
    private  String receiver ;

    /**
     * 发送者
     */
    private String sender;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date msgDate;
}
