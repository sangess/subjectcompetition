package com.sang.subjectcompetition.service;

import com.sang.subjectcompetition.entity.WorkFile;

public interface WorkFileService {
    WorkFile findWorkFileById(Integer workFileId);

    WorkFile save(WorkFile workFile);

    void delete(Integer workFileId);

}
