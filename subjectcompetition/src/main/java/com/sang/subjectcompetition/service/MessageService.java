package com.sang.subjectcompetition.service;

import com.sang.subjectcompetition.entity.Message;
import com.sang.subjectcompetition.entity.resultInfo.MessageResult;

import java.util.List;

public interface MessageService {

    /**
     * 保存通知消息
     * @return
     */
    Message save(Message message);

    /**
     * 根据标识符查找未读的信息
     * @param targetId
     * @return
     */
    List<Message> findUnReadMessage( String targetId);


    /**
     * 根据标识符查找已读的信息
     * @param targetId
     * @return
     */
    List<Message> findReadMessage( String targetId);

    /**
     * 标记信息已读
     * @return
     */
    MessageResult markRead(Integer messageId);

    /**
     * 删除信息
     * @param messageId
     * @return
     */
    MessageResult deleteMessage(Integer messageId);

}
