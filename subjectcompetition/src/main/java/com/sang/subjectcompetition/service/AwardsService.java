package com.sang.subjectcompetition.service;

import com.sang.subjectcompetition.entity.Awards;
import com.sang.subjectcompetition.entity.resultInfo.ProResult;

import java.util.Date;
import java.util.List;

public interface AwardsService {

    Awards getAwardsById(Integer awardsId);

    ProResult save(Awards awards);

    ProResult applyAwards(Integer awardsId);

    ProResult invokeAwards(Integer awardsId);

    ProResult deleteApply(Integer awardsId);

    List<Awards> findAll();

    List<Awards> findAll(Date beginDate,Date endDate,Integer type,Integer level);

}
