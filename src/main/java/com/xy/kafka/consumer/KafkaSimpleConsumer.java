//package com.xy.kafka.consumer;
//
//import com.xy.kafka.constant.Topic;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.consumer.Consumer;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.support.Acknowledgment;
//import org.springframework.kafka.support.KafkaHeaders;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.stereotype.Component;
//
///**
// * @author liBai
// * @Classname KafkaSimpleConsumer
// * @Description 单个消费者
// * @Date 2019-05-14 10:08
// */
//@Slf4j
//@Component
//public class KafkaSimpleConsumer {
//    // 简单消费者
//    @KafkaListener(groupId = "simpleGroup", topics = Topic.SIMPLE)
//    public void consumer1_1(ConsumerRecord<String, Object> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, Consumer consumer, Acknowledgment ack) {
//        log.info("单独消费者消费消息,topic= {} ,content = {}",topic,record.value());
//        log.info("consumer content = {}",consumer);
//        ack.acknowledge();
//
//        /*
//         * 如果需要手工提交异步 consumer.commitSync();
//         * 手工同步提交 consumer.commitAsync()
//         */
//    }
//}
