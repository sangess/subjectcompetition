package com.sang.subjectcompetition.respository;

import com.sang.subjectcompetition.entity.Teacher_Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Teacher_ProjectRepository extends JpaRepository<Teacher_Project,Integer>, JpaSpecificationExecutor<Teacher_Project> {

    /**
     * 根据老师和项目得到确切的关系
     * @param teacherId
     * @param projectId
     * @return
     */
    Teacher_Project getTeacher_ProjectByTeacherIdAndProjectId(Integer teacherId,Integer projectId);

    /**
     * 根据是否是项目创建者和项目id返回关系
     * @param projectId
     * @param isCreator
     * @return
     */
    List<Teacher_Project> getTeacher_ProjectsByProjectIdAndIsCreator(Integer projectId,Boolean isCreator);

    /**
     * 根据老师创建者和项目返回关系
     * @param teacherId
     * @param projectId
     * @param isCreator
     * @return
     */
    Teacher_Project findTeacher_ProjectByTeacherIdAndProjectIdAndIsCreator(Integer teacherId,Integer projectId,Boolean isCreator);

    /**
     * 根据项目id获得老师关系集合
     * @param projectId
     * @return
     */
    List<Teacher_Project> getTeacher_ProjectByProjectId(Integer projectId);

    /**
     * 根据老师是否是创建者来得到项目关系
     * @param teacherId
     * @param isCreator
     * @return
     */
    List<Teacher_Project> getTeacher_ProjectByTeacherIdAndIsCreator(Integer teacherId,Boolean isCreator);

    /**
     * 根据老师和项目删除指定的关系
     * @param teacherId
     * @param projectId
     */
    void deleteTeacher_ProjectByTeacherIdAndProjectId(Integer teacherId,Integer projectId);

    /**
     * 根据项目删除老师之间的关系
     * @param projectId
     */
    void deleteTeacher_ProjectsByProject(Integer projectId);

    /**
     * 根据是否是带队老师来获得关系
     * @param isCreator
     * @return
     */
    List<Teacher_Project> getTeacher_ProjectsByIsCreator(Boolean isCreator);

    /**
     * 根据项目id删除
     */
    void deleteTeacher_ProjectsByProjectId(Integer projectId);

    /**
     * 根据教师id返回所有的联系
     * @param teacherId
     * @return
     */
    @Query(value = "select * from `teacher_project` where `teacher_id` =?1 ",nativeQuery = true)
    List<Teacher_Project> getTeacher_ProjectsByTeacherId(Integer teacherId);
}
