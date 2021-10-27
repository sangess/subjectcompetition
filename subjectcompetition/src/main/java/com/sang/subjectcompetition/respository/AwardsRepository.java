package com.sang.subjectcompetition.respository;

import com.sang.subjectcompetition.entity.Awards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AwardsRepository extends JpaRepository<Awards,Integer>, JpaSpecificationExecutor<Awards> {

}
