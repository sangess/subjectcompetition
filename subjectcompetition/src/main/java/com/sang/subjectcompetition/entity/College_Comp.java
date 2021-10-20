package com.sang.subjectcompetition.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "college_comp")
@Getter
@Setter
public class College_Comp implements Comparable<College_Comp>{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private College college;

    @ManyToOne
    private Comp comp;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date applyDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateApplyDate;

/*    *//**
     * 申请内容/驳回原因
     *//*
    private String content;*/

    /**
     * 院系对竞赛的关系  0学院向管理员申请创建的竞赛  1申请  2驳回  3同意  4重新申请
     * 5管理员同意院系创建比赛   6管理员驳回院系的请求
     */
    private int applyOrJoin;

    @Override
    public int compareTo(College_Comp o) {
        if(this.updateApplyDate.compareTo(o.updateApplyDate)<0)return 1;
        else if(this.updateApplyDate.compareTo(o.updateApplyDate)==0){
            return this.id.compareTo(o.id);
        }
        else return -1;
    }
}
