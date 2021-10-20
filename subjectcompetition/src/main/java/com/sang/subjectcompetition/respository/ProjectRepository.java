package com.sang.subjectcompetition.respository;

import com.sang.subjectcompetition.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Integer>,JpaSpecificationExecutor<Project> {
    Project findProjectByCompIdAndCreateByTeacherId(Integer compId,Integer teacherId);

    /**
     * 根据院系id返回所有的项目
     * @param compId
     * @return
     */
    List<Project> findProjectsByCompId(Integer compId);


    @Query(value = "SELECT * FROM `project` where " +
            "if(?1 !='',project_name like concat('%',?1,'%'),1=1) and " +
            "if(IFNULL(?2,'')!='',comp_id=?2,1=1) and " +
            "if(IFNULL(?3,'')!='',apply_state=?3,1=1)",nativeQuery = true)
    List<Project> findProjectsBySearch(String projectName,Integer compId,Integer projectState);

}
