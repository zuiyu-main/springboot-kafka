package com.xy.kafka.producer;

import com.xy.kafka.bean.XyKafkaInMsg;
import com.xy.kafka.dao.XyKafkaInMsgMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Date;

/**
 * @author liBai
 * @Classname KafKaConsumerProducer
 * @Description 生产消息
 * @Date 2019-05-14 10:04
 */
@Component
@Slf4j
public class KafKaConsumerProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;
    @Autowired
    private XyKafkaInMsgMapper inMsgMapper;

    public void sendMessage(String topic, Object object) {

        /*
         * 这里的ListenableFuture类是spring对java原生Future的扩展增强,是一个泛型接口,用于监听异步方法的回调
         * 而对于kafka send 方法返回值而言，这里的泛型所代表的实际类型就是 SendResult<K, V>,而这里K,V的泛型实际上
         * 被用于ProducerRecord<K, V> producerRecord,即生产者发送消息的key,value 类型
         */
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, object);
        future.addCallback(
                o -> log.info("消息发送成功,{}", o.toString()), throwable -> log.info("消息发送失败,{}" + throwable.getMessage())
        );

        XyKafkaInMsg build = new XyKafkaInMsg();
        build.setFwBh(System.currentTimeMillis());
        build.setGmtCreate(new Date());
        XyKafkaInMsg inMsg = inMsgMapper.selectByFuBh(build.getFwBh());
        if (inMsg == null){
            int saveMsgResult = inMsgMapper.insertSelective(build);
            log.info("消息插入结果：{}",saveMsgResult == 1 ? "成功" : "失败");
        }
    }
}
