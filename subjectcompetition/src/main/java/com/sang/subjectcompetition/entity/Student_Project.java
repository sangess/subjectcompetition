package com.sang.subjectcompetition.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "student_project")
@Getter
@Setter
public class Student_Project implements Comparable<Student_Project> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date applyDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateApplyDate;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;

/*    *//**
     * 内容
     *//*
    private String content;*/

    /**
     * 学生和项目的状态  0默认  1申请  2驳回  3同意  4再次申请
     */
    private Integer applyOrJoin;

    @Override
    public int compareTo(Student_Project o) {
        if(this.updateApplyDate.compareTo(o.updateApplyDate)<0)return 1;
        else if(this.updateApplyDate.compareTo(o.updateApplyDate)==0){
            return this.id.compareTo(o.id);
        }else return -1;
    }
}
