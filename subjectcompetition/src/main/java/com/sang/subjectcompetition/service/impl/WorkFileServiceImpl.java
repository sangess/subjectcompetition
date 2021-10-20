package com.sang.subjectcompetition.service.impl;

import com.sang.subjectcompetition.entity.WorkFile;
import com.sang.subjectcompetition.respository.WorkFileRepository;
import com.sang.subjectcompetition.service.WorkFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkFileServiceImpl implements WorkFileService {

    @Autowired
    private WorkFileRepository workFileRepository;

    @Override
    public WorkFile findWorkFileById(Integer workFileId) {
        return workFileRepository.findById(workFileId).orElse(null);
    }

    @Override
    public WorkFile save(WorkFile workFile) {
        return workFileRepository.save(workFile);
    }

    @Override
    public void delete(Integer workFileId) {
        workFileRepository.deleteById(workFileId);
    }
}
