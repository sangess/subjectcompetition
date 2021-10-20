package com.sang.subjectcompetition.entity;
//竞赛类

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "comp")
public class Comp implements Comparable<Comp>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String compName;//竞赛名称

    //竞赛的主办单位
    private String organizer;

    //竞赛级别  0:国家级  1：省级  2：校级
    private Integer level;

    //竞赛申报的学院
    @OneToMany(mappedBy = "comp")
    @JSONField(serialize=false)
    @JsonIgnore
    private List<College_Comp> colleges;

    //比赛所拥有的项目
    @OneToMany(mappedBy = "comp")
    @JSONField(serialize=false)
    @JsonIgnore
    private List<Project> projects;

    //竞赛时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format="yyyy年MM月dd日")
    private Date occurrenceTime;

    //发布时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publishTime;

    //开始申报时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format="yyyy年MM月dd日")
    private Date canApplyTime;

    //截止申报时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format="yyyy年MM月dd日")
    private Date notApplyTime;

    //举办地点
    private String place;

    //竞赛的项目数量
    private Integer countProject = 0;

    //最多比赛所容项目数
    private Integer maxCountProject;

    //竞赛状况 0未开始  1申报可以开始 2申报结束  3进行中    4已结束
    private Integer compState;

    //所属学科
    private String subjectType;

    //项目参与老师人数最高
    private Integer maxCountTeacher;

    //项目参与学生最高人数
    private Integer maxCountStudent;

    /**
     * 可视
     */
    private Boolean visiable=false;

    @Override
    public int compareTo(Comp o) {
        if(this.publishTime.compareTo(o.publishTime)<0)return 1;
        else if(this.publishTime.compareTo(o.publishTime)==0){
            return this.id.compareTo(o.id);
        }
        else return -1;
    }
}
