package com.sang.subjectcompetition.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "work")
@Getter
@Setter
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String workName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date applyDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    private Integer score=0;

    //是否评分
    private Boolean ifMark = false;

    @OneToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "work")
    private List<WorkFile> workFiles;
}
