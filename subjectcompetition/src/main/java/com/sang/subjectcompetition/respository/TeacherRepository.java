package com.sang.subjectcompetition.respository;

import com.sang.subjectcompetition.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {

    /**
     * 根据院系得到全部的老师
     * @param id
     * @return
     */
    List<Teacher> getTeachersByCollege_Id(Integer id);

    Teacher findTeacherByUsernameAndPassword(String username,String password);

    Teacher findTeacherByTargetId(String targetId);
}
