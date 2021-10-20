package com.sang.subjectcompetition.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notice")
public class Notice  implements Serializable,Comparable<Notice> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;//标题

    private String content;//内容

    private Integer level;//公告等级

    @ManyToOne
    @JoinColumn(name = "adminId")
    private Admin author;//作者

    private String type;//公告类型

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;//发布日期

    @Override
    public int compareTo(Notice o) {
        if (this.level<o.level)
            return 1;
        else if(this.level-o.level==0){
            if(this.time.compareTo(o.time)<=0)return 1;
            else return -1;
        } else
            return -1;
    }
}
