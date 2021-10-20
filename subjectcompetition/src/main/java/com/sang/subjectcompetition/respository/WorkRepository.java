package com.sang.subjectcompetition.respository;

import com.sang.subjectcompetition.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRepository extends JpaRepository<Work,Integer> {
}
