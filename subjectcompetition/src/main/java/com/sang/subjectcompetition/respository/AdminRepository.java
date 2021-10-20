package com.sang.subjectcompetition.respository;

import com.sang.subjectcompetition.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Admin findAdminByUsernameAndAndPassword(String username,String password);

    Admin findAdminByTargetId(String targetId);
}
