package com.sang.subjectcompetition.service.impl;

import com.sang.subjectcompetition.entity.Message;
import com.sang.subjectcompetition.entity.resultInfo.MessageResult;
import com.sang.subjectcompetition.respository.MessageRepository;
import com.sang.subjectcompetition.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    /**
     * 新增信息
     * @param message
     * @return
     */
    @Override
    public Message save(Message message) {
        Message save = messageRepository.save(message);
        return save;
    }

    /**
     * 根据标识符查找未读的信息
     * @param receiver
     * @return
     */
    @Override
    public List<Message> findUnReadMessage(String receiver) {
        List<Message> messages = messageRepository.findMessagesByIfReadAndReceiver(false,  receiver);
        return messages;
    }

    /**
     * 根据标识符查找已读的信息
     * @param receiver
     * @return
     */
    @Override
    public List<Message> findReadMessage(String receiver) {
        List<Message> messages = messageRepository.findMessagesByIfReadAndReceiver(true,  receiver);
        return messages;
    }

    /**
     * 标记已读
     * @param messageId
     * @return
     */
    @Override
    public MessageResult markRead(Integer messageId) {
        MessageResult messageResult=new MessageResult();
        Message message = messageRepository.findById(messageId).orElse(null);
        message.setIfRead(true);
        Message save = this.save(message);
        if(save.getId()!=message.getId()){
            messageResult.setCode(400);
            messageResult.setMessage("已读失败！");
            return messageResult;
        }else {
            messageResult.setCode(200);
            messageResult.setMessage("已读！");
            return messageResult;
        }
    }

    /**
     * 删除信息
     * @param messageId
     * @return
     */
    @Override
    public MessageResult deleteMessage(Integer messageId) {
        MessageResult messageResult=new MessageResult();
        try {
            Message message = messageRepository.findById(messageId).orElse(null);
            messageRepository.delete(message);
            messageResult.setCode(200);
            messageResult.setMessage("删除成功！");
            return messageResult;
        }catch (Exception e){
            messageResult.setCode(400);
            messageResult.setMessage("删除失败！");
            return messageResult;
        }
    }
}
