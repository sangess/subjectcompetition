package com.sang.subjectcompetition.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

//指导老师
@Getter
@Setter
@Entity
@Table(name = "teacher")
public class Teacher extends User{

    public Teacher() {
    }

    public Teacher(int id,String username, String nickname, String targetId,String token) {
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

    //所属学院
    @ManyToOne
    @JoinColumn(name = "collegeId")
    private College college;

    //老师所带的项目
    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    private List<Teacher_Project> projects;

}
