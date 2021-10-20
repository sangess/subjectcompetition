package com.sang.subjectcompetition.respository;

import com.sang.subjectcompetition.entity.Student;
import com.sang.subjectcompetition.entity.Student_Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    /**
     * 根据院系得到全部学生
     * @param id
     * @return
     */
    List<Student> getStudentsByCollege_Id(Integer id);

    Student findStudentByUsernameAndPassword(String username,String password);

    Student findStudentByOpenId(String oppenId);

    Student findStudentByTargetId(String targetId);

}
