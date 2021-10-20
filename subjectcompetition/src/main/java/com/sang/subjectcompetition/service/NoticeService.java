package com.sang.subjectcompetition.service;

import com.sang.subjectcompetition.entity.Notice;

import java.util.List;
import java.util.Map;

public interface NoticeService {

    /**
     * 查看所有的公告信息
     * @return
     */
    List<Notice> findAllNotice();

    /**
     * 新增公告
     * @param notice
     * @return
     */
    String addNotice(Notice notice);

    /**
     * 根据id删除公告
     * @param id
     * @return
     */
    String deleteNoticeById(Integer id);

    /**
     * 查询所有公告，并以类型分类，都以级别从高到底排列。
     * @return
     */
    Map<String ,List<Notice>> findNoticeByTypeOrderByLevelDesc();
}
