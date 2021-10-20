package com.sang.subjectcompetition.respository;

import com.sang.subjectcompetition.entity.College;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegeRepository extends JpaRepository<College,Integer> {
    College findCollegeByUsernameAndPassword(String username,String password);

    College findCollegeByTargetId(String targetId);
}
