package com.sang.subjectcompetition.respository;

import com.sang.subjectcompetition.entity.Student_Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Student_ProjectRepository extends JpaRepository<Student_Project,Integer>, JpaSpecificationExecutor<Student_Project> {
    /**
     * 根据学生id和项目id返回关系
     * @param studentId
     * @param projectId
     * @return
     */
    Student_Project findStudent_ProjectByStudentIdAndAndProjectId(Integer studentId,Integer projectId);

    /**
     * 根据项目id查看集合
     * @return
     */
    List<Student_Project> findStudent_ProjectsByProjectId(Integer projectId);

    /**
     * 根据学生查看集合
     * @param studentId
     * @return
     */
    List<Student_Project> findStudent_ProjectsByStudentId(Integer studentId);

    /**
     * 根据项目id删除
     */
    void deleteStudent_ProjectsByProjectId(Integer projectId);

    /**
     * 根据项目id和状态返回集合
     */
    @Query(value = "SELECT * FROM `student_project` WHERE `project_id` =?1 AND `apply_or_join` =?2",nativeQuery = true)
    List<Student_Project> findStudent_ProjectByProjectsIdAndApplyJoinState(Integer projectId,Integer applyOrJoin);

    @Query(value = "SELECT * FROM `student_project` WHERE `student_id` =?1 AND `apply_or_join` =?2",nativeQuery = true)
    List<Student_Project> getApplyStudentProjectList(Integer studentId,Integer applyOrJoin);


}
