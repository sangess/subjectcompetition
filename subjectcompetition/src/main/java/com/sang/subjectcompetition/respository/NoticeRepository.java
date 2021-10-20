package com.sang.subjectcompetition.respository;

import com.sang.subjectcompetition.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice,Integer> {
    List<Notice> findNoticeByTypeOrderByLevelDesc(String type);
}
