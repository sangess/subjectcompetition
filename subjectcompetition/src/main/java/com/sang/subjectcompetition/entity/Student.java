package com.sang.subjectcompetition.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
//学生实体类

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student extends User {

    public Student() {
    }

    public Student(int id,String username, String nickname, String targetId,String token) {
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
    private String openId;
    /**
     * skey
     */
    private String skey;
    /**
     * session_key
     */
    private String sessionKey;
    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 性别
     */
    private Integer gender;
    /**
     * 网名
     */
    private String wxName;

    private Integer score = 0;

    private Integer compCount = 0;

    //所属院系
    @ManyToOne
    @JoinColumn(name = "collegeId")
    private College college;

    //参加的比赛
    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<Student_Project> projects;
}
