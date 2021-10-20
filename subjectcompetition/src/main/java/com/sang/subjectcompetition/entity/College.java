package com.sang.subjectcompetition.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

//学院
@Getter
@Setter
@Entity
@Table(name = "college")
public class College extends User {
    public College() {
    }

    public College(int id,String username, String nickname, String targetId,String token) {
        this.id=id;
        this.username = username;
        this.nickname = nickname;
        this.targetId = targetId;
        this.setToken(token);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String nickname;
    private String password;
    private String targetId;

    //院系拥有的比赛
    @OneToMany(mappedBy = "college")
    @JsonIgnore
    private List<College_Comp> comps;

    //院系的老师
    @OneToMany(mappedBy = "college")
    @JsonIgnore
    private List<Teacher> teachers;

    //院系的学生
    @OneToMany(mappedBy = "college")
    @JsonIgnore
    private List<Student> students;



}
