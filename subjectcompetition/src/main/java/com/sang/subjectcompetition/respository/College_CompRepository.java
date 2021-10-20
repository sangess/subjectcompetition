package com.sang.subjectcompetition.respository;

import com.sang.subjectcompetition.entity.College_Comp;
import com.sang.subjectcompetition.entity.Comp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface College_CompRepository extends JpaRepository<College_Comp,Integer>, JpaSpecificationExecutor<College_Comp> {
    College_Comp findCollege_CompByCollege_IdAndComp_Id(Integer collegeId,Integer compId);
    List<College_Comp> findCollege_CompsByCollege_Id(Integer collegeId);
    @Query(value = "SELECT * FROM `college_comp` WHERE `apply_or_join` =?1",nativeQuery = true)
    List<College_Comp> findCollege_CompsByApplyOrJoin(Integer applyOrJoin);
    @Query(value = "SELECT * FROM `college_comp` WHERE `college_id`=?1 AND`apply_or_join` =?2",nativeQuery = true)
    List<College_Comp> findCollege_CompsByApplyOrJoin(Integer collegeId,Integer applyOrJoin);
}
