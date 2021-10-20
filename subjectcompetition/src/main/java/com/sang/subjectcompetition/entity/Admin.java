package com.sang.subjectcompetition.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

//校方
@Getter
@Setter
@Entity
@Table(name = "admin")
public class Admin extends User{
    public Admin() {
    }

    public Admin(int id,String username, String nickname, String targetId,String token) {
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

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private List<Notice> notices;

}