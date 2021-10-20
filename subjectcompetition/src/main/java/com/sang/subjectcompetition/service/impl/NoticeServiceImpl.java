package com.sang.subjectcompetition.service.impl;

import com.sang.subjectcompetition.entity.Notice;
import com.sang.subjectcompetition.respository.NoticeRepository;
import com.sang.subjectcompetition.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;

    /**
     * 查询所有的公告
     * @return
     */
    @Override
    public List<Notice> findAllNotice() {
        List<Notice> noticeList = noticeRepository.findAll();
        return noticeList;
    }

    /**
     * 新增公告
     * @param notice
     * @return
     */
    @Override
    public String addNotice(Notice notice) {
        notice.setTime(new Date());
        Notice save = noticeRepository.save(notice);
        if (save!=null){
            return "发布成功！";
        }else {
            return "服务器错误，发布失败！";
        }
    }

    /**
     * 根据id删除公告
     * @param id
     * @return
     */
    @Override
    public String deleteNoticeById(Integer id) {
        try {
            noticeRepository.deleteById(id);
            return "删除成功！";
        }catch (Exception e){
            e.printStackTrace();
            return "服务器错误，删除失败！";
        }
    }

    /**
     * 查询所有公告，并以类型分类，都以级别从高到底排列。
     * @return
     */
    @Override
    public Map<String ,List<Notice>> findNoticeByTypeOrderByLevelDesc() {
        Map<String ,List<Notice>> noticeList=new HashMap<>();
        List<Notice> 系统公告 = noticeRepository.findNoticeByTypeOrderByLevelDesc("系统公告");
        系统公告.sort(Comparator.naturalOrder());
        noticeList.put("xitonggonggao",系统公告);
        List<Notice> 荣誉墙 = noticeRepository.findNoticeByTypeOrderByLevelDesc("荣誉墙");
        荣誉墙.sort(Comparator.naturalOrder());
        noticeList.put("rongyuqiang",荣誉墙);
        List<Notice> 竞赛信息 = noticeRepository.findNoticeByTypeOrderByLevelDesc("竞赛信息");
        竞赛信息.sort(Comparator.naturalOrder());
        noticeList.put("jingsaixinxi",竞赛信息);
        return noticeList;
    }
}
