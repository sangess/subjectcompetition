package com.sang.subjectcompetition.controller;

import com.sang.subjectcompetition.entity.Notice;
import com.sang.subjectcompetition.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    /**
     * 查询所有的公告信息
     * @return
     */
    @GetMapping("/findAllNotice")
    public List<Notice> findAllNotice(){
        List<Notice> allNotice = noticeService.findAllNotice();
        allNotice.sort(Comparator.naturalOrder());
        return allNotice;
    }

    /**
     * 发布新的公告
     * @param notice
     * @return
     */
    @PostMapping("/addNotice")
    public String addNotice(Notice notice){
        System.out.println(notice.getTime());
        return noticeService.addNotice(notice);
    }

    /**
     * 更新新的公告
     * @param notice
     * @return
     */
    @PostMapping("/updateNotice")
    public String updateNotice(Notice notice){
        return noticeService.addNotice(notice);
    }

    /**
     * 根据id删除公告
     * @param id
     * @return
     */
    @GetMapping("/deleteNoticeById")
    public String deleteNoticeById(int id){
        return noticeService.deleteNoticeById(id);
    }

    /**
     * 查询所有公告，并以类型分类，都以级别从高到底排列。
     * @return
     */
    @GetMapping("/selectAllByTypeOrderBYLevelDesc")
    public Map<String,List<Notice>> selectAllByTypeOrderBYLevelDesc(){
        return noticeService.findNoticeByTypeOrderByLevelDesc();
    }
}
