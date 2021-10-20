package com.sang.subjectcompetition.respository;

import com.sang.subjectcompetition.entity.Comp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CompRepository extends JpaRepository<Comp,Integer>, JpaSpecificationExecutor<Comp> {

    /**
     * 多条件模糊查询
     * @param compName
     * @param organizer
     * @param level
     * @param subjectType
     * @param place
     * @param compState
     * @return
     */
    @Query(value = "select * from comp where " +
            " if(?1 !='',comp_name like concat('%',?1,'%'),1=1) and" +
            " if(?2 !='',organizer like concat('%',?2,'%'),1=1) and " +
            " if(IFNULL(?3,'')!='',level=?3,1=1) and " +
            " if(?4 !='',subject_type like concat('%',?4,'%'),1=1) and " +
            " if(?5 !='',place like concat('%',?5,'%'),1=1) and " +
            " if(IFNULL(?6,'')!='',comp_state=?6,1=1)",nativeQuery = true)
    List<Comp> getCompsBySelf(String compName,String organizer,Integer level,String subjectType,String place,Integer compState);
}
