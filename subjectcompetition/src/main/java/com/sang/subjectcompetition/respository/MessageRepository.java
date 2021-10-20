package com.sang.subjectcompetition.respository;

import com.sang.subjectcompetition.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {

    /**
     * 未读的信息集合
     * @param read
     * @param receiver
     * @return
     */
    List<Message> findMessagesByIfReadAndReceiver(Boolean read,String receiver);


}
