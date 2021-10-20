package com.sang.subjectcompetition.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

//项目
@Getter
@Setter
@Entity
@Table(name = "project")
public class Project implements Comparable<Project> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //项目名称
    private String projectName;

    //审核状态 0:未审核  1:审核通过  2:审核不通过
    private Integer applyState;

    //所属比赛
    @ManyToOne
    @JoinColumn(name = "compId")
    private Comp comp;

    //项目创建者
    @ManyToOne
    @JoinColumn(name = "createByTeacherId")
    private Teacher createByTeacher;

    //参加项目的学生
    @OneToMany(mappedBy = "project")
    @JsonIgnore
    private List<Student_Project> students;

    //项目指导老师
    @OneToMany(mappedBy = "project")
    @JsonIgnore
    private List<Teacher_Project> teachers;

    @OneToOne(mappedBy = "project")
    @JsonIgnore
    private Awards awards;

    @OneToOne(mappedBy = "project")
    @JsonIgnore
    private Work work;

    //设置参与学生人数
    private Integer countStudent = 0;

    //设置参指导人数
    private Integer countTeacher = 0;

    /**
     * 项目预期耗资
     */
    private Integer pay =0;

    @Override
    public int compareTo(Project p) {
        if(this.getComp().getCanApplyTime().compareTo(p.getComp().getCanApplyTime())<0)return 1;
        else if (this.getComp().getCanApplyTime().compareTo(p.getComp().getCanApplyTime())==0){
            return p.getId()-this.getId();
        }
        else return  -1;
    }
}
