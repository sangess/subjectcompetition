package com.sang.subjectcompetition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling/*定时任务*/
public class SubjectcompetitionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubjectcompetitionApplication.class, args);
    }

}
