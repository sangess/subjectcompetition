package com.sang.subjectcompetition.service.impl;

import com.sang.subjectcompetition.entity.Work;
import com.sang.subjectcompetition.respository.WorkRepository;
import com.sang.subjectcompetition.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    private WorkRepository workRepository;
    @Override
    public Work findWorkById(Integer workId) {
        return workRepository.findById(workId).orElse(null);
    }

    @Override
    public Work save(Work work) {
        work.setUpdateDate(new Date());
        return workRepository.save(work);
    }

    @Override
    public void deleteWork(Integer workId) {
        workRepository.deleteById(workId);
    }
}
