package com.xy.kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

/**
 * @author liBai
 * @Classname SendMsgService
 * @Description TODO
 * @Date 2019-05-14 15:02
 */
public interface SendMsgService {
    /**
     * 获取队列消息
     * @return
     */
    String getMsg();

    /**
     * 获取消息使用的此方法，getMsg反应慢
     * @return
     */
    String getMsg2();

    /**
     * 修改消息一读壮体啊
     * @param fuBh
     */
    void resolve(Long fuBh);
}
