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
@Table(name = "teacher_project")
@Getter
@Setter
public class Teacher_Project implements Comparable<Teacher_Project>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "teacherId")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date applyDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateApplyDate;

    /**
     * 老师和院系对项目的关系(如果是创建者)  0默认  1申请  2驳回  3同意  4重新申请
     */
    private Integer applyOrJoin;

/*    private String content;*/

    /**
     * 是否是创建者
     */
    private Boolean isCreator=false;

    @Override
    public int compareTo(Teacher_Project o) {
        if(this.updateApplyDate.compareTo(o.updateApplyDate)<0)return 1;
        else if(this.updateApplyDate.compareTo(o.updateApplyDate)==0){
            return this.id.compareTo(o.id);
        }
        else return -1;
    }
}


