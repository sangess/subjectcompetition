package com.sang.subjectcompetition.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "work_File")
@Entity
public class WorkFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fileUrl;

    private String fileName;

    @ManyToOne
    @JoinColumn(name = "work_id")
    @JsonIgnore
    private Work work;
}
