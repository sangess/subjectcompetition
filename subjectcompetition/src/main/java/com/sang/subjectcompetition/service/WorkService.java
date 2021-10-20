package com.sang.subjectcompetition.service;

import com.sang.subjectcompetition.entity.Work;

public interface WorkService {
    Work findWorkById(Integer workId);
    Work save(Work work);
    void deleteWork(Integer workId);
}
