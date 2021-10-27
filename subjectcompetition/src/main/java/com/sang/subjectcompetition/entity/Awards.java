package com.sang.subjectcompetition.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "awards")
public class Awards implements Comparable<Awards>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 1申请  2同意  3驳回
     */
    private Integer state;

    /**
     * 4国家级  3省级  2市级  1校级
     */
    private Integer type;

    /**
     * 5状态对应的奖项
     */
    private String content;

    /**
     * 4一等奖  3二等奖  2三等奖    1其他
     */
    private Integer level;

    /**
     * 证书路径
     */
    private String imageUrl;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date applyDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateApplyDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date awardsTime;

    @OneToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Override
    public int compareTo(Awards o) {
        if (this.type*150+this.level*20<(o.type*150+level*20))return 1;
        else if(this.type*150+this.level*20==(o.type*150+level*20)){
            return this.updateApplyDate.compareTo(o.updateApplyDate);
        }
        else return -1;
    }
}
